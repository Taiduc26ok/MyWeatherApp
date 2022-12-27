package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ProgressBar PB;
    TextView cityName, degree, minT, maxT, win, rain, cloud, timeline, weatherReport;
    TextInputEditText EditText;
    ImageButton button;
    Button button2;
    VideoView videoView;
    String video_path;


    //Function for VideoView Setup
    public void SetVideoBG(String video_path){
        Uri uri = Uri.parse(video_path);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //set up calendar for main layout
        Calendar calendar = Calendar.getInstance();
        String current_time = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        //find view by ID from xml layout file
        PB = findViewById(R.id.loading);
        cityName = findViewById(R.id.cityname);
        degree = findViewById(R.id.degree);
        minT = findViewById(R.id.minT);
        maxT = findViewById(R.id.maxT);
        win = findViewById(R.id.win);
        rain = findViewById(R.id.visi);
        cloud = findViewById(R.id.cloud);
        timeline = findViewById(R.id.dailytime);
        EditText = findViewById(R.id.etTextRI);
        button =  findViewById(R.id.button);
        weatherReport = findViewById(R.id.weatherReport);
        button2 = findViewById(R.id.fivedays);
        timeline.setText(current_time);

        //video background setup
        videoView = findViewById(R.id.videoview);
        video_path = "android.resource://" + getPackageName() + "/" + R.raw.main;
        SetVideoBG(video_path);


        final DataWeather dataWeather = new DataWeather(MainActivity.this);

        //set up button 1 (Data for the current weather)
        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataWeather.cityWeatherToDay(EditText.getText().toString().trim(), new DataWeather.CallBack_today() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(WeatherModel today_weather) {

                    //response project today_weather and fetch data to the exact view
                    degree.setText(today_weather.getTemp());
                    minT.setText(today_weather.getMinT());
                    maxT.setText(today_weather.getMaxT());
                    win.setText(today_weather.getWin());
                    rain.setText(today_weather.getVisible());
                    cloud.setText(today_weather.getCloud());
                    cityName.setText(EditText.getText().toString());
                    weatherReport.setText(today_weather.getWeather());

                    //Change Background Video per WeatherID
                    if (today_weather.getId() == 800 ) {
                        video_path = "android.resource://" + getPackageName() + "/" + R.raw.clear;      //clear sky
                        SetVideoBG(video_path);
                    }
                    else if (today_weather.getId() <= 232 & today_weather.getId() >= 200 ) {
                        video_path = "android.resource://" + getPackageName() + "/" + R.raw.thunder;    //thunderstorm
                        SetVideoBG(video_path);
                    }
                    else if (today_weather.getId() <= 531 & today_weather.getId() >= 500 ) {
                        video_path = "android.resource://" + getPackageName() + "/" + R.raw.rain;       //rain
                        SetVideoBG(video_path);
                    }
                    else if (today_weather.getId() <= 622 & today_weather.getId() >= 600 ) {
                        video_path = "android.resource://" + getPackageName() + "/" + R.raw.snow;       //snow
                        SetVideoBG(video_path);
                    }
                    else if (today_weather.getId() <= 804 & today_weather.getId() >= 800 ) {
                        video_path = "android.resource://" + getPackageName() + "/" + R.raw.cloud;      //cloudy
                        SetVideoBG(video_path);
                    }
                    else video_path = "android.resource://" + getPackageName() + "/" + R.raw.main;      //default video on the main layout
                        SetVideoBG(video_path);
                        hide_keyboard();        //hide the keyboard afterwards

                    }
                });
         }
        });

        //Set up button 2 for the - moving to activity 2
        button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            toTheFiveDay();

            }
        });
        }

        //Necessary set up for the video view
        @Override
        protected void onPostResume() {
            videoView.resume();
            super.onPostResume();
        }

        @Override
        protected void onStart() {
            videoView.start();
            super.onStart();
        }

        @Override
        protected void onPause() {
            videoView.suspend();
            super.onPause();
        }

        @Override
        protected void onDestroy() {
            videoView.stopPlayback();
            super.onDestroy();
        }


        //function to move from main activity to second activity
    public void toTheFiveDay(){
        String city_name = EditText.getText().toString().trim();
        Intent intent = new Intent(this, fivedayweather.class);
        intent.putExtra("city_name", city_name); //sending data "city name" to layout 2
        startActivity(intent);


    }

    //function to turn off the keyboard showing on the device
    public void hide_keyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}