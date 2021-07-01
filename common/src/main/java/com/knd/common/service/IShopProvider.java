package com.knd.common.service;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.knd.common.bean.GetOrderInfoRequest;
import com.knd.common.bean.OrderBean;
import com.knd.common.bean.VipMenu;

public interface IShopProvider extends IProvider {
    void gotoBuyVip(Object activity,GetOrderInfoRequest request, VipMenu vipMenu);
    void gotoBuyVip(Object activity,GetOrderInfoRequest request, VipMenu vipMenu,boolean needFinish);

    void gotoBuyGoods(GetOrderInfoRequest request);
    void gotoBuyGoods(Object activity,GetOrderInfoRequest request, OrderBean orderBean);
    void gotoBuyGoods(Object activity,GetOrderInfoRequest request, OrderBean orderBean,boolean needFinish);
}
