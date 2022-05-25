package com.trpp.englishproject.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import com.trpp.englishproject.*;


public class LoadingActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private int mProgressStatus = 0;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_loading);
        progressBar = findViewById(R.id.progressBar);

        new Thread(() -> {
            while (mProgressStatus < 100){
                mProgressStatus++;

                android.os.SystemClock.sleep(12);
                mHandler.post(() -> progressBar.setProgress(mProgressStatus));
            }
            mHandler.post(() -> {
               Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
            });
        }).start();

    }
}