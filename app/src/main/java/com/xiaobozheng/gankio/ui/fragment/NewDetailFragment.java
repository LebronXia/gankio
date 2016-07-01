package com.xiaobozheng.gankio.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.mvp.presenter.impl.RecentPresent;
import com.xiaobozheng.gankio.mvp.view.RecentView;
import com.xiaobozheng.gankio.ui.adapter.DailyDetailAdapter;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 6/27/2016.
 */
public class NewDetailFragment extends BaseFragment implements RecentView{

    @Bind(R.id.rv_new_detail)
    EasyRecyclerView dailydetailRv;
    private DailyDetailAdapter detailAdapter;
    private RecentPresent mRecentPresent;
    private int mYear, mMonth, mDay;
    private ArrayList<ArrayList<GankDataBean>> mGankList;
    public Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
        this.mRecentPresent = new RecentPresent(this);
        this.context = getActivity();
        mGankList = new ArrayList<ArrayList<GankDataBean>>();
        //初始化分段线
        EasyBorderDividerItemDecoration decoration = new EasyBorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans)
        );
        this.dailydetailRv.addItemDecoration(decoration);

        this.detailAdapter = new DailyDetailAdapter(context);
        //this.detailAdapter.setOnItemClickListener(context);
        this.dailydetailRv.setAdapter(detailAdapter);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mRecentPresent.getRecentData(mYear, mMonth, mDay);
    }

    @Override
    public void showData(GankDaily gankDaily) {
        if (gankDaily != null){
           mGankList = addAllResults(gankDaily.results);
            detailAdapter.setList(mGankList);
            detailAdapter.notifyDataSetChanged();
        }
    }

    //将数据添加到Arraylist中
    private ArrayList<ArrayList<GankDataBean>> addAllResults(GankDaily.DailyResults results){
        if (results.welfareData != null && results.welfareData.size() > 0){
            mGankList.add(results.welfareData);
        }
        if (results.androidData != null && results.androidData.size() > 0){
            mGankList.add(results.androidData);
        }
        if (results.iosData != null && results.iosData.size() > 0){
            mGankList.add(results.iosData);
        }
        if (results.jsData != null && results.jsData.size() > 0){
            mGankList.add(results.jsData);
        }
        if (results.videoData != null && results.videoData.size() > 0){
            mGankList.add(results.videoData);
        }
        if (results.recommendData != null && results.recommendData.size() > 0){
            mGankList.add(results.recommendData);
        }
        if (results.resourcesData != null && results.resourcesData.size() > 0){
            mGankList.add(results.resourcesData);
        }
        return mGankList;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

}
