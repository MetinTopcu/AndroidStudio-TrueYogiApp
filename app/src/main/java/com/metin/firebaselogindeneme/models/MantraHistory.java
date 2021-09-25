package com.metin.firebaselogindeneme.models;

import java.io.Serializable;

public class MantraHistory implements Serializable {

    private int id;
    private String codes;

    public MantraHistory() {
    }

    public MantraHistory(int id, String codes) {
        this.id = id;
        this.codes = codes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }
}
