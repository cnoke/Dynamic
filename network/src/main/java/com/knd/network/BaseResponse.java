package com.knd.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BaseResponse {
    @Expose
    public String code;
    @Expose
    public String message;

}
