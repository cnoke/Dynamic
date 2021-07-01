package com.knd.common.bean;

import java.util.List;

public class FilterCourseListDTO {
    private String userId;
    private int currentPage;
    private int pageSize;
    private int isPay = 0;
    private List<CommonBean> typeList;
    private List<CommonBean> targetList;
    private List<CommonBean> partList;

    public FilterCourseListDTO(String userId, int currentPage,int pageSize ,List<CommonBean> typeList, List<CommonBean> targetList, List<CommonBean> partList) {
        this.currentPage=currentPage;
        this.pageSize = pageSize;
        this.userId=userId;
        this.typeList = typeList;
        this.targetList = targetList;
        this.partList = partList;
    }
}
