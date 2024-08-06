package com.honley.flazemobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.honley.flazemobile.databinding.ActivityAddArticleBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddArticleActivity extends AppCompatActivity {

    private ActivityAddArticleBinding binding;
    private String downloadImageUrl;
    private String articleName, articleDescription, articleText, saveCurrentDate, saveCurrentTime, articleRandomKey;
    private Uri imageUri;
    private StorageReference articleImageRef;
    private DatabaseReference articleRef;
    private ProgressDialog loadingBar;
    private ActivityResultLauncher<Intent> pickImageActivityResultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pickImageActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null && result.getData().getData() != null) {
                            imageUri = result.getData().getData();
                            binding.articleImage.setImageURI(imageUri);
                        }
                    }
                }
        );

        binding.articleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        binding.createArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateArticleData();
            }
        });
    }

    private void validateArticleData() {
        articleName = binding.inputArticleName.getText().toString();
        articleDescription = binding.inputArticleDescription.getText().toString();
        articleText = binding.inputArticleText.getText().toString();

        if (imageUri == null) {
            Toast.makeText(this, "Добавьте изображение для статьи", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(articleName)) {
            Toast.makeText(this, "Добавьте название для статьи", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(articleDescription)) {
            Toast.makeText(this, "Добавьте описание для статьи", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(articleText)) {
            Toast.makeText(this, "Добавьте текст для статьи", Toast.LENGTH_SHORT).show();
        } else {
            storeArticleInformation();
        }
    }

    private void storeArticleInformation() {
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Загрузка данных");
        loadingBar.setMessage("Пожалуйста, подождите...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentDate = currentDate.format(calendar.getTime());
        saveCurrentTime = currentTime.format(calendar.getTime());
        articleRandomKey = saveCurrentDate + saveCurrentTime;

        articleImageRef = FirebaseStorage.getInstance().getReference().child("Article images");
        StorageReference filePath = articleImageRef.child(imageUri.getLastPathSegment() + articleRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(imageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AddArticleActivity.this, message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddArticleActivity.this, "Изображение успешно загружено", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();
                            saveArticleInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void saveArticleInfoToDatabase() {
        HashMap<String, Object> articleMap = new HashMap<>();
        articleMap.put("date", saveCurrentDate);
        articleMap.put("time", saveCurrentTime);
        articleMap.put("article_name", articleName);
        articleMap.put("description", articleDescription);
        articleMap.put("text", articleText);
        articleMap.put("image", downloadImageUrl);

        articleRef = FirebaseDatabase.getInstance().getReference().child("Articles");
        articleRef.child(articleRandomKey).updateChildren(articleMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    loadingBar.dismiss();
                    Toast.makeText(AddArticleActivity.this, "Статья успешно создана", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(AddArticleActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                } else {
                    String message = task.getException().toString();
                    Toast.makeText(AddArticleActivity.this, message, Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        pickImageActivityResultLauncher.launch(galleryIntent);
    }
}
