package com.huucong.demohandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private Handler mHandler;
    private static final int MSG_COUNT_DOWN = 1;
    private static final int MSG_COUNT_DOWN_DONE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        listenerHandler();
        countDown();
    }

    private void countDown(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        mHandler.sendEmptyMessage(MSG_COUNT_DOWN);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(MSG_COUNT_DOWN_DONE);
            }
        }).start();
    }

    private void listenerHandler() {
        mHandler = new Handler(getMainLooper()) {
            @NonNull
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MSG_COUNT_DOWN:
                        break;
                    case MSG_COUNT_DOWN_DONE:
                        openMainActivity();
                        break;
                    default:
                        break;
                }
            }
        };
    }
    private void openMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
