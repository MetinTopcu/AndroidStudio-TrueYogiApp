package com.metin.firebaselogindeneme.models;

import java.io.Serializable;

public class MoodTracking implements Serializable {

    private int id;
    private String title;
    private String content;
    private String iconurl;

    public MoodTracking() {
    }

    public MoodTracking(int id, String title, String content, String iconurl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.iconurl = iconurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }
}
