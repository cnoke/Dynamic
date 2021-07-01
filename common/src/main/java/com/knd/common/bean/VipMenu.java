package com.knd.common.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员套餐表
 * @date on 2021/1/21
 * @author huanghui
 * @title
 * @describe
 */
public class VipMenu implements Serializable {

    private String id;
    //会员套餐名称 0普通会员1个人会员2家庭主会员3家庭副会员
    private String vipName;

    //会员套餐名称 0普通会员1个人会员2家庭主会员3家庭副会员
    private int vipType;

    //价格
    private BigDecimal price;

    //0包年1季度2包月
    private String packageType;

    //原价
    private BigDecimal costPrice;

    //描述
    private String description;

    //排序
    private String sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
