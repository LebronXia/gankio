package com.xiaobozheng.gankio.data.API;

import com.xiaobozheng.gankio.data.model.CategoryData;
import com.xiaobozheng.gankio.data.model.GankDaily;
import com.xiaobozheng.gankio.data.model.RecentlyBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiaobozheng on 6/20/2016.
 */
public interface GankApiManagerService {

    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getRecentlyData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    /**分类数据： http://gank.io/api/data/数据类型/请求个数/第几页*/
    @GET("data/{gankType}/{size}/{page}")
    Observable<CategoryData> getCategoryData(@Path("gankType") String gankType, @Path("size") int size, @Path("page") int page);
}
