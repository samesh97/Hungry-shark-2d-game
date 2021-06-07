package com.sba.thehungryshark;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    GameView gameView;

    private Handler handler = new Handler();
    private static final long Interval = 30;
    int backPressedTimes = 0;

    static int height;
    static int width;

    @Override
    public void onBackPressed()
    {
        backPressedTimes++;
        if(backPressedTimes == 2)
        {
            Intent intent = new Intent(MainActivity.this,SplashScreen.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                backPressedTimes = 0;
            }
        },2500);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        gameView = new GameView(MainActivity.this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        gameView.invalidate();
                    }
                });
            }
        },0,Interval);
    }
}
