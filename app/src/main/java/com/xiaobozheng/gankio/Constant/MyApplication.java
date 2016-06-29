package com.xiaobozheng.gankio.Constant;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by xiaobozheng on 6/29/2016.
 */
public class MyApplication extends Application {

    private static final String YOUR_TAG ="xiamu";
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(YOUR_TAG);
    }
}
