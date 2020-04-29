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
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.retrofit.ApiRequest;
import com.peoplestrong.mvvmdemo.retrofit.RetrofitRequest;

import java.util.ArrayList;
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

    //method to add note
    public void addMyLibrary(MyLibrary myLibrary) {
        new AddMyLibrary().execute(myLibrary);
    }
    public LiveData<ArticleResponse> getMovieArticles(String query, String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        apiRequest.getMovieArticles(query, key)
                .enqueue(new Callback<ArticleResponse>() {


                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());
                            /*List<Article> list =new ArrayList<>();
                            list=response.body().getData();
                            for (int i = 0; i <list.size() ; i++) {
                                Article article=new Article();
                                long date=Long.valueOf(list.get(i).getCreatedAt());
                                DateConverter dateConverter=new DateConverter();
                                Date date1=dateConverter.toDate(date);
                                String imagePath="";
                                MyLibrary myLibrary=new MyLibrary(list.get(i).getSubName(),list.get(i).getWork(),list.get(i).getRemark(),imagePath,date1);
                                addMyLibrary(myLibrary);
                            }*/

                            /*Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());*/
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
