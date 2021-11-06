package com.example.quakereport;

public class EarthQuakeData {
    private Double magnitude;
    private String place;
    private long timeInMilliseconds;
    private String url;

    public EarthQuakeData(Double magnitude, String place, long timeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
