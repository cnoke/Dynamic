package com.knd.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemBean implements Serializable {

    private String id;

    private String goodsName;

    /*
     * 商品介绍
     */
    private String goodsDesc;

    /**
     * 商品图片附件id
     */
    private String coverUrl;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private String quantity;

    /**
     * 主题描述
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}