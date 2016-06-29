package com.xiaobozheng.gankio.data.API;

import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.RecentlyBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private static final int DEFAULT_TIMEOUT = 5;

    private GankApiManagerService mGankApiManagerService;
    private Retrofit retrofit;

    private ApiManager(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
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
    public void getRecentlyData(Subscriber<GankDaily> subscriber, int year, int month, int day){
        //yao gen qing qiu guolai de  shu ju baochi  yizhi
        mGankApiManagerService.getRecentlyData(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
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



}
