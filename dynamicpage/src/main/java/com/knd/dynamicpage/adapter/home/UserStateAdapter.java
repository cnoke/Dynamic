package com.knd.dynamicpage.adapter.home;

import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.base.util.SpUtils;
import com.knd.common.bean.FloorBean;
import com.knd.common.bean.UserInfo;
import com.knd.common.key.Flag;
import com.knd.common.manager.RouteManager;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicItemUserStateBinding;
import com.knd.dynamicpage.utils.DelegateViewType;

@Adapter(DelegateViewType.UserState)
public class UserStateAdapter extends FloorDelegateAdapter {

    private UserInfo mUserInfo;

    public UserStateAdapter() {
        super(R.layout.dynamic_item_user_state,DelegateViewType.UserState);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StickyLayoutHelper();
    }

    @Override
    protected void onItemViewHolderCreated(BaseDataBindingHolder viewHolder, int viewType) {
        mUserInfo= (UserInfo) SpUtils.getInstance().getData(Flag.FLAG_USER,null);
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        DynamicItemUserStateBinding viewBinding = (DynamicItemUserStateBinding)holder.getDataBinding();

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

        //用户中心
        viewBinding.llUserLogin.setOnClickListener(view -> {

        });
        //切换用户
        viewBinding.ivSwitch.setOnClickListener(view -> {
            UserInfo userInfo= (UserInfo) SpUtils.getInstance().getData(Flag.FLAG_USER,null);
            if(userInfo!=null){

                mUserInfo=null;
//                DialogUtils.showConfirm(getContext(), "切换新账户", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        RouteManager.getInstance().user_center_service.logout(userInfo,false);
//                        mUserInfo=null;
//                    }
//                });
            }else{

            }
        });

    }

}
