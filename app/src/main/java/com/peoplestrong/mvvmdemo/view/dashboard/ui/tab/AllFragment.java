package com.peoplestrong.mvvmdemo.view.dashboard.ui.tab;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

import com.peoplestrong.mvvmdemo.MainActivity;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.adapter.MovieArticleAdapter;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.model.Article;
import com.peoplestrong.mvvmdemo.receiver.NotificationReceiver;
import com.peoplestrong.mvvmdemo.response.ArticalData;
import com.peoplestrong.mvvmdemo.response.ArticleResponse;
import com.peoplestrong.mvvmdemo.view_model.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.peoplestrong.mvvmdemo.commonutills.CommonUtill.showDialog;

public class AllFragment extends Fragment {

    private AllViewModel mViewModel;
    AllNotificationReceiver notificationReceiver;

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
    notificationReceiver = new AllNotificationReceiver();
    getActivity().registerReceiver(notificationReceiver, new IntentFilter("GET_SIGNAL_STRENGTH"));
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
    public void onResume() {
        super.onResume();
        //getActivity().registerReceiver(notificationReceiver, new IntentFilter("GET_SIGNAL_STRENGTH"));

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(notificationReceiver);
    }
public void getshow(String str){
        Log.e("str::::",str);
//    Toast.makeText(getActivity(),str+"",Toast.LENGTH_LONG).show();
       /* if (getActivity()!=null){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
            builder1.setMessage(str);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }*/

}
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllViewModel.class);
        // TODO: Use the ViewModel

    }
    class AllNotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("GET_SIGNAL_STRENGTH"))
            {
                String level = intent.getStringExtra("title");
                Log.e("All notif",level+"");
                Toast.makeText(getActivity(),level+"",Toast.LENGTH_LONG).show();
               // showDialog(getActivity(),level);
            }
        }
    }


}
