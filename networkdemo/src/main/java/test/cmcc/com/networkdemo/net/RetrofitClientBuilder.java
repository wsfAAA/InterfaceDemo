package test.cmcc.com.networkdemo.net;

import android.content.Context;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import test.cmcc.com.networkdemo.net.callback.IError;
import test.cmcc.com.networkdemo.net.callback.IFailure;
import test.cmcc.com.networkdemo.net.callback.IRequest;
import test.cmcc.com.networkdemo.net.callback.ISuccess;
import test.cmcc.com.networkdemo.net.ui.LoaderStyle;

/**
 * Created by wsf on 2018/11/27.
 * 使用构建者模式
 */
public final class RetrofitClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RetrofitCreator.getParams();//WeakHashMap 不在使用时会被gc
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    public final RetrofitClient build() {
        return new RetrofitClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody,mContext,mLoaderStyle);
    }

    public RetrofitClientBuilder() {
    }

    //不需要别人修改方法 final
    public final RetrofitClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    /**
     * 请求体
     *
     * @param raw json格式
     * @return
     */
    public final RetrofitClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 添加参数
     *
     * @param params
     * @return
     */
    public final RetrofitClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RetrofitClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RetrofitClientBuilder onRequest(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    public final RetrofitClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RetrofitClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RetrofitClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    /**
     * 加载样式支持 LoaderStyle 枚举类型
     * @param context
     * @param style
     * @return
     */
    public final RetrofitClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 默认样式 BallClipRotatePulseIndicator
     * @param context
     * @return
     */
    public final RetrofitClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }
}