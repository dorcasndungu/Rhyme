
package com.example.rhyme.lyricsModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Body {

    @SerializedName("lyrics")
    @Expose
    private Lyrics lyrics;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Body() {
    }

    /**
     * 
     * @param lyrics
     */
    public Body(Lyrics lyrics) {
        super();
        this.lyrics = lyrics;
    }

    public Lyrics getLyrics() {
        return lyrics;
    }

    public void setLyrics(Lyrics lyrics) {
        this.lyrics = lyrics;
    }

}
