package com.xiaobozheng.gankio.data.API;

import com.xiaobozheng.gankio.data.model.RecentBeanResult;
import com.xiaobozheng.gankio.data.model.RecentlyBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiaobozheng on 6/20/2016.
 */
public interface GankApiManagerService {

    @GET("/day/{year}/{month}/{day}")
    Observable<RecentBeanResult<RecentlyBean>> getRecentlyData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

}
