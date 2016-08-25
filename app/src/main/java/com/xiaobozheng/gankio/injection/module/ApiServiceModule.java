package com.xiaobozheng.gankio.injection.module;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiaobozheng.gankio.Constant.Constant;
import com.xiaobozheng.gankio.data.API.ApiManager;
import com.xiaobozheng.gankio.data.API.GankApiManagerService;
import com.xiaobozheng.gankio.mvp.model.impl.RecycentModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaobozheng on 8/23/2016.
 */
@Module
public class ApiServiceModule {

    String mBaseUrl;

    public ApiServiceModule(String baseUrl){
        this.mBaseUrl = baseUrl;
    }

    //提供依赖
    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                });
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(Constant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return  httpClientBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GankApiManagerService provideApiService(Retrofit retrofit){
        return retrofit.create(GankApiManagerService.class);
    }

    @Provides
    @Singleton
    ApiManager providerApiManager(GankApiManagerService gankApiManagerService){
        return new ApiManager(gankApiManagerService);
    }

    @Provides
    @Singleton
    RecycentModel provideREcycentModel(ApiManager apiManager){
        return new RecycentModel(apiManager);
    }
}
