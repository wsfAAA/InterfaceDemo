package test.cmcc.com.networkdemo.retrofit;


import io.reactivex.Observer;

/**
 * Created by yutao on 2018/7/4.
 */

public abstract class MgBaseObserver<T> implements Observer<T> {


//    @Override
//    public void onError(Throwable e) {
//        Log.e("MgBaseObserver", "onError: " + e.getMessage());
//    }

    @Override
    public void onComplete() {

    }
}
