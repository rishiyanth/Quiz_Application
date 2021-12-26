package com.example.quizzard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quizzard.ui.login.LoginActivity;

public class HomePage extends AppCompatActivity {
    String[] genres=
    {
        "Politics", "Current Affairs", "Sports", "Indian Mythology"
    };
    ListView listView;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        listView =(ListView)findViewById(R.id.list);
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,genres);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);

    }
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"Attempt quiz");
        menu.add(0,v.getId(),0,"Practice");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        if(item.getTitle()=="Attempt quiz") {
            Toast.makeText(HomePage.this, "Quiz will start in few moments", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            openQuiz();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener(){
                        @Override
                public void onClick(DialogInterface dialog, int which){
                            dialog.cancel();
                        }
            });
            AlertDialog alert= builder.create();
            alert.setTitle("Confirmation");
            alert.show();

        }
        else if(item.getTitle()=="Practice") {
            Toast.makeText(HomePage.this, "Practice session will start in few moments", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            openPractice();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert= builder.create();
            alert.setTitle("Confirmation");
            alert.show();
        }
        return true;
    }
    public void sendNotification(View view){
        String channelId="100";
        NotificationCompat.Builder notificationBuilder= new NotificationCompat.Builder(this,channelId)
        .setSmallIcon(R.drawable.unnamed)
                .setContentTitle("Quizzard Event Notification")
        .setContentText("Online quiz is going to be on the way soon!")
                .setAutoCancel(true);
        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(channelId,"Channel",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(0,notificationBuilder.build());
        }
    }
    public void openQuiz()
    {
        Intent intent=new Intent(this, QuizPage.class);
        startActivity(intent);
    }
    public void openPractice()
    {
        Intent intent=new Intent(this, PracticePage.class);
        startActivity(intent);
    }
}