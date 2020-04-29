package com.peoplestrong.mvvmdemo.view.dashboard.ui.tab;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.peoplestrong.mvvmdemo.MainActivity;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.adapter.MovieArticleAdapter;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.response.ArticalData;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    private AllViewModel mViewModel;


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular_movie_article;
    private LinearLayoutManager layoutManager;
    private MovieArticleAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    ArticleViewModel articleViewModel;

    public static AllFragment newInstance() {
        return new AllFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(AllViewModel.class);
        View root=inflater.inflate(R.layout.all_fragment, container, false);
        /*final TextView textView = root.findViewById(R.id.txtHome);
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        init(root);
        try{
            articleArrayList.clear();
        }catch (Exception e){
            Log.d("Error","All Fragment 64");
        }
        if (articleArrayList.size()<=0) {

            if (CommonUtill.isNetwork(getActivity())){
                getMovieArticles();
            }else {
                offlineGetMovieArticles();
            }

        }else {
            progress_circular_movie_article.setVisibility(View.GONE);
        }

        return root;
    }
public void init(View view){

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
    adapter = new MovieArticleAdapter(getActivity(), articleArrayList,"ALL");
    my_recycler_view.setAdapter(adapter);

    // View Model
    articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
}
    private void offlineGetMovieArticles() {
        articleViewModel.getOfflineResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {

                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse;
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged();
            }else {
                progress_circular_movie_article.setVisibility(View.GONE);
            }
        });
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
        mViewModel = ViewModelProviders.of(this).get(AllViewModel.class);
        // TODO: Use the ViewModel
    }

}
