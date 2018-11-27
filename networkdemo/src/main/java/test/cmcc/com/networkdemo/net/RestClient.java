package test.cmcc.com.networkdemo.net;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import test.cmcc.com.networkdemo.net.callback.IError;
import test.cmcc.com.networkdemo.net.callback.IFailure;
import test.cmcc.com.networkdemo.net.callback.IRequest;
import test.cmcc.com.networkdemo.net.callback.ISuccess;
import test.cmcc.com.networkdemo.net.callback.RequestCallBack;

/**
 * Created by wsf on 2018/11/26.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private RequestBody BOOY;

    public RestClient(String url, WeakHashMap<String, Object> params, IRequest request, ISuccess iSuccess, IFailure iFailure, IError iError, RequestBody booy) {
        this.URL = url;
        this.IREQUEST = request;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.BOOY = booy;
        PARAMS.putAll(params);
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(OkhttpMethod method) {
        RestService service = RestCreator.getRestService();
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

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallBack(IREQUEST, IERROR, IFAILURE, ISUCCESS);
    }

    public void get(){
        request(OkhttpMethod.GET);
    }

    public void post(){
        request(OkhttpMethod.POST);
    }

    public void put(){
        request(OkhttpMethod.PUT);
    }

    public void delete(){
        request(OkhttpMethod.DELETE);
    }
}
