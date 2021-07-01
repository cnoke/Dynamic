package com.knd.dynamicpage.model;

import com.knd.dynamicannotations.Model;
import com.knd.dynamicpage.inter.DynamicHomeContract;

@Model
public class DynamicHomeModel extends DynamicBaseModel<DynamicHomeContract.IPresenter>
        implements DynamicHomeContract.IModel{


//    @Override
//    @Url("user/getUserRecommendCourse")
//    public void getRecommendCourse(@Key("adapter")BaseDelegateAdapter adapter) {
//        DynamicApiClient.getInstance().getUserRecommendCourse(new BaseObserver<DataResponse<RecommendCoursesDto>>(this){
//            @Override
//            public void onNext(DataResponse<RecommendCoursesDto> value) {
//                super.onNext(value);
//                List<RecommendCourse> list = value.data.getRecords();
//                for (int i=0;i<3;i++){
//                    if (list.size()>i){
//                        ElementBean elementBean = new ElementBean();
//                        elementBean.setImageUrl("");
//                    }
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        });
//    }
}
