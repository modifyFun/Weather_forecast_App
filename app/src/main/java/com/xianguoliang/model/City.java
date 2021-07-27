package com.xianguoliang.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class City implements Serializable, Parcelable {
    private String name;
    private String id;
    private String lat;
    private String lon;
    private String adm2;
    private String adm1;
    private String country;
    private String tz;
    private String utcOffset;
    private String isDst;
    private String type;
    private String rank;
    private String fxLink;
    private boolean isExist;

    public City(){}
    protected City(Parcel in) {
        name = in.readString();
        id = in.readString();
        lat = in.readString();
        lon = in.readString();
        adm2 = in.readString();
        adm1 = in.readString();
        country = in.readString();
        tz = in.readString();
        utcOffset = in.readString();
        isDst = in.readString();
        type = in.readString();
        rank = in.readString();
        fxLink = in.readString();
        isExist = in.readByte() != 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public void setIsDst(String isDst) {
        this.isDst = isDst;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getAdm2() {
        return adm2;
    }

    public String getAdm1() {
        return adm1;
    }

    public String getCountry() {
        return country;
    }

    public String getTz() {
        return tz;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public String getIsDst() {
        return isDst;
    }

    public String getType() {
        return type;
    }

    public String getRank() {
        return rank;
    }

    public String getFxLink() {
        return fxLink;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", adm2='" + adm2 + '\'' +
                ", adm1='" + adm1 + '\'' +
                ", country='" + country + '\'' +
                ", tz='" + tz + '\'' +
                ", utcOffset='" + utcOffset + '\'' +
                ", isDst='" + isDst + '\'' +
                ", type='" + type + '\'' +
                ", rank='" + rank + '\'' +
                ", fxLink='" + fxLink + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(adm2);
        dest.writeString(adm1);
        dest.writeString(country);
        dest.writeString(tz);
        dest.writeString(utcOffset);
        dest.writeString(isDst);
        dest.writeString(type);
        dest.writeString(rank);
        dest.writeString(fxLink);
        dest.writeByte((byte) (isExist ? 1 : 0));
    }


//    public static final Parcelable.Creator<City> CREATOR = new Creator(){
//
//        @Override
//        public Gate createFromParcel(Parcel source) {
//            // TODO Auto-generated method stub
//            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
//            Gate p = new Gate();
//            p.setId(source.readString());
//            p.setName(source.readString());
//            return p;
//        }
//
//        @Override
//        public Gate[] newArray(int size) {
//            // TODO Auto-generated method stub
//            return new Gate[size];
//        }
//    };
}
