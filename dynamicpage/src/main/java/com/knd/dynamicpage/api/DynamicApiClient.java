package com.knd.dynamicpage.api;

import com.knd.common.bean.CommitEquipmentReportDTO;
import com.knd.common.bean.Course;
import com.knd.common.bean.FilterCourseListDTO;
import com.knd.common.bean.PageBean;
import com.knd.common.bean.RecommendCourse;
import com.knd.common.bean.RecommendCoursesDto;
import com.knd.common.bean.SeriesCourse;
import com.knd.common.bean.TypeTrainPlanDto;
import com.knd.dynamicpage.BuildConfig;
import com.knd.dynamicpage.dto.RecommendTrainProgramDto;
import com.knd.network.ApiBase;
import com.knd.network.DataResponse;
import com.knd.network.ListResponse;
import com.knd.network.subscriber.BaseObserver;

public class DynamicApiClient extends ApiBase {

    public DynamicApiClient(String baseurl) {
        super(baseurl);
    }

    private static volatile DynamicApiClient mClient;
    private static DynamicApi api;
    public static DynamicApiClient getInstance(){

        if(mClient==null){
            synchronized (DynamicApiClient.class){
                if(mClient==null){
                    mClient=new DynamicApiClient(BuildConfig.URL_CONFIG);
                    api=mClient.mRetrofit.create(DynamicApi.class);
                }
            }
        }
        return mClient;
    }

    /**
     * login/getPageMessage
     * 查询页面
     */
    public void getPageMessage(BaseObserver<DataResponse<PageBean>> observer, String key){
        ApiSubscribe(api.getPageMessage(key),observer);
    }

    /**
     * user/getUserRecommendTrain
     * 训练计划推荐
     */
    public void getUserRecommendTrain(BaseObserver<ListResponse<TypeTrainPlanDto>> observer, int currentPage){
        ApiSubscribe(api.getUserRecommendTrain(currentPage),observer);
    }

    /**
     * user/getUserRecommendCourse
     * 获取推荐课程
     */
    public void getUserRecommendCourse(BaseObserver<ListResponse<RecommendCourse>> observer, int currentPage){
        ApiSubscribe(api.getUserRecommendCourse(currentPage),observer);
    }

    /**
     * user/getUserRecommendCourse
     * 获取训练过的课程
     */
    public void getUserTrainCourseList(BaseObserver<ListResponse<RecommendCourse>> observer, int currentPage){
        ApiSubscribe(api.getUserTrainCourseList(currentPage),observer);
    }

    /**
     * user/getUserSeriesCourseList
     * 获取推荐系列课程
     */
    public void getUserSeriesCourseList(BaseObserver<ListResponse<SeriesCourse>> observer, int currentPage){
        ApiSubscribe(api.getUserSeriesCourseList(currentPage),observer);
    }

    /**
     * train/getFilterCourseList
     * 筛选课程
     */
    public void getFilterCourseList(BaseObserver<ListResponse<Course>> observer, FilterCourseListDTO currentPage){
        ApiSubscribe(api.getFilterCourseList(currentPage),observer);
    }

    /**
     * train/commitEquipmentReportInfo
     * 上报设备信息
     */
    public void commitEquipmentReportInfo(BaseObserver<DataResponse<String>> observer, CommitEquipmentReportDTO param){
        ApiSubscribe(api.commitEquipmentReportInfo(param),observer);
    }


}
