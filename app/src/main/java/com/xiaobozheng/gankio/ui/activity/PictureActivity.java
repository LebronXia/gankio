package com.xiaobozheng.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.umeng.analytics.MobclickAgent;
import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;
import com.xiaobozheng.gankio.util.GlideUtils;
import com.xiaobozheng.gankio.util.Shares;
import com.xiaobozheng.gankio.util.Support;

import java.io.File;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xiaobozheng on 7/1/2016.
 */
public class PictureActivity extends BaseActivity{

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";

    @Bind(R.id.iv_picture)
    ImageView mImageView;
    PhotoViewAttacher mPhotoViewAttacher;
    private GlideBitmapDrawable glideBitmapDrawable;

    private String mImageUrl, mImageTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void initView() {
        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
       // GlideUtils.display(mImageView, mImageUrl);
        setAppBarAlpha(0.7f);
        setTitle(mImageTitle);
        setPhotoAttacher();
    }

    private void setPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener((view, x, y) -> hideOrShowToolbar());
    }

    @Override
    protected void initData() {
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        Glide.with(this)
                .load(mImageUrl)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        PictureActivity.this.glideBitmapDrawable = (GlideBitmapDrawable) resource;
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(this.mImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_share:
                Support.savaImageAndGetPathObservable(this,glideBitmapDrawable,mImageUrl, mImageTitle)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(uri -> Shares.shareImage(this, uri, getString(R.string.share_meizhi_to))
                        ,error -> showToast(error.getMessage()));
                return true;
            case R.id.action_save:
                saveImageToGallery();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImageToGallery() {
        Subscription subscription = null;
        if (this.glideBitmapDrawable != null){
            //保存图片
            subscription = Support.savaImageAndGetPathObservable(this,glideBitmapDrawable,mImageUrl, mImageTitle)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(uri -> {
                        File appDir = new File(Environment.getExternalStorageDirectory(), "meizi");
                        String msg = String.format(getString(R.string.picture_has_save_to),
                                appDir.getAbsolutePath());
                        showToast(msg);
                    }, error -> showToast(error.getMessage() + "\n再试试~"));
        }else{
            Snackbar.make(mImageView, "图片还没加载成功", Snackbar.LENGTH_LONG).show();
        }

        addSubscription(subscription);
    }

    public static Intent newIntent(Context context, String url, String desc){
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        return intent;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
