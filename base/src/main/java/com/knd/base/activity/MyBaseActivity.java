package com.knd.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.knd.base.R;
import com.knd.base.event.MessageEvent;
import com.knd.base.loadsir.EmptyCallback;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.ErrorCallback2;
import com.knd.base.loadsir.LoadingCallback;
import com.knd.base.util.ScreenUtils;
import com.knd.base.util.ToastUtils;
import com.knd.base.view.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;


public abstract class MyBaseActivity<V extends ViewDataBinding> extends AppCompatActivity implements LifecycleNetWork,IBaseView{
    protected LoadService mLoadService;
    protected V viewBinding;
    public static String TAG;
    protected boolean isFront;
    private List<Disposable> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        EventBus.getDefault().register(this);
        TAG=getClass().getSimpleName();
        viewBinding = DataBindingUtil.setContentView(this,getLayoutId());
        initView();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {

    }

    public abstract @LayoutRes
    int getLayoutId();

    /**
     * 全透明
     * @return
     */
    protected boolean isStatusTransparent(){
        return false;
    }



    /**
     * 半透明
     * @return
     */
    protected boolean isStatusTranslucent(){
        return false;
    }

    protected void initView(){
        if (isStatusTransparent()) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            getWindow().getDecorView().setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            setToolbarPadding();
        }else if(isStatusTranslucent()){
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setToolbarPadding();
        }
    }

    /**
     * 透明或者半透明需要手动给toolbar 加paddingTop
     */
    private void setToolbarPadding() {
        MyToolbar toolbar=viewBinding.getRoot().findViewWithTag(MyToolbar.TAG);
        if(toolbar!=null){ //如果有toolbar
            toolbar.setPadding(toolbar.getPaddingLeft(),toolbar.getPaddingTop()+ ScreenUtils.getStatusBarHeight(this),toolbar.getPaddingRight(),toolbar.getPaddingBottom());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isFront=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFront=false;
    }

    //重试按钮点击回调方法
    public abstract void onRetryBtnClick();

    public  void setupLoadView(View view){
        mLoadService= LoadSir.getDefault().register(view, (Callback.OnReloadListener) v -> {
//                if(mLoadService.getCurrentCallback()==ErrorCallback.class ||mLoadService.getCurrentCallback()== EmptyCallback.class ||mLoadService.getCurrentCallback()== ErrorCallback2.class){
//                    onRetryBtnClick();
//                }
        });
        mLoadService.setCallBack(ErrorCallback.class, (context, view1) -> {
            TextView tv_reload= view1.findViewById(R.id.tv_reload);
            if(tv_reload!=null){
                tv_reload.setOnClickListener(v -> onRetryBtnClick());
            }
        });

        mLoadService.setCallBack(ErrorCallback2.class, (context, view12) -> {
            TextView tv_reload= view12.findViewById(R.id.tv_reload);
            if(tv_reload!=null){
                tv_reload.setOnClickListener(v -> onRetryBtnClick());
            }
        });
        mLoadService.showSuccess();
    }

    public void addRequest(Disposable disposable){
        if(disposable != null){
            list.add(disposable);
        }
    }

    public void clearRequest(){
        for(Disposable disposable:list){
            if(!disposable.isDisposed()){
                disposable.dispose();
            }
        }
        list.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        clearRequest();
        ToastUtils.dismiss();
    }

    @Override
    public void onRefreshEmpty() {
        if (mLoadService != null) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String message) {
        if (mLoadService != null) {
            mLoadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showContent() {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }
}
