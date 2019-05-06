package com.example.wotj7.a0128;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wotj7.a0128.api.ArticleService;
import com.example.wotj7.a0128.model.Article;
import com.example.wotj7.a0128.request.InsertArticleRequest;
import com.example.wotj7.a0128.response.ArticlesResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ArticleRecyclerviewClickListener {

    private static final String TAG = "ooooo";
    ArticleService articleService;
    private RecyclerView rvArticle;
    private ArticleAdapter articleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvArticle = findViewById(R.id.rv_article);
        articleService = ServiceGenerator.createService(ArticleService.class);
        setupRecyclerview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllArticles();

    }

    private void setupRecyclerview() {
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        articleAdapter = new ArticleAdapter();
        rvArticle.setAdapter(articleAdapter);
    }

    private void getAllArticles() {
        articleService
                .getAllArticles()
                .enqueue(new Callback<ArticlesResponse>() {
                    @Override
                    public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                        ArticlesResponse articlesResponse = response.body();
                        List<Article> articleList = articlesResponse.getArticleList();
                        articleAdapter.addItems(articleList);
                    }

                    @Override
                    public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onItemClicked(Article article) {
        viewArticle(article);
    }

    private void viewArticle(Article article) {

        Article article1 = article;
        //String title, String description/*, int author*/, int categoryId, String status, String image

       Intent intent = new Intent(this, ArticleDetailActivity.class);
       intent.putExtra("article",article1);
       intent.putExtra("title",article.getTitle());
       intent.putExtra("description",article.getDescription());
       intent.putExtra("categoryId",article.getCategoryId());
       intent.putExtra("status",article.getStatus());
       intent.putExtra("image",article.getImage());

        startActivity(intent);

        /*ImageView d_image;
        TextView d_title, d_description, d_author, d_category, d_status;

        d_image = findViewById(R.id.default_image);
        d_title = findViewById(R.id.default_title);
        d_description = findViewById(R.id.default_description);
        d_author = findViewById(R.id.default_author);
        d_category = findViewById(R.id.default_category_id);
        d_status = findViewById(R.id.default_status);


        Glide.with(this).load(image).into(d_image);

        d_title.setText(title);
        d_description.setText(description);
        //d_author.setText(author);
        d_category.setText(categoryId);
        d_status.setText(status);*/


    }

    @Override
    public void onBtnDeleteClicked(Article article){
        deleteArticle(article.getId());
    }

    private void deleteArticle(final int id) {
        articleService
                .deleteArticle(id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            articleAdapter.remove(id);
                            Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.insert) {
            Intent i = new Intent(this, InsertArticleActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.logout){
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
