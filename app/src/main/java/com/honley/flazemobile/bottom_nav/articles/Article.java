package com.honley.flazemobile.bottom_nav.articles;

public class Article {
    private String article_name;
    private String description;
    private String text;
    private String image;
    private String date;
    private String time;

    public Article() {
        // Пустой конструктор нужен для Firebase
    }

    public Article(String article_name, String description, String text, String image, String date, String time) {
        this.article_name = article_name;
        this.description = description;
        this.text = text;
        this.image = image;
        this.date = date;
        this.time = time;
    }

    // Геттеры и сеттеры для всех полей
    public String getArticleName() {
        return article_name;
    }

    public void setArticleName(String article_name) {
        this.article_name = article_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}