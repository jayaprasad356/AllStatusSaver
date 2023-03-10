package com.vedha.allinonedownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
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
import com.vedha.allinonedownloader.utils.AdManager;
import com.vedha.allinonedownloader.utils.SVSupport;
import com.vedha.allinonedownloader.utils.Utils;

public class KSnackActivity extends AppCompatActivity {
    ImageView backBtn;
    LinearLayout snackBtn;

    EditText linkEdt;
    TextView downloadBtn;

    ImageView help1, help2, help3, help4;
    String url;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_snack);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adIRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(KSnackActivity.this);
        interstitial.setAdUnitId(getString(R.string.admob_interstitial));
        interstitial.loadAd(adIRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function when the Ad loads
                displayInterstitial();
            }
        });

        help1 = findViewById(R.id.help1);
        help2 = findViewById(R.id.help2);
        help3 = findViewById(R.id.help3);
        help4 = findViewById(R.id.help4);

        Glide.with(KSnackActivity.this)
                .load(ContextCompat.getDrawable(this, R.drawable.snack_step1))
                .into(help1);

        Glide.with(KSnackActivity.this)
                .load(R.drawable.snack_step2)
                .into(help2);

        Glide.with(KSnackActivity.this)
                .load(R.drawable.snack_step3)
                .into(help3);

        Glide.with(KSnackActivity.this)
                .load(ContextCompat.getDrawable(this, R.drawable.intro04))
                .into(help4);

        linkEdt = findViewById(R.id.linkEdt);
        downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(KSnackActivity.this)) {
                    if (linkEdt.getText().toString().trim().length() == 0) {
                        Toast.makeText(KSnackActivity.this, "Please paste url and download!!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        url = linkEdt.getText().toString();
                        if (url.contains("snackvideo.com")) {
                            SVSupport.context = KSnackActivity.this;
                            new SVSupport.getSnackUrlAsync().execute(url);
                        } else if (url.contains("sck.io")) {
                            Utils.displayLoader(KSnackActivity.this);
                            SVSupport.getVideoInfoFromUrl(url);
                        }

                        linkEdt.getText().clear();
                        AdRequest adIRequest = new AdRequest.Builder().build();
                        interstitial = new InterstitialAd(KSnackActivity.this);
                        interstitial.setAdUnitId(getString(R.string.admob_interstitial_for_dowload));
                        interstitial.loadAd(adIRequest);
                        interstitial.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // Call displayInterstitial() function when the Ad loads
                                displayInterstitial();
                            }
                        });

                    }
                } else {
                    Toast.makeText(KSnackActivity.this, "Internet Connection not available!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        snackBtn = findViewById(R.id.snackBtn);
        snackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSnack();
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        LinearLayout adContainer = findViewById(R.id.banner_container);
        LinearLayout adaptiveAdContainer = findViewById(R.id.banner_adaptive_container);
        if (!AdManager.isloadFbAd) {
            //admob
            AdManager.initAd(KSnackActivity.this);
            AdManager.loadBannerAd(KSnackActivity.this, adContainer);
            AdManager.adptiveBannerAd(KSnackActivity.this, adaptiveAdContainer);
        } else {
            //MAX + Fb banner Ads
            AdManager.initMAX(KSnackActivity.this);
            AdManager.maxBannerAdaptive(KSnackActivity.this, adaptiveAdContainer);
            AdManager.maxInterstital(KSnackActivity.this);
        }
    }

    private void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }


    private void openSnack() {
        try {
            Intent i = this.getPackageManager().getLaunchIntentForPackage("com.kwai.bulldog");
            this.startActivity(i);
        } catch (Exception var4) {
            this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "com.kwai.bulldog")));
        }

    }

}