package com.peoplestrong.mvvmdemo.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.repository.ArticleRepository;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;

import java.util.List;


public class ArticleViewModel extends AndroidViewModel {

    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;
    private LiveData<List<Article>> allmyLibrary;

    public ArticleViewModel(@NonNull Application application) {
        super(application);

        articleRepository = new ArticleRepository(application);
        this.articleResponseLiveData = articleRepository.getMovieArticles("movies", "84a7decf3110498ea372bd16dd0601e8");
   this.allmyLibrary=articleRepository.getAllmyLibrary();
    }
    public LiveData<List<Article>> getOfflineResponseLiveData() {
        return allmyLibrary;
    }
    public LiveData<ArticleResponse> getArticleResponseLiveData() {
        return articleResponseLiveData;
    }
}
