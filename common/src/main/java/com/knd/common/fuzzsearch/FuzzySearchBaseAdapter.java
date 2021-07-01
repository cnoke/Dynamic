package com.knd.common.fuzzsearch;

import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class FuzzySearchBaseAdapter<ITEM extends IFuzzySearchItem, VH extends BaseViewHolder> extends BaseQuickAdapter<ITEM,VH>
	 implements Filterable {

	private FuzzySearchFilter mFilter;
	private List<ITEM> mBackDataList = new ArrayList<>();
	private IFuzzySearchRule  mIFuzzySearchRule;


	public FuzzySearchBaseAdapter(int layout ,IFuzzySearchRule rule) {
		this(layout,rule, null);
	}

	public FuzzySearchBaseAdapter(int layoutResId , IFuzzySearchRule rule, List<ITEM> dataList) {
		super(layoutResId,dataList);
		if (rule == null) {
			mIFuzzySearchRule = new DefaultFuzzySearchRule();
		}
		mBackDataList.addAll(dataList);
	}

	public void setDate(List<ITEM> date){
		mBackDataList.clear();
		mBackDataList.addAll(date);
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new FuzzySearchFilter();
		}
		return mFilter;
	}

	private class FuzzySearchFilter extends Filter {

		/**
		 * 执行过滤操作,如果搜索的关键字为空，默认空
		 */
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults result = new FilterResults();
			List<ITEM> filterList;
			if (TextUtils.isEmpty(constraint)) {
				filterList = new ArrayList<>();
			} else {
				filterList = new ArrayList<>();
				for (ITEM item : mBackDataList) {
					if (mIFuzzySearchRule.accept(constraint, item.getSourceKey(), item.getFuzzyKey())) {
						filterList.add(item);
					}
				}
			}
			result.values = filterList;
			result.count = filterList.size();
			return result;
		}

		/**
		 * 得到过滤结果
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			setList((List<ITEM>) results.values);
		}
	}

}
