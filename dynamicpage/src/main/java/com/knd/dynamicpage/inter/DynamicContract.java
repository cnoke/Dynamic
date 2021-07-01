package com.knd.dynamicpage.inter;

public interface DynamicContract {

    interface IView extends IDynamicBaseView {

        void netError();
    }

    interface IModel extends IDynamicBaseModel {

    }

    interface IPresenter extends IDynamicBasePresenter {

    }


}
