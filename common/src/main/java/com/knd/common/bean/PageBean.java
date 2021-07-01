package com.knd.common.bean;

import java.util.List;

/**
 * 动态页面
 */
public class PageBean extends DynamicBean{

    /**
     * 类型
     */
    private String pageType;
    /**
     * 名称
     */
    private String pageName;
    /**
     * 详情
     */
    private String pageDetail;
    private List<FloorBean> floorDtoList;

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageDetail() {
        return pageDetail;
    }

    public void setPageDetail(String pageDetail) {
        this.pageDetail = pageDetail;
    }

    public List<FloorBean> getFloorDtoList() {
        return floorDtoList;
    }

    public void setFloorDtoList(List<FloorBean> floorDtoList) {
        this.floorDtoList = floorDtoList;
    }
}
