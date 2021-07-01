package com.knd.dynamicpage.inter;

import com.knd.common.activity.mvp.IBasePresenter;
import com.knd.common.bean.FloorBean;
import com.knd.common.bean.PageBean;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;

import java.util.List;

public interface IDynamicBasePresenter extends IBasePresenter {

    void init(String pageId);

    void setElement(List<FloorBean> elementDtoList);

    void remove(BaseDelegateAdapter adapter);

    void notifyItemChanged(BaseDelegateAdapter adapter);

    void setPage(PageBean data);

    void loadMoreFinish();

    void noMoreData();

    void netError();
}
