package com.example.househunt;

import android.app.Application;

import java.util.ArrayList;

public class GlobalArraylist extends Application {

    ArrayList<houseItem> globalArraylist;
    int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public ArrayList<houseItem> getGlobalArraylist() {
        return globalArraylist;
    }

    public void setGlobalArraylist(ArrayList<houseItem> globalArraylist) {
        this.globalArraylist = globalArraylist;
    }
}
