package test.cmcc.com.networkdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.rxnet.RestClient;
import test.cmcc.com.networkdemo.rxnet.RestCreator;

public class RxNetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_net);

        findViewById(R.id.rx_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxGet();
            }
        });
    }

    private void rxGet() {
//        为什么异常

        //http://www.wanandroid.com/hotkey/json
        Observable<String> observable = RestClient.builder().url("hotkey/json").build().get();
//        WeakHashMap<String,Object> aa=new WeakHashMap<>();
//        Observable<String> observable = RestCreator.getRestService().get("hotkey/json", aa);
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("wsf", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("wsf", "onNext：  " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("wsf", "onError：  " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.i("wsf", "onComplete");
            }
        });
    }
}
