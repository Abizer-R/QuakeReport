package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EdgeEffect;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<EarthQuakeData> earthquakes = QueryUtils.extractEarthquakes();

//        ArrayList<EarthQuakeData> earthquakes = new ArrayList<>();
//
//        earthquakes.add(new EarthQuakeData("1.0", "Kalyan","1-2-3"));
//        earthquakes.add(new EarthQuakeData("2.0", "Mumbai", "1-2-3"));
//        earthquakes.add(new EarthQuakeData("3.0", "Indore", "1-2-3"));
//        earthquakes.add(new EarthQuakeData("4.0", "Rampura", "1-2-3"));
//        earthquakes.add(new EarthQuakeData("5.0", "Neemuch", "1-2-3"));
//        earthquakes.add(new EarthQuakeData("6.0", "Kurla", "1-2-3"));
//        earthquakes.add(new EarthQuakeData("7.0", "Hyderabad", "1-2-3"));

        ListView earthQuakeListView = findViewById(R.id.list_view);

        EarthQuakeAdapter earthQuakeAdapter = new EarthQuakeAdapter(this, 0, earthquakes);

        earthQuakeListView.setAdapter(earthQuakeAdapter);

        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent launchWebsite = new Intent(Intent.ACTION_VIEW);
                launchWebsite.setData(Uri.parse(QueryUtils.websiteUrl()));
                startActivity(launchWebsite);
            }
        });
    }
}