package test.cmcc.com.networkdemo.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by wsf on 2018/11/23.
 */

public interface ReastService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);


}
