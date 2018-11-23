package test.cmcc.com.networkdemo.retrofit.interceptor;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共请求头
 */
public class HeaderInterceptor implements Interceptor {
    private Map<String, String> headers;
    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request()
                .newBuilder();

        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        Log.d("RetrofitClent",  "Okhttp url:" + builder.build().url());
        return chain.proceed(builder.build());

    }
}