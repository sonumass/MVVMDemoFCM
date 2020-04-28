package com.peoplestrong.mvvmdemo.view.dashboard.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.AllFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.AssessmentFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.SurveyFragment;
import com.peoplestrong.mvvmdemo.view.dashboard.ui.tab.TrainingFragment;

import java.util.ArrayList;
import java.util.List;

public  class ViewPagerAdapter extends FragmentStateAdapter {
    private String[] titles = new String[]{"All ", "TRAINING", "ASSESSMENT","SURVEY"};
    public ViewPagerAdapter(@NonNull Fragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllFragment();
            case 1:
                return new TrainingFragment();
            case 2:
                return new AssessmentFragment();
            case 3:
                return new SurveyFragment();
        }
        return new AllFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}


