package com.example.wotj7.a0128;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.wotj7.a0128.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleVH> {

    private List<Article> articleList = new ArrayList<>();
    private ArticleRecyclerviewClickListener listener;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        listener = (ArticleRecyclerviewClickListener) recyclerView.getContext();
    }

    @NonNull
    @Override
    public ArticleVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_article, viewGroup, false);

        return new ArticleVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleVH articleVH, int position) {
        articleVH.setData(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void addItems(List<Article> articles) {
        articleList.clear();
        articleList.addAll(articles);
        notifyDataSetChanged();
    }

    public void remove(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            if (articleList.get(i).getId() == id) {
                articleList.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }

    }

    public class ArticleVH extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDescription;
        ImageView image, btnDelete;

        public ArticleVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.list_title);
            tvDescription = itemView.findViewById(R.id.list_description);
            image = itemView.findViewById(R.id.list_image);
            btnDelete = itemView.findViewById(R.id.btn_Delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(articleList.get(getAdapterPosition()));
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBtnDeleteClicked(articleList.get(getAdapterPosition()));
                }
            });
        }

        public void setData(Article article) {
            tvTitle.setText(article.getTitle());
           tvDescription.setText(article.getDescription());
           if(article.getImage() != null)
               GlideApp.with(itemView).load(article.getImage()).diskCacheStrategy(DiskCacheStrategy.DATA).error(R.drawable.noimage).fallback(R.drawable.noimage).transition(DrawableTransitionOptions.withCrossFade()).into(image);
        }

    }

}
