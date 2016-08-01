package com.xiaobozheng.gankio.ui.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.UrlMatch;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.ui.base.RecyclerViewAdapter;
import com.xiaobozheng.gankio.util.DateUtils;
import com.xiaobozheng.gankio.util.GlideUtils;
import com.xiaobozheng.gankio.widget.RatioImageView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xiaobozheng on 7/29/2016.
 */
public class CategoryListAdapter extends EasyRecyclerViewAdapter {

    public static final int LAYOUT_TYPE_TECHNOLOGY = 0;
    public static final int LAYOUT_TYPE_TECHNOLOGY_PIC = 1;
    public static final int LAYOUT_TYPE_WELFARE = 2;
    private List<GankDataBean> mGankDataBeanList;
    private String mType;
    private Context context;

    public CategoryListAdapter(Context context) {
        super();
        this.context = context;
       // this.mGankDataBeanList = mGankDataBeanList;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{
                R.layout.item_category_data, R.layout.item_category_withpic,R.layout.item_welfate
        };
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        int layoutType = getRecycleViewItemType(position);
        switch (layoutType){
            case LAYOUT_TYPE_TECHNOLOGY:
                initTechnology(viewHolder, position);
                break;
            case LAYOUT_TYPE_TECHNOLOGY_PIC:
                initTechnologyWithPic(viewHolder, position);
                break;
            case LAYOUT_TYPE_WELFARE:
                initWelfare(viewHolder, position);
                break;
        }
    }

    /**
     * 初始化福利界面
     * @param viewHolder
     * @param position
     */
    private void initWelfare(EasyRecyclerViewHolder viewHolder, int position) {

        GankDataBean gankDataBean = this.getItem(position);
        if (gankDataBean == null) return;

        RatioImageView dataWelPicTV = viewHolder.findViewById(R.id.categoty_wel_iv);

        if (TextUtils.isEmpty(gankDataBean.getUrl())){
            GlideUtils.displayNative(dataWelPicTV, R.mipmap.img_default_gray);
        } else {
            GlideUtils.display(dataWelPicTV, gankDataBean.getUrl());
        }

    }

    /**
     * 初始化有图的技术界面
     * @param viewHolder
     * @param position
     */
    private void initTechnologyWithPic(EasyRecyclerViewHolder viewHolder, int position) {
        GankDataBean gankDataBean = this.getItem(position);
        if (gankDataBean == null) return;

        TextView dataDateTV = viewHolder.findViewById(R.id.catedata_date_tv);
        TextView dataViaTV = viewHolder.findViewById(R.id.catedata_via_tv);
        ImageView dataPicIV = viewHolder.findViewById(R.id.catedata_pic_iv);

        //时间
        if (gankDataBean.getPublishedAt() == null){
            dataDateTV.setText("");
        } else {
            dataDateTV.setText(DateUtils.getTimestampString(gankDataBean.getPublishedAt()));
        }

        //作者
        if (TextUtils.isEmpty(gankDataBean.getWho())){
            dataViaTV.setText("");
        } else {
            dataViaTV.setText(gankDataBean.getWho());
        }

        if (TextUtils.isEmpty(gankDataBean.getUrl())){
            GlideUtils.displayNative(dataPicIV, R.mipmap.img_default_gray);
        } else {
            GlideUtils.display(dataPicIV, gankDataBean.getUrl());
        }

    }

    /**
     * 初始化技术界面
     * @param viewHolder
     * @param position
     */
    private void initTechnology(EasyRecyclerViewHolder viewHolder, int position) {
        GankDataBean gankDataBean = this.getItem(position);
        if (gankDataBean == null) return;
        TextView dataDateTV = viewHolder.findViewById(R.id.tv_data_date);
        TextView dataTitleTV = viewHolder.findViewById(R.id.tv_data_title);
        TextView dataViaTV = viewHolder.findViewById(R.id.tv_data_via);
        TextView dataTagTV = viewHolder.findViewById(R.id.tv_data_tag);

        //标题
        if (TextUtils.isEmpty(gankDataBean.getDesc())){
            dataTitleTV.setText("");
        } else {
            dataTitleTV.setText(gankDataBean.getDesc().trim());
        }

        //时间
        if (gankDataBean.getPublishedAt() == null){
            dataDateTV.setText("");
        } else {
            dataDateTV.setText(DateUtils.getTimestampString(gankDataBean.getPublishedAt()));
        }

        //作者
        if (TextUtils.isEmpty(gankDataBean.getWho())){
            dataViaTV.setText("");
        } else {
            dataViaTV.setText(gankDataBean.getWho());
        }

        //tag标签
        if (TextUtils.isEmpty(gankDataBean.getUrl())){
            dataTagTV.setVisibility(View.GONE);
        } else {
            this.setTag(dataTagTV, gankDataBean);
        }


    }


    /**
     * 给标签设置颜色和文字
     * @param dataTagTv
     * @param gankDataBean
     */
    private void setTag(TextView dataTagTv, GankDataBean gankDataBean){
        String url = gankDataBean.getUrl();
        String key = UrlMatch.processUrl(url);
        GradientDrawable drawable = (GradientDrawable) dataTagTv.getBackground();
        if (UrlMatch.url2Content.containsKey(key)){
            drawable.setColor(UrlMatch.url2Color.get(key));
            dataTagTv.setText(UrlMatch.url2Content.get(key));
        } else {
            if (gankDataBean.getType().equals("休息视频")){
                drawable.setColor(UrlMatch.OTHER_BLOG_COLOR);
                dataTagTv.setText(UrlMatch.OSCHINA_CONTENT);
            } else {
                //https://github.com/xuyisheng,切割不好
                if (url.contains(UrlMatch.GITHUB_PREFIX) ){
                    drawable.setColor(UrlMatch.GITHUB_COLOR);
                    dataTagTv.setText(UrlMatch.GITHUB_CONTENT);
                } else {
                    drawable.setColor(UrlMatch.OTHER_BLOG_COLOR);
                    dataTagTv.setText(UrlMatch.OTHER_BLOG_CONTENT);
                }
            }
        }
    }

    /**
     * 有不同类型的时候使用
     * @param position
     * @return
     */
    @Override
    public int getRecycleViewItemType(int position) {
        mGankDataBeanList = getList();

     //   Logger.d("type类型;" + mType);
        //在all里区别福利和平时数据
        if (!mType.equals("福利")){
            //技术的列表下图片和技术数据
            if (!mGankDataBeanList.get(position).getType().equals("福利")){
                  return LAYOUT_TYPE_TECHNOLOGY;
            } else {
                    return LAYOUT_TYPE_TECHNOLOGY_PIC;
            }
        } else {
                return LAYOUT_TYPE_WELFARE;
        }
    }

    //设置传进来的type类型
    public void setType(String type){
        this.mType = type;
    }

    public String getType(){
        return mType;
    }
}
