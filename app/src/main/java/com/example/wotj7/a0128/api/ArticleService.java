package com.example.wotj7.a0128.api;


import com.example.wotj7.a0128.model.Article;
import com.example.wotj7.a0128.request.InsertArticleRequest;
import com.example.wotj7.a0128.response.ArticlesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArticleService {

    @GET("v1/api/articles")
    Call<ArticlesResponse> getAllArticles();

    @DELETE("v1/api/articles/{id}")
    Call<ResponseBody> deleteArticle(@Path("id") int id);

    @POST("v1/api/articles")
    Call<ResponseBody> insertArticle(@Body InsertArticleRequest request);

    @PUT("v1/api/articles/{id}")
    Call<ResponseBody> putArticle(@Path("id") int id, @Body Article article);
}
