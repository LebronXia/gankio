package com.xiaobozheng.gankio.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;

import butterknife.Bind;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by xiaobozheng on 8/12/2016.
 */
public class GitHubLoginWebActivity extends BaseActivity{

    @Bind(R.id.circle_progress)
    MaterialProgressBar mCircleProgressView;
    @Bind(R.id.progressbar_layout)
    LinearLayout mLoadLayout;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initView() {
        mToolbar.setTitle("GitHub授权登陆");
        mToolbar.setNavigationIcon(R.mipmap.ic_clear);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}
