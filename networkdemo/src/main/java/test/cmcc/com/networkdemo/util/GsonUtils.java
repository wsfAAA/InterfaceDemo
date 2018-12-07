package test.cmcc.com.networkdemo.util;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.WeakHashMap;


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

    /**
     * 请求参数转Json
     *
     * @return String
     */
    public static String toJson(WeakHashMap<String, Object> params) {
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                json.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json.toString();
    }

}
