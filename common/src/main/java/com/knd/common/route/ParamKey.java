package com.knd.common.route;

import com.knd.dynamicannotations.Comments;
import com.knd.dynamicannotations.ParamKeys;

@ParamKeys
public class ParamKey {

    /**
     * @Comments 可写可不写。用来在Param中生成注释
     * 方便查看
     */
    @Comments("页面查询key")
    public static final String PAGE_ID = "pageId";

    @Comments("课程ID")
    public static final String COURSE_ID = "courseId";

    @Comments("系列课程ID")
    public static final String SERIES_COURSE_ID = "seriesCourseId";

    @Comments("计划ID")
    public static final String PLAN_ID = "planId";

    @Comments("自由训练ID")
    public static final String ACTION_ID = "actionId";

    @Comments("自由训练结果ID")
    public static final String ACTION_RESULT_ID = "actionResultId";

    @Comments("自由训练结果ID")
    public static final String ACTION_ARRAY_RESULT_ID = "actionArrayResultId";

    @Comments("课程训练结果ID")
    public static final String COURSE_RESULT_ID = "courseResultId";

    @Comments("商品ID")
    public static final String GOODS_ID = "goodsId";

    @Comments("页面查询key")
    public static final String PART_NAME = "partName";

    @Comments("ID")
    public static final String ID = "id";

    @Comments("url")
    public static final String URL = "url";
}
