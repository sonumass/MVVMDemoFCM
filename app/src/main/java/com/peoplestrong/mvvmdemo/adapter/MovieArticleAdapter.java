package com.peoplestrong.mvvmdemo.adapter;

import android.content.Context;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.model.Article;


import java.util.ArrayList;

public class MovieArticleAdapter extends RecyclerView.Adapter<MovieArticleAdapter.ViewHolder> {

    private Context context;
    ArrayList<Article> articleArrayList;
    private String str="ALL";
    private int[] tabIcons = {
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8,
            R.drawable.image_11,
            R.drawable.image_10,
            R.drawable.image_11,
            R.drawable.image_7};
    public MovieArticleAdapter(Context context, ArrayList<Article> articleArrayList,String str) {
        this.context = context;
        this.articleArrayList = articleArrayList;
        this.str=str;
    }

    @NonNull
    @Override
    public MovieArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movie_article,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieArticleAdapter.ViewHolder viewHolder, int i) {
        //Article article=articleArrayList.get(i);
        ////viewHolder.tvTitle.setText(article.getTitle());
        ////viewHolder.tvAuthorAndPublishedAt.setText("-"+article.getAuthor() +" | "+"Piblishetd At: "+article.getPublishedAt());
        //viewHolder.tvDescription.setText(article.getDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.imgViewCover.setImageDrawable(context.getResources().getDrawable(tabIcons[i], context.getApplicationContext().getTheme()));
            } else {
            viewHolder.imgViewCover.setImageDrawable(context.getResources().getDrawable(tabIcons[i]));

        }
        viewHolder.iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Video work in progress", Toast.LENGTH_SHORT).show();
            }
        });
     /*   Glide.with(context)
                .load(article.getUrlToImage())
                .into(viewHolder.imgViewCover);*/
        if (str.equals("ALL")){
            if(i==0|| 1==5){
                viewHolder.iv_play.setVisibility(View.VISIBLE);
                viewHolder.txt_survey.setText("Training ");
            }else if (i==1|| i==3){
                viewHolder.txt_survey.setText("Assessments");
            }else if(i==4||i==6){
                viewHolder.iv_play.setVisibility(View.VISIBLE);
            }

        }else if(str.equals("Training")){
            viewHolder.txt_survey.setText("Training ");
        }else if(str.equals("Survey")){
            viewHolder.txt_survey.setText("Survey ");
        }else if(str.equals("Assessments")){
            viewHolder.txt_survey.setText("Assessments ");
        }
    }

    @Override
    public int getItemCount() {
        //return articleArrayList.size();
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgViewCover;
        private final TextView tvTitle;
        private final TextView tvAuthorAndPublishedAt;
        private final TextView tvDescription;
        private final TextView txt_survey;
        private final ImageView iv_play;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_play=(ImageView)itemView.findViewById(R.id.iv_play);
            imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthorAndPublishedAt=(TextView) itemView.findViewById(R.id.tvAuthorAndPublishedAt);
            tvDescription=(TextView) itemView.findViewById(R.id.tvDescription);
            txt_survey=(TextView)itemView.findViewById(R.id.txt_survey);
        }
    }
}
