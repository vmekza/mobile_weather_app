package com.example.myapplication;

import static java.lang.Math.round;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private String description = "click to update";
    private double temperature = 0;
    private double windSpeed = 0;
    private int humidity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        if(savedInstanceState != null) {
            description = savedInstanceState.getString("WEATHER_DESCRIPTION");
            if(description == null) {
                description = "click to update";
            }
            temperature = savedInstanceState.getDouble("TEMPERATURE", 0);
            windSpeed = savedInstanceState.getDouble("WIND_SPEED", 0);
            humidity = savedInstanceState.getInt("HUMIDITY", 0);
            if (description.equals("broken clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.brokenclouds);
            }
            else if(description.equals("overcast clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("scattered clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("few clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("light snow")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.lightsnow);
            }
            else if(description.equals("clear sky")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.sun);
            }
            else if(description.equals("mist")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
        }

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(description);

        TextView temperatureTextView = findViewById(R.id.temperatureTextView);
        temperatureTextView.setText("" + temperature + "C");

        TextView windTextView = findViewById(R.id.windTextView);
        windTextView.setText("" + windSpeed + " m/s");

        TextView humidityTextView = findViewById(R.id.humidityTextView);
        humidityTextView.setText("" + humidity + "%");

        String date = new SimpleDateFormat("dd.MM", Locale.getDefault()).format(new Date());
        TextView dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);

    }
    protected void onStart() {
        super.onStart();
        Log.d("MY_APP", "MainActivity: onStartCalled");
    }
    protected void onResume() {
        super.onResume();
    }
    protected void onPause() {
        super.onPause();
    }
    protected void onStop() {
        super.onStop();
    }
    protected void onDestroy() {
        super.onDestroy();
    }

    public void openForecastActivity(View view) {
        Intent openForecast = new Intent(this, ForecastActivity.class);
        openForecast.putExtra("CITY_NAME","Pirkkala");
        openForecast.putExtra("HOW_MANY_DAYS", 3);
        startActivity(openForecast);
    }
    //Automatic callback from the system before the activity is destroyed
    //UI can be saved here
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("WEATHER_DESCRIPTION", description);
        outState.putDouble("TEMPERATURE", temperature );
        outState.putDouble("WIND_SPEED", windSpeed);
        outState.putInt("HUMIDITY", humidity);
    }

    public void fetchWeatherData(View view) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=pirkkala&units=metric&appid=1b7290845f6bd631eb2edb775417b106";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    parseJsonAndUpdateUI( response );

                }, error -> {
                    //Error (timeout, other errors)

                });

        // Add the request to the queue to actually send it
        queue.add(stringRequest);

    }


    private void parseJsonAndUpdateUI(String response) {
        try {
            JSONObject weatherResponse = new JSONObject(response);

            description = weatherResponse.getJSONArray("weather").getJSONObject(0).getString("description");
            temperature = Math.round(weatherResponse.getJSONObject("main").getDouble("temp"));
            windSpeed = Math.round(weatherResponse.getJSONObject("wind").getDouble("speed"));
            humidity = weatherResponse.getJSONObject("main").getInt("humidity");

            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(description);

            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText("" + temperature + "C");

            TextView windTextView = findViewById(R.id.windTextView);
            windTextView.setText("" + windSpeed + " m/s");

            TextView humidityTextView = findViewById(R.id.humidityTextView);
            humidityTextView.setText("" + humidity + "%");
//
            if (description.equals("broken clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.brokenclouds);
            }
            else if(description.equals("overcast clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("scattered clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("few clouds")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }
            else if(description.equals("light snow")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.lightsnow);
            }
            else if(description.equals("clear sky")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.sun);
            }
            else if(description.equals("mist")) {
                ImageView img = findViewById(R.id.imageView2);
                img.setImageResource(R.drawable.overcastcloud);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    public void openWebPage(View view) {
        String urlString = "https://www.accuweather.com/en/fi/pirkkala/134632/current-weather/134632";
        Uri uri = Uri.parse(urlString);
        Intent openWebPage = new Intent(Intent.ACTION_VIEW, uri);
    try {
        startActivity(openWebPage);
    }
    catch(ActivityNotFoundException e) {

    }

    }




}