package com.vedha.allinonedownloader;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.vedha.allinonedownloader.utils.AdManager;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout wsBtn, waBusiBtn, insBtn, tokBtn, fbBtn, tweatBtn, galBtn, vimeoBtn,
            likeeBtn, snackBtn, sChatBtn, mojBtn, roposoBtn, chingariBtn, mxBtn, mitronBtn, ziliBtn, youtubebtn;
    ImageView settings;

    String[] permissionsList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        init();




    }


    private void loadRewardedAd() {
        rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }

    private void showRewardedAd() {
        if (rewardedAd.isLoaded()) {
            Activity activityContext = HomeActivity.this;
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    // Ad opened.
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                    loadRewardedAd();
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    // Ad failed to display.
                }
            };
            rewardedAd.show(activityContext, adCallback);
        } else {
            Log.d("TAG", "The rewarded ad wasn't loaded yet.");
        }
    }


    void init() {
        wsBtn = findViewById(R.id.wsBtn);
        wsBtn.setOnClickListener(this);
        waBusiBtn = findViewById(R.id.waBusiBtn);
        waBusiBtn.setOnClickListener(this);
        insBtn = findViewById(R.id.insBtn);
        insBtn.setOnClickListener(this);
        tokBtn = findViewById(R.id.tokBtn);
        tokBtn.setOnClickListener(this);
        fbBtn = findViewById(R.id.fbBtn);
        fbBtn.setOnClickListener(this);
        galBtn = findViewById(R.id.galBtn);
        galBtn.setOnClickListener(this);
        tweatBtn = findViewById(R.id.tweatBtn);
        tweatBtn.setOnClickListener(this);
        vimeoBtn = findViewById(R.id.vimeoBtn);
        vimeoBtn.setOnClickListener(this);
        likeeBtn = findViewById(R.id.likeeBtn);
        likeeBtn.setOnClickListener(this);
        snackBtn = findViewById(R.id.snackBtn);
        snackBtn.setOnClickListener(this);
        sChatBtn = findViewById(R.id.sChatBtn);
        sChatBtn.setOnClickListener(this);
        mojBtn = findViewById(R.id.mojBtn);
        mojBtn.setOnClickListener(this);
        roposoBtn = findViewById(R.id.roposoBtn);
        roposoBtn.setOnClickListener(this);
        chingariBtn = findViewById(R.id.chingariBtn);
        chingariBtn.setOnClickListener(this);
        mxBtn = findViewById(R.id.mxBtn);
        mxBtn.setOnClickListener(this);
        mitronBtn = findViewById(R.id.mitronBtn);
        mitronBtn.setOnClickListener(this);
        ziliBtn = findViewById(R.id.ziliBtn);
        youtubebtn = findViewById(R.id.youtubebtn);
        youtubebtn.setOnClickListener(this);
        ziliBtn.setOnClickListener(this);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wsBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {

                    startActivityes(new Intent(HomeActivity.this, WAppActivity.class));
                }
                break;

            case R.id.waBusiBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, WABusiActivity.class));
                }
                break;

            case R.id.insBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, InstaActivity.class));
                }
                break;

            case R.id.tokBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, TikActivity.class));
                }
                break;

            case R.id.fbBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, FBActivity.class));
                }
                break;

            case R.id.tweatBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, TwetActivity.class));
                }
                break;

            case R.id.vimeoBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, VimeoActivity.class));
                }
                break;

            case R.id.galBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, MyGalleryActivity.class));
                }
                break;

            case R.id.likeeBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KLikeActivity.class));
                }
                break;

            case R.id.snackBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KSnackActivity.class));
                }
                break;

            case R.id.sChatBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, SChatActivity.class));
                }
                break;

            case R.id.mojBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KMojActivity.class));
                }
                break;

            case R.id.mxBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KMXActivity.class));
                }
                break;

            case R.id.roposoBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KRopoActivity.class));
                }
                break;

            case R.id.chingariBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KChingariActivity.class));
                }
                break;

            case R.id.mitronBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KMitroActivity.class));
                }
                break;

            case R.id.ziliBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, KZiliActivity.class));
                }
                break;

            case R.id.settings:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, SettingsActivity.class));
                }
                break;
            case R.id.youtubebtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                }else {
                    startActivityes(new Intent(HomeActivity.this, Youtube.class));
                }
                break;

        }
    }

    void startActivityes(Intent intent) {
        if (!AdManager.isloadFbAd) {
            AdManager.adCounter++;
            AdManager.showInterAd(HomeActivity.this, intent);
        } else {
            AdManager.adCounter++;
            AdManager.showMaxInterstitial(HomeActivity.this, intent);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000L);
    }


    public static boolean checkPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 21) {
            if (!checkPermissions(this, permissionsList)) {
                ActivityCompat.requestPermissions(this, permissionsList, 21);
            }
        }
    }

}
