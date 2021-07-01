package com.knd.dynamicpage.model;

import android.util.Log;

import com.knd.base.util.SpUtils;
import com.knd.common.activity.mvp.BaseMvpModel;
import com.knd.common.bean.Course;
import com.knd.common.bean.ElementBean;
import com.knd.common.bean.FilterCourseListDTO;
import com.knd.common.bean.FloorBean;
import com.knd.common.bean.PageBean;
import com.knd.common.bean.RecommendCourse;
import com.knd.common.bean.SeriesCourse;
import com.knd.common.bean.TypeTrainPlanDto;
import com.knd.common.key.Flag;
import com.knd.common.route.Param;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicannotations.Key;
import com.knd.dynamicannotations.Url;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.adapter.base.ElementDelegateAdapter;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.api.DynamicApiClient;
import com.knd.dynamicpage.inter.FloorFormat;
import com.knd.dynamicpage.inter.IDynamicBaseModel;
import com.knd.dynamicpage.inter.IDynamicBasePresenter;
import com.knd.network.DataResponse;
import com.knd.network.ListResponse;
import com.knd.network.subscriber.BaseObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DynamicBaseModel<T extends IDynamicBasePresenter>
        extends BaseMvpModel <T> implements IDynamicBaseModel {


    @Override
    public void getInitDate(String pageId) {
        DynamicApiClient.getInstance().getPageMessage(new BaseObserver<DataResponse<PageBean>>(this){

            @Override
            public void onNext(DataResponse<PageBean> value) {
                super.onNext(value);
//                Log.e("getPageMsg", MyGsonConverterFactory.toJson(value));
                if(value == null || value.data == null){
                    return;
                }
                getPresenter().setPage(value.data);
                if(value.data.getFloorDtoList() == null
                        || value.data.getFloorDtoList().isEmpty()){
                    return;
                }
                getPresenter().setElement(value.data.getFloorDtoList());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().netError();
                Log.e("getPageMsg", Objects.requireNonNull(e.getMessage()));
            }
        },pageId);
    }

    /**
     * 训练计划推荐
     * @param currentPage
     * @param adapter
     */
    @Override
    @Url("user/getUserRecommendTrain")
    public void getUserRecommendTrain(@Key("currentPage")String currentPage, @Key("adapter") BaseDelegateAdapter adapter) {
        int page = StringUtils.toInt(currentPage,1);
        DynamicApiClient.getInstance().getUserRecommendTrain(new BaseObserver<ListResponse<TypeTrainPlanDto>>(this){

            @Override
            public void onNext(ListResponse<TypeTrainPlanDto> value) {
                super.onNext(value);
                onListResponse(value,(elementBean, index) -> {
                    TypeTrainPlanDto dto = value.data.getRecords().get(index);
                    elementBean.setElementName(dto.getProgramName());
                    elementBean.setImageUrl(dto.getPicAttachUrl());
                    if(StringUtils.isEmpty(elementBean.getSkipUrl())){
                        elementBean.setSkipUrl("quinnoid://quinnoid.com/user_center/activity/CustomizePlanActivity");
                    }
                    Param param = elementBean.getParam();
                    param.planId = dto.getId();
                }, adapter,page);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().remove(adapter);
            }
        },page);
    }


    /**
     * 获取推荐课程
     * @param currentPage
     * @param adapter
     */
    @Override
    @Url("user/getUserRecommendCourse")
    public void getRecommendCourse(@Key("currentPage")String currentPage, @Key("adapter") BaseDelegateAdapter adapter) {
        int page = StringUtils.toInt(currentPage,1);
        DynamicApiClient.getInstance().getUserRecommendCourse(new BaseObserver<ListResponse<RecommendCourse>>(this){

            @Override
            public void onNext(ListResponse<RecommendCourse> value) {
                super.onNext(value);
                onListResponse(value,(elementBean, index) -> {
                    RecommendCourse course = value.data.getRecords().get(index);
                    elementBean.setElementName(course.getCourse());
                    if(!StringUtils.isEmpty(course.getVideoDurationMinutes())){
                        elementBean.setElementDetail(course.getVideoDurationMinutes() + "分钟");
                    }
                    elementBean.setImageUrl(course.getPicAttachUrl());
                    if(StringUtils.isEmpty(elementBean.getSkipUrl())){
                        elementBean.setSkipUrl("quinnoid://quinnoid.com/gym/activity/CourseDetailActivity");
                    }
                    Param param = elementBean.getParam();
                    param.courseId = course.getId();
                }, adapter,page);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().remove(adapter);
            }
        },page);
    }


    /**
     * 获取训练过的课程
     * @param currentPage
     * @param adapter
     */
    @Override
    @Url("user/getUserTrainCourseList")
    public void getUserTrainCourseList(@Key("currentPage")String currentPage, @Key("adapter") BaseDelegateAdapter adapter) {
        String userId = SpUtils.getInstance().getData(Flag.FLAG_USER_ID,"").toString();
        if(StringUtils.isEmpty(userId)){
            getPresenter().remove(adapter);
            return;
        }
        int page = StringUtils.toInt(currentPage,1);
        DynamicApiClient.getInstance().getUserTrainCourseList(new BaseObserver<ListResponse<RecommendCourse>>(this){

            @Override
            public void onNext(ListResponse<RecommendCourse> value) {
                super.onNext(value);
                onListResponse(value,(elementBean, index) -> {
                    RecommendCourse course = value.data.getRecords().get(index);
                    elementBean.setElementName(course.getCourse());
                    if(!StringUtils.isEmpty(course.getVideoDurationMinutes())){
                        elementBean.setElementDetail(course.getVideoDurationMinutes() + "分钟");
                    }
                    elementBean.setImageUrl(course.getPicAttachUrl());
                    if(StringUtils.isEmpty(elementBean.getSkipUrl())){
                        elementBean.setSkipUrl("quinnoid://quinnoid.com/gym/activity/CourseDetailActivity");
                    }
                    Param param = elementBean.getParam();
                    param.courseId = course.getId();
                }, adapter,page);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().remove(adapter);
            }
        },page);
    }


    /**
     * 获取推荐系列课程
     * @param currentPage
     * @param adapter
     */
    @Override
    @Url("user/getUserSeriesCourseList")
    public void getUserSeriesCourseList(@Key("currentPage")String currentPage, @Key("adapter") BaseDelegateAdapter adapter) {
        int page = StringUtils.toInt(currentPage,1);
        DynamicApiClient.getInstance().getUserSeriesCourseList(new BaseObserver<ListResponse<SeriesCourse>>(this){

            @Override
            public void onNext(ListResponse<SeriesCourse> value) {
                super.onNext(value);
                onListResponse(value,(elementBean, index) -> {
                    SeriesCourse course = value.data.getRecords().get(index);
                    elementBean.setElementName(course.getName());
                    elementBean.setElementDetail(course.getSynopsis());
                    elementBean.setImageUrl(course.getPicAttachUrl());
                    if(StringUtils.isEmpty(elementBean.getSkipUrl())){
                        elementBean.setSkipUrl("quinnoid://quinnoid.com/gym/activity/SeriesCourseDetailActivity");
                    }
                    Param param = elementBean.getParam();
                    param.seriesCourseId = course.getId();
                }, adapter,page);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().remove(adapter);
            }
        },page);
    }


    /**
     * 筛选课程
     * @param currentPage
     * @param adapter
     */
    @Override
    @Url("train/getFilterCourseList")
    public void getFilterCourseList(@Key("currentPage")String currentPage, @Key("adapter") BaseDelegateAdapter adapter) {
        int page = StringUtils.toInt(currentPage,1);
        FilterCourseListDTO dto = new FilterCourseListDTO(SpUtils.getInstance().getData(Flag.FLAG_USER_ID,"").toString(), page,12,null,null,null);
        DynamicApiClient.getInstance().getFilterCourseList(new BaseObserver<ListResponse<Course>>(this){

            @Override
            public void onNext(ListResponse<Course> value) {
                super.onNext(value);
                onListResponse(value,(elementBean, index) -> {
                    Course course = value.data.getRecords().get(index);
                    elementBean.setElementName(course.getCourse());
                    StringBuilder sb = new StringBuilder();
                    if(course.getTypeList() != null
                            && !course.getTargetList().isEmpty()){
                        sb.append(course.getTargetList().get(0).getName());
                        sb.append("·");
                    }
                    if(!StringUtils.isEmpty(course.getVideoDurationMinutes())){
                        sb.append(course.getVideoDurationMinutes());
                        sb.append("分");
                        sb.append("·");
                    }
                    if(course.getPartList() != null
                            && !course.getPartList().isEmpty()){
                        sb.append(course.getPartList().get(0).getName());
                    }

                    if(sb.toString().endsWith("·")){
                        sb.deleteCharAt(sb.length() -1);
                    }

                    elementBean.setElementDetail(sb.toString());
                    elementBean.setImageUrl(course.getPicAttachUrl());
                    if(StringUtils.isEmpty(elementBean.getSkipUrl())){
                        elementBean.setSkipUrl("quinnoid://quinnoid.com/gym/activity/CourseDetailActivity");
                    }
                    Param param = elementBean.getParam();
                    param.courseId = course.getCourseId();
                }, adapter,page);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getPresenter().remove(adapter);
            }
        },dto);
    }

    /**
     * 分页类型数据处理
     * @param value 网络返回值
     * @param floorFormat 数据格式转换回调
     * @param adapter 界面adapter
     * @param currentPage 当前页码
     * @param <M> 返回数据泛型
     */
    private <M> void onListResponse(ListResponse<M> value ,FloorFormat floorFormat,BaseDelegateAdapter adapter,int currentPage){
        if(value.data != null && value.data.getRecords() != null
                && !value.data.getRecords().isEmpty()){

            List<M> netList = value.data.getRecords();
            int netSize = netList.size();

            getBean(floorFormat , adapter, netSize,currentPage,value.data.getTotal(),value.data.getPages());

        }else{
            if(adapter.isLoadMore() && currentPage != 1){
                getPresenter().loadMoreFinish();
                if(currentPage == value.data.getPages()){
                    getPresenter().noMoreData();
                }
            }else{
                getPresenter().remove(adapter);
            }
        }
    }

    /**
     * 网络数据转化成界面数据
     * @param floorFormat 数据格式转换
     * @param adapter 界面adapter
     * @param dataLength 数据长度
     */
    protected void getBean(FloorFormat floorFormat , BaseDelegateAdapter adapter,int dataLength){
        if(adapter instanceof FloorDelegateAdapter){
            getFloorBean(floorFormat,(FloorDelegateAdapter)adapter,dataLength,0,0,0);
        }else if(adapter instanceof ElementDelegateAdapter){
            getElementBean(floorFormat,(ElementDelegateAdapter)adapter,dataLength,0,0,0);
        }
    }

    /**
     * 分页查询
     * 网络数据转化成界面数据
     * @param floorFormat 数据格式转换
     * @param adapter 界面adapter
     * @param dataLength 数据长度
     * @param currentPage 当前页码
     * @param total 总数
     * @param pages 总页数
     */
    protected void getBean(FloorFormat floorFormat , BaseDelegateAdapter adapter,int dataLength,int currentPage,int total , int pages){
        if(adapter instanceof FloorDelegateAdapter){
            getFloorBean(floorFormat,(FloorDelegateAdapter)adapter,dataLength,currentPage,total,pages);
        }else if(adapter instanceof ElementDelegateAdapter){
            getElementBean(floorFormat,(ElementDelegateAdapter)adapter,dataLength,currentPage,total,pages);
        }
    }

    /**
     * 楼层adapter赋值
     * @param floorFormat 数据格式转换回调
     * @param adapter 元素Adapter
     * @param dataLength 数据长度
     * @param currentPage 当前页码
     * @param total 总数
     * @param pages 总页数
     */
    protected void getFloorBean(FloorFormat floorFormat , FloorDelegateAdapter adapter,int dataLength,int currentPage,int total , int pages){

        FloorBean bean =  adapter.getData().isEmpty() ? new FloorBean() : adapter.getItem(0);

        List<ElementBean> list = new ArrayList<>();

        //根据adapter中count删减网络数据 count -1不删减
        int adapterCount = adapter.getCount();
        int needSize = adapterCount == -1 ? dataLength : Math.min(dataLength,adapterCount);

        //服务端配置了元素
        List<ElementBean> netList = null;
        int size = 0;
        if(bean.getElementDtoList() != null
                && !bean.getElementDtoList().isEmpty()){
            netList = bean.getElementDtoList();
            size = netList.size();
        }
        ElementBean elementBean;
        for(int i = 0 ; i < needSize ; i++){
            elementBean = new ElementBean();
            if(netList != null){
                //服务端配置了元素，将元素属性取出展示
                elementBean.setElement(netList.get(i < size ? i : 0));
            }
            if(floorFormat != null && i < dataLength){
                //网络数据转换界面数据
                floorFormat.result(elementBean,i);
            }
            list.add(elementBean);
        }

        bean.setElementDtoList(list);

        //数据加载到界面
        if(adapter.isLoadMore()){
            //支持加载更多
            if(total == 0){
                adapter.getData().clear();
            }else{
                if(currentPage != 1){
                    adapter.addData(bean);
                    getPresenter().loadMoreFinish();
                }else{
                    adapter.resetData(bean);
                }
                if(currentPage == pages){
                    getPresenter().noMoreData();
                }
            }
        }else{
            adapter.resetData(bean);
        }

        getPresenter().notifyItemChanged(adapter);
    }

    /**
     * 元素Adapter赋值
     * @param floorFormat 数据格式转换回调
     * @param adapter 元素Adapter
     * @param dataLength 数据长度
     * @param currentPage 当前页码
     * @param total 总数
     * @param pages 总页数
     */
    protected void getElementBean(FloorFormat floorFormat , ElementDelegateAdapter adapter, int dataLength,int currentPage,int total , int pages){

        List<ElementBean> floorList = adapter.getData();
        List<ElementBean> list = new ArrayList<>();

        //根据adapter中count删减网络数据 count -1不删减
        int adapterCount = adapter.getCount();
        int needSize = adapterCount == -1 ? dataLength : Math.min(dataLength,adapterCount);

        //服务端配置了元素
        List<ElementBean> netList = null;
        int size = 0;
        if(floorList != null
                && !floorList.isEmpty()){
            netList = floorList;
            size = netList.size();
        }
        ElementBean elementBean;
        for(int i = 0 ; i < needSize ; i++){
            elementBean = new ElementBean();
            if(netList != null){
                //服务端配置了元素，将元素属性取出展示
                elementBean.setElement(netList.get(i < size ? i : 0));
            }
            if(floorFormat != null && i < dataLength){
                //网络数据转换界面数据
                floorFormat.result(elementBean,i);
            }
            list.add(elementBean);
        }


        //数据加载到界面
        if(adapter.isLoadMore()){
            //支持加载更多
            if(total == 0){
                adapter.getData().clear();
            }else{
                if(currentPage != 1){
                    adapter.addList(list);
                    getPresenter().loadMoreFinish();
                }else{
                    adapter.setList(list);
                }
                if(currentPage == pages){
                    getPresenter().noMoreData();
                }
            }
        }else{
            adapter.setList(list);
        }

        getPresenter().notifyItemChanged(adapter);
    }

}
