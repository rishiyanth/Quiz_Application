package com.example.quizzard;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizzard.ui.login.LoginActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    MyLocalService localService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();
                displayDate(v);
            }
        });
        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               openSignIn();
               displayDate(v);
           }
        });

    }
    public void openLogin()
    {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openSignIn()
    {
        Intent intent=new Intent(this, SigninActivity.class);
        startActivity(intent);
    }

    @Override
   protected void onStart()    {
        super.onStart();
        Intent i= new Intent(this, MyLocalService.class);
        bindService(i,connection,BIND_AUTO_CREATE);
    }

    protected void onStop(){
        super.onStop();
        if(isBound)
        {
            unbindService(connection);
            isBound=false;
        }
    }
    public void displayDate(View v){
        if(isBound)
        {
            Date date= localService.getCurrentDate();
            Toast.makeText(this, String.valueOf(date),Toast.LENGTH_LONG).show();
        }
    }

    private boolean isBound;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalService.LocalBinder binder= (MyLocalService.LocalBinder) service;
            localService = ((MyLocalService.LocalBinder)binder).getService();
            isBound=true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };


}