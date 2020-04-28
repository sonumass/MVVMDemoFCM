package com.peoplestrong.mvvmdemo.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.peoplestrong.mvvmdemo.repository.ArticleRepository;
import com.peoplestrong.mvvmdemo.repository.LoginRepository;
import com.peoplestrong.mvvmdemo.response.LoginRasponse;


public class LoginViewModel extends AndroidViewModel {
    public  String str[];
    private LoginRepository articleRepository;

    private LiveData<LoginRasponse> articleResponseLiveData;

    public LoginViewModel(@NonNull Application application,String... str) {
        super(application);
        articleRepository = new LoginRepository();
        this.articleResponseLiveData = articleRepository.getLogin(str[0], str[1]);
    }
    public LiveData<LoginRasponse> getLoginLiveData() {
        return articleResponseLiveData;
    }
}
