package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String websiteUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

//    min magnitude 6: https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<EarthQuakeData> earthquakes = QueryUtils.extractEarthquakes();
//
//
//        ListView earthQuakeListView = findViewById(R.id.list_view);
//
//        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(this, 0, earthquakes);
//
//        earthQuakeListView.setAdapter(earthQuakeAdapter);
//
//        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                // TODO Set item Click Listener
//            }
//        });

        EarthquakeTask task = new EarthquakeTask();

        task.execute(websiteUrl);

    }

    private class EarthquakeTask extends AsyncTask<String, Void, List<EarthQuakeData>> {

        @Override
        protected List<EarthQuakeData> doInBackground(String... strings) {
            if(strings.length < 1 || strings[0] == null)
                return null;
            List<EarthQuakeData> earthquakes = QueryUtils.fetchEarthquakeData(strings[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<EarthQuakeData> earthquakes) {
            if(earthquakes.size() < 1)
                return;
            updateUi(earthquakes);
        }
    }

    public void updateUi(List<EarthQuakeData> earthquakes) {
        ListView earthQuakeListView = findViewById(R.id.list_view);

        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(MainActivity.this, 0, earthquakes);

        earthQuakeListView.setAdapter(earthQuakeAdapter);

        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EarthQuakeData currEarthquake = earthQuakeAdapter.getItem(i);

                Uri earthquakeUri = Uri.parse(currEarthquake.getUrl());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(browserIntent);
            }
        });
    }
}