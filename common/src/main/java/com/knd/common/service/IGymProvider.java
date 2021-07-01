package com.knd.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

import java.util.List;

public interface IGymProvider extends IProvider {
    void goToFreeTrainDetail(String trainReportId);

    List<String>  getVideoCacheUrl();
}
