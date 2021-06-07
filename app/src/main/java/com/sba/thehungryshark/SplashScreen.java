package com.sba.thehungryshark;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;*/


public class SplashScreen extends AppCompatActivity {

    ImageView play;
    Button highScore;
    String score;
    int backPressedTimes = 0;
    //private InterstitialAd mInterstitialAd;

    @Override
    public void onBackPressed()
    {
        backPressedTimes++;
        if(backPressedTimes == 2)
        {
            super.onBackPressed();
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
        setContentView(R.layout.activity_splash_screen);


        /*MobileAds.initialize(this, "ca-app-pub-3538783908730049~4490199801");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3538783908730049/6465626453");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        score = preferences.getString("Score", null);


        play = (ImageView) findViewById(R.id.play);

        highScore = (Button) findViewById(R.id.highScore);

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                if(score != null)
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SplashScreen.this);
                    dialog.setTitle("High Score");
                    dialog.setMessage("Score : "+score);
                    dialog.setIcon(R.drawable.highscore);
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
                else
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SplashScreen.this);
                    dialog.setTitle("High Score");
                    dialog.setMessage( "Score : " + "0");
                    dialog.setIcon(R.drawable.highscore);
                    dialog.setCancelable(false);

                    dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }



            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());

                }
                else
                {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());*/
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                //}


            }
        });
/*
       mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed()
            {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

    }
}
