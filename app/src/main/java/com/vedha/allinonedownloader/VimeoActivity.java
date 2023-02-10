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
import com.vedha.allinonedownloader.model.VimeoVideoDownloader;
import com.vedha.allinonedownloader.utils.AdManager;
import com.vedha.allinonedownloader.utils.Utils;

public class VimeoActivity extends AppCompatActivity {

    ImageView backBtn;
    LinearLayout vimeoBtn;

    EditText linkEdt;
    TextView downloadBtn;
    private InterstitialAd interstitial;

    ImageView help1, help2, help3, help4, help5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vimeo);

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adIRequest = new AdRequest.Builder().build();
        interstitial = new InterstitialAd(VimeoActivity.this);
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

        vimeoBtn = findViewById(R.id.vimeoBtn);
        vimeoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVimeo();
            }
        });

        linkEdt = findViewById(R.id.linkEdt);
        downloadBtn = findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvailable(VimeoActivity.this)) {
                    final String URL = linkEdt.getText().toString();
                    if (URL.trim().length() == 0) {
                        Toast.makeText(VimeoActivity.this, "Please paste url and download!!!!", Toast.LENGTH_SHORT).show();
                    } else {

                        //ads
                        if (!AdManager.isloadFbAd) {
                            AdManager.adCounter++;
                            AdManager.showInterAd(VimeoActivity.this, null);
                        } else {
                            AdManager.adCounter++;
                            AdManager.showMaxInterstitial(VimeoActivity.this, null);
                        }

                        VimeoVideoDownloader downloader = new VimeoVideoDownloader(VimeoActivity.this,URL);
                        downloader.DownloadVideo();
                        linkEdt.getText().clear();
                    }
                } else {
                    Toast.makeText(VimeoActivity.this, "Internet Connection not available!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        help1 = findViewById(R.id.help1);
        help2 = findViewById(R.id.help2);
        help3 = findViewById(R.id.help3);
        help4 = findViewById(R.id.help4);
        help5 = findViewById(R.id.help5);

        Glide.with(VimeoActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.vimeo1))
                .into(help1);

        Glide.with(VimeoActivity.this)
                .load(R.drawable.vimeo2)
                .into(help2);

        Glide.with(VimeoActivity.this)
                .load(R.drawable.vimeo3)
                .into(help3);

        Glide.with(VimeoActivity.this)
                .load(R.drawable.vimeo4)
                .into(help4);

        Glide.with(VimeoActivity.this)
                .load(ContextCompat.getDrawable(this,R.drawable.intro04))
                .into(help5);

        LinearLayout adContainer = findViewById(R.id.banner_container);
        LinearLayout adaptiveAdContainer = findViewById(R.id.banner_adaptive_container);
        if (!AdManager.isloadFbAd) {
            //admob
            AdManager.initAd(VimeoActivity.this);
            AdManager.loadBannerAd(VimeoActivity.this, adContainer);
            AdManager.adptiveBannerAd(VimeoActivity.this, adaptiveAdContainer);
        } else {
            //MAX + Fb banner Ads
            AdManager.initMAX(VimeoActivity.this);
            AdManager.maxBannerAdaptive(VimeoActivity.this, adaptiveAdContainer);
            AdManager.maxInterstital(VimeoActivity.this);
        }
    }

    private void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    private void openVimeo() {
        try {
            Intent i = this.getPackageManager().getLaunchIntentForPackage("com.vimeo.android.videoapp");
            this.startActivity(i);
        } catch (Exception var4) {
            this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "com.vimeo.android.videoapp")));
        }

    }
}
