package com.peoplestrong.mvvmdemo.view.dashboard.ui.tab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;
    public AllViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is All fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
