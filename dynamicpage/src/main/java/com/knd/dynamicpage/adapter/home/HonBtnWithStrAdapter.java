package com.knd.dynamicpage.adapter.home;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.base.util.SpUtils;
import com.knd.common.bean.FloorBean;
import com.knd.common.bean.UserInfo;
import com.knd.common.key.Flag;
import com.knd.common.manager.RouteManager;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.route.Param;
import com.knd.common.utils.DialogUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicLayoutAdapterHonBtnsBinding;
import com.knd.dynamicpage.itemAdapter.HonBtnWithStrContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.HonBtnWithStr)
public class HonBtnWithStrAdapter extends FloorDelegateAdapter {

    private UserInfo mUserInfo;

    public HonBtnWithStrAdapter() {
        super(R.layout.dynamic_layout_adapter_hon_btns,DelegateViewType.HonBtnWithStr);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StickyLayoutHelper();
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicLayoutAdapterHonBtnsBinding viewBinding = (DynamicLayoutAdapterHonBtnsBinding)holder.getDataBinding();

        mUserInfo= (UserInfo) SpUtils.getInstance().getData(Flag.FLAG_USER,null);
        if(mUserInfo!=null){
            viewBinding.setUser(mUserInfo);
            viewBinding.ivAvatar.setVisibility(View.GONE);
            viewBinding.vwAvatar.setData(mUserInfo.getNickName(),mUserInfo.getHeadPicUrl());
            viewBinding.vwAvatar.setVisibility(View.VISIBLE);
        }else {
            viewBinding.ivAvatar.setVisibility(View.VISIBLE);
            viewBinding.vwAvatar.setVisibility(View.GONE);
            viewBinding.setUser(null);
        }

        int size = item.getElementDtoList().size();
        if(size > 0){
            viewBinding.recycleHonBtn.setLayoutManager(new GridLayoutManager(getContext(),size));
            HonBtnWithStrContentAdapter adapter = new HonBtnWithStrContentAdapter(item.getElementDtoList());

            adapter.setOnItemClickListener((adapter1, view, position) -> {
                Param param = item.getElementDtoList().get(position).getParam();
                param.id = item.getElementDtoList().get(position).getElementDetail();
                DynamicRouteManager.getInstance().route(item.getElementDtoList().get(position).getSkipUrl(),param);
            });
            viewBinding.recycleHonBtn.setAdapter(adapter);

        }

        //用户中心
        viewBinding.llUserLogin.setOnClickListener(view -> {
            mUserInfo= (UserInfo) SpUtils.getInstance().getData(Flag.FLAG_USER,null);
        });
        //切换用户
        viewBinding.ivSwitch.setOnClickListener(view -> {
            UserInfo userInfo= (UserInfo) SpUtils.getInstance().getData(Flag.FLAG_USER,null);
            if(userInfo != null){
//                RouteManager.getInstance().user_center_service.logout(userInfo,false);
//                mUserInfo=null;
                DialogUtils.showConfirm(getContext(), "退出并登录新账户", v -> {
                    mUserInfo=null;
                });
            }else{
            }
        });

    }

}
