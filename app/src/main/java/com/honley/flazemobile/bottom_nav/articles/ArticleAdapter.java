package com.honley.flazemobile.bottom_nav.articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honley.flazemobile.R;

import java.util.Arrays;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;
    private List<Article> articleList;

    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.articleName.setText(article.getArticleName());
        holder.articleDescription.setText(article.getDescription());
        holder.articleText.setText(article.getText());

        Glide.with(context)
                .load(article.getImage())
                .into(holder.articleImage);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {


        ImageView articleImage;
        TextView articleName;
        TextView articleDescription;
        TextView articleText;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.article_image);
            articleName = itemView.findViewById(R.id.article_name);
            articleDescription = itemView.findViewById(R.id.article_description);
            articleText = itemView.findViewById(R.id.article_text);
        }
    }
}
