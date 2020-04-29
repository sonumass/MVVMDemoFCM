package com.peoplestrong.mvvmdemo.view.dashboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.commonutills.SharedPrefrence;
import com.peoplestrong.mvvmdemo.view.LoginActivity;
import com.peoplestrong.mvvmdemo.view.dashboard.adapter.DrawerItemCustomAdapter;
import com.peoplestrong.mvvmdemo.view.dashboard.model.DataModel;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.aboutus.AboutUsFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.library.MyLibraryFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.offline.OfflineFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.language.LanguageFragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    ProgressDialog p;
    private AppBarConfiguration mAppBarConfiguration;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    private SharedPrefrence pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pref=new SharedPrefrence(DashboardActivity.this);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= new String[6];
        mNavigationDrawerItemTitles[0]="My Library";
        mNavigationDrawerItemTitles[1]="My Library";
        mNavigationDrawerItemTitles[2]="Offline";
        mNavigationDrawerItemTitles[3]="Language";
        mNavigationDrawerItemTitles[4]="About us";
        mNavigationDrawerItemTitles[5]="LogOut";
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        setupToolbar();

        DataModel[] drawerItem = new DataModel[6];

        drawerItem[0] = new DataModel(R.drawable.ic_mylibrary, "My Library");
        drawerItem[1] = new DataModel(R.drawable.ic_mylibrary, "My Library");
        drawerItem[2] = new DataModel(R.drawable.ic_offline, "Offline");
        drawerItem[3] = new DataModel(R.drawable.ic_language, "Language");
        drawerItem[4] =new DataModel(R.drawable.ic_about, "About us");
        drawerItem[5] = new DataModel(R.drawable.ic_logout, "LogOut");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        selectItem(0);
       // new AsyncTaskDownload().execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position!=0){
                selectItem(position);
            }

        }

    }
    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new MyLibraryFragment();
                break;
            case 1:
                fragment = new MyLibraryFragment();

                break;
            case 2:
                fragment = new OfflineFragment();

                break;
            case 3:
                fragment = new LanguageFragment();

                break;
            case 4:
                fragment = new AboutUsFragment();

                break;
            case 5:
                pref.setBoolean("login",false);
                Toast.makeText(getApplicationContext(),"Logout sucessfully",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }*/
        if (item.getItemId() == R.id.search) {
            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.notification) {
            Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.filter) {
            showFilter();
            //Toast.makeText(getApplicationContext(), "Filter", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
    public void showFilter(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alert_list, viewGroup, false);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        Button btnCancel=(Button)dialogView.findViewById(R.id.btnCancel);
        Button btnalertSave=(Button)dialogView.findViewById(R.id.btnalertSave);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnalertSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Updated your list as per filter",Toast.LENGTH_LONG).show();
            }
        });
    }
    private class AsyncTaskDownload extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             p = new ProgressDialog(DashboardActivity.this);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            p.dismiss();
            try {
                String str=null;
                str= CommonUtill.getDownloadImagePath(strings[0],"png");
                return str;
            }catch (Exception e){
                return "null";
            }


            // return str;
        }
        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap!=null){
                Toast.makeText(getApplicationContext(),bitmap,Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),bitmap,Toast.LENGTH_LONG).show();
            }
        }
    }
}
