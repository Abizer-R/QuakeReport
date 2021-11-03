package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuakeData> {

    public EarthQuakeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<EarthQuakeData> objects) {
        super(context, 0, objects);
    }

    // Convert Unix Time into date
    public String formatDate(Date earthquakeDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormat.format(earthquakeDate);
    }

    // Convert Unix Time into time
    public String formatTime(Date earthquakeTime) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(earthquakeTime);
    }

    // Convert the magnitude into "0.0" format
    public String formatMagnitude(Double mag) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return "" + decimalFormat.format(mag);
    }

    public int getMagnitudeColor(Double magnitude) {
        int magnitudeResourceID;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeResourceID = R.color.magnitude10;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeResourceID);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_listliew_layout,
                    parent,
                    false
            );
        }

        EarthQuakeData currEarthquake = getItem(position);

        TextView mag = listItemView.findViewById(R.id.magnitude);
        mag.setText(formatMagnitude(currEarthquake.getMagnitude()));

        // Setting color of magnitude circle
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        // Getting appropriate background color based on current Earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        // Dividing the location in 2 strings
        String currLocation = currEarthquake.getPlace();
        int index = currLocation.indexOf("of");
        String offsetLoc, primaryLoc;
        if(index == -1)
            offsetLoc = "Near the";
        else
            offsetLoc = currLocation.substring(0, index + 2);
        primaryLoc = currLocation.substring(index + 3);

        TextView offsetLocation = listItemView.findViewById(R.id.offsetLocation);
        offsetLocation.setText(offsetLoc);

        TextView primaryLocation = listItemView.findViewById(R.id.primaryLocation);
        primaryLocation.setText(primaryLoc);

        Date earthquakeDate = new Date(currEarthquake.getTimeInMilliseconds());
        Date earthquakeTime = new Date(currEarthquake.getTimeInMilliseconds());

//        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
//        SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:mm:ss");

//        String dateToDisplay = dateFormatter.format(earthquakeDate);
//        String timeToDisplay = timeFormatter.format(earthquakeTime);

        TextView date = listItemView.findViewById(R.id.date);
        date.setText(formatDate(earthquakeDate));

        TextView time = listItemView.findViewById(R.id.time);
        time.setText(formatTime(earthquakeTime));

        return listItemView;
    }
}
