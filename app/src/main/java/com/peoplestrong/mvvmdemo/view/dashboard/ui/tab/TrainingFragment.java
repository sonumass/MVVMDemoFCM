package com.peoplestrong.mvvmdemo.view.dashboard.ui.tab;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peoplestrong.mvvmdemo.MainActivity;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.adapter.MovieArticleAdapter;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.response.ArticalData;
import com.peoplestrong.mvvmdemo.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment {

    private TrainingViewModel mViewModel;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    ArticleViewModel articleViewModel;
    private TextView txtTarningData;
    public static TrainingFragment newInstance() {
        return new TrainingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.training_fragment, container, false);
                init(root);
        try{
            articleArrayList.clear();
        }catch (Exception e){

        }
        if (articleArrayList.size()<=0) {
            if (CommonUtill.isNetwork(getActivity())){
                getMovieArticles();
            }else {
                txtTarningData.setVisibility(View.VISIBLE);
                progress_circular_movie_article.setVisibility(View.GONE);
            }
        }else {
            progress_circular_movie_article.setVisibility(View.GONE);
        }
        return root;
    }
    public void init(View view){
        txtTarningData=(TextView)view.findViewById(R.id.txtTarningData);
        progress_circular_movie_article = (ProgressBar)view.findViewById(R.id.progress_circular_movie_article);
        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        progress_circular_movie_article.setVisibility(View.VISIBLE);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new MovieArticleAdapter(getActivity(), articleArrayList,"Training");
        my_recycler_view.setAdapter(adapter);

        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }
    private void getMovieArticles() {
        articleViewModel.getArticleResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {

                progress_circular_movie_article.setVisibility(View.GONE);
                ArticalData articles = articleResponse.getData();
                List<Article> list=new ArrayList<>();

                articleArrayList.addAll(articles.getData());
                adapter.notifyDataSetChanged();
            }else {
                progress_circular_movie_article.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        // TODO: Use the ViewModel
    }

}
