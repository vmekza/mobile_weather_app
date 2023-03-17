package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ForecastActivity extends AppCompatActivity {
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("CITY_NAME");
        String somethingNotThere = intent.getStringExtra("NOT_THERE");
        int howManyDays = intent.getIntExtra("HOW_MANY_DAYS", 5);
        TextView ForecastHeaderTextView = findViewById(R.id.ForecastHeaderTextView);
        if (cityName != null) {
            ForecastHeaderTextView.setText(cityName);
        } else {
            ForecastHeaderTextView.setText(R.string.LOCATION_NOT_DEFINED);
        }
        queue = Volley.newRequestQueue(this);

        String url = "https://api.openweathermap.org/data/2.5/forecast?q=pirkkala&units=metric&appid=d1836a6af5f8c619aa8c18830b487399";
//
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    //The response here as a string
                    parseJsonAndUpdateForecast(response);

                }, error -> {
            //Error (timeout, other errors)

        });

        // Add the request to the queue to actually send it
        queue.add(stringRequest);

    }


    @SuppressLint("SimpleDateFormat")
    private void parseJsonAndUpdateForecast(String response) {
        try {
            JSONObject weatherResponse = new JSONObject(response);

            String description1, description2, description3 ;
            double temperature1, temperature2, temperature3;
            double windSpeed1, windSpeed2, windSpeed3;

            //First day
            description1 = weatherResponse.getJSONArray("list").getJSONObject(8).getJSONArray("weather").getJSONObject(0).getString("description");
            temperature1 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(8).getJSONObject("main").getDouble("temp"));
            windSpeed1 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(8).getJSONObject("wind").getDouble("speed"));


            TextView description1TextView = findViewById(R.id.description1TextView);
            description1TextView.setText(description1);
            TextView temperature1TextView = findViewById(R.id.temperature1TextView);
            temperature1TextView.setText("" + temperature1 + "C");
            TextView wind1TextView = findViewById(R.id.wind1TextView);
            wind1TextView.setText("" + windSpeed1 + " m/s");

            //Second day
            description2 = weatherResponse.getJSONArray("list").getJSONObject(16).getJSONArray("weather").getJSONObject(0).getString("description");
            temperature2 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(16).getJSONObject("main").getDouble("temp"));
            windSpeed2 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(16).getJSONObject("wind").getDouble("speed"));

            TextView descriptionTextView2 = findViewById(R.id.description2TextView);
            descriptionTextView2.setText(description2);
            TextView temperatureTextView = findViewById(R.id.temperature2TextView);
            temperatureTextView.setText("" + temperature2 + "C");
            TextView windTextView = findViewById(R.id.wind2TextView);
            windTextView.setText("" + windSpeed2 + " m/s");

            //Third day
            description3 = weatherResponse.getJSONArray("list").getJSONObject(24).getJSONArray("weather").getJSONObject(0).getString("description");
            temperature3 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(24).getJSONObject("main").getDouble("temp"));
            windSpeed3 = Math.round(weatherResponse.getJSONArray("list").getJSONObject(24).getJSONObject("wind").getDouble("speed"));

            TextView descriptionTextView3 = findViewById(R.id.description3TextView);
            descriptionTextView3.setText(description3);
            TextView temperatureTextView3 = findViewById(R.id.temperature3TextView);
            temperatureTextView3.setText("" + temperature3 + "C");
            TextView windTextView3 = findViewById(R.id.wind3TextView);
            windTextView3.setText("" + windSpeed3 + " m/s");

            if (description1.equals("overcast clouds")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.overcastcloud);
            }
            else if (description1.equals("broken clouds")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.brokenclouds);
            }
            else if (description1.equals("scattered clouds")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.overcastcloud);
            }
            else if (description1.equals("few clouds")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.overcastcloud);
            }
            else if (description1.equals("light snow")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.lightsnow);
            }
            else if (description1.equals("clear sky")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.sun);
            }
            else if (description1.equals("mist")) {
                ImageView img1 = findViewById(R.id.image1View);
                img1.setImageResource(R.drawable.overcastcloud);
            }

            if (description2.equals("overcast clouds")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.overcastcloud);
            }
            else if (description2.equals("broken clouds")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.brokenclouds);
            }
            else if (description2.equals("scattered clouds")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.overcastcloud);
            }
            else if (description2.equals("few clouds")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.overcastcloud);
            }
            else if (description1.equals("light snow")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.lightsnow);
            }
            else if (description1.equals("clear sky")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.sun);
            }
            else if (description1.equals("mist")) {
                ImageView img2 = findViewById(R.id.image2View);
                img2.setImageResource(R.drawable.overcastcloud);
            }

            if (description3.equals("overcast clouds")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.overcastcloud);
            }
            else if (description3.equals("broken clouds")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.brokenclouds);
            }
            else if (description3.equals("scattered clouds")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.overcastcloud);
            }
            else if (description3.equals("few clouds")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.overcastcloud);
            }
            else if (description3.equals("light snow")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.lightsnow);
            }
            else if (description3.equals("clear sky")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.sun);
            }
            else if (description3.equals("mist")) {
                ImageView img3 = findViewById(R.id.image3View);
                img3.setImageResource(R.drawable.overcastcloud);
            }


            String s1, s2;
            Date date1;
            Date date2;
            Date date3;
            Format formatter1, formatter2, formatter3;
            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DATE,1);
            date1 = calendar.getTime();
            formatter1 = new SimpleDateFormat("dd.MM");
            s1 = formatter1.format(date1);
            TextView day1 = findViewById(R.id.day1TextView);
            day1.setText(s1);

            calendar.add(Calendar.DATE,1);
            date2 = calendar.getTime();
            formatter2 = new SimpleDateFormat("dd.MM");
            s2 = formatter2.format(date2);
            TextView day2 = findViewById(R.id.day2TextView);
            day2.setText(s2);

            calendar.add(Calendar.DATE,1);
            date3 = calendar.getTime();
            formatter3 = new SimpleDateFormat("dd.MM");
            s2 = formatter3.format(date3);
            TextView day3 = findViewById(R.id.day3TextView);
            day3.setText(s2);

        }
        catch (JSONException e) {
            e.printStackTrace();

        }
    }



    public void openMainActivity(View view) {
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);

    }


    public void fetchForecastWeather(View view) {
    }
}