package com.example.wotj7.a0128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.wotj7.a0128.model.Article;

public class ArticleDetailActivity2 extends AppCompatActivity {


    ImageView d_image;
    TextView d_title, d_description, d_status;

    Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail2);

        d_image = findViewById(R.id.default_image);
        d_title = findViewById(R.id.default_title);
        d_description = findViewById(R.id.default_description);
        d_status = findViewById(R.id.default_status);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        article= (Article) bundle.get("article");
        if(intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String status = intent.getStringExtra("status");
            String image = intent.getStringExtra("image");

            if (image != null)
                GlideApp.with(this).load(image).diskCacheStrategy(DiskCacheStrategy.DATA).error(R.drawable.noimage).fallback(R.drawable.noimage).transition(DrawableTransitionOptions.withCrossFade()).into(d_image);

            d_title.setText(title);
            d_description.setText(description);
            d_status.setText(status);
        }
    }

}
