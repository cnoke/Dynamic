package com.knd.network;


public class DataResponse<T> extends BaseResponse {
    public T data;

    @Override
    public String toString() {
        return "GymResponse{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
