package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

/**
 * Created by xiaobozheng on 6/27/2016.
 */
public class SortFragment extends BaseFragment{
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

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
