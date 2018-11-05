package test.cmcc.com.basedemo.test.mvpvu;

import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import test.cmcc.com.basedemo.R;
import test.cmcc.com.basedemo.basemvp.mvp.MgMvpXVu;

/**
 * Created by wsf on 2018/11/5.
 * <p>
 * 注意：vu  extends BaseMvpXVu  butterknife没有注册无效，需 extends MgMvpXVu
 *      控制view层
 */
public class MvpVu extends MgMvpXVu<MvpPersenter> {

    @BindView(R.id.tv_mgmvp)
    TextView tvMgmvp;

    @Override
    public int getLayoutId() {
        Log.e("wsf", TAG + ":getLayoutId");
        return R.layout.activity_presenter;
    }

    @Override
    public void bindView() {
        super.bindView();
        Log.e("wsf", TAG + ":bindView  " + tvMgmvp);
    }

    public void test(){
        mPresenter.testPersenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("wsf", TAG + ":onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("wsf", TAG + ":onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("wsf", TAG + ":onDestroy");
    }

    @OnClick(R.id.tv_mgmvp)
    public void onViewClicked() {
        if (context instanceof MvpPresenterActivity) {
            ((MvpPresenterActivity) context).finish();
        }
    }
}
