package com.knd.common.bean;

import com.knd.common.route.Param;

import java.io.Serializable;

/**
 * 动态页面基类
 */
public class DynamicBean implements Serializable {

    /**
     * key唯一标识
     */
    private String keyValue;
    /**
     * 图片URL
     */
    private String imageUrl;
    /**
     * 图片URL储存ID
     */
    private String imageUrlId;
    /**
     * 背景图片URL
     */
    private String backgroundUrl;
    /**
     * 跳转url
     */
    private String skipUrl;
    /**
     * 查询URL
     */
    private String searchUrl;
    /**
     * 排序
     */
    private String sort;
    /**
     * 扩展数据
     */
    private Object extend;
    /**
     * 路由跳转传参
     */
    private Param param = new Param();

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(String imageUrlId) {
        this.imageUrlId = imageUrlId;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public void setDynamicBean(DynamicBean bean){
        if(bean == null){
            return;
        }
        keyValue = bean.getKeyValue();
        imageUrl = bean.getImageUrl();
        imageUrlId = bean.getImageUrlId();
        backgroundUrl = bean.getBackgroundUrl();
        skipUrl = bean.getSkipUrl();
        searchUrl = bean.getSearchUrl();
        sort = bean.getSort();
        param = bean.getParam();
        extend = bean.getExtend();
    }
}
