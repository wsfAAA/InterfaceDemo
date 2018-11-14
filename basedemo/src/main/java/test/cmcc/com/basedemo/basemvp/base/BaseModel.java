package test.cmcc.com.basedemo.basemvp.base;


/**
 * Created by wsf on 2018/11/6.
 */

public class BaseModel<P extends BasePresenter> {

    protected final String TAG = getClass().getSimpleName();

    protected P mBasePresenter;

    public P getmPresenter() {
        return mBasePresenter;
    }

    /**
     * 绑定 Presenter
     *
     * @param mPresenter
     */
    public void setmPresenter(P mPresenter) {
        this.mBasePresenter = mPresenter;
    }

    public BaseModel() {
    }

    public BaseModel(P mPresenter) {
        this.mBasePresenter = mPresenter;
    }

    public void onDestroy() {
        mBasePresenter = null;
    }
}
