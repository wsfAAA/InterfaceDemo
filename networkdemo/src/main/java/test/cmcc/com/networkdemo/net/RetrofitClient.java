package test.cmcc.com.networkdemo.net;

import android.content.Context;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import test.cmcc.com.networkdemo.net.callback.IError;
import test.cmcc.com.networkdemo.net.callback.IFailure;
import test.cmcc.com.networkdemo.net.callback.IRequest;
import test.cmcc.com.networkdemo.net.callback.ISuccess;
import test.cmcc.com.networkdemo.net.callback.RequestCallBack;
import test.cmcc.com.networkdemo.net.ui.RetrofitLoader;
import test.cmcc.com.networkdemo.net.ui.LoaderStyle;

/**
 * Created by wsf on 2018/11/26.
 */

public class RetrofitClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BOOY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    public RetrofitClient(String url, WeakHashMap<String, Object> params, IRequest request,
                          ISuccess iSuccess, IFailure iFailure, IError iError, RequestBody booy,
                          Context context, LoaderStyle loaderStyle) {
        this.URL = url;
        this.IREQUEST = request;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BOOY = booy;
        PARAMS.putAll(params);
        this.CONTEXT=context;
        this.LOADER_STYLE=loaderStyle;
    }

    public static RetrofitClientBuilder builder() {
        return new RetrofitClientBuilder();
    }

    private void request(HttpMethod method) {
        RetrofitService service = RetrofitCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (LOADER_STYLE != null) {
            RetrofitLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBack(IREQUEST, IERROR, IFAILURE, ISUCCESS,LOADER_STYLE);
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        request(HttpMethod.POST);
    }

    public void put() {
        request(HttpMethod.PUT);
    }

    public void delete() {
        request(HttpMethod.DELETE);
    }
}
