package com.honley.flazemobile.bottom_nav.articles;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.honley.flazemobile.AddArticleActivity;
import com.honley.flazemobile.bottom_nav.articles.Article;
import com.honley.flazemobile.bottom_nav.articles.ArticleAdapter;
import com.honley.flazemobile.databinding.FragmentArticlesBinding;

import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment {

    private FragmentArticlesBinding binding;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;
    private DatabaseReference articleRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArticlesBinding.inflate(inflater, container, false);

        // Инициализация RecyclerView и Adapter
        recyclerView = binding.articlesRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(getContext(), articleList);
        recyclerView.setAdapter(articleAdapter);

        // Инициализация Firebase Database Reference
        articleRef = FirebaseDatabase.getInstance().getReference().child("Articles");

        // Загрузка данных из Firebase
        loadArticles();

        binding.createArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddArticleActivity.class));
            }
        });

        return binding.getRoot();
    }

    private void loadArticles() {
        articleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                articleList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Article article = dataSnapshot.getValue(Article.class);
                    articleList.add(article);
                }
                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Обработка ошибок
            }
        });
    }
}
