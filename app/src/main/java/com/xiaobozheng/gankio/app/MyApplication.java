package com.xiaobozheng.gankio.app;

import android.app.Application;

import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xiaobozheng on 6/29/2016.
 */
public class MyApplication extends Application {

    private static final String YOUR_TAG ="xiamu";
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(YOUR_TAG);
        // The realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }
}
