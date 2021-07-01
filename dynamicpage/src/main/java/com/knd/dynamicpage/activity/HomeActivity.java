package com.knd.dynamicpage.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.knd.base.util.PackageUtils;
import com.knd.base.util.SpUtils;
import com.knd.common.BaseModuleApp;
import com.knd.common.activity.mvp.InjectPresenter;
import com.knd.common.bean.CommitEquipmentReportDTO;
import com.knd.common.bean.DeviceResponseBean;
import com.knd.common.bean.PageBean;
import com.knd.common.event.PowerChangeEvent;
import com.knd.common.event.RealTimeInfoEvent;
import com.knd.common.event.UserLoginEvent;
import com.knd.common.key.ArouterKey;
import com.knd.common.utils.DeviceUtils;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.adapter.home.HomePowerAdapter;
import com.knd.dynamicpage.api.DynamicApiClient;
import com.knd.dynamicpage.databinding.DynamicActivityHomeBinding;
import com.knd.dynamicpage.inter.DynamicHomeContract;
import com.knd.dynamicpage.presenter.DynamicHomePresenter;
import com.knd.network.subscriber.BaseObserver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Route(path = ArouterKey.DYNAMIC_ACTIVITY_HOME)
public class HomeActivity extends DynamicBaseActivity<DynamicActivityHomeBinding>
        implements DynamicHomeContract.IView{

    @InjectPresenter
    private DynamicHomePresenter mPresenter;
    private DelegateAdapter delegateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            BaseModuleApp.getInstance().setDevices(savedInstanceState.getParcelableArrayList("data"));
            BaseModuleApp.getInstance().hardwareVersion=savedInstanceState.getString("hard_version");
            BaseModuleApp.getInstance().softwareVersion=savedInstanceState.getString("soft_version");
        }
    }

    private void uploadInfo(String mAddress){
        CommitEquipmentReportDTO param= new CommitEquipmentReportDTO();
        if(StringUtils.isEmpty(BaseModuleApp.getInstance().hardwareVersion)){
            param.setHardVersion("1.0.0");
            param.setMainboardVersion("1.0.0");
        }else{
            param.setHardVersion(BaseModuleApp.getInstance().hardwareVersion);
            param.setMainboardVersion(BaseModuleApp.getInstance().softwareVersion);
        }
        param.setAppVersion(PackageUtils.getVersionName(this));
        param.setPositionInfo(mAddress);
        param.setEquipmentNo(DeviceUtils.getAndroidId(this));
        param.setTurnOnTime(new Date().toLocaleString());
        DynamicApiClient.getInstance().commitEquipmentReportInfo(new BaseObserver<>(this),param);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ArrayList<DeviceResponseBean> data= (ArrayList<DeviceResponseBean>) BaseModuleApp.getInstance().getDevices();
        outState.putParcelableArrayList("data",data);
        outState.putString("hard_version" , BaseModuleApp.getInstance().hardwareVersion);
        outState.putString("soft_version" , BaseModuleApp.getInstance().softwareVersion);
    }

    @Override
    protected void initView() {
        super.initView();
        SpUtils.getInstance().putData("isFirst",false);

        //初始化
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        viewBinding.rvContent.getmRecyclerView().setLayoutManager(layoutManager);

        viewBinding.rvContent.getmRecyclerView().addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e!=null){
                    View v = rv.findChildViewUnder(e.getX(),e.getY());
                    if (v!=null){
                        rv.requestDisallowInterceptTouchEvent(rv.indexOfChild(v)==1);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewBinding.rvContent.getmRecyclerView().setRecycledViewPool(viewPool);

        //设置适配器
        viewBinding.rvContent.getmRecyclerView().setAdapter(delegateAdapter = new DelegateAdapter(layoutManager, true));

        viewBinding.rvContent.setRefreshListener(() -> {
            viewBinding.rvContent.getmSmartRefreshLayout().finishRefresh();
            getData();
        });

        getData();

    }

    private void getData(){
        mPresenter.init("home");
    }

    @Override
    public int getLayoutId() {
        return R.layout.dynamic_activity_home;
    }

    @Override
    public void onRetryBtnClick() {
        getData();
    }

    @Override
    public void setData(List<DelegateAdapter.Adapter> mAdapters) {
//        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
//        viewBinding.rvContent.getmRecyclerView().setAdapter(delegateAdapter = new DelegateAdapter(layoutManager, true));
        viewBinding.rvContent.getmRecyclerView().setAdapter(delegateAdapter);
//        delegateAdapter = new DelegateAdapter(layoutManager, true);
        delegateAdapter.setAdapters(mAdapters);
    }

    @Override
    public void setPage(PageBean data) {

    }

    @Override
    public void remove(int position) {
        delegateAdapter.removeAdapter(position);
        delegateAdapter.notifyItemRemoved(position);
    }

    @Override
    public void remove(DelegateAdapter.Adapter adapter) {
        delegateAdapter.removeAdapter(adapter);
    }

    @Override
    public void notifyItemChanged(int position) {
        delegateAdapter.notifyItemChanged(position);
    }

    @Override
    public void goBack() {
        viewBinding.rvContent.getmRecyclerView().smoothScrollToPosition(0);
    }

    @Override
    public boolean hasLoadMore() {
        viewBinding.rvContent.setLoadMoreListener(() -> {
            mPresenter.currentPage ++;
            mPresenter.loadMoreData();
        });
        return true;
    }

    @Override
    public void loadMoreFinish() {
        viewBinding.rvContent.finishLoadMore();
    }

    @Override
    public void noMoreData() {
        viewBinding.rvContent.finishLoadMoreWithNoMoreData();
    }


    //    private int limitedPower = 45;//限制的最大力
//    private int limitCount = 5;//功率限制后，取5次实时力最大值作为最大力限制
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRealTimeInfoEvent(RealTimeInfoEvent event){
//        if (event.realTimeInfo.error == 0) {
            if (delegateAdapter.findAdapterByIndex(0) instanceof HomePowerAdapter) {
//                //功率限制功能
//                if (event.realTimeInfo.isPowerLimited){
//                    if (limitCount>0){
//                        if (limitedPower==45){
//                            limitedPower = event.realTimeInfo.realTimeValue;
//                        }else if (limitedPower<event.realTimeInfo.realTimeValue){
//                            limitedPower = event.realTimeInfo.realTimeValue;
//                        }
//                        limitCount--;
//                    }
//                }else {
//                    limitedPower = 45;
//                }
//                if (limitedPower<45){
//                    ((HomePowerAdapter)delegateAdapter.findAdapterByIndex(0)).setPowerNoChange(limitedPower,0,0,false);
//                }

                ((HomePowerAdapter)delegateAdapter.findAdapterByIndex(0)).setState(event.realTimeInfo.isAddingPower,true);
                ((HomePowerAdapter)delegateAdapter.findAdapterByIndex(0)).setValue(event.realTimeInfo.realTimeValue, event.realTimeInfo.isPowerLimited);
            }
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPowerChangeEvent(PowerChangeEvent event) {
        if (delegateAdapter.findAdapterByIndex(0) instanceof HomePowerAdapter){
            ((HomePowerAdapter)delegateAdapter.findAdapterByIndex(0)).setState(event.state == 1,true);
            ((HomePowerAdapter)delegateAdapter.findAdapterByIndex(0)).setPowerNoChange(event.mBasePower, event.mContiousePower, event.mBackPower, !event.resetRadio);
        }

    }

    @Subscribe
    public void onUserLoginEvent(UserLoginEvent event){
        getData();
    }

    @Override
    public void addData(boolean needClean , BaseDelegateAdapter adapter) {
        if(needClean){
            delegateAdapter.clear();
        }
        delegateAdapter.addAdapter(adapter);
    }
}
