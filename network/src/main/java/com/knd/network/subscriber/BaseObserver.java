package com.knd.network.subscriber;

import com.knd.base.activity.LifecycleNetWork;
import com.knd.base.application.BaseApplication;
import com.knd.base.event.MessageEvent;
import com.knd.base.util.SpUtils;
import com.knd.base.util.ToastUtils;
import com.knd.base.view.LoadingDialog;
import com.knd.common.bean.UserInfo;
import com.knd.common.manager.RouteManager;
import com.knd.network.errorhandler.CustomeServerException;
import com.knd.network.errorhandler.ExceptionHandle;
import com.knd.common.key.ResponeCode;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<T> {

    private LifecycleNetWork lifecycleNetWork;

    public BaseObserver(Object object) {
        if(object instanceof LifecycleNetWork){
            this.lifecycleNetWork = (LifecycleNetWork) object;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if(lifecycleNetWork !=null ){
            lifecycleNetWork.addRequest(d);
        }
    }

    @Override
    public void onNext(T value) {
        LoadingDialog.getInstance().dismiss();
        //设备已授权
        MessageEvent event = new MessageEvent();
        event.setType(ResponeCode.DEVICE_AUTHORIZED);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onError(Throwable e) {
        LoadingDialog.getInstance().dismiss();
        if(e instanceof ExceptionHandle.ResponeThrowable){
            ToastUtils.show(BaseApplication.sApplication.getBaseContext(),((ExceptionHandle.ResponeThrowable)e).message);
        }else if(e instanceof CustomeServerException){
            ToastUtils.show(BaseApplication.sApplication.getBaseContext(),e.getMessage());
            CustomeServerException customeServerException = (CustomeServerException) e;
            String code = customeServerException.getCode();
            if(ResponeCode.FROZEN_USER.equals(code) || ResponeCode.DELETE_USER.equals(code)){
                //冻结删除
                UserInfo info= (UserInfo) SpUtils.getInstance().getData("user",null);

            }else if(ResponeCode.DEVICE_UNAUTHORIZED.equals(code)){
                //设备未授权
                MessageEvent event = new MessageEvent();
                event.setType(ResponeCode.DEVICE_UNAUTHORIZED);
                EventBus.getDefault().post(event);
            }
        }else{
            ToastUtils.show(BaseApplication.sApplication.getBaseContext(),e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

}
