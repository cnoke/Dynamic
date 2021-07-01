package com.knd.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class TbOrder implements Serializable {

    //会员ID
    private String userId;

    //总金额
    private BigDecimal amount;

    //支付方式：1微信,2支付宝
    private String paymentType;

    //主题描述
    private String description;

    //外部订单号
    private String outOrderNo;

    //订单号
    private String orderNo;

    //状态：1未付款,2待发货,3已发货,4已完成，5已取消,6已退款
    private int status;

    //剩余支付时间单位秒
    private long remainingTime;

    //ali支付二维码
    private String aliQrCode;

    //wx支付二维码
    private String wxQrCode;

    //商品类型：1会员购买,2商品购买
    private String goodsType;

    //购买商品Id
    private String goodsId;

    //购买数量
    private String quantity;

    //收件地址id
    private String userReceiveAddressId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getAliQrCode() {
        return aliQrCode;
    }

    public void setAliQrCode(String aliQrCode) {
        this.aliQrCode = aliQrCode;
    }

    public String getWxQrCode() {
        return wxQrCode;
    }

    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUserReceiveAddressId() {
        return userReceiveAddressId;
    }

    public void setUserReceiveAddressId(String userReceiveAddressId) {
        this.userReceiveAddressId = userReceiveAddressId;
    }

    public void setOrderBean(OrderBean item) {
        if(item == null){
            return;
        }
        setAmount(item.getAmount());
        setPaymentType(item.getPaymentType());
        setDescription(item.getDescription());
        setOutOrderNo(item.getOutOrderNo());
        setOrderNo(item.getOrderNo());
        setStatus(item.getStatus());
        setRemainingTime(item.getRemainingTime());
        setAliQrCode(item.getAliQrCode());
        setWxQrCode(item.getWxQrCode());
//        setGoodsType(item.getGoodsType());
//        setGoodsId(item.getGoodsId());
//        setQuantity(item.getQuantity());
        if(item.getUserReceiveAddress() != null){
            setUserReceiveAddressId(item.getUserReceiveAddress().getId());
        }
    }
}
