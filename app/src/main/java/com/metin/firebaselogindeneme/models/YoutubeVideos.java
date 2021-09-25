package com.metin.firebaselogindeneme.models;

public class YoutubeVideos {
    private String videoUrl;

    public YoutubeVideos() {
    }

    public YoutubeVideos(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
