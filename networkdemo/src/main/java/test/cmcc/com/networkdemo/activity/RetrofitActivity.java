package test.cmcc.com.networkdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.TestBean;
import test.cmcc.com.networkdemo.retrofit.DataServiceManager;
import test.cmcc.com.networkdemo.retrofit.service.RetrofitService;

public class RetrofitActivity extends AppCompatActivity {

    private Call<Object> home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReq();
            }
        });
    }

    private void getReq() {
        //1、请求拿到baseactivity中去，来控制请求的生命周期
        //2、简化封装，添加统一错误的回调
        RetrofitService serviceAPI = DataServiceManager.getServiceAPI(RetrofitService.baseUrl, RetrofitService.class);
        home = serviceAPI.getHome();
        home.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                String toString = response.body().toString();
                Toast.makeText(RetrofitActivity.this, "" + toString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(RetrofitActivity.this, "错误: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (home != null) {
            home.cancel();
        }
    }
}
