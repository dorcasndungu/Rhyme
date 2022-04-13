
package com.example.rhyme.lyricsModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LyricsApiPojos {

    @SerializedName("message")
    @Expose
    private Message message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LyricsApiPojos() {
    }

    /**
     * 
     * @param message
     */
    public LyricsApiPojos(Message message) {
        super();
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
