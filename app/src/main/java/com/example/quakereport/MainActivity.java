package com.example.quakereport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuakeData>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String websiteUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private EarthQuakeAdapter earthQuakeAdapter;
    private TextView emptyView;


//    min magnitude 6: https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView earthquakeListView = findViewById(R.id.list_view);
        earthQuakeAdapter = new EarthQuakeAdapter(this, 0, new ArrayList<EarthQuakeData>());
        earthquakeListView.setAdapter(earthQuakeAdapter);

        emptyView = findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyView);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EarthQuakeData currEarthquake = earthQuakeAdapter.getItem(i);
                Uri earthquakeUri = Uri.parse(currEarthquake.getUrl());
                Intent browerIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(browerIntent);
            }
        });

        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }




    @Override
    public Loader<List<EarthQuakeData>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, websiteUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuakeData>> loader, List<EarthQuakeData> earthquakes) {
        earthQuakeAdapter.clear();

        if(earthquakes.size() > 0)
            earthQuakeAdapter.addAll(earthquakes);
        else
            emptyView.setText("No earthquakes found.");
    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuakeData>> loader) {
        earthQuakeAdapter.clear();
    }



//    public void updateUi(List<EarthQuakeData> earthquakes) {
//        ListView earthQuakeListView = findViewById(R.id.list_view);
//
//        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(MainActivity.this, 0, earthquakes);
//
//        earthQuakeListView.setAdapter(earthQuakeAdapter);
//
//        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                EarthQuakeData currEarthquake = earthQuakeAdapter.getItem(i);
//
//                Uri earthquakeUri = Uri.parse(currEarthquake.getUrl());
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//                startActivity(browserIntent);
//            }
//        });
//    }

}