package com.knd.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;
import com.knd.base.R;
import com.knd.base.activity.LifecycleNetWork;
import com.knd.base.event.MessageEvent;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.base.loadsir.ErrorCallback2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class MyBaseFragment<V extends ViewDataBinding> extends Fragment implements LifecycleNetWork {
    protected V viewBinding;
    protected String mFragmentTag = "";
    protected LoadService mLoadService;
    protected Context mContext;
    private List<Disposable> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        EventBus.getDefault().register(this);
        initParameters();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initView();
        return viewBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearRequest();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        //Do Nothing
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext=null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
//                onRetryBtnClick();
            }
        });
        mLoadService.setCallBack(ErrorCallback.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                TextView tv_reload= view.findViewById(R.id.tv_reload);
                if(tv_reload!=null){
                    tv_reload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryBtnClick();
                        }
                    });
                }
            }
        });

        mLoadService.setCallBack(ErrorCallback2.class, new Transport() {
            @Override
            public void order(Context context, View view) {
                TextView tv_reload= view.findViewById(R.id.tv_reload);
                if(tv_reload!=null){
                    tv_reload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRetryBtnClick();
                        }
                    });
                }
            }
        });
    }

    protected void initView(){

    }

    /***
     *   初始化参数
     */
    protected void initParameters() {

    }
    protected abstract String getFragmentTag();
    protected abstract void onRetryBtnClick();
    public abstract @LayoutRes int getLayoutId();

    public void clearRequest(){
        for(Disposable disposable:list){
            if(!disposable.isDisposed()){
                disposable.dispose();
            }
        }
        list.clear();
    }

    public void addRequest(Disposable disposable){
        if(disposable != null){
            list.add(disposable);
        }
    };
}
