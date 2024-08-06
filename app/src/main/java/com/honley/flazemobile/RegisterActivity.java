package com.honley.flazemobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.honley.flazemobile.databinding.ActivityRegisterBinding;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEt.getText().toString().isEmpty() ||
                        binding.usernameEt.getText().toString().isEmpty() ||
                        binding.passwordEt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEt.getText().toString(), binding.passwordEt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        try {
                                            if (user != null) {
                                                HashMap<String, String> userInfo = new HashMap<>();
                                                userInfo.put("email", binding.emailEt.getText().toString());
                                                userInfo.put("username", binding.usernameEt.getText().toString());
                                                userInfo.put("profileImage", "");
                                                userInfo.put("articles", "");
                                                FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).setValue(userInfo);

                                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            }
                                        }
                                        catch (NullPointerException e) {
                                            HashMap<String, String> userInfo = new HashMap<>();
                                            userInfo.put("email", binding.emailEt.getText().toString());
                                            userInfo.put("username", binding.usernameEt.getText().toString());
                                            userInfo.put("profileImage", "");
                                            userInfo.put("articles", "");
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).setValue(userInfo);

                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Registration Failed: " + task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
