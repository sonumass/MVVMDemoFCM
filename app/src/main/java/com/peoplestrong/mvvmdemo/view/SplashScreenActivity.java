package com.peoplestrong.mvvmdemo.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.peoplestrong.mvvmdemo.MainActivity;
import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.commonutills.SharedPrefrence;
import com.peoplestrong.mvvmdemo.view.dashboard.DashboardActivity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=2000;
    public   int RequestPermissionCode=1;
    SharedPrefrence pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pref=new SharedPrefrence(SplashScreenActivity.this);
if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
    if(CommonUtill.CheckingPermissionIsEnabledOrNot(SplashScreenActivity.this))
    {
        OpenNextActivity();
    }

    // If, If permission is not enabled then else condition will execute.
    else {

        //Calling method to enable permission.
        RequestMultiplePermission();

    }
}else {
    OpenNextActivity();
}





    }
    public void OpenNextActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pref.getBoolean("login")){
                    Intent i=new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.

                    finish();
                }else {
                    Intent i=new Intent(SplashScreenActivity.this,
                            LoginActivity.class);
                    //Intent is used to switch from one activity to another.

                    startActivity(i);
                    //invoke the SecondActivity.

                    finish();
                }

                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
    private void RequestMultiplePermission() {
String[] str={Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_CONTACTS};
        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(SplashScreenActivity.this, str
                , RequestPermissionCode);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordAudioPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean SendSMSPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContact = grantResults[3] == PackageManager.PERMISSION_GRANTED;


                    if (CameraPermission && RecordAudioPermission && SendSMSPermission && ReadContact) {
                        OpenNextActivity();
                        //Toast.makeText(SplashScreenActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(SplashScreenActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();

                    }
                }

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
