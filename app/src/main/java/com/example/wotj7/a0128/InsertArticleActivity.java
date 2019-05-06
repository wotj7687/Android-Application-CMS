package com.example.wotj7.a0128;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wotj7.a0128.api.ArticleService;
import com.example.wotj7.a0128.request.InsertArticleRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertArticleActivity extends AppCompatActivity {

    private ArticleService articleService;
    Button btnInsert;
    EditText title, description, author, categoryId, status, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_article);

        btnInsert = findViewById(R.id.btn_insert);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        author = findViewById(R.id.author);
        categoryId = findViewById(R.id.category_id);
        status = findViewById(R.id.status);
        image = findViewById(R.id.image);
        final String date;
        date = getCurrentTime();

        articleService = ServiceGenerator.createService(ArticleService.class);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertArticle(title.getText().toString(), description.getText().toString()/*, author.getInputType()*/, categoryId.getInputType(), status.getText().toString(), image.getText().toString(), date);
                finish();
            }
        });
    }

    private void insertArticle(String title, String description/*, int author*/, int categoryId, String status, String image, String date) {

        final InsertArticleRequest request = new InsertArticleRequest(
                title,
                description,
                //author,
                categoryId,
                status,
                image, date
        );

        articleService
                .insertArticle(request)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(InsertArticleActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}

