package com.knd.common.bean;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderBean implements Serializable {
    private String id;

    private List<OrderItemBean> userOrderItemDtoList;

    /**
     * 总金额
     */
    private BigDecimal amount;

    /**
     * 支付方式：1微信,2支付宝
     */
    private String paymentType;

    /**
     * 1会员购买 2 商品购买 3 课程购买
     */
    private int orderType;

    /**
     * 主题描述
     */
    private String description;


    /**
     * 外部订单号
     */
    private String outOrderNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 1未付款,2待发货,3已发货,4已完成，5已取消,6退款中,7已退款
     */
    private int status;

    /**
     * 剩余支付时间
     */
    private long remainingTime;

    /**
     * ali支付二维码
     */
    private String aliQrCode;

    /**
     * wx支付二维码
     */
    private String wxQrCode;

    /**
     * 收件地址详情
     */
    private AddressEntity userReceiveAddress;

    /**
     * 下单人
     */
    private String createBy;

    /**
     * 创建订单时间
     * yyyy-MM-dd HH:mm:ss
     */
    private String createDate;

    /**
     * 支付时间
     * yyyy-MM-dd HH:mm:ss
     */
    private String paymentTime;


    /**
     * 发货时间
     * yyyy-MM-dd HH:mm:ss
     */
    private String deliveryTime;

    /**
     * 确认收货时间
     */
    private String receiveTime;

    /**
     * 确认收货状态[0->未确认；1->已确认]
     */
    private String confirmStatus;
    /**
     * 确认收货码
     */
    private String receivingCode;
    /**
     * 退款时间
     */
    private String refundTime;
    /**
     * 外部退款单号
     */
    private String outRefundNo;
    /**
     * 退款单号
     */
    private String refundNo;
    /**
     * 预期送达时间
     */
    private String deliveryDate;
    /**
     * 订单取消时间
     */
    private String cancelTime;

    public void setTbOrder(@Nullable TbOrder tbOrder){
        if(tbOrder == null){
            return;
        }
        amount = tbOrder.getAmount();
        paymentType = tbOrder.getPaymentType();
        description = tbOrder.getDescription();
        orderNo = tbOrder.getOrderNo();
        outOrderNo = tbOrder.getOutOrderNo();
        aliQrCode = tbOrder.getAliQrCode();
        wxQrCode = tbOrder.getWxQrCode();
        status = tbOrder.getStatus();
        remainingTime = tbOrder.getRemainingTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemBean> getUserOrderItemDtoList() {
        return userOrderItemDtoList;
    }

    public void setUserOrderItemDtoList(List<OrderItemBean> userOrderItemDtoList) {
        this.userOrderItemDtoList = userOrderItemDtoList;
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
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

    public AddressEntity getUserReceiveAddress() {
        return userReceiveAddress;
    }

    public void setUserReceiveAddress(AddressEntity userReceiveAddress) {
        this.userReceiveAddress = userReceiveAddress;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getReceivingCode() {
        return receivingCode;
    }

    public void setReceivingCode(String receivingCode) {
        this.receivingCode = receivingCode;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }
}
