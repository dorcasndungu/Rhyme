package com.example.rhyme.lyricsModels;

import org.parceler.Parcel;

import java.util.List;

public class MyRhyme {
String Title;
String Rhyme;
    String index;

    private String pushId;

    public MyRhyme(String title, String rhyme) {
        Title = title;
        Rhyme = rhyme;
this.index="not_specified";
    }
    public MyRhyme() {
    }
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRhyme() {
        return Rhyme;
    }

    public void setRhyme(String rhyme) {
        Rhyme = rhyme;
    }



    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
