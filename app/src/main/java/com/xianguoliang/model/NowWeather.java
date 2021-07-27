package com.xianguoliang.model;

import java.io.Serializable;

public class NowWeather implements Serializable {
    private String obsTime;  //数据观测时间
    private String temp;  //温度，默认单位：摄氏度
    private String  feelsLike;  //体感温度，默认单位：摄氏度
    private String icon;    //天气状况和图标的代码
    private String text;    //天气状况的文字描述，包括阴晴雨雪等天气状态的描述
    private String wind360; //风向360角度
    private String windDir; //风向
    private String windScale;   //风力等级
    private String windSpeed;   //风速，公里/小时
    private String humidity;    //相对湿度，百分比数值
    private String precip;  //当前小时累计降水量，默认单位：毫米
    private String pressure;    //大气压强，默认单位：百帕
    private String vis;    //能见度，默认单位：公里
    private String cloud;   //云量，百分比数值
    private String dew; //露点温度

    public void setObsTime(String obsTime) {
        this.obsTime = obsTime;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWind360(String wind360) {
        this.wind360 = wind360;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }

    public String getObsTime() {
        return obsTime;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public String getWind360() {
        return wind360;
    }

    public String getWindDir() {
        return windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPrecip() {
        return precip;
    }

    public String getPressure() {
        return pressure;
    }

    public String getVis() {
        return vis;
    }

    public String getCloud() {
        return cloud;
    }

    public String getDew() {
        return dew;
    }

    @Override
    public String toString() {
        return "NowWeather{" +
                "obsTime='" + obsTime + '\'' +
                ", temp='" + temp + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", icon='" + icon + '\'' +
                ", text='" + text + '\'' +
                ", wind360='" + wind360 + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windScale='" + windScale + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", precip='" + precip + '\'' +
                ", pressure='" + pressure + '\'' +
                ", vis='" + vis + '\'' +
                ", cloud='" + cloud + '\'' +
                ", dew='" + dew + '\'' +
                '}';
    }
}
