package com.knd.dynamicpage.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.knd.base.loadsir.ErrorCallback;
import com.knd.common.activity.mvp.InjectPresenter;
import com.knd.common.bean.PageBean;
import com.knd.common.key.ArouterKey;
import com.knd.common.route.ParamKey;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.BaseDelegateAdapter;
import com.knd.dynamicpage.databinding.DynamicActivityMainBinding;
import com.knd.dynamicpage.inter.DynamicContract;
import com.knd.dynamicpage.presenter.DynamicPresenter;

import java.util.List;

@Route(path = ArouterKey.DYNAMIC_ACTIVITY_MAIN)
public class DynamicActivity extends DynamicBaseActivity<DynamicActivityMainBinding>
        implements DynamicContract.IView{

    @InjectPresenter
    private DynamicPresenter mPresenter;
    private DelegateAdapter delegateAdapter;
    @Autowired(name = ParamKey.PAGE_ID ,required = true)
    public String pageId;

    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);
        setupLoadView(viewBinding.rvContent);
        //初始化
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        viewBinding.rvContent.setLayoutManager(layoutManager);
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewBinding.rvContent.getmRecyclerView().setRecycledViewPool(viewPool);

        //设置适配器
        viewBinding.rvContent.setAdapter(delegateAdapter = new DelegateAdapter(layoutManager, true));
        mPresenter.init(pageId);
        viewBinding.rvContent.setRefreshListener(() -> {
            viewBinding.rvContent.getmSmartRefreshLayout().finishRefresh();
            getData();
        });
    }

    private void getData(){
        for(DelegateAdapter.Adapter adapter : mPresenter.mAdapters){
            if(adapter instanceof BaseDelegateAdapter){
                ((BaseDelegateAdapter) adapter).onDestroy();
            }
        }
        delegateAdapter.clear();
        mPresenter.init(pageId);
    }

    @Override
    public void setPage(PageBean data) {
        mLoadService.showSuccess();
        viewBinding.tvTitle.setText(data.getPageName());
    }

    @Override
    public void remove(int position) {
        delegateAdapter.removeAdapter(position);
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
    public void setData(List<DelegateAdapter.Adapter> mAdapters) {
        viewBinding.rvContent.getmRecyclerView().setAdapter(delegateAdapter);
        delegateAdapter.setAdapters(mAdapters);
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

    @Override
    public int getLayoutId() {
        return R.layout.dynamic_activity_main;
    }

    @Override
    public void onRetryBtnClick() {
        getData();
    }

    @Override
    public void netError() {
        mLoadService.showCallback(ErrorCallback.class);
    }
}
