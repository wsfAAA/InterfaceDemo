package test.cmcc.com.networkdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.RestBean;
import test.cmcc.com.networkdemo.net.RetrofitClient;
import test.cmcc.com.networkdemo.net.callback.IError;
import test.cmcc.com.networkdemo.net.callback.IFailure;
import test.cmcc.com.networkdemo.net.callback.ISuccess;
import test.cmcc.com.networkdemo.net.ui.LoaderStyle;
import test.cmcc.com.networkdemo.util.GsonUtils;

public class NetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get();
            }
        });

        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
    }

    private void upload() {
        RetrofitClient.builder().url("").success(new ISuccess() {
            @Override
            public void onSuccess(String response) {

            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {

            }
        }).file("").build().upload();
    }

    private void get() {
        RetrofitClient.builder().url("/wxarticle/chapters/json")
                .loader(this, LoaderStyle.BallBeatIndicator).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                RestBean restBean = GsonUtils.fromJson(response, RestBean.class);
                ToastUtils.showShort("成功");
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                ToastUtils.showShort(code + "  " + msg);
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {
                ToastUtils.showShort("失败:  " + throwable.toString());
            }
        }).build().get();
    }
}
