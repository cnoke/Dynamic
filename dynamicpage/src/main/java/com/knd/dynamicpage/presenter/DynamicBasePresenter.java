package com.knd.dynamicpage.presenter;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.knd.common.activity.mvp.BasePresenter;
import com.knd.common.bean.FloorBean;
import com.knd.common.bean.PageBean;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicpage.adapter.BackAdapter;
import com.knd.dynamicpage.adapter.TitleAdapter;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.adapter.DynamicBind;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.bean.SearchBean;
import com.knd.dynamicpage.inter.IDynamicBasePresenter;
import com.knd.dynamicpage.inter.IDynamicBaseView;
import com.knd.dynamicpage.inter.OnItemClickListener;
import com.knd.dynamicpage.model.DynamicBaseModel;

import java.util.ArrayList;
import java.util.List;

public abstract class DynamicBasePresenter <V extends IDynamicBaseView, M extends DynamicBaseModel> extends BasePresenter<V, M> implements IDynamicBasePresenter {

    public List<DelegateAdapter.Adapter> mAdapters = new ArrayList<>();
    protected List<SearchBean> urlList = new ArrayList<>();
    public int currentPage = 1;
    private String loadUrl;
    private BaseDelegateAdapter loadAdapter;
    private boolean hasLoadMore;

    public void init(String pageId) {
        hasLoadMore = false;
        loadUrl = null;
        loadAdapter = null;
        currentPage = 1;
        mAdapters = new ArrayList<>();
        urlList = new ArrayList<>();
        getModel().getInitDate(pageId);
    }

    @Override
    public void setPage(PageBean data) {
        getView().setPage(data);
    }

    @Override
    public void setElement(List<FloorBean> floorBeans) {
        urlList.clear();
        for(FloorBean floorBean : floorBeans){
            initFloorBean(floorBean);
        }
        if(!urlList.isEmpty()){
            int content = urlList.size();
            for(int i = 0 ; i < content ; i++){
                SearchBean entry = urlList.get(i);
                getFloorData(i == content - 1 ,entry.getUrl(),entry.getAdapter());
            }
        }
        getView().setData(mAdapters);
    }

    @Override
    public synchronized void remove(BaseDelegateAdapter adapter) {
        if(hasLoadMore){
            if(adapter.isLoadMore()){
                if(currentPage != 1){
                    currentPage --;
                    return;
                }else{
                    loadAdapter = null;
                }
            }
        }
        int index = mAdapters.indexOf(adapter);
        int last = index - 1;
        if(last > 0 && mAdapters.get(last) instanceof TitleAdapter){
            DelegateAdapter.Adapter title = mAdapters.get(last);
            adapter.onDestroy();
            getView().remove(title);
            mAdapters.remove(title);
        }
        adapter.onDestroy();
        getView().remove(adapter);
        mAdapters.remove(adapter);
    }

    @Override
    public void loadMoreFinish() {
        getView().loadMoreFinish();
    }

    @Override
    public void noMoreData() {
        getView().noMoreData();
    }

    @Override
    public synchronized void notifyItemChanged(BaseDelegateAdapter adapter) {
        int index = mAdapters.indexOf(adapter);
        getView().notifyItemChanged(index);
    }

    private void initFloorBean(FloorBean floorBean) {
        BaseDelegateAdapter adapter = DynamicBind.initDynamic(floorBean.getKeyValue());
        if(adapter == null){
            return;
        }
        if(adapter instanceof BackAdapter){
            adapter.setOnItemClickListener((OnItemClickListener) (adapter1, view, position) -> {
                getView().goBack();
            });
        }
        if(adapter instanceof FloorDelegateAdapter){
            adapter.addData(floorBean);
        }else{
            adapter.setList(floorBean.getElementDtoList());
        }
        mAdapters.add(adapter);

        if(!StringUtils.isEmpty(floorBean.getSearchUrl())
                && adapter != null
                && (Patterns.WEB_URL.matcher(floorBean.getSearchUrl()).matches()
                || URLUtil.isValidUrl(floorBean.getSearchUrl()))){
            urlList.add(new SearchBean(floorBean.getSearchUrl(),adapter));
        }

    }

    public void loadMoreData(){
        if(!needLoadMore() || loadAdapter == null || StringUtils.isEmpty(loadUrl)){
            return;
        }
        getFloorData(true,loadUrl,loadAdapter);
    }

    protected abstract boolean needLoadMore();

    private void getFloorData(boolean isEnd, String searchUrl, BaseDelegateAdapter adapter) {
        if(needLoadMore() && isEnd && adapter.isLoadMore()){
            Uri uri = Uri.parse(searchUrl);
            String parameter = uri.getQueryParameter("currentPage");
            if(!StringUtils.isEmpty(parameter)){
                hasLoadMore = getView().hasLoadMore();
                if(hasLoadMore){
                    searchUrl = replace(searchUrl,"currentPage",String.valueOf(currentPage));
                    loadUrl = searchUrl;
                    loadAdapter = adapter;
                }else{
                    adapter.setLoadMore(false);
                }
            }else{
                adapter.setLoadMore(false);
            }
        }else{
            adapter.setLoadMore(false);
        }
        getNetWorkData(searchUrl,adapter);
    }

    private String replace(String url, String key, String value) {
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(key)) {
            url = url.replaceAll("(" + key + "=[^&]*)", key + "=" + value);
        }
        return url;
    }

    protected abstract boolean getNetWorkData(String searchUrl, BaseDelegateAdapter adapter);

}
