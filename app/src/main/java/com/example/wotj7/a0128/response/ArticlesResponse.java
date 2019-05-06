package com.example.wotj7.a0128.response;

import com.example.wotj7.a0128.model.Article;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponse {

    @SerializedName("CODE")
    private String code;

    @SerializedName("MESSAGE")
    private String message;

    @SerializedName("DATA")
    private List<Article> articleList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
