package ren.solid.library.http.provider.base;

import ren.solid.library.http.callback.HttpCallBack;
import ren.solid.library.http.callback.adapter.FileHttpCallBack;
import ren.solid.library.http.request.HttpRequest;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:9:49
 */
public interface IHttpProvider {

    void loadString(HttpRequest request, HttpCallBack callBack);

    void download(String downloadUrl,String savePath, FileHttpCallBack callBack);
}
