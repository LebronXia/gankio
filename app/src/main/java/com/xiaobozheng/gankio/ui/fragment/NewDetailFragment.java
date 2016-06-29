package com.xiaobozheng.gankio.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.RecentlyBean;
import com.xiaobozheng.gankio.mvp.presenter.impl.RecentPresent;
import com.xiaobozheng.gankio.mvp.view.RecentView;
import com.xiaobozheng.gankio.ui.base.BaseFragment;

import java.util.Calendar;
import java.util.List;

/**
 * Created by xiaobozheng on 6/27/2016.
 */
public class NewDetailFragment extends BaseFragment implements RecentView{

    private RecentPresent mRecentPresent;
    private int mYear, mMonth, mDay;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {
        this.mRecentPresent = new RecentPresent(this);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mRecentPresent.getRecentData(mYear, mMonth, mDay);
    }

    @Override
    public void showData(GankDaily gankDaily) {
        if (gankDaily != null){
           // Logger.d("haha", recentlyBean.getAndroid().get(0).getWho());
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

}
