package com.xianguoliang.model;

import com.google.gson.annotations.SerializedName;

public class Result<T> {
    public int code;
    public String updateTime;
    public String fxLink;

    @SerializedName(value = "daily",alternate = {"now","location"})
    public T data;
}
