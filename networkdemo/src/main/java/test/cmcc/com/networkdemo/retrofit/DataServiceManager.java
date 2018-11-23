package test.cmcc.com.networkdemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.cmcc.com.networkdemo.retrofit.adapter.DoubleDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.IntegerDefault0Adapter;
import test.cmcc.com.networkdemo.retrofit.adapter.LongDefault0Adapter;

public class DataServiceManager {

    public static <T> T getServiceAPI(String endpoint, final Class<T> service) {
        OkHttpClient okHttpClient = MiguClient.getUnsafeOkHttpClient();
        StringBuffer buffer = new StringBuffer();
        buffer.append(endpoint).append("/");
        final Gson customGsonInstance = new GsonBuilder().
                 registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(buffer.toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))  //gson解析器
                .client(okHttpClient)
                .build();
        return retrofit.create(service);
    }

    /**
     * 可设置 读取请求超时
     *
     * @param endpoint
     * @param service
     * @param connectTimeOut
     * @param readTimeOut
     * @param <T>
     * @return
     */
    public static <T> T getServiceAPI(String endpoint, final Class<T> service, int connectTimeOut, int readTimeOut) {
        OkHttpClient okHttpClient = MiguClient.getUnsafeOkHttpClient(connectTimeOut, readTimeOut);
        StringBuffer buffer = new StringBuffer();
        buffer.append(endpoint).append("/");
        final Gson customGsonInstance = new GsonBuilder().registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter())
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(buffer.toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //绑定rxjavas
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                .client(okHttpClient)
                .build();

        return retrofit.create(service);
    }

}
