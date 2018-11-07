package test.cmcc.com.basedemo.basemvp.base;


/**
 * Created by wsf on 2018/11/6.
 */

public class BaseModel<P extends BasePresenter> {

    protected final String TAG = getClass().getSimpleName();

    protected P mPresenter;

    public P getmPresenter() {
        return mPresenter;
    }

    public void setmPresenter(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    public BaseModel() {
    }

    public BaseModel(P mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void onDestroy() {

    }
}
