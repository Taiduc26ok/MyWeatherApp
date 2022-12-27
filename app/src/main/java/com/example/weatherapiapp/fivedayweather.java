package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fivedayweather extends AppCompatActivity {

    ImageView i1,i2,i3,i4,i5;
    TextView min1,min2,min3,min4,min5,max1,max2,max3,max4,max5, thecity,day1,day2,day3,day4,day5, time2;
    ImageView imageView;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivedayweather);
        getSupportActionBar().hide();

        //set calender for the next upcoming 5 days
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");

        calendar.add(Calendar.DATE,0);                  //adds 0 day
        Date today = calendar.getTime();
        String today_date = dateFormat.format(today);       //day 1 which is current day

        calendar.add(Calendar.DATE,1);
        Date day_2 = calendar.getTime();
        String day2_date = dateFormat.format(day_2);        //day 2

        calendar.add(Calendar.DATE,1);
        Date day_3 = calendar.getTime();
        String day3_date = dateFormat.format(day_3);        //day 3

        calendar.add(Calendar.DATE,1);
        Date day_4 = calendar.getTime();
        String day4_date = dateFormat.format(day_4);        //day 4

        calendar.add(Calendar.DATE,1);
        Date day_5 = calendar.getTime();
        String day5_date = dateFormat.format(day_5);        //day 5

        //Find view by id
        i1 = findViewById(R.id.imageday1);
        i2 = findViewById(R.id.imageday2);
        i3 = findViewById(R.id.imageday3);
        i4 = findViewById(R.id.imageday4);
        i5 = findViewById(R.id.imageday5);                  //for the image of the weather pictures

        min1 = findViewById(R.id.minday1);
        min2 = findViewById(R.id.minday2);
        min3 = findViewById(R.id.minday3);
        min4 = findViewById(R.id.minday4);
        min5 = findViewById(R.id.minday5);                  //for the minimum temperatur

        max1 = findViewById(R.id.maxday1);
        max2 = findViewById(R.id.maxday2);
        max3 = findViewById(R.id.maxday3);
        max4 = findViewById(R.id.maxday4);
        max5 = findViewById(R.id.maxday5);                  //for the maximum temperatur

        day1 = findViewById(R.id.day1);
        day2 = findViewById(R.id.day2);
        day3 = findViewById(R.id.day3);
        day4 = findViewById(R.id.day4);
        day5 = findViewById(R.id.day5);                     //for the date infos

        thecity = findViewById(R.id.cityname2);             //the city name

        time2 = findViewById(R.id.time2);

        //Set up current date for layout weather in 5 days
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat1 = new SimpleDateFormat("MM-dd-yyyy");
        c.add(Calendar.DAY_OF_MONTH,0);
        Date today_1 = c.getTime();
        String today_1_s = dateFormat1.format(today_1);
        time2.setText(today_1_s);


        //Set up background Image for 2nd layout
        imageView = findViewById(R.id.back2);
        drawable = getResources().getDrawable(R.drawable.back2);
        imageView.setImageDrawable(drawable);

        // receive city name from main Activity
        final DataWeather dataWeather_5days = new DataWeather(fivedayweather.this);
        String city_name = getIntent().getStringExtra("city_name");
        thecity.setText(city_name);

        //assign the string value for each 5 days from the current day
        day1.setText(today_date);
        day2.setText(day2_date);
        day3.setText(day3_date);
        day4.setText(day4_date);
        day5.setText(day5_date);


        dataWeather_5days.cityWeather5Days(city_name, new DataWeather.CallBack() {

            @Override
            public void onError(String message) {
                Toast.makeText(fivedayweather.this, "Error!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(WeatherModel weather) {
                //assign value from the from volley callbackfunction responsed object to the fields
                min1.setText(weather.getMin_1());
                max1.setText(weather.getMax_1());
                min2.setText(weather.getMin_2());
                max2.setText(weather.getMax_2());
                min3.setText(weather.getMin_3());
                max3.setText(weather.getMax_3());
                min4.setText(weather.getMin_4());
                max4.setText(weather.getMax_4());
                min5.setText(weather.getMin_5());
                max5.setText(weather.getMax_5());


                //assign each id for the picture of each days and set image for those id
                int id2 = getResources().getIdentifier(weather.getIm2(),"drawable",getPackageName());
                i2.setImageResource(id2);

                int id3 = getResources().getIdentifier(weather.getIm3(),"drawable",getPackageName());
                i3.setImageResource(id3);

                int id4 = getResources().getIdentifier(weather.getIm4(),"drawable",getPackageName());
                i4.setImageResource(id4);

                int id5 = getResources().getIdentifier(weather.getIm5(),"drawable",getPackageName());
                i5.setImageResource(id5);

                int id1 = getResources().getIdentifier(weather.getIm1(),"drawable",getPackageName());
                i1.setImageResource(id1);
            }
        });

    }


}