package ren.solid.materialdesigndemo.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ren.solid.materialdesigndemo.SolidApplication;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:15:02
 */
public class SolidHttpUtils {

    private Handler mHandler;
    private static SolidHttpUtils mInstance;

    private SolidHttpUtils() {
        mHandler = new Handler(Looper.getMainLooper());
    }


    public static SolidHttpUtils getInstance() {
        if (mInstance == null)
            mInstance = new SolidHttpUtils();
        return mInstance;
    }

    public void loadString(final String url, final HttpCallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onLoading();
                        }
                    });

                    Response response = client.newCall(request).execute();

                    final String result = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(result);
                        }
                    });

                } catch (final IOException e) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onError(e);
                        }
                    });
                }
            }
        }).start();
    }

    public void loadImage(String url, ImageView iv) {
        Picasso.with(SolidApplication.getInstance()).load(url).into(iv);
    }

    public interface HttpCallBack {
        void onLoading();

        void onSuccess(String result);

        void onError(Exception e);
    }
}
