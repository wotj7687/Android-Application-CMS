package com.example.wotj7.a0128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wotj7.a0128.api.ArticleService;
import com.example.wotj7.a0128.model.Article;
import com.example.wotj7.a0128.response.ArticlesResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity implements ArticleRecyclerviewClickListener {

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


        //String title, String description/*, int author*/, int categoryId, String status, String image

       Intent intent = new Intent(this, ArticleDetailActivity2.class);

       intent.putExtra("title",article.getTitle());
       intent.putExtra("description",article.getDescription());
       intent.putExtra("categoryId",article.getCategoryId());
       intent.putExtra("status",article.getStatus());
       intent.putExtra("image",article.getImage());

        startActivity(intent);


    }

    @Override
    public void onBtnDeleteClicked(Article article){
        Toast.makeText(this, "You are not Admin [ Please login ]", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
