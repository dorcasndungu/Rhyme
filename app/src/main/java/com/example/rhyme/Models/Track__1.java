
package com.example.rhyme.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Track__1 {

    @SerializedName("track_id")
    @Expose
    private Integer trackId;
    @SerializedName("track_name")
    @Expose
    private String trackName;
    @SerializedName("track_name_translation_list")
    @Expose
    private List<Object> trackNameTranslationList = null;
    @SerializedName("track_rating")
    @Expose
    private Integer trackRating;
    @SerializedName("commontrack_id")
    @Expose
    private Integer commontrackId;
    @SerializedName("instrumental")
    @Expose
    private Integer instrumental;
    @SerializedName("explicit")
    @Expose
    private Integer explicit;
    @SerializedName("has_lyrics")
    @Expose
    private Integer hasLyrics;
    @SerializedName("has_subtitles")
    @Expose
    private Integer hasSubtitles;
    @SerializedName("has_richsync")
    @Expose
    private Integer hasRichsync;
    @SerializedName("num_favourite")
    @Expose
    private Integer numFavourite;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("track_share_url")
    @Expose
    private String trackShareUrl;
    @SerializedName("track_edit_url")
    @Expose
    private String trackEditUrl;
    @SerializedName("restricted")
    @Expose
    private Integer restricted;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("primary_genres")
    @Expose
    private PrimaryGenres primaryGenres;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Track__1() {
    }

    /**
     * 
     * @param primaryGenres
     * @param albumName
     * @param updatedTime
     * @param trackRating
     * @param trackId
     * @param albumId
     * @param artistId
     * @param trackName
     * @param explicit
     * @param trackNameTranslationList
     * @param trackShareUrl
     * @param hasLyrics
     * @param numFavourite
     * @param trackEditUrl
     * @param instrumental
     * @param restricted
     * @param hasRichsync
     * @param artistName
     * @param hasSubtitles
     * @param commontrackId
     */
    private String pushId;
    public Track__1(Integer trackId, String trackName, List<Object> trackNameTranslationList, Integer trackRating, Integer commontrackId, Integer instrumental, Integer explicit, Integer hasLyrics, Integer hasSubtitles, Integer hasRichsync, Integer numFavourite, Integer albumId, String albumName, Integer artistId, String artistName, String trackShareUrl, String trackEditUrl, Integer restricted, String updatedTime, PrimaryGenres primaryGenres) {
        super();
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackNameTranslationList = trackNameTranslationList;
        this.trackRating = trackRating;
        this.commontrackId = commontrackId;
        this.instrumental = instrumental;
        this.explicit = explicit;
        this.hasLyrics = hasLyrics;
        this.hasSubtitles = hasSubtitles;
        this.hasRichsync = hasRichsync;
        this.numFavourite = numFavourite;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.trackShareUrl = trackShareUrl;
        this.trackEditUrl = trackEditUrl;
        this.restricted = restricted;
        this.updatedTime = updatedTime;
        this.primaryGenres = primaryGenres;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public List<Object> getTrackNameTranslationList() {
        return trackNameTranslationList;
    }

    public void setTrackNameTranslationList(List<Object> trackNameTranslationList) {
        this.trackNameTranslationList = trackNameTranslationList;
    }

    public Integer getTrackRating() {
        return trackRating;
    }

    public void setTrackRating(Integer trackRating) {
        this.trackRating = trackRating;
    }

    public Integer getCommontrackId() {
        return commontrackId;
    }

    public void setCommontrackId(Integer commontrackId) {
        this.commontrackId = commontrackId;
    }

    public Integer getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(Integer instrumental) {
        this.instrumental = instrumental;
    }

    public Integer getExplicit() {
        return explicit;
    }

    public void setExplicit(Integer explicit) {
        this.explicit = explicit;
    }

    public Integer getHasLyrics() {
        return hasLyrics;
    }

    public void setHasLyrics(Integer hasLyrics) {
        this.hasLyrics = hasLyrics;
    }

    public Integer getHasSubtitles() {
        return hasSubtitles;
    }

    public void setHasSubtitles(Integer hasSubtitles) {
        this.hasSubtitles = hasSubtitles;
    }

    public Integer getHasRichsync() {
        return hasRichsync;
    }

    public void setHasRichsync(Integer hasRichsync) {
        this.hasRichsync = hasRichsync;
    }

    public Integer getNumFavourite() {
        return numFavourite;
    }

    public void setNumFavourite(Integer numFavourite) {
        this.numFavourite = numFavourite;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackShareUrl() {
        return trackShareUrl;
    }

    public void setTrackShareUrl(String trackShareUrl) {
        this.trackShareUrl = trackShareUrl;
    }

    public String getTrackEditUrl() {
        return trackEditUrl;
    }

    public void setTrackEditUrl(String trackEditUrl) {
        this.trackEditUrl = trackEditUrl;
    }

    public Integer getRestricted() {
        return restricted;
    }

    public void setRestricted(Integer restricted) {
        this.restricted = restricted;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public PrimaryGenres getPrimaryGenres() {
        return primaryGenres;
    }

    public void setPrimaryGenres(PrimaryGenres primaryGenres) {
        this.primaryGenres = primaryGenres;
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
