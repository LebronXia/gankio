package com.xiaobozheng.gankio.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xiaobozheng.gankio.R;

import java.net.URI;

/**
 * Created by xiaobozheng on 7/22/2016.
 */
public class Shares {

    public static void shareImage(Context context, Uri uri, String title ){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent, title));
    }
    public static void share(Context context, String extraText ){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.action_share)));
    }

}
