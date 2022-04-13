
package com.example.rhyme.lyricsModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TrackNameTranslation__1 {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("translation")
    @Expose
    private String translation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrackNameTranslation__1() {
    }

    /**
     * 
     * @param translation
     * @param language
     */
    public TrackNameTranslation__1(String language, String translation) {
        super();
        this.language = language;
        this.translation = translation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
