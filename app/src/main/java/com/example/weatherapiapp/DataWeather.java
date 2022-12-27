package com.example.weatherapiapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataWeather {

    public static final String URL_OPEN_1 = "https://api.openweathermap.org/data/2.5/forecast?"; //5days's Weather
    public static final String URL_OPEN_2 = "https://api.openweathermap.org/data/2.5/weather?"; // Today's Weather
    public static final String API = "&appid=187d9620e7ab65d060e8313c2a7279ac";

    Context context;


    public DataWeather(Context context) {
        this.context = context;
    }


    //Call back function for the function to get weather for 5 days from the API
    public interface CallBack {
        void onError(String message);

        void onResponse(WeatherModel weather);
    }
    // Function to get Data Weather 5 days using REST API Volley  and response a object with assigned data from API
    public void cityWeather5Days(String cityName, CallBack callBack) {

        // Get longtitude and latitude from the city using geocoding
        double lon = 0.0;
        double lat = 0.0;

        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(cityName, 1);

            if (addressList != null) {
                lon = addressList.get(0).getLongitude();    //get longtitude
                lat = addressList.get(0).getLatitude();     //get latitude

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Url is built from long and lat for the API Request
        String url = URL_OPEN_1 + "lat=" + lat + "&lon=" + lon + API;

        //Creating a Request of JSONObject type
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    // create new object, which will be responsed lately in this function
                    WeatherModel weather5 = new WeatherModel();

                    //Weather Day 1 or Today, get temperature as double type and turn it to int then String
                    double Temp1_min = response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp1_min_rounded = (int)Math.rint(Temp1_min);
                    weather5.setMin_1(Integer.toString(Temp1_min_rounded));

                    double Temp1_max = response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp_max")-273.15;
                    int Temp1_max_rounded = (int)Math.rint(Temp1_max);
                    weather5.setMax_1(Integer.toString(Temp1_max_rounded));

                    weather5.setId_1(response.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather5.setIm1(weather5.SetupImage_weather(weather5.getId_1()));       //Set Image from day 1 as a String relying on the variable of the weather ID from API


                    //Weather Day 2
                    double Temp2_min = response.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp2_min_rounded = (int)Math.rint(Temp2_min);
                    weather5.setMin_2(Integer.toString(Temp2_min_rounded));


                    double Temp2_max = response.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp_max")-273.15;
                    int Temp2_max_rounded = (int)Math.rint(Temp2_max);
                    weather5.setMax_2(Integer.toString(Temp2_max_rounded));

                    weather5.setId_2(response.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather5.setIm2(weather5.SetupImage_weather(weather5.getId_2()));

                    //Weather Day 3
                    double Temp3_min = response.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp3_min_rounded = (int)Math.rint(Temp3_min);
                    weather5.setMin_3(Integer.toString(Temp3_min_rounded));

                    double Temp3_max = response.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp_max")-273.15;
                    int Temp3_max_rounded = (int)Math.rint(Temp3_max);
                    weather5.setMax_3(Integer.toString(Temp3_max_rounded));

                    weather5.setId_3(response.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather5.setIm3(weather5.SetupImage_weather(weather5.getId_3()));

                    //Weather Day 4
                    double Temp4_min = response.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp4_min_rounded = (int)Math.rint(Temp4_min);
                    weather5.setMin_4(Integer.toString(Temp4_min_rounded));

                    double Temp4_max = response.getJSONArray("list").getJSONObject(3).getJSONObject("main").getDouble("temp_max")-273.15;
                    int Temp4_max_rounded = (int)Math.rint(Temp4_max);
                    weather5.setMax_4(Integer.toString(Temp4_max_rounded));

                    weather5.setId_4(response.getJSONArray("list").getJSONObject(3).getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather5.setIm4(weather5.SetupImage_weather(weather5.getId_4()));

                    //Weather Day 5
                    double Temp5_min = response.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp5_min_rounded = (int)Math.rint(Temp5_min);
                    weather5.setMin_5(Integer.toString(Temp5_min_rounded));

                    double Temp5_max = response.getJSONArray("list").getJSONObject(4).getJSONObject("main").getDouble("temp_max")-273.15;
                    int Temp5_max_rounded = (int)Math.rint(Temp5_max);
                    weather5.setMax_5(Integer.toString(Temp5_max_rounded));

                    weather5.setId_5(response.getJSONArray("list").getJSONObject(4).getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather5.setIm5(weather5.SetupImage_weather(weather5.getId_5()));


                    callBack.onResponse(weather5);          //response the object when the function is called


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                callBack.onError("Something went wrong");   //on error
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request); // put the request to the request queue using the class MySingleton

    }


    //Callback function for current weather
    public interface CallBack_today {
        void onError(String message);

        void onResponse(WeatherModel today_weather);
    }

    //Function to get current weather using REST API Volley and fetch data to the object
    public void cityWeatherToDay(String cityName, CallBack_today callBack_listener) {


        //Get longtitude and latitude using geocoding
        double lon = 0.0;
        double lat = 0.0;

        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(cityName, 1);

            if (addressList != null) {
                lon = addressList.get(0).getLongitude();
                lat = addressList.get(0).getLatitude();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = URL_OPEN_2 + "lat=" + lat + "&lon=" + lon + API;

        //Creating a JSONObject request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    // create new object
                    WeatherModel weather = new WeatherModel();


                    double Temp = response.getJSONObject("main").getDouble("temp")-273.15;
                    int Temp_rounded = (int)Math.rint(Temp);

                    double Temp_min = response.getJSONObject("main").getDouble("temp_min")-273.15;
                    int Temp_min_rd = (int)Math.rint(Temp_min);

                    double Temp_max = response.getJSONObject("main").getDouble("temp_max")- 273.15;
                    int Temp_max_rd = (int)Math.rint(Temp_max);

                    //Weather current
                    //fetching Data from API to the object
                    weather.setTemp(Integer.toString(Temp_rounded));
                    weather.setMinT(Integer.toString(Temp_min_rd));
                    weather.setMaxT(Integer.toString(Temp_max_rd));

                    weather.setWin(response.getJSONObject("wind").getString("speed"));
                    weather.setCloud(response.getJSONObject("clouds").getString("all"));
                    weather.setVisible(response.getString("visibility"));
                    weather.setId(response.getJSONArray("weather").getJSONObject(0).getInt("id"));
                    weather.setWeather(response.getJSONArray("weather").getJSONObject(0).getString("description"));


                    callBack_listener.onResponse(weather);  //response the object


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                callBack_listener.onError("something wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);        //put the request to the request queue

    }


}



