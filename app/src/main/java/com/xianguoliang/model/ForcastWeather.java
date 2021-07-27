package com.xianguoliang.model;

import java.io.Serializable;

public class ForcastWeather implements Serializable {
    private String fxDate; //预报日期
    private String sunrise;  //日出时间
    private String sunset; //日落时间
    private String moonrise; //月升时间
    private String moonset; //月落时间
    private String moonPhase; //月相名称
    private int tempMax; //预报当天最高温度
    private int tempMin;  //预报当天最低温度
    private String iconDay;  //预报白天天气状况的图标代码
    private String textDay;  //预报白天天气状况文字描述
    private String iconNight;  //预报夜间天气状况的图标代码
    private String textNight;  //预报晚间天气状况文字描述
    private String wind360Day;  //预报白天风向360角度
    private String windDirDay;  //预报白天风向
    private String windScaleDay;  //预报白天风力等级
    private String windSpeedDay;  //预报白天风速，公里/小时
    private String wind360Night;  //预报夜间风向360角度
    private String windDirNight; //预报夜间当天风向
    private String windScaleNight;  //预报夜间风力等级
    private String windSpeedNight;  //预报夜间风速，公里/小时
    private String humidity;  //相对湿度，百分比数值
    private String precip;   //预报当天总降水量，默认单位：毫米
    private String pressure;  //大气压强，默认单位：百帕
    private String vis;  //能见度，默认单位：公里
    private String cloud;  //云量，百分比数值
    private String uvIndex;  //紫外线强度指数

    public String getFxDate() {
        return fxDate;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public String getMoonset() {
        return moonset;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public int getTempMax() {
        return tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public String getIconDay() {
        return iconDay;
    }

    public String getTextDay() {
        return textDay;
    }

    public String getIconNight() {
        return iconNight;
    }

    public String getTextNight() {
        return textNight;
    }

    public String getWind360Day() {
        return wind360Day;
    }

    public String getWindDirDay() {
        return windDirDay;
    }

    public String getWindScaleDay() {
        return windScaleDay;
    }

    public String getWindSpeedDay() {
        return windSpeedDay;
    }

    public String getWind360Night() {
        return wind360Night;
    }

    public String getWindDirNight() {
        return windDirNight;
    }

    public String getWindScaleNight() {
        return windScaleNight;
    }

    public String getWindSpeedNight() {
        return windSpeedNight;
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

    public String getUvIndex() {
        return uvIndex;
    }

    @Override
    public String toString() {
        return "ForcastWeather{" +
                "fxDate='" + fxDate + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", moonrise='" + moonrise + '\'' +
                ", moonset='" + moonset + '\'' +
                ", moonPhase='" + moonPhase + '\'' +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", iconDay='" + iconDay + '\'' +
                ", textDay='" + textDay + '\'' +
                ", iconNight='" + iconNight + '\'' +
                ", textNight='" + textNight + '\'' +
                ", wind360Day='" + wind360Day + '\'' +
                ", windDirDay='" + windDirDay + '\'' +
                ", windScaleDay='" + windScaleDay + '\'' +
                ", windSpeedDay='" + windSpeedDay + '\'' +
                ", wind360Night='" + wind360Night + '\'' +
                ", windDirNight='" + windDirNight + '\'' +
                ", windScaleNight='" + windScaleNight + '\'' +
                ", windSpeedNight='" + windSpeedNight + '\'' +
                ", humidity='" + humidity + '\'' +
                ", precip='" + precip + '\'' +
                ", pressure='" + pressure + '\'' +
                ", vis='" + vis + '\'' +
                ", cloud='" + cloud + '\'' +
                ", uvIndex='" + uvIndex + '\'' +
                '}';
    }
}
