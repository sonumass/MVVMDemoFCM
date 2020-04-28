package com.peoplestrong.mvvmdemo.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peoplestrong.mvvmdemo.commonutills.APIUrls;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.response.LoginRasponse;
import com.peoplestrong.mvvmdemo.retrofit.ApiRequest;
import com.peoplestrong.mvvmdemo.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = LoginRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    public LoginRepository(){
        apiRequest = RetrofitRequest.getRetrofitInstance(APIUrls.baseUrlLogin).create(ApiRequest.class);
    }
   public LiveData<LoginRasponse> getLogin(String stuCode,String type){
       final MutableLiveData<LoginRasponse> data = new MutableLiveData<>();
       apiRequest.isUser(stuCode, type)
               .enqueue(new Callback<LoginRasponse>() {


                   @Override
                   public void onResponse(Call<LoginRasponse> call, Response<LoginRasponse> response) {
                       Log.d(TAG, "onResponse response:: " + response);
                       String str=response.body().getStatus();
                       Log.d(TAG,str);



                       if (response.body() != null) {
                           data.setValue(response.body());
                           Log.d(TAG, "articles total result:: " + response.body().getStatus());

                       }
                   }

                   @Override
                   public void onFailure(Call<LoginRasponse> call, Throwable t) {
                       data.setValue(null);
                   }
               });
       return data;
    }
}
