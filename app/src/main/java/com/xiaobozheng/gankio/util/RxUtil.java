package com.xiaobozheng.gankio.util;

import android.icu.text.DateFormat;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xiaobozheng.gankio.app.MyApplication;

import java.lang.reflect.Field;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobozheng on 11/15/2016.
 */
public class RxUtil {

    //从SD卡磁盘中读取数据
    public static <T>Observable rxCreateDiskObservable(final String key, final Class<T> clazz){
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //读取
                String json = ACache.get(MyApplication.getInstance()).getAsString(key);
                if (!TextUtils.isEmpty(json)){
                    subscriber.onNext(json);
                }
                subscriber.onCompleted();
            }
        }).map(new Func1<String, T>() {    //将json转为所需的类
            @Override
            public T call(String s) {
                return new Gson().fromJson(s, clazz);
            }
        }) .subscribeOn(Schedulers.io());
    }

    //缓存进内存Cache中
    public static <T> Observable.Transformer<T, T> rxCacheListHelper(final String key){
        //换言之就是：Transformers可以通过它将一种类型的Observable转换成另一种类型的Observable
        return new Observable.Transformer<T, T>(){
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())//指定是一个新线程
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(T data) {
                                Schedulers.io().createWorker().schedule(new Action0() {
                                    @Override
                                    public void call() {
                                        Logger.d("get data from network finish ,start cache...");
                                        //通过反射获取list，再判空决定是否缓存
                                        Class clazz = data.getClass();
                                        Field[] fields = clazz.getFields();
                                        for (Field field : fields){
                                            String className = field.getType().getSimpleName();

                                            //得到属性值
                                            if (className.equalsIgnoreCase("List")){
                                                try {
                                                    List list = (List) field.get(data);
                                                    Logger.d("list==" + list);
                                                    if (!list.isEmpty()) {
                                                        ACache.get(MyApplication.getInstance())
                                                                .put(key, new Gson().toJson(data, clazz));
                                                        Logger.d("cache finish");
                                                    }
                                                } catch (IllegalAccessException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }
                                    }
                                });
                            }
                        }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
