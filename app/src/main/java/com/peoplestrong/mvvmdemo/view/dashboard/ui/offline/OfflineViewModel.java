package com.peoplestrong.mvvmdemo.view.dashboard.ui.offline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfflineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OfflineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}