package com.example.wotj7.a0128.request;

import com.google.gson.annotations.SerializedName;

public class InsertArticleRequest {

    @SerializedName("TITLE")
    private String title;

    @SerializedName("DESCRIPTION")
    private String description;

    /*@SerializedName("AUTHOR")
    private int author;*/

    @SerializedName("CATEGORY_ID")
    private int categoryId;

    @SerializedName("STATUS")
    private String status;

    @SerializedName("IMAGE")
    private String image;

    @SerializedName("CREATED_DATE")
    private String date;

    public InsertArticleRequest(String title, String description/*, int author*/, int categoryId, String status, String image, String date) {
        this.title = title;
        this.description = description;
        //this.author = author;
        this.categoryId = categoryId;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }*/

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}