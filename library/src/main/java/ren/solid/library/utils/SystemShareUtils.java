package ren.solid.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:12:45
 */
public class SystemShareUtils {

    public static void shareText(Context ctx, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }

    public static void shareImage(Context ctx, Uri uri) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType("image/jpeg");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }

    public static void shareImageList(Context ctx, ArrayList<Uri> uris) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putExtra(Intent.EXTRA_STREAM, uris);
        sendIntent.setType("image/*");
        ctx.startActivity(Intent.createChooser(sendIntent, "分享至"));
    }
}
