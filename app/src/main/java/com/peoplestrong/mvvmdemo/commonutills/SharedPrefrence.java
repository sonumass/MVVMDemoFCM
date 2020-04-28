package com.peoplestrong.mvvmdemo.commonutills;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefrence {
    Context context;

    public SharedPrefrence(Context context){
        this.context=context;

    }
    public void setString(String key,String vaule){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString(key,vaule);
        myEdit.commit();
    }
    public String getString(String key){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        String value=sharedPreferences.getString(key,"");
        return value;
    }
    public void setBoolean(String  key,Boolean value){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putBoolean(key,value);
        myEdit.commit();
    }
    public Boolean getBoolean(String key){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        Boolean value=sharedPreferences.getBoolean(key,false);
        return value;
    }

    public void setInt(String  key,int value){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt(key,value);
        myEdit.commit();
    }
    public Integer getInt(String key){
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("com.peoplestrong.mvvmdemo",
                MODE_PRIVATE);
        int value=sharedPreferences.getInt(key,0);
        return value;
    }

}
