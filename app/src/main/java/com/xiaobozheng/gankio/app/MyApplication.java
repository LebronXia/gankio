package com.xiaobozheng.gankio.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.injection.component.AppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xiaobozheng on 6/29/2016.
 */
public class MyApplication extends Application {

    private static final String YOUR_TAG ="xiamu";
    private AppComponent appComponent;
    public static Application get(Context context){
        return (Application) context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(YOUR_TAG);
        //完成整个注入

        // The realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }
}
