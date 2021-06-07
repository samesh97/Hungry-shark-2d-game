package com.sba.thehungryshark;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/*
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;*/


public class GameOver extends AppCompatActivity {

    ImageView gameover;

    TextView score;

    String marks;
    Button goToMain;
    //private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
/*
        MobileAds.initialize(this, "ca-app-pub-3538783908730049~4490199801");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3538783908730049/6824719105");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());*/

        Intent intent = getIntent();

        marks = intent.getStringExtra("Marks");

        goToMain = (Button) findViewById(R.id.goToMain);

        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameOver.this,SplashScreen.class);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String currentScore = preferences.getString("Score", null);


        if(currentScore != null)
        {
            if(Integer.parseInt(marks) > Integer.parseInt(currentScore))
            {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Score",marks);
                editor.apply();
            }
        }
        else
        {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Score",marks);
            editor.apply();
        }





        gameover = (ImageView) findViewById(R.id.gameover);

        score = (TextView) findViewById(R.id.score);

        score.setText("Your Score : " + marks);

        gameover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {/*

               if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                }
                else
                {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());*/
                    Intent intent = new Intent(GameOver.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            //}
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
                Intent intent = new Intent(GameOver.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }
}
