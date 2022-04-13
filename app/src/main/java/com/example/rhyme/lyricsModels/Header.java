
package com.example.rhyme.lyricsModels;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Header {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("execute_time")
    @Expose
    private Double executeTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Header() {
    }

    /**
     * 
     * @param statusCode
     * @param executeTime
     */
    public Header(Integer statusCode, Double executeTime) {
        super();
        this.statusCode = statusCode;
        this.executeTime = executeTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }

}
