package com.peoplestrong.mvvmdemo;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.peoplestrong.mvvmdemo.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//background serveices contet read show phone no and emai id and name. store in DB
//show only mobile no not land line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}
