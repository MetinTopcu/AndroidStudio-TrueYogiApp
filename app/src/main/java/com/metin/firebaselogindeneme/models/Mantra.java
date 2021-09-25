package com.metin.firebaselogindeneme.models;

import java.io.Serializable;

public class Mantra  implements Serializable {
    private int id;
    private String mantra;


    public Mantra() {

    }

    public Mantra(int id, String mantra) {
        this.id = id;
        this.mantra = mantra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMantra() {
        return mantra;
    }

    public void setMantra(String mantra) {
        this.mantra = mantra;
    }
}
