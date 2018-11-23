package test.cmcc.com.networkdemo.retrofit;


import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import test.cmcc.com.networkdemo.BuildConfig;
import test.cmcc.com.networkdemo.MyApplication;
import test.cmcc.com.networkdemo.retrofit.interceptor.CacheInterceptor;

/**
 * Created by caominyan on 2018/4/4.
 */
public class MiguClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 10000;
    private static final int READ_TIMEOUT_MILLIS = 5000;

    private static boolean mOpenNetworkLog = false;

    private static OkHttpClient mOkHttpClient;
    private static File httpCacheDirectory; //缓存目录
    private static Cache cache;

    static {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(MyApplication.getInstance().getCacheDir(), "tamic_cache");
        }
        Log.i("wsf", "httpCacheDirectory:  " + httpCacheDirectory);
        try {
            if (cache == null && httpCacheDirectory != null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            throw new RuntimeException("MiguClient 缓存对象异常!");
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            builder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            if (cache != null) {
                builder.cache(cache);
            }
//            builder.addInterceptor(new HeaderInterceptor(headers));
            builder.addInterceptor(new CacheInterceptor(MyApplication.getInstance()));

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(new LogInterceptor());// TODO: 2018/11/19 网络日志打印
            }

//            ConnectionPool connectionPool = new ConnectionPool(8, 15, TimeUnit.SECONDS);       // 容纳8个空闲连接，这些连接在15分钟的不活动之后将被清除。
//            builder.connectionPool(connectionPool);      //设置用于回收HTTP和HTTPS连接的连接池。如果未设置，将使用一个新的连接池.

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException("OkHttpClient 配置异常!");
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient(int connectTimeOut, int readTimeOut) {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS);
            builder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);
            builder.cache(cache);
//            builder.addInterceptor(new HeaderInterceptor(headers));
            builder.addInterceptor(new CacheInterceptor(MyApplication.getInstance()));

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(new LogInterceptor());
            }

//            ConnectionPool connectionPool = new ConnectionPool(8, 15, TimeUnit.SECONDS);
//            builder.connectionPool(connectionPool);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException("OkHttpClient 配置异常!");
        }
    }
}
