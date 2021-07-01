package com.knd.dynamicpage.inter;

import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;

public interface DynamicHomeContract {

    interface IView extends IDynamicBaseView {

        void addData(boolean needClean , BaseDelegateAdapter adapter);
    }

    interface IModel extends IDynamicBaseModel {

    }

    interface IPresenter extends IDynamicBasePresenter {

    }


}
