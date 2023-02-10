package com.vedha.allinonedownloader.interfaces;

public interface VideoDownloader {

//    String createDirectory();

    String getVideoId(String link);

    void DownloadVideo();
}
