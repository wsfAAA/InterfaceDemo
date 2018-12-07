package test.cmcc.com.networkdemo.rxnet;


import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 使用构建者模式
 */
public final class RestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();//WeakHashMap 不在使用时会被gc
    private String mUrl = null;
    private RequestBody mBody = null;
    private File mFile = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mBody, mFile);
    }
}
