package com.peoplestrong.mvvmdemo.response;

import com.peoplestrong.mvvmdemo.model.Article;

import java.util.List;

public class ArticalData {
    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    private List<Article> data;
}
