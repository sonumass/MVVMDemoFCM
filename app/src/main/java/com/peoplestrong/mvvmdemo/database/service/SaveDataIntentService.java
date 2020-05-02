package com.peoplestrong.mvvmdemo.database.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.peoplestrong.mvvmdemo.database.db.LeaerAltDatabase;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibrary;
import com.peoplestrong.mvvmdemo.database.mylibrary.MyLibraryDao;
import com.peoplestrong.mvvmdemo.model.Article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SaveDataIntentService extends IntentService {
    private Context context;
    private MyLibraryDao myLibraryDao;
    private LeaerAltDatabase leaerAltDatabase;
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.peoplestrong.mvvmdemo.database.service.action.FOO";
    private static final String ACTION_BAZ = "com.peoplestrong.mvvmdemo.database.service.action.BAZ";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.peoplestrong.mvvmdemo.database.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.peoplestrong.mvvmdemo.database.service.extra.PARAM2";
    public SaveDataIntentService() {
        super("SaveDataIntentService");
    }
    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2, final ArrayList<Article> list) {
        Intent intent = new Intent(context, SaveDataIntentService.class);
       /* for (int i = 0; i <list.size() ; i++) {
            Log.e("list",list.get(i).getSubName());
        }*/
        /*Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)list);
        intent.putExtra("BUNDLE",args);*/
        Bundle bundle = new Bundle();

        intent.putExtras(bundle);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        intent.putExtra("ArtivalListExtra", list);
       context=context;
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SaveDataIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }
void SaveMyLibrary(ArrayList<Article> articles){
    leaerAltDatabase = LeaerAltDatabase.getInstanceDataBase(context);
    myLibraryDao=leaerAltDatabase.myLibraryDao();
    List<MyLibrary> list=new ArrayList<>();
    list=myLibraryDao.getAllLibraryWithoutLLive();
    if (list.size()>0){
        for (int i = 0; i <articles.size() ; i++) {
            int count=myLibraryDao.findMyLibraryByID(list.get(i).getId());
            if (count==0){
                Date createdAt = Calendar.getInstance().getTime();
                MyLibrary myLibrary=new MyLibrary(articles.get(i).getSubName(),articles.get(i).getWork(),articles.get(i).getRemark(),"",createdAt);
                myLibraryDao.insertLibrary(myLibrary);
            }
        }
    }else {
        for (int i = 0; i <articles.size() ; i++) {
            Date createdAt = Calendar.getInstance().getTime();
            MyLibrary myLibrary=new MyLibrary(articles.get(i).getSubName(),articles.get(i).getWork(),articles.get(i).getRemark(),"",createdAt);
            myLibraryDao.insertLibrary(myLibrary);
        }

    }


}
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);

                ArrayList<Article> challenge = (ArrayList<Article>) intent.getSerializableExtra("ArtivalListExtra");
                handleActionFoo(param1, param2,challenge);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2,ArrayList<Article> list) {
        // TODO: Handle action Foo
        if (param1.equals("AllFrament")){
            SaveMyLibrary(list);
        }
       // throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
