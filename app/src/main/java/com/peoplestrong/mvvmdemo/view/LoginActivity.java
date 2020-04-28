package com.peoplestrong.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.peoplestrong.mvvmdemo.R;
import com.peoplestrong.mvvmdemo.commonutills.CommonUtill;
import com.peoplestrong.mvvmdemo.commonutills.SharedPrefrence;
import com.peoplestrong.mvvmdemo.view.dashboard.DashboardActivity;
import com.peoplestrong.mvvmdemo.view_model.ArticleViewModel;
import com.peoplestrong.mvvmdemo.view_model.LoginViewModel;
import com.peoplestrong.mvvmdemo.viewmodelfactory.ViewModelFactory;
//Sonu Saini
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private SharedPrefrence pref;
private EditText edtEmail,edtPassword;
private Button btnLogin;
private ProgressBar progress_login;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref=new SharedPrefrence(LoginActivity.this);
        init();
    }
    public void init(){
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        progress_login=(ProgressBar)findViewById(R.id.progress_login);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                if(edtEmail.getText().toString().length()<=0){
                    edtEmail.setError("Please enter email ID");
                }else if(edtPassword.getText().toString().length()<=0){
                    edtPassword.setError("Please enter password");
                }else if(!CommonUtill.isValidEmail(edtEmail.getText().toString())){
                    edtEmail.setError("Please enter valid mail ID");
                } else{
                    if(CommonUtill.isNetwork(LoginActivity.this)){
                        progress_login.setVisibility(View.VISIBLE);
                         loginViewModel = ViewModelProviders.of(this, new ViewModelFactory(this.getApplication(), edtPassword.getText().toString(),"Student")).get(LoginViewModel.class);
                        loginViewModel.getLoginLiveData().observe(this,loginRasponse -> {
                            progress_login.setVisibility(View.GONE);
                            if(loginRasponse!=null){
                                if(loginRasponse.getStatus().equals("200")){
                                    pref.setBoolean("login",true);
                                    Intent intent=new Intent(LoginActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),"InValid cridential ",Toast.LENGTH_LONG).show();
                                }
                            }else {
                                Toast.makeText(getApplicationContext(),"Somthing went wrong",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                break;
        }
    }
}
