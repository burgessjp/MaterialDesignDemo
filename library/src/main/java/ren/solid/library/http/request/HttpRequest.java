package ren.solid.library.http.request;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:9:57
 */
public class HttpRequest {

    public enum Method {
        GET, POST
    }

    private String url;
    private Method method;
    private Map<String, String> headers;
    private Map<String, String> params;


    private Map<String, File> files;


    public HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public static class Builder {
        private String url;
        private Method method;
        private Map<String, String> headers;
        private Map<String, String> params;
        private Map<String, File> files;

        public Builder() {
            this.url = "";
            this.method = Method.GET;
            headers = null;
            params = null;
            files = null;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder addHeader(String key, String val) {
            if (headers == null) {
                headers = new HashMap<>();
            }
            this.headers.put(key, val);
            return this;
        }

        public Builder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder addParams(String key, String val) {
            if (params == null) {
                params = new HashMap<>();
            }
            this.params.put(key, val);
            return this;
        }

        public Builder files(Map<String, File> files) {
            this.files = files;
            return this;
        }

        public Builder addFiles(String key, File file) {
            if (files == null) {
                files = new HashMap<>();
            }
            this.files.put(key, file);
            return this;
        }


        public HttpRequest create() {
            if ("".equals(url)) throw new IllegalArgumentException("the url cannot be empty");
            return new HttpRequest(this);
        }
    }


}
