package com.xiaobozheng.gankio.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.injection.component.AppComponent;
import com.xiaobozheng.gankio.injection.component.DaggerAppComponent;
import com.xiaobozheng.gankio.injection.module.ApiServiceModule;
import com.xiaobozheng.gankio.injection.module.AppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by xiaobozheng on 6/29/2016.
 */
public class MyApplication extends Application {

    private static final String YOUR_TAG ="xiamu";
    private AppComponent appComponent;

    public static MyApplication get(Context context){
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(YOUR_TAG);
        //完成整个注入
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule(Constant.BASE_URL))
                .build();
//        appComponent = Dagger.builder()
//                .appModule(new AppModule(this))
//                .apiServiceModule(new ApiServiceModule(Constant.BASE_URL))
//                .build();

        // The realm file will be located in Context.getFilesDir() with name "default.realm"
//        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(configuration);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
