package com.peoplestrong.mvvmdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("GET_SIGNAL_STRENGTH"))
        {
            String level = intent.getStringExtra("title");
            Log.e("level",level);
            // Show it in GraphView
        }
    }

}
