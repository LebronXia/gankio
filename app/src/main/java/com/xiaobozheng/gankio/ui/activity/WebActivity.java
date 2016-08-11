package com.xiaobozheng.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.umeng.analytics.MobclickAgent;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;
import com.xiaobozheng.gankio.util.Shares;
import com.xiaobozheng.gankio.util.Support;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 7/22/2016.
 */
public class WebActivity extends BaseActivity{

    private static final String EXTRA_URL = "extre_url";
    private static final String EXTRA_TITLE = "extra_title";
    @Bind(R.id.progressbar)
    NumberProgressBar mNumberProgressBar;
    @Bind(R.id.webView)
    WebView mWebView;

    String mUrl = "";
    String mTitle = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        getSupportActionBar().setTitle(mTitle);
        WebSettings mWebSetting = mWebView.getSettings();
        //支持缩放
        mWebSetting.setSupportZoom(true);
        //缩放至屏幕大小
        mWebSetting.setLoadWithOverviewMode(true);
        //将图片调整到适合webView的大小
        mWebSetting.setUseWideViewPort(true);
        //
        mWebSetting.setDefaultTextEncodingName("utf-8");
        //支持自动加载图片
        mWebSetting.setLoadsImagesAutomatically(true);
        //支持内容重新布局
        mWebSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //开启 Application Caches 功能
        mWebSetting.setAppCacheEnabled(true);
        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        mWebSetting.setJavaScriptEnabled(true);
        //WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
        mWebView.setWebChromeClient(mWebChromeClient);
        //WebViewClient就是帮助WebView处理各种通知、请求事件的。
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.loadUrl(mUrl);
    }

    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_share:
                if (TextUtils.isEmpty(mUrl)){
                    String shareText = mUrl + mTitle + "【东京食尸鬼】";
                    Shares.share(WebActivity.this, shareText);
                } else {
                    Shares.share(WebActivity.this,"神秘星球消失~");
                }
                return true;
            case R.id.action_refresh:
                mWebView.reload();
                return true;
            case R.id.action_copy_url:
                String copyDone = "复制成功";
                Support.copyToClipBoard(this, mWebView.getUrl(), copyDone);
                return true;
            case R.id.action_open_url:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(mUrl);
                intent.setData(uri);
                //查询是否有符合Uri.parse(url), mimetype这两个条件的Activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    //Toasts.showLong(R.string.tip_open_fail);
                    showToast("复制失败");
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //设置是否有返回键
    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_BACK:
                if (mWebView.canGoBack()){
                    mWebView.goBack();
                } else {
                    finish();
                }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
        MobclickAgent.onPause(this);

    }


    @Override protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null){
            mWebView.destroy();
        }
    }

    /**
     * 对Web进度监听
     */
    WebChromeClient mWebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mNumberProgressBar.setProgress(newProgress);
            if (newProgress == 100){
                mNumberProgressBar.setVisibility(View.GONE);
            } else {
                mNumberProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    };

    WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null)
                //让url在此界面打开
                view.loadUrl(url);
            return true;
        }
    };

    public static Intent newIntent(Context context,String url, String title){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        return intent;
    }

}
