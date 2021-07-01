package com.knd.dynamicpage.inter;

import com.knd.common.activity.mvp.IBaseModel;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;

public interface IDynamicBaseModel extends IBaseModel {

    void getInitDate(String pageId);

    void getUserRecommendTrain(String currentPage, BaseDelegateAdapter adapter);

    void getRecommendCourse(String currentPage, BaseDelegateAdapter adapter);

    void getUserTrainCourseList(String currentPage, BaseDelegateAdapter adapter);

    void getUserSeriesCourseList(String currentPage, BaseDelegateAdapter adapter);

    void getFilterCourseList(String currentPage, BaseDelegateAdapter adapter);
}
