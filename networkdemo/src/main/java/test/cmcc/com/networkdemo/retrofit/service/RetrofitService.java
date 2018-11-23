package test.cmcc.com.networkdemo.retrofit.service;


import retrofit2.Call;
import retrofit2.http.GET;
import test.cmcc.com.networkdemo.TestBean;

/**
 * Created by yutao on 2018/1/22.
 * 接口实现
 */

public interface RetrofitService {
    public static final String baseUrl = "http://www.wanandroid.com/";

//    http://www.wanandroid.com/article/list/0/json

    @GET("/article/list/0/json")
     Call<Object> getHome();
}