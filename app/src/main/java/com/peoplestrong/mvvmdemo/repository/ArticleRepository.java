package com.peoplestrong.mvvmdemo.repository;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peoplestrong.mvvmdemo.commonutills.APIUrls;
import com.peoplestrong.mvvmdemo.database.DateConverter;
import com.peoplestrong.mvvmdemo.database.db.LeaerAltDatabase;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibraryDao;
import com.peoplestrong.mvvmdemo.database.service.SaveDataIntentService;
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.response.ArticalData;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.retrofit.ApiRequest;
import com.peoplestrong.mvvmdemo.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private MyLibraryDao myLibraryDao;
    private LeaerAltDatabase leaerAltDatabase;
    private LiveData<List<Article>> allmyLibrary;

    public ArticleRepository(Context application) {
         leaerAltDatabase = LeaerAltDatabase.getInstanceDataBase(application);
        myLibraryDao=leaerAltDatabase.myLibraryDao();
        allmyLibrary=myLibraryDao.getAllLibrary();
        apiRequest = RetrofitRequest.getRetrofitInstance(APIUrls.baseUrlLogin).create(ApiRequest.class);
    }
    //method to get all notes
    public LiveData<List<Article>> getAllmyLibrary() {
        return allmyLibrary;
    }
    public LiveData<ArticleResponse> getMovieArticles(Context context,String query, String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key)
                .enqueue(new Callback<ArticleResponse>() {


                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {

                            data.setValue(response.body());
                            List<Article> list =new ArrayList<>();
                            ArticalData data=response.body().getData();
                            list=data.getData();
                            SaveDataIntentService saveDataIntentService=new SaveDataIntentService();
                            saveDataIntentService.startActionFoo(context,"AllFrament","save", (ArrayList<Article>) list);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Log.e("Error","Artical Reposi 86/"+t.getMessage());
                        data.setValue(null);
                    }
                });
        return data;
    }
    //Async task to add note
    public class AddMyLibrary extends AsyncTask<MyLibrary, Void, Void> {
        @Override
        protected Void doInBackground(MyLibrary... myLibraries) {

    myLibraryDao.insertLibrary(myLibraries[0]);


            return null;
        }
    }
}
