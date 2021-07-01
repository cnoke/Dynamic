package com.knd.dynamicpage.bean;

import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;

public class SearchBean {

    private String url;
    private BaseDelegateAdapter adapter;

    public SearchBean(String url, BaseDelegateAdapter adapter) {
        this.url= url;
        this.adapter = adapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BaseDelegateAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseDelegateAdapter adapter) {
        this.adapter = adapter;
    }
}
