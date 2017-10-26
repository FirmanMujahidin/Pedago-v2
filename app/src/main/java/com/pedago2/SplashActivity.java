package com.pedago2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pedago2.HomeScreen.HomeScreenActivity;
import com.pedago2.Database.DatabaseHelper;
import com.pedago2.First.InfiniteViewPagerActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (new DatabaseHelper(SplashActivity.this).getDataLoginCount() > 0)
                {
                    startActivity(new Intent(SplashActivity.this, HomeScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                }
                else
                {
                    startActivity(new Intent(SplashActivity.this, InfiniteViewPagerActivity.class));
                    finish();
                }
            }
        }, 500);
    }
}
