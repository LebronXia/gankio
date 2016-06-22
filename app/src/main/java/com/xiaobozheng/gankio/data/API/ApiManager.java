package com.xiaobozheng.gankio.data.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by xiaobozheng on 6/22/2016.
 */
public class ApiManager {
    private static String BASEURL = "http://gank.io/api";

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    private static final GankApiManagerService apiManager = sRetrofit.create(GankApiManagerService.class);

    /**
     * //获取最近一天的数据
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Observable getRecentlyData(int year, int month, int day){
        return apiManager.getRecentlyData(year, month, day);
    }

}
