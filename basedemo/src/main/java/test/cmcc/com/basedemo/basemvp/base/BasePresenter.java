package test.cmcc.com.basedemo.basemvp.base;

import android.support.v7.app.AppCompatActivity;

import test.cmcc.com.basedemo.basemvp.TUtil;
import test.cmcc.com.basedemo.basemvp.bk.BindViewModel;

public class BasePresenter<V extends AppCompatActivity, M extends BaseModel> implements BindViewModel<V, M> {

    protected final String TAG = getClass().getSimpleName();

    public V mView;
    public M mModel;
//    public RxManager mRxManager = new RxManager();


    public BasePresenter() {
        mView = bindView();
        mModel = bindModel();
        mModel.setmPresenter(this);
    }

    public void onDestroy() {
//        mRxManager.clear();
        if (mModel != null) {
            mModel.onDestroy();
            mModel = null;
        }
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public V bindView() {
        return TUtil.getT(this, 0);
    }

    @Override
    public M bindModel() {
        return TUtil.getT(this, 1);
    }

}
