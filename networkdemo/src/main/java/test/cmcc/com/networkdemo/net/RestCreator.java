package test.cmcc.com.networkdemo.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.cmcc.com.networkdemo.retrofit.adapter.DoubleDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.IntegerDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.LongDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.StringDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.ToStringConverterFactory;

/**
 * Created by wsf on 2018/11/23.
 */

public class RestCreator {
    public static final String BASE_URL = "http://wanandroid.com/";

    /**
     * 参数容器
     */
    public static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REAST_SERVICE;
    }

    /**
     * 构建全局Retrofit客户端  静态内部类 单利模式
     */
    public static final class RetrofitHolder {
        private static final Retrofit RETROFIT_CLICENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(GsonHolder.CUSTOM_GSON_INSTANCE))  //gson解析器
                .addConverterFactory(new ToStringConverterFactory())  //解析成string
//                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**
     * 自定义gson解析器
     */
    public static final class GsonHolder {
        private static final Gson CUSTOM_GSON_INSTANCE = new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .registerTypeAdapter(String.class,new StringDefault0Adapter())
                .create();
    }

    /**
     * RestService 实例
     */
    public static final class RestServiceHolder {
        private static final RestService REAST_SERVICE = RetrofitHolder.RETROFIT_CLICENT.create(RestService.class);
    }

    /**
     * OkHttpClient 相关配置
     */
    public static final class OkHttpClientHolder {
        private static final int CONNECT_TIME_OUT = 10;
        private static final int READ_TIME_OUT = 5;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

}
