package com.example.weatherapiapp;

public class WeatherModel {

    //Current weather instances
    private String time;
    private String temp;
    private String minT;
    private String maxT;
    private String image;
    private String win;
    private String visible;
    private String cloud;
    private int id;
    private String weather;

    //Instances of weather in 5 days
    private int id_1,id_2,id_3,id_4,id_5;
    private String min_1,min_2,min_3,min_4,min_5;
    private String max_1,max_2,max_3,max_4,max_5;
    private String im1, im2, im3, im4, im5;


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp + "°C";
    }

    public void setTemp(String temp) {
        this.temp = temp  ;
    }

    public String getMinT() {
        return "Min: " + minT + "°C";
    }

    public void setMinT(String minT) {
        this.minT = minT;
    }

    public String getMaxT() {
        return "Max: " + maxT + "°C";
    }

    public void setMaxT(String maxT) {
        this.maxT = maxT;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWin() {
        return win + " m/s";
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getVisible() {
        return visible + " km";
    }

    public void setVisible(String rain) {
        this.visible = rain;
    }

    public String getCloud() {
        return cloud + " %";
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public int getId_1() {
        return id_1;
    }

    public void setId_1(int id_1) {
        this.id_1 = id_1;
    }

    public String getMin_1() {
        return  min_1 + "°C";
    }

    public void setMin_1(String min_1) {
        this.min_1 = min_1;
    }

    public String getMax_1() {
        return max_1 + "°C";
    }

    public void setMax_1(String max_1) {
        this.max_1 = max_1;
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(String im1) {
        this.im1 = im1;
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(String im2) {
        this.im2 = im2;
    }

    public String getIm3() {
        return im3;
    }

    public void setIm3(String im3) {
        this.im3 = im3;
    }

    public String getIm4() {
        return im4;
    }

    public void setIm4(String im4) {
        this.im4 = im4;
    }

    public String getIm5() {
        return im5;
    }

    public void setIm5(String im5) {
        this.im5 = im5;
    }

    public int getId_2() {
        return id_2;
    }

    public void setId_2(int id_2) {
        this.id_2 = id_2;
    }

    public int getId_3() {
        return id_3;
    }

    public void setId_3(int id_3) {
        this.id_3 = id_3;
    }

    public int getId_4() {
        return id_4;
    }

    public void setId_4(int id_4) {
        this.id_4 = id_4;
    }

    public int getId_5() {
        return id_5;
    }

    public void setId_5(int id_5) {
        this.id_5 = id_5;
    }

    public String getMin_2() {
        return min_2 + "°C";
    }

    public void setMin_2(String min_2) {
        this.min_2 = min_2;
    }

    public String getMin_3() {
        return min_3 + "°C";
    }

    public void setMin_3(String min_3) {
        this.min_3 = min_3;
    }

    public String getMin_4() {
        return min_4 + "°C";
    }

    public void setMin_4(String min_4) {
        this.min_4 = min_4;
    }

    public String getMin_5() {
        return min_5 + "°C";
    }

    public void setMin_5(String min_5) {
        this.min_5 = min_5;
    }

    public String getMax_2() {
        return max_2 + "°C";
    }

    public void setMax_2(String max_2) {
        this.max_2 = max_2;
    }

    public String getMax_3() {
        return max_3 + "°C";
    }

    public void setMax_3(String max_3) {
        this.max_3 = max_3;
    }

    public String getMax_4() {
        return max_4 + "°C";
    }

    public void setMax_4(String max_4) {
        this.max_4 = max_4;
    }

    public String getMax_5() {
        return max_5 + "°C";
    }

    public void setMax_5(String max_5) {
        this.max_5 = max_5;
    }

    //Function to set String for each ID, the returned string data will match with the image which located in drawable file
    public String SetupImage_weather(int id){
        if (id >= 210 && id <= 221){
            return "thunderstorm";
        }
        if ((id >= 200 && id <= 202) || (id >= 230 && id <= 232) ){
            return "thunderstormrain";
        }
        if (id >= 300 && id <= 321){
            return "showerrain";
        }
        if (id == 500 || id == 501 ){
            return "lightrain";
        }
        if (id >= 502 && id <= 504){
            return "heavyrain";
        }
        if (id == 511){
            return "snowrain";
        }
        if (id >= 520 && id <= 531){
            return "showerrain";
        }
        if (id >= 600 && id <= 622){
            return "snow";
        }
        if (id >= 701 && id <= 781){
            return "mist";
        }
        if (id >= 801 && id <= 804){
            return "cloud";
        }
        if (id == 800){
            return "clear";
        }
        return "nothing";
    }
}

