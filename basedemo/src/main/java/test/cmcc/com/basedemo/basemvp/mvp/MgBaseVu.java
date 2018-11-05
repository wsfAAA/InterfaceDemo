package test.cmcc.com.basedemo.basemvp.mvp;


import butterknife.ButterKnife;
import test.cmcc.com.basedemo.basemvp.vu.BaseVu;

/**
 * Created by yutao on 2018/6/21.
 */

public class MgBaseVu<T> extends BaseVu<T> {

    @Override
    public void bindView() {
        ButterKnife.bind(this, view);
    }
}
