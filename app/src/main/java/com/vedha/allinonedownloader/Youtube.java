package com.vedha.allinonedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class Youtube extends AppCompatActivity {
    EditText urll;
    TextView btn;
    String downloadurl;
    ImageView backbtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        urll = findViewById(R.id.linkEdt);
        btn = findViewById(R.id.downloadBtn);
        backbtn = findViewById(R.id.backBtn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Youtube.this, HomeActivity.class));
            }
        });

        YouTubeUriExtractor down = new YouTubeUriExtractor(this) {
            @Override
            public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                if (ytFiles != null) {
                    int itag = 22;
                    try {
                        downloadurl = ytFiles.get(itag).getUrl();
                        if (downloadurl != null) {
                            Toast.makeText(Youtube.this, "Downloading Start...", Toast.LENGTH_SHORT).show();
                            downloadvideo(downloadurl);
                            progressDialog.dismiss();
                            urll.setText(null);
                        }
                    } catch (Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Youtube.this, "Cheak your Url", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                
                String url = urll.getText().toString().toString();

                if (url.isEmpty()) {
                    progressDialog.dismiss();
                    Toast.makeText(Youtube.this, "Enter Url", Toast.LENGTH_SHORT).show();

                } else {
                    down.extract(url);

                }
            }
        });
    }

    void downloadvideo(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Download");
        request.setDescription("Your file is downloading...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(Youtube.this, Environment.DIRECTORY_DOWNLOADS, "Downloads");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}