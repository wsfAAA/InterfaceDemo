package test.cmcc.com.basedemo.basemvp.mvp;


import butterknife.ButterKnife;
import test.cmcc.com.basedemo.basemvp.mvp.BaseMvpXVu;
import test.cmcc.com.basedemo.basemvp.persenter.BasePresenterX;

/**
 * Created by yutao on 2018/6/28.
 */

public class MgMvpXVu<P extends BasePresenterX> extends BaseMvpXVu<P> {

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
