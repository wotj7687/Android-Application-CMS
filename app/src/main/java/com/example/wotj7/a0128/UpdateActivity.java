package com.example.wotj7.a0128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.wotj7.a0128.api.ArticleService;
import com.example.wotj7.a0128.model.Article;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    EditText title,description;
    Button btn_update;
    private ArticleService articleService;
    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        title=findViewById(R.id.et_title);
        description=findViewById(R.id.et_description);
        btn_update=findViewById(R.id.btn_update);

        articleService=ServiceGenerator.createService(ArticleService.class);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        article = (Article) bundle.get("article");
        title.setText(article.getTitle());
        description.setText(article.getDescription());

    }

    public void update()
    {
        article.setTitle(title.getText().toString());
        article.setDescription(description.getText().toString());
        articleService.putArticle(article.getId(),article).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(UpdateActivity.this, "Success...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        /*Intent intent=new Intent(this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable("ok",article);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();*/
    }


    public void onUpdateClicked(View view) {
    update();

    }
}
