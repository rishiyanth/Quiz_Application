package com.example.quizzard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

public class PracticePage extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_page);

        progressDialog=new ProgressDialog(PracticePage.this);
        progressDialog.setTitle("Practice");
        progressDialog.setMessage("Preparing for warm-up");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);

        new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Thread.sleep(10000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}