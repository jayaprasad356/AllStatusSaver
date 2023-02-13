package com.vedha.allinonedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardAdActivity extends AppCompatActivity {

    private RewardedAd rewardedAd;
    Button btnad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_ad);


        btnad = findViewById(R.id.btnad);

        btnad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedAd();
            }
        });





        // Create a new RewardedAd object
        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");

        // Load the ad
        AdRequest adRequest = new AdRequest.Builder().build();
        rewardedAd.loadAd(adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad loaded, you can now show the ad
                btnad.setEnabled(true);
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load, you can check the error code for more information
                btnad.setEnabled(false);
            }
        });

    }


    private void showRewardedAd() {
        // Check if the ad is loaded and ready to be displayed
        if (rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onUserEarnedReward(com.google.android.gms.ads.rewarded.RewardItem reward) {
                    // User earned a reward, you can grant the reward here
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed, you can now load another ad
                    loadRewardedAd();
                }
            };
            rewardedAd.show(this, adCallback);
        } else {
            // Ad not loaded, you can load another ad
            loadRewardedAd();
        }
    }


    private void loadRewardedAd() {
        // Create a new RewardedAd object
        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");

        // Load the ad
        AdRequest adRequest = new AdRequest.Builder().build();
        rewardedAd.loadAd(adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad loaded, you can now show the ad
                btnad.setEnabled(true);
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load, you can check the error code for more information
                btnad.setEnabled(false);
            }
        });
    }
}