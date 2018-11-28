package test.cmcc.com.networkdemo.util;

import com.google.gson.Gson;


/**
 * Created by wsf on 2018/11/27.
 */

public class GsonUtils {

    private static Gson mGson;

    public static <T> T fromJson(String json, Class<T> classOfT) {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson.fromJson(json, classOfT);
    }
}
