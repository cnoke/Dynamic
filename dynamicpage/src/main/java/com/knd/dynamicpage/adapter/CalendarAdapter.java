package com.knd.dynamicpage.adapter;

import android.net.Uri;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearSpaceItemDecoration;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.knd.base.util.ScreenUtils;
import com.knd.common.bean.ElementBean;
import com.knd.common.bean.FloorBean;
import com.knd.common.key.ArouterKey;
import com.knd.common.route.DynamicRouteManager;
import com.knd.common.utils.StringUtils;
import com.knd.dynamicannotations.Adapter;
import com.knd.dynamicpage.R;
import com.knd.dynamicpage.adapter.base.FloorDelegateAdapter;
import com.knd.dynamicpage.bean.DayContent;
import com.knd.dynamicpage.databinding.DynamicItemCalendarBinding;
import com.knd.dynamicpage.itemAdapter.DayAdapter;
import com.knd.dynamicpage.itemAdapter.DayContentAdapter;
import com.knd.dynamicpage.utils.DelegateViewType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Adapter(DelegateViewType.Calendar)
public class CalendarAdapter extends FloorDelegateAdapter {

    private DynamicItemCalendarBinding viewBinding;
    private List<Date> dates;
    private DayAdapter adapter;
    private DayContentAdapter dayContentAdapter;
    private HashMap<Date, List<DayContent>> dayContentMap = new HashMap<>();
    private HashMap<Date, String> markData = new HashMap<>();
    SimpleDateFormat showFormat = new SimpleDateFormat("yyyy-MM-dd");

    public CalendarAdapter() {
        super(R.layout.dynamic_item_calendar, DelegateViewType.Calendar);
    }

    @Override
    protected void onItemViewHolderCreated(BaseDataBindingHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);
        viewBinding = (DynamicItemCalendarBinding)viewHolder.getDataBinding();
        viewBinding.rvDay.setLayoutManager(new GridLayoutManager(getContext(),7));
        viewBinding.rvDay.setAdapter(adapter = new DayAdapter());
        if(dates == null){
            dates = getWeekDateList();
        }
        adapter.setList(dates);
        adapter.setOnItemClickListener((mAdapter, view, position) -> {
            Date date = adapter.getItem(position);
            adapter.setSelect(date);
            adapter.notifyDataSetChanged();
            setDayContent(date);
        });

        viewBinding.rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        viewBinding.rvContent.addItemDecoration(new RecyclerViewLinearSpaceItemDecoration
                .Builder(getContext()).margin((int) ScreenUtils.dp2px(getContext()
                , 17.33f)).create());
        viewBinding.rvContent.setAdapter(dayContentAdapter = new DayContentAdapter());
        dayContentAdapter.setOnItemClickListener((mAdapter, view, position) -> {
            DayContent content = dayContentAdapter.getItem(position);
            if(isLiveService(content.getSkipUrl())){

            }else{
                DynamicRouteManager.getInstance().route(content.getSkipUrl(),null);
            }
        });
    }

    private boolean isLiveService(String url){
        if(StringUtils.isEmpty(url)){
            return false;
        }
        Uri uri = Uri.parse(url);
        return ArouterKey.SERVICE_LIVE.equals(uri.getPath());
    }

    private void setDayContent(Date date) {
        dayContentAdapter.setList(dayContentMap.get(date));
    }

    @Override
    protected void convert(BaseDataBindingHolder holder, FloorBean item) {
        viewBinding = (DynamicItemCalendarBinding)holder.getDataBinding();
        initData(item.getElementDtoList());
    }

    private void initData(List<ElementBean> elementDtoList){
        dayContentMap.clear();
        markData.clear();
        if(elementDtoList != null &&  !elementDtoList.isEmpty()){
            for(ElementBean bean : elementDtoList){
                if(StringUtils.isEmpty(bean.getElementName())
                        || bean.getExtend() == null || !(bean.getExtend() instanceof List)){
                    continue;
                }
                List extend = (List)bean.getExtend();
                if(extend == null
                        || extend.isEmpty()
                        || !(extend.get(0) instanceof DayContent) ){
                    continue;
                }
                try {
                    List<DayContent> dayContents = (List<DayContent>)bean.getExtend();
                    if(dayContents != null && !dayContents.isEmpty()){
                        Date date = showFormat.parse(bean.getElementName());
                        dayContentMap.put(date,dayContents);
                        markData.put(date,dayContents.size() + "???");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        adapter.setMarkData(markData);
        adapter.notifyDataSetChanged();
    }

    /**
     * ???????????????????????????????????????????????????????????????
     * @return
     */
    private List<Date> getWeekDateList() {
        Calendar cal = Calendar.getInstance();
        // ???????????????????????????????????????????????????????????????????????????????????????
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // ?????????????????????????????????????????????
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayWeek==1){
            dayWeek = 8;
        }

        // ???????????????????????????????????????????????????????????????????????????????????????
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();

        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();

        List lDate = new ArrayList();
        lDate.add(mondayDate);
        Calendar calBegin = Calendar.getInstance();
        // ??????????????? Date ????????? Calendar ?????????
        calBegin.setTime(mondayDate);
        Calendar calEnd = Calendar.getInstance();
        // ??????????????? Date ????????? Calendar ?????????
        calEnd.setTime(sundayDate);
        //??????????????????????????????????????????
        while (sundayDate.after(calBegin.getTime())) {
            // ?????????????????????????????????????????????????????????????????????????????????
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }
}
