package com.xiaobozheng.gankio.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiaobozheng on 6/24/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    @Bind(R.id.app_bar_layout) protected AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutId());
        ButterKnife.bind(this);
        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if(canBack()){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initData();
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public boolean canBack(){
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void setAppBarAlpha(float alpha){
        mAppBarLayout.setAlpha(alpha);
    }

    /*********
     * Toast *
     *********/

    public void showToast(String msg) {
        this.showToast(msg, Toast.LENGTH_SHORT);
    }


    public void showToast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this, msg, duration);
        } else {
            ToastUtils.show(this, msg, ToastUtils.LENGTH_SHORT);
        }
    }


    public void showToast(int resId) {
        this.showToast(resId, Toast.LENGTH_SHORT);
    }


    public void showToast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this, resId, duration);
        } else {
            ToastUtils.show(this, resId, ToastUtils.LENGTH_SHORT);
        }
    }

    /*********
     * Toast *
     *********/
}
