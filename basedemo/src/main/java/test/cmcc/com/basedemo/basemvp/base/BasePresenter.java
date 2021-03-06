package test.cmcc.com.basedemo.basemvp.base;

import android.support.v4.app.FragmentActivity;

import test.cmcc.com.basedemo.basemvp.TUtil;
import test.cmcc.com.basedemo.basemvp.bk.BindViewModel;

public abstract class BasePresenter<V extends FragmentActivity, M extends BaseModel> implements BindViewModel<M> {

    protected final String TAG = getClass().getSimpleName();

    protected M mBaseModel;
    protected V mBaseView;

    public BasePresenter() {
        mBaseModel = creatModel();
        mBaseModel.setPresenter(this);
    }

    /**
     * 绑定 view
     *
     * @param activity
     */
    public void addActivityInstanc(V activity) {
        this.mBaseView = activity;
    }

    public void onDestroy() {
        if (mBaseModel != null) {
            mBaseModel.onDestroy();
            mBaseModel = null;
        }
        mBaseView = null;
    }

//    /**
//     * 绑定 model, 通过反射
//     *
//     * @return
//     */
//    @Override
//    public M bindModel() {
//        return TUtil.getT(this, 1);
//    }

    public abstract M creatModel();
}
