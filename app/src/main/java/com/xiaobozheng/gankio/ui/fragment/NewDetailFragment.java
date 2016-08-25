package com.xiaobozheng.gankio.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.umeng.analytics.MobclickAgent;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.app.MyApplication;
import com.xiaobozheng.gankio.data.model.GankDataBean;
import com.xiaobozheng.gankio.injection.module.NewDetailFragmentModule;
import com.xiaobozheng.gankio.mvp.presenter.impl.RecentPresent;
import com.xiaobozheng.gankio.mvp.view.Impl.RecentView;
import com.xiaobozheng.gankio.ui.activity.PictureActivity;
import com.xiaobozheng.gankio.ui.activity.WebActivity;
import com.xiaobozheng.gankio.ui.adapter.DailyDetailAdapter;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 6/27/2016.
 */
public class NewDetailFragment extends BaseFragment implements RecentView{

    @Bind(R.id.rv_new_detail)
    EasyRecyclerView dailydetailRv;
    private DailyDetailAdapter detailAdapter;
    private int mYear, mMonth, mDay;
    public Context context;

    @Inject
    private RecentPresent mRecentPresent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
       // this.mRecentPresent = new RecentPresent(this);
        this.context = getActivity();
       // mGankList = new ArrayList<ArrayList<GankDataBean>>();
        //初始化分段线
        EasyBorderDividerItemDecoration decoration = new EasyBorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans)
        );
        this.dailydetailRv.addItemDecoration(decoration);

        this.detailAdapter = new DailyDetailAdapter(context);
        this.detailAdapter.setOnCardItemClickListener(new DailyDetailAdapter.onCardItemClickListener() {
            @Override
            public void onCardItemOnclick(String urlType, String title, String url) {
                startActivity(WebActivity.newIntent(getActivity(), url, title));
            }

            @Override
            public void onWelfareOnClick(String url, String title, View v) {
                startActivity(PictureActivity.newIntent(getActivity(), url, title));
            }
        });

        this.dailydetailRv.setAdapter(detailAdapter);
    }

    @Override
    protected void setupFragmentComponent() {
        //将present构造函数注入到里面
        MyApplication.get(context).getAppComponent()
                .plus(new NewDetailFragmentModule(this))
                .inject(this);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecentPresent.detachView();
    }

    @Override
    protected void initData() {
        mRecentPresent.getRecentData(new Date());
    }

    @Override
    public void showData(ArrayList<ArrayList<GankDataBean>> mGankList) {
        if (mGankList != null){
            detailAdapter.setList(mGankList);
            detailAdapter.notifyDataSetChanged();
        }
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

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

}
