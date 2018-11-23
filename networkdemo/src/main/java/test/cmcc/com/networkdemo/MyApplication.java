package test.cmcc.com.networkdemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by wsf on 2018/11/19.
 */

public class MyApplication extends Application {

    public static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        Utils.init(this);
    }

    public static synchronized MyApplication getInstance() {
        return sInstance;
    }
}
