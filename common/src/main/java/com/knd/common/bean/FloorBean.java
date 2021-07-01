package com.knd.common.bean;

import java.util.List;

/**
 * 动态页面楼层
 */
public class FloorBean extends DynamicBean{

    /**
     * 楼层类型
     */
    private String floorType;
    /**
     * 名称
     */
    private String floorName;
    /**
     * 详情
     */
    private String floorDetail;
    /**
     * 备注
     */
    private String floorNote;
    /**
     * 元素
     */
    private List<ElementBean> elementDtoList;

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getFloorDetail() {
        return floorDetail;
    }

    public void setFloorDetail(String floorDetail) {
        this.floorDetail = floorDetail;
    }

    public String getFloorNote() {
        return floorNote;
    }

    public void setFloorNote(String floorNote) {
        this.floorNote = floorNote;
    }

    public List<ElementBean> getElementDtoList() {
        return elementDtoList;
    }

    public void setElementDtoList(List<ElementBean> elementDtoList) {
        this.elementDtoList = elementDtoList;
    }
}
