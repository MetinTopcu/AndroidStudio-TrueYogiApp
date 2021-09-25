package com.metin.firebaselogindeneme;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class SunPositionActivity extends AppCompatActivity {
    private Button buttondeneme , buttondevam;
    private Toolbar toolbar;
    private EditText etCity ,etCountry;
    private TextView tvResult;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e53301e27efa0b66d05045d91b2742d3";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_position);

        toolbar = findViewById(R.id.toolbar);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
        buttondeneme = findViewById(R.id.buttondeneme);
        buttondevam = findViewById(R.id.buttondevam);

        toolbar.setTitle("Day/Night Sun Position");
        toolbar.setSubtitle("Londra");
        setSupportActionBar(toolbar);

        buttondevam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempUrl = "https://www.metaweather.com/api/location/search/?query=london";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

        buttondeneme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempUrl = "https://api.sunrise-sunset.org/json?lat=51.5074138&lng=-0.1279125,18";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        String output ="";
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonObjectresult = jsonResponse.getJSONObject("results");
                            String sunrise = jsonObjectresult.getString("sunrise");
                            String sunset = jsonObjectresult.getString("sunset");
                            String solar_noon = jsonObjectresult.getString("solar_noon");
                            String day_length = jsonObjectresult.getString("day_length");
                            String civil_twilight_begin = jsonObjectresult.getString("civil_twilight_begin");
                            String civil_twilight_end = jsonObjectresult.getString("civil_twilight_end");
                            String nautical_twilight_begin = jsonObjectresult.getString("nautical_twilight_begin");
                            String nautical_twilight_end = jsonObjectresult.getString("nautical_twilight_end");
                            String astronomical_twilight_begin = jsonObjectresult.getString("astronomical_twilight_begin");
                            String astronomical_twilight_end = jsonObjectresult.getString("astronomical_twilight_end");
                            String status = jsonResponse.getString("status");
                            tvResult.setTextColor(Color.rgb(68,134,199));
                            output += "Şafak Saati: " + civil_twilight_begin + "\n Gün Doğumu: " + sunrise
                                    + "\n Güneşin Tepede Olduğu Saat: " + solar_noon + "\n Gün Batımı: " + sunset
                                    + "\n Alacakaranlık Satti: " + astronomical_twilight_begin;
                            tvResult.setText(output);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

    }

    public void getWeatherDetails(View view) {

        String tempUrl = url + "?q=" + "London" + "&appid=" + appid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("response",response);
                String output ="";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse.getString("name");
                    tvResult.setTextColor(Color.rgb(68,134,199));
                    output += "Current weather of " + cityName + " ("+ countryName +")" + "\n Temp: " + df.format(temp) + " °C"
                            + "\n Feels Like: " + df.format(feelsLike) + " °C"
                            + "\n Humidity: " + humidity + "%"
                            + "\n Description: " + description
                            + "\n Wind Speed: " + wind + "m/s (meters per second)"
                            + "\n Cloudiness: " + clouds + "%"
                            + "\n Pressure: " + pressure + " hPa";
                    tvResult.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}