package com.xiaobozheng.gankio.data.API;

import com.google.common.eventbus.Subscribe;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiaobozheng.gankio.data.model.CategoryData;
import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.GankDataBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 对请求过程进行封装
 * Created by xiaobozheng on 6/22/2016.
 */
public class ApiManager {
    private static final String BASE_URL = "http://gank.io/api/";
    private static final int DEFAULT_TIMEOUT = 50;

    private GankApiManagerService mGankApiManagerService;
    private Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            }).create();

    private ApiManager(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mGankApiManagerService = retrofit.create(GankApiManagerService.class);
    }



    private static class SingletonHolder{
        private static final ApiManager INSTANCE = new ApiManager();
    }

    public static ApiManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * //获取最近一天的数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    public void getRecentlyData(Subscriber<ArrayList<ArrayList<GankDataBean>>> subscriber, int year, int month, int day){
        //yao gen qing qiu guolai de  shu ju baochi  yizhi
        mGankApiManagerService.getRecentlyData(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(GankDaily -> GankDaily.results)
                .map(this::addAllResults)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取资源分类数据
     * @param subscriber
     * @param type
     * @param size
     * @param page
     */
    public void getCategoryData(Subscriber<List<GankDataBean>> subscriber, String type, int size, int page){
        mGankApiManagerService.getCategoryData(type, size, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(CategoryData -> CategoryData.results)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
//    private class RecentResultFunc<T> implements Func1<RecentBeanResult<T>,T>{
//        @Override
//        public T call(RecentBeanResult<T> tRecentBeanResult) {
//            if (!tRecentBeanResult.isError()){
//                throw new RuntimeException();
//            }
//            return tRecentBeanResult.getResults();
//        }
//    }
    //将数据添加到Arraylist中
    private ArrayList<ArrayList<GankDataBean>> addAllResults(GankDaily.DailyResults results){
        ArrayList<ArrayList<GankDataBean>> mGankList = new ArrayList<>();
        if (results.welfareData != null && results.welfareData.size() > 0){
            mGankList.add(results.welfareData);
        }
        if (results.androidData != null && results.androidData.size() > 0){
            mGankList.add(results.androidData);
        }
        if (results.iosData != null && results.iosData.size() > 0){
            mGankList.add(results.iosData);
        }
        if (results.jsData != null && results.jsData.size() > 0){
            mGankList.add(results.jsData);
        }
        if (results.videoData != null && results.videoData.size() > 0){
            mGankList.add(results.videoData);
        }
        if (results.recommendData != null && results.recommendData.size() > 0){
            mGankList.add(results.recommendData);
        }
        if (results.resourcesData != null && results.resourcesData.size() > 0){
            mGankList.add(results.resourcesData);
        }
        return mGankList;
    }



}
