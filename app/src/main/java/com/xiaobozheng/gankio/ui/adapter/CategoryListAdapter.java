package com.xiaobozheng.gankio.ui.adapter;

import android.content.Context;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDataBean;

import java.util.List;

/**
 * Created by xiaobozheng on 7/29/2016.
 */
public class CategoryListAdapter extends EasyRecyclerViewAdapter{

    public static final int LAYOUT_TYPE_TECHNOLOGY = 0;
    public static final int LAYOUT_TYPE_TECHNOLOGY_PIC = 1;
    public static final int LAYOUT_TYPE_WELFARE = 2;
    private List<GankDataBean> mGankDataBeanList;
    private String mType;
    private Context context;

    public CategoryListAdapter(Context context, List<GankDataBean> mGankDataBeanList) {
        super();
        this.context = context;
        this.mGankDataBeanList = mGankDataBeanList;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{
                R.layout.item_category_data, R.layout.item_category_withpic
        };
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        int layoutType = getRecycleViewItemType(position);
    }

    /**
     * 有不同类型的时候使用
     * @param position
     * @return
     */
    @Override
    public int getRecycleViewItemType(int position) {
        //在all里区别福利和平时数据
        if (!mType.equals("福利")){
            //技术的列表下图片和技术数据
            if (!mGankDataBeanList.get(position).getType().equals("福利")){

            } else {

            }
        } else {

        }
        return 0;
    }

    //设置传进来的type类型
    public void setType(String type){
        this.mType = type;
    }

    public String getType(){
        return mType;
    }
}
