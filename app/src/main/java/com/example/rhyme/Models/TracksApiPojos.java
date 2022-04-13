
package com.example.rhyme.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TracksApiPojos {

    @SerializedName("message")
    @Expose
    private Message message;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TracksApiPojos() {
    }

    /**
     * 
     * @param message
     */
    public TracksApiPojos(Message message) {
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
