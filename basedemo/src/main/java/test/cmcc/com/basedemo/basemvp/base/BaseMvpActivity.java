package test.cmcc.com.basedemo.basemvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import test.cmcc.com.basedemo.basemvp.TUtil;

/**
 * Created by wsf on 2018/11/14.
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasePresenter = TUtil.getT(this, 0);//绑定Presenter
        if (mBasePresenter != null) {
            mBasePresenter.addActivityInstanc(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
    }
}
