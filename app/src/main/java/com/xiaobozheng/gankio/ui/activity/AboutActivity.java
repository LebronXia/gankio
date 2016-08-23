package com.xiaobozheng.gankio.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.view.MenuItem;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 8/12/2016.
 */
public class AboutActivity extends BaseActivity{

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        mCollapsingToolbarLayout.setTitle("关于gankio");
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
