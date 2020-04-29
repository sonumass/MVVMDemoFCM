package com.peoplestrong.mvvmdemo.commonutills;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.core.content.ContextCompat;

import com.peoplestrong.mvvmdemo.model.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class CommonUtill {

    public static Boolean isNetwork(Context context){
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();

    }
    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean CheckingPermissionIsEnabledOrNot(Context context) {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(context, CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
        int FourPermissionResult = ContextCompat.checkSelfPermission(context, READ_CONTACTS);
        int FivePermissionResult = ContextCompat.checkSelfPermission(context, ACCESS_NETWORK_STATE);


        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED  &&
                FourPermissionResult == PackageManager.PERMISSION_GRANTED  &&
                FivePermissionResult == PackageManager.PERMISSION_GRANTED;

    }
    public static File CreateFolderInSDcard(){
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "PSLAlt");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            return folder;
            // Do something on success
        } else {
            // Do something else on failure
            return null;
        }
    }
    public static String getDownloadImagePath(String downloadUrl,String fileType){
        String filepath = null;
        try
        {
            URL url = new URL(downloadUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
            String filename="downloadedFile."+fileType;
            Log.i("Local filename:",""+filename);
            File file = new File(CreateFolderInSDcard(),filename);
            if(file.createNewFile())
            {
                file.createNewFile();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ( (bufferLength = inputStream.read(buffer)) > 0 )
            {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
            }
            fileOutput.close();
            if(downloadedSize==totalSize) filepath=file.getPath();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            filepath=null;
            e.printStackTrace();
        }
        Log.i("filepath:"," "+filepath) ;
        return filepath;
    }

}
