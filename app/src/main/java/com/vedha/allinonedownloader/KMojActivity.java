package com.vedha.allinonedownloader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.vedha.allinonedownloader.utils.AdManager;
import com.vedha.allinonedownloader.utils.KMoSupport;
import com.vedha.allinonedownloader.utils.Utils;


public class KMojActivity extends AppCompatActivity {

    ImageView backBtn;
    LinearLayout kMojBtn;

    EditText linkEdt;
    TextView downloadBtn;

    ImageView help1, help2, help3, help4;
    String url;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kmoj);

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adIRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(KMojActivity.this);
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

        Glide.with(KMojActivity.this)
                .load(ContextCompat.getDrawable(this, R.drawable.moj0))
                .into(help1);

        Glide.with(KMojActivity.this)
                .load(R.drawable.moj1)
                .into(help2);

        Glide.with(KMojActivity.this)
                .load(R.drawable.moj2)
                .into(help3);

        Glide.with(KMojActivity.this)
                .load(ContextCompat.getDrawable(this, R.drawable.intro04))
                .into(help4);

        linkEdt = findViewById(R.id.linkEdt);
        downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkAvailable(KMojActivity.this)) {
                    if (linkEdt.getText().toString().trim().length() == 0) {
                        Toast.makeText(KMojActivity.this, "Please paste url and download!!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        url = linkEdt.getText().toString();
                        if (url.contains("moj")) {
                            KMoSupport.KMojDownload(KMojActivity.this, url);
                            linkEdt.getText().clear();
                        } else {
                            Toast.makeText(KMojActivity.this, "Url not exists!!!!", Toast.LENGTH_SHORT).show();
                        }

                        AdRequest adIRequest = new AdRequest.Builder().build();
                        interstitial = new InterstitialAd(KMojActivity.this);
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
                }else {
                    Toast.makeText(KMojActivity.this, "Internet Connection not available!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        kMojBtn = findViewById(R.id.kMojBtn);
        kMojBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKMoj();
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
            AdManager.initAd(KMojActivity.this);
            AdManager.loadBannerAd(KMojActivity.this, adContainer);
            AdManager.adptiveBannerAd(KMojActivity.this, adaptiveAdContainer);
        } else {
            //MAX + Fb banner Ads
            AdManager.initMAX(KMojActivity.this);
            AdManager.maxBannerAdaptive(KMojActivity.this, adaptiveAdContainer);
            AdManager.maxInterstital(KMojActivity.this);
        }

    }

    private void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    private void openKMoj() {
        try {
            Intent i = this.getPackageManager().getLaunchIntentForPackage("in.mohalla.video");
            this.startActivity(i);
        } catch (Exception e) {
            this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "in.mohalla.video")));
        }

    }
}