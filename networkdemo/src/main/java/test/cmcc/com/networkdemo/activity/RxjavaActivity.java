package test.cmcc.com.networkdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.net.RestClient;
import test.cmcc.com.networkdemo.net.callback.IError;
import test.cmcc.com.networkdemo.net.callback.IFailure;
import test.cmcc.com.networkdemo.net.callback.ISuccess;

public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        RestClient.builder().url("/wxarticle/chapters/json").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                ToastUtils.showShort(response);
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                ToastUtils.showShort(code + "  " + msg);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {
                Log.i("wsf",""+throwable);
                ToastUtils.showShort("失败:  "+throwable.toString());
            }
        }).build().get();
    }
}
