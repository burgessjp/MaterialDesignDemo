package ren.solid.library.http.callback;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:9:52
 */
public abstract class HttpCallBack<T> {

    public void onStart() {
    }

    public void onLoading(int progress) {
    }

    public abstract void onSuccess(T result);

    public abstract void onError(Exception e);

    public abstract T parseData(String result);
}

