package com.test.uitestservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import java.io.IOException;

public class UiTestService extends Service {
    final private String TAG = "UiTestService";

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        try {
            (new UiAutoTestServer()).start();
        }
        catch (IOException e) {
        }
    }
    
    @Override
    public void onDestroy() {

        super.onDestroy();
    }

}
