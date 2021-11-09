package com.example.quakereport;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuakeData>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String urlString;

    public EarthquakeLoader(@NonNull Context context, String urlString) {
        super(context);
        this.urlString = urlString;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<EarthQuakeData> loadInBackground() {
        if(urlString == null)
            return null;

        List<EarthQuakeData> earthquakes = QueryUtils.fetchEarthquakeData(urlString);
        return earthquakes;
    }
}
