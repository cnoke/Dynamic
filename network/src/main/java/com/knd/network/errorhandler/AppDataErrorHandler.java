package com.knd.network.errorhandler;

import com.knd.common.key.ResponeCode;
import com.knd.network.BaseResponse;

import io.reactivex.functions.Function;


/**
 * HandleFuc处理以下网络错误：
 * 1、应用数据的错误会抛RuntimeException；
 */
public class AppDataErrorHandler implements Function<BaseResponse,BaseResponse> {
    @Override
    public BaseResponse apply(BaseResponse response) throws Exception {
        //response中code码不会0 出现错误
        if (response != null && !ResponeCode.SUCCESS.equals(response.code))
            throw new CustomeServerException((response.message != null ? response.message : ""),response.code);
        return response;
    }
}