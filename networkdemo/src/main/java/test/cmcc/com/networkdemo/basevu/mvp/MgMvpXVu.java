package test.cmcc.com.networkdemo.basevu.mvp;


import butterknife.ButterKnife;
import test.cmcc.com.networkdemo.basevu.persenter.BasePresenterX;

/**
 * Created by yutao on 2018/6/28.
 */

public class MgMvpXVu<P extends BasePresenterX> extends BaseMvpXVu<P> {

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
