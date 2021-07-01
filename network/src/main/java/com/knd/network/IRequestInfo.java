package com.knd.network;

import java.util.HashMap;

/**
 * 需要附加的请求信息
 */
public interface IRequestInfo {
    HashMap<String,String> getRequestHead();
}
