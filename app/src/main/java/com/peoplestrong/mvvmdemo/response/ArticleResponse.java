package com.peoplestrong.mvvmdemo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.peoplestrong.mvvmdemo.model.Article;


import java.util.List;

public class ArticleResponse {
    public ArticalData getData() {
        return data;
    }

    public void setData(ArticalData data) {
        this.data = data;
    }

    private ArticalData data;
    /*@SerializedName("status")
    @Expose
    private String status;*/


   /* public String getStatus() {
        return status;
    }*/

   /* public void setStatus(String status) {
        this.status = status;
    }
*/
    /*public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    private List<Article> data;*/


}
