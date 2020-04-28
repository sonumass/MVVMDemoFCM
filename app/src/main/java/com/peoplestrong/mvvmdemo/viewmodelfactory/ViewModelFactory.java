package com.peoplestrong.mvvmdemo.viewmodelfactory;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.peoplestrong.mvvmdemo.view_model.LoginViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;
    private Object[] mParams;

    public ViewModelFactory(Application application, Object... params) {
        mApplication = application;
        mParams = params;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == LoginViewModel.class) {
            return (T) new LoginViewModel(mApplication, (String) mParams[0],(String) mParams[1]);
        } else {
            return super.create(modelClass);
        }
    }
}
