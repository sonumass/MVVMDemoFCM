package com.peoplestrong.mvvmdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;


public class MainActivity extends AppCompatActivity {
TextView txtFcmMessage;
private String message="";
    WifiLevelReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new WifiLevelReceiver();
        //registerReceiver(receiver, new IntentFilter("GET_SIGNAL_STRENGTH"));
        txtFcmMessage=(TextView)findViewById(R.id.txtFcmMessage);
        String titile=this.getIntent().getStringExtra("title");
        //Log.e("title",titile);
        Intent intent=this.getIntent();
        txtFcmMessage.setText(titile);
        Toast.makeText(getApplicationContext(),intent.getStringExtra("title"),Toast.LENGTH_LONG).show();

    }
    class WifiLevelReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("GET_SIGNAL_STRENGTH"))
            {
                String level = intent.getStringExtra("title");
            }
        }
    }
    @Override
    public void onStop()
    {
        super.onStop();
       // unregisterReceiver(receiver);           //<-- Unregister to avoid memoryleak
    }
}
