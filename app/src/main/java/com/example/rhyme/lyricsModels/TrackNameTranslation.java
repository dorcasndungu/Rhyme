
package com.example.rhyme.lyricsModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TrackNameTranslation {

    @SerializedName("track_name_translation")
    @Expose
    private TrackNameTranslation__1 trackNameTranslation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrackNameTranslation() {
    }

    /**
     * 
     * @param trackNameTranslation
     */
    public TrackNameTranslation(TrackNameTranslation__1 trackNameTranslation) {
        super();
        this.trackNameTranslation = trackNameTranslation;
    }

    public TrackNameTranslation__1 getTrackNameTranslation() {
        return trackNameTranslation;
    }

    public void setTrackNameTranslation(TrackNameTranslation__1 trackNameTranslation) {
        this.trackNameTranslation = trackNameTranslation;
    }

}
