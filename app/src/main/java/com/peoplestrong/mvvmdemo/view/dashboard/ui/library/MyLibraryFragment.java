package com.peoplestrong.mvvmdemo.view.dashboard.ui.library;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayoutMediator;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.view.dashboard.adapter.ViewPagerAdapter;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.AllFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.AssessmentFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.SurveyFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.TrainingFragment;

import java.util.ArrayList;


public class MyLibraryFragment extends Fragment implements View.OnClickListener{

    private LibraryViewModel galleryViewModel;
    private LinearLayout lay_lib,lay_event,lay_scan,lay_catlog;
    private ImageView iv_catlog,iv_scan,iv_event,iv_libraray;
    private TextView textCatlog,textBarCode,textEvent,txtLibrary;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private int[] tabIcons = {
            R.drawable.ic_bottom_catlog_blue,
            R.drawable.ic_bottom_library_blue,
            R.drawable.ic_bottom_event_blue
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LibraryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_labrary, container, false);
       // final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        init(root);
        return root;
    }
    public void init(View view){
        viewPager = (ViewPager2) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablay);
        setupViewPager(viewPager,tabLayout);


        /*tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();*/
        /*tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("TRAINING"));
        tabLayout.addTab(tabLayout.newTab().setText("ASSESSMENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("SURVEYS"));*/
        tabLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                int tabLayoutWidth = tabLayout.getWidth();

                DisplayMetrics metrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int deviceWidth = metrics.widthPixels;

                if (tabLayoutWidth < deviceWidth)
                {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                } else
                {
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
            }
        });
        lay_lib=(LinearLayout)view.findViewById(R.id.lay_lib);
        lay_lib.setOnClickListener(this);
        lay_event=(LinearLayout)view.findViewById(R.id.lay_event);
        lay_event.setOnClickListener(this);
        lay_scan=(LinearLayout)view.findViewById(R.id.lay_scan);
        lay_scan.setOnClickListener(this);
        lay_catlog=(LinearLayout)view.findViewById(R.id.lay_catlog);
        lay_catlog.setOnClickListener(this);
        iv_catlog=(ImageView)view.findViewById(R.id.iv_catlog);
        textCatlog=(TextView)view.findViewById(R.id.textCatlog);

        iv_scan=(ImageView)view.findViewById(R.id.iv_scan);
        textBarCode=(TextView)view.findViewById(R.id.textBarCode);
        iv_event=(ImageView)view.findViewById(R.id.iv_event);
        textEvent=(TextView)view.findViewById(R.id.textEvent);
        iv_libraray=(ImageView)view.findViewById(R.id.iv_libraray);
        txtLibrary=(TextView)view.findViewById(R.id.txtLibrary);
    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_tab, null);
        tabOne.setText("All");
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_bottom_catlog_blue, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
        tabTwo.setText("TRAINNING");
        //tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_bottom_library_blue, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
        tabThree.setText("ASSESSMENTS");
        //tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_bottom_event_blue, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.view_tab, null);
        tabFour.setText("SURVEYS");
        //tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_bottom_event_blue, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);

    }

    private void setupViewPager(ViewPager2 viewPager, TabLayout tableLayout) {
         String[] titles = new String[]{"All ", "TRAINING", "ASSESSMENT","SURVEY"};
        viewPager.setAdapter(new ViewPagerAdapter(this));
        new TabLayoutMediator(tabLayout,viewPager,
                (tab, position) -> tab.setText(titles[position])).attach();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lay_lib:
                setbuttom("Library");
                break;
            case R.id.lay_event:
                setbuttom("Event");
                break;
            case R.id.lay_scan:
                setbuttom("Scan");
                break;
            case R.id.lay_catlog:
                setbuttom("Catlog");
                break;
        }
    }
    public void setbuttom(String str){
        switch (str){
            case "Library":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_blue, getActivity().getApplicationContext().getTheme()));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey, getActivity().getApplicationContext().getTheme()));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey, getActivity().getApplicationContext().getTheme()));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey, getActivity().getApplicationContext().getTheme()));

                } else {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_blue));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey));
                }
               // iv_libraray.setBackgroundResource(R.drawable.ic_bottom_library_blue);
                txtLibrary.setTextColor(Color.parseColor("#344ff4"));
                textEvent.setTextColor(Color.parseColor("#8f8f8f"));
                textBarCode.setTextColor(Color.parseColor("#8f8f8f"));
                textCatlog.setTextColor(Color.parseColor("#8f8f8f"));
                break;
            case "Event":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey, getActivity().getApplicationContext().getTheme()));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_blue, getActivity().getApplicationContext().getTheme()));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey, getActivity().getApplicationContext().getTheme()));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey, getActivity().getApplicationContext().getTheme()));

                } else {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_blue));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey));
                }
                txtLibrary.setTextColor(Color.parseColor("#8f8f8f"));
                textEvent.setTextColor(Color.parseColor("#344ff4"));
                textBarCode.setTextColor(Color.parseColor("#8f8f8f"));
                textCatlog.setTextColor(Color.parseColor("#8f8f8f"));
                break;
            case "Scan":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey, getActivity().getApplicationContext().getTheme()));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey, getActivity().getApplicationContext().getTheme()));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_blue, getActivity().getApplicationContext().getTheme()));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey, getActivity().getApplicationContext().getTheme()));

                } else {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_blue));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_grey));
                }
                txtLibrary.setTextColor(Color.parseColor("#8f8f8f"));
                textEvent.setTextColor(Color.parseColor("#8f8f8f"));
                textBarCode.setTextColor(Color.parseColor("#344ff4"));
                textCatlog.setTextColor(Color.parseColor("#8f8f8f"));
                break;
            case "Catlog":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey, getActivity().getApplicationContext().getTheme()));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey, getActivity().getApplicationContext().getTheme()));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey, getActivity().getApplicationContext().getTheme()));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_blue, getActivity().getApplicationContext().getTheme()));

                } else {
                    iv_libraray.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_library_grey));
                    iv_event.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_event_grey));
                    iv_scan.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_scanner_grey));
                    iv_catlog.setImageDrawable(getResources().getDrawable(R.drawable.ic_bottom_catlog_blue));
                }
                txtLibrary.setTextColor(Color.parseColor("#8f8f8f"));
                textEvent.setTextColor(Color.parseColor("#8f8f8f"));
                textBarCode.setTextColor(Color.parseColor("#8f8f8f"));
                textCatlog.setTextColor(Color.parseColor("#344ff4"));
                break;
        }

    }


}

