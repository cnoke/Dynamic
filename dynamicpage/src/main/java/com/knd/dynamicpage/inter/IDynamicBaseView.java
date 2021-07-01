package com.knd.dynamicpage.inter;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.knd.base.activity.IBaseView;
import com.knd.common.bean.PageBean;

import java.util.List;

public interface IDynamicBaseView extends IBaseView {

    void setData(List<DelegateAdapter.Adapter> mAdapters);

    void setPage(PageBean data);

    void remove(int position);

    void remove(DelegateAdapter.Adapter adapter);

    void notifyItemChanged(int position);

    void goBack();

    boolean hasLoadMore();

    void loadMoreFinish();

    void noMoreData();

    void onRetryBtnClick();
}
