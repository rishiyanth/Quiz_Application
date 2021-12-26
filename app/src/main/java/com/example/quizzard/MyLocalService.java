package com.example.quizzard;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Calendar;
import java.util.Date;

public class MyLocalService extends Service {
    public static Object LocalBinder;
    private final IBinder binder= new LocalBinder();
    public class LocalBinder extends Binder {
        MyLocalService getService(){
            return MyLocalService.this;
        }

        public Date getCurrentDate() {

            return null;
        }
    }
    @Override
    public IBinder onBind(Intent i){
        return binder; 
    }
    public Date getCurrentDate(){
        return Calender.getInstance().getTime();
    }

    private static class Calender {
        public static Calendar getInstance() {
            return null;
        }
    }
}
