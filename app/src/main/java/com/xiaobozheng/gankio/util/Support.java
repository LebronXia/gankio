package com.xiaobozheng.gankio.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobozheng on 7/22/2016.
 */
public class Support {

    public static void copyToClipBoard(Context context, String text, String success) {
        ClipData clipData = ClipData.newPlainText("meizhi_copy", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
    }

    /**
     * 检测网络是否可用
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    public static Observable<Uri> savaImageAndGetPathObservable(Context context, String url, String title){
        //Bitmap bitmap = Picasso.with(context).load(url).get();
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    bitmap = Picasso.with(context).load(url).get();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
                if (bitmap == null) {
                    subscriber.onError(new Exception("无法下载到图片"));
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();

            }
            //生成新的Obserable
        }).flatMap(bitmap -> {
                    //设置存放的位置
                    File appDir = new File(Environment.getExternalStorageDirectory(),"meizi");
                    if (!appDir.exists()){
                        appDir.mkdir();
                    }
                    String fileName = title.replace('/', '-') + ".jpg";
                    //创建file文件
                    File file = new File(appDir, fileName);
                    try {
                        //文件存储流
                        FileOutputStream outputStream = new FileOutputStream(file);
                        //boolean表达式
                        assert bitmap != null;
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    }  catch (IOException e) {
                        e.printStackTrace();
                    }
                     Uri uri = Uri.fromFile(file);

                    Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                    context.sendBroadcast(scannerIntent);
                    return Observable.just(uri);
        }).subscribeOn(Schedulers.io());
    }
}
