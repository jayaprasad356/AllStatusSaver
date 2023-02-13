package com.vedha.allinonedownloader;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
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
import com.vedha.allinonedownloader.utils.InstaDownload;
import com.vedha.allinonedownloader.utils.Utils;

public class InstaActivity extends AppCompatActivity {
    ImageView backBtn;
    LinearLayout instaBtn;
    private InterstitialAd interstitial;

    EditText linkEdt;
    TextView downloadBtn;
    ImageView help1, help2, help3, help4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adIRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(InstaActivity.this);
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

        Glide.with(InstaActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.intro01))
                .into(help1);

        Glide.with(InstaActivity.this)
                .load(R.drawable.intro02)
                .into(help2);

        Glide.with(InstaActivity.this)
                .load(R.drawable.intro03)
                .into(help3);

        Glide.with(InstaActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.intro04))
                .into(help4);

        linkEdt = findViewById(R.id.linkEdt);
        downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (Utils.isNetworkAvailable(InstaActivity.this)) {



                    if (linkEdt.getText().toString().trim().length() == 0) {
                        Toast.makeText(InstaActivity.this, "Please paste url and download!!!!", Toast.LENGTH_SHORT).show();
                    } else {


                        AdRequest adIRequest = new AdRequest.Builder().build();
                        interstitial = new InterstitialAd(InstaActivity.this);
                        interstitial.setAdUnitId(getString(R.string.admob_interstitial_for_dowload));
                        interstitial.loadAd(adIRequest);
                        interstitial.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                // Call displayInterstitial() function when the Ad loads
                                displayInterstitial();
                            }
                        });


                        final String url = linkEdt.getText().toString();

                        if (!Patterns.WEB_URL.matcher(url).matches() && !url.contains("instagram")) {
                            Toast.makeText(InstaActivity.this, R.string.invalid, Toast.LENGTH_SHORT).show();
                        } else {
                            InstaDownload.INSTANCE.startInstaDownload(url, InstaActivity.this);
                            linkEdt.getText().clear();
                        }

                        //ads

                    }
                }else {
                    Toast.makeText(InstaActivity.this, "Internet Connection not available!!!!", Toast.LENGTH_SHORT).show();
                }
            }


        });


        instaBtn = findViewById(R.id.instaBtn);
        instaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchInstagram();


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
            AdManager.initAd(InstaActivity.this);
            AdManager.loadBannerAd(InstaActivity.this, adContainer);
            AdManager.adptiveBannerAd(InstaActivity.this, adaptiveAdContainer);
        } else {
            //MAX + Fb banner Ads
            AdManager.initMAX(InstaActivity.this);
            AdManager.maxBannerAdaptive(InstaActivity.this, adaptiveAdContainer);
        }

    }


    private void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void launchInstagram() {
        String instagramApp = "com.instagram.android";
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(instagramApp);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.instagram_not_found, Toast.LENGTH_SHORT).show();
        }
    }



}
