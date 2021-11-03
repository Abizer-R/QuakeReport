package com.example.quakereport;

public class EarthQuakeData {
    private Double magnitude;
    private String place;
    private long timeInMilliseconds;

    public EarthQuakeData(Double magnitude, String place, long timeInMilliseconds) {
        this.magnitude = magnitude;
        this.place = place;
        this.timeInMilliseconds = timeInMilliseconds;
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
}
