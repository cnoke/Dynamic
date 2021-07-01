package com.knd.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class GoodsRequest implements Serializable {
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 购买数量
     */
    private String quantity;
    /**
     * 金额
     */
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}