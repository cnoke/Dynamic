package com.knd.dynamicpage.api;

import com.knd.common.bean.CommitEquipmentReportDTO;
import com.knd.common.bean.Course;
import com.knd.common.bean.FilterCourseListDTO;
import com.knd.common.bean.PageBean;
import com.knd.common.bean.RecommendCourse;
import com.knd.common.bean.SeriesCourse;
import com.knd.common.bean.TypeTrainPlanDto;
import com.knd.dynamicpage.dto.RecommendTrainProgramDto;
import com.knd.network.DataResponse;
import com.knd.network.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DynamicApi {

    @GET("login/getPageMessage")
    Observable<DataResponse<PageBean>> getPageMessage(@Query("key")String key);

    @GET("user/getUserRecommendCourse")
    Observable<ListResponse<RecommendCourse>> getUserRecommendCourse(@Query("currentPage")int currentPage);

    @GET("user/getUserTrainCourseList")
    Observable<ListResponse<RecommendCourse>> getUserTrainCourseList(@Query("currentPage")int currentPage);

    @GET("user/getUserRecommendTrain")
    Observable<ListResponse<TypeTrainPlanDto>> getUserRecommendTrain(@Query("currentPage")int currentPage);

    @GET("user/getUserSeriesCourseList")
    Observable<ListResponse<SeriesCourse>> getUserSeriesCourseList(@Query("currentPage")int currentPage);

    @POST("train/commitEquipmentReportInfo")
    Observable<DataResponse<String>> commitEquipmentReportInfo(@Body CommitEquipmentReportDTO param);

    @POST("train/getFilterCourseList")
    Observable<ListResponse<Course>> getFilterCourseList(@Body FilterCourseListDTO param);
}
