package com.knd.common.bean;

import java.io.Serializable;
import java.util.List;

public class GetOrderInfoRequest implements Serializable {
    //用户id
    private String userId;
    //订单类型 订单类型只能是1vip购买2商品购买3课程购买
    private String orderType;
    //
    private String orderSource = "1";

    List<GoodsRequest> goodsRequestList;
    //备注
    private String remarks;
    //地址ID
    private String userReceiveAddressId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<GoodsRequest> getGoodsRequestList() {
        return goodsRequestList;
    }

    public void setGoodsRequestList(List<GoodsRequest> goodsRequestList) {
        this.goodsRequestList = goodsRequestList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getUserReceiveAddressId() {
        return userReceiveAddressId;
    }

    public void setUserReceiveAddressId(String userReceiveAddressId) {
        this.userReceiveAddressId = userReceiveAddressId;
    }
}