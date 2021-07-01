package com.knd.dynamicpage;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.knd.common.service.IDynamicProvider;

import static com.knd.common.key.ArouterKey.SERVICE_DYNAMIC_PAGE;

@Route(path = SERVICE_DYNAMIC_PAGE)
public class DynamicProviderImpl implements IDynamicProvider {

    private Context context;


    @Override
    public void init(Context context) {
        this.context = context;
    }
}
