package com.xiaobozheng.gankio.ui.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.util.GankTypeDict;
import com.xiaobozheng.gankio.util.GlideUtils;
import com.xiaobozheng.gankio.widget.RatioImageView;

import java.util.List;

/**
 * Created by xiaobozheng on 6/30/2016.
 */
public class DailyDetailAdapter extends EasyRecyclerViewAdapter{

    private Context context;
    private int cardItemPadding;  //字体的边距
    private int cardCategoryPaddingTopBottom;  //字体与顶部和底部的距离
    private int cardItemDivider;            //card的高度
    private static final int dividerColor = 0xffCCCCCC;
    private DailyDetailAdapter.onCardItemClickListener onCardItemClickListener;

    public DailyDetailAdapter(Context context) {
        this.context = context;
        Resources res = this.context.getResources();
        initCardItemStyle(res);
    }

    @Override
    public int getRecycleViewItemType(int position) {
        return 0;
    }

    private void initCardItemStyle(Resources res){
        this.cardItemPadding = res.getDimensionPixelOffset(R.dimen.card_item_content_padding);
        this.cardCategoryPaddingTopBottom = res.getDimensionPixelOffset(
                R.dimen.card_category_padding_top_bottom);
        this.cardItemDivider = res.getDimensionPixelOffset(R.dimen.card_item_divider);
    }

    //添加布局格式
    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_daily_detail};
    }

    //onBindView的position是指相对于整个数据集的位置，而不是显示view的位置
    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        //获取各个列表所表示数据
        List<GankDataBean> categoryData = this.getItem(position);
        if (categoryData == null || categoryData.size() <= 0)
            return;
        LinearLayout detailLL= viewHolder.findViewById(R.id.ll_daily_detail);
        //初始移除所有的View
        detailLL.removeAllViews();
        for (int i = 0; i < categoryData.size(); i++){
            final GankDataBean gankDataBean = categoryData.get(i);
            if (i == 0){
                TextView categorvTV = this.creatCardCategory(gankDataBean.getType());
                detailLL.addView(categorvTV);
                detailLL.addView(this.creatDivider());
            }
            //获取到类型所对应的id值找到福利图片
            if (GankTypeDict.urlType2TypeDict.get(gankDataBean.getType()) == Constant.welfare){
                Logger.i(gankDataBean.getUrl());
            RatioImageView welfareIV = this.createRationImageView();
                GlideUtils.display(welfareIV, gankDataBean.getUrl());
                //设置图片能点击事件
                welfareIV.setOnClickListener(v -> {
                    if (DailyDetailAdapter.this.onCardItemClickListener != null){
                        DailyDetailAdapter.this.onCardItemClickListener.onWelfareOnClick(
                                gankDataBean.getUrl(), gankDataBean.getDesc(), v
                        );
                    }
                });
                detailLL.addView(welfareIV);
            } else {
                TextView itemText = this.creatCardItemText(gankDataBean);
               // itemText.setText("哈哈哈哈哈哈哈");
                detailLL.addView(itemText);
            }
        }

    }

    //常见item字体
    private TextView creatCardItemText(GankDataBean gankDataBean){
        TextView itemText = (TextView) LayoutInflater.from(this.context)
                .inflate(R.layout.view_card_item,null);
        itemText.setPadding(this.cardItemPadding, this.cardItemPadding, this.cardItemPadding,
                this.cardItemPadding);

        String content = gankDataBean.getDesc().trim() + "  " + String.format("via." + gankDataBean.getWho());

        itemText.setText(content);
        //itemText.setTag(R.id.tag_card_item_url, baseGankData.url);
        //itemText.setTag(R.id.tag_card_item_desc, baseGankData.desc.trim());
        //itemText.setTag(R.id.tag_card_item_type, baseGankData.type);
        itemText.setOnClickListener(v -> {
            if (DailyDetailAdapter.this.onCardItemClickListener != null){
                DailyDetailAdapter.this.onCardItemClickListener.onCardItemOnclick(
                        gankDataBean.getType(),gankDataBean.getDesc(),gankDataBean.getUrl()
                );
            }
        });
        return itemText;
    }

    //创建分割线
    private View creatDivider(){
        View divider = new View(this.context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                this.cardItemDivider));
        divider.setBackgroundColor(dividerColor);
        return divider;
    }

    //创建压缩图片
    private RatioImageView createRationImageView(){
        return (RatioImageView)LayoutInflater.from(this.context)
                .inflate(R.layout.view_card_radio_view, null);
    }

    //创建图片标题文字
    private TextView creatCardCategory(String urlType){
        TextView tv_categoty = new TextView(this.context);
        tv_categoty.setPadding(this.cardItemPadding,this.cardCategoryPaddingTopBottom,
                this.cardItemPadding,this.cardCategoryPaddingTopBottom);

        tv_categoty.setText(urlType);
        Logger.d("urlType" + urlType);
        tv_categoty.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_categoty.setTextSize(20);
        tv_categoty.setTextColor(GankTypeDict.urlType2ColorDict.get(urlType));
        //设置白色的背景，点击不会变颜色
        tv_categoty.setBackgroundResource(R.drawable.shape_card_background);
        return  tv_categoty;
    }

    public void setOnCardItemClickListener(DailyDetailAdapter.onCardItemClickListener onCardItemClickListener){
        this.onCardItemClickListener = onCardItemClickListener;

    }

    public interface onCardItemClickListener{
        void onCardItemOnclick(String urlType, String title, String url);
        void onWelfareOnClick(String url, String title, View v);
    }

}
