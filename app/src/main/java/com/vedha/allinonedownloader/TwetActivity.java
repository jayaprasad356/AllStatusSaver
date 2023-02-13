package com.vedha.allinonedownloader;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.vedha.allinonedownloader.model.TwitterVideoDownloader;
import com.vedha.allinonedownloader.utils.AdManager;
import com.vedha.allinonedownloader.utils.Utils;

public class TwetActivity extends AppCompatActivity {

    ImageView backBtn;
    LinearLayout tweatBtn;
    private InterstitialAd interstitial;

    EditText linkEdt;
    TextView downloadBtn;
    ImageView help1, help2, help3, help4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twet);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adIRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(TwetActivity.this);
        interstitial.setAdUnitId(getString(R.string.admob_interstitial));
        interstitial.loadAd(adIRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function when the Ad loads
                displayInterstitial();
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tweatBtn = findViewById(R.id.tweatBtn);
        tweatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitter();
            }
        });

        linkEdt = findViewById(R.id.linkEdt);
        downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(TwetActivity.this)) {
                    final String URL = linkEdt.getText().toString();
                    if (URL.trim().length() == 0) {
                        Toast.makeText(TwetActivity.this, "Please paste url and download!!!!", Toast.LENGTH_SHORT).show();
                    } else {

                        AdRequest adIRequest = new AdRequest.Builder().build();
                        interstitial = new InterstitialAd(TwetActivity.this);
                        interstitial.setAdUnitId(getString(R.string.admob_interstitial_for_dowload));
                        interstitial.loadAd(adIRequest);
                        interstitial.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // Call displayInterstitial() function when the Ad loads
                                displayInterstitial();
                            }
                        });


                        TwitterVideoDownloader downloader = new TwitterVideoDownloader(TwetActivity.this, URL);
                        downloader.DownloadVideo();
                        linkEdt.getText().clear();
                    }
                }else {
                    Toast.makeText(TwetActivity.this, "Internet Connection not available!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        help1 = findViewById(R.id.help1);
        help2 = findViewById(R.id.help2);
        help3 = findViewById(R.id.help3);
        help4 = findViewById(R.id.help4);

        Glide.with(TwetActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.twet_1))
                .into(help1);

        Glide.with(TwetActivity.this)
                .load(R.drawable.twet_2)
                .into(help2);

        Glide.with(TwetActivity.this)
                .load(R.drawable.twet_3)
                .into(help3);

        Glide.with(TwetActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.intro04))
                .into(help4);

        LinearLayout adContainer = findViewById(R.id.banner_container);
        LinearLayout adaptiveAdContainer = findViewById(R.id.banner_adaptive_container);
        if (!AdManager.isloadFbAd) {
            //admob
            AdManager.initAd(TwetActivity.this);
            AdManager.loadBannerAd(TwetActivity.this, adContainer);
            AdManager.adptiveBannerAd(TwetActivity.this, adaptiveAdContainer);
        } else {
            //MAX + Fb banner Ads
            AdManager.initMAX(TwetActivity.this);
            AdManager.maxBannerAdaptive(TwetActivity.this, adaptiveAdContainer);
            AdManager.maxInterstital(TwetActivity.this);
        }

    }

    private void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }



    private void openTwitter() {
        try {
            Intent i = this.getPackageManager().getLaunchIntentForPackage("com.twitter.android");
            this.startActivity(i);
        } catch (Exception var4) {
            this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "com.twitter.android")));
        }

    }
}
