package com.xiaobozheng.gankio.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.xiaobozheng.gankio.R;
import com.xiaobozheng.gankio.ui.base.BaseActivity;
import com.xiaobozheng.gankio.util.GlideUtils;
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
        GlideUtils.display(mImageView, mImageUrl);
        setAppBarAlpha(0.7f);
        setTitle(mImageTitle);

    }

    @Override
    protected void initData() {
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
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

                break;
            case R.id.action_save:
                saveImageToGallery();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveImageToGallery() {

    }

    public static Intent newIntent(Context context, String url, String desc){
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        return intent;
    }
}
