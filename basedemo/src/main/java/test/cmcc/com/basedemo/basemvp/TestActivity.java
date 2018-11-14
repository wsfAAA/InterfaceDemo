package test.cmcc.com.basedemo.basemvp;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import test.cmcc.com.basedemo.R;
import test.cmcc.com.basedemo.basemvp.base.BaseMvpActivity;

public class TestActivity extends BaseMvpActivity<TestPersenter> {

    @BindView(R.id.test)
    Button test;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    protected void initView() {

    }

    @Override
    protected void getLayoutResID() {
        setContentView(R.layout.activity_test);
    }

    protected void testview() {
        Toast.makeText(mContext, "回调成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void reloadClickListener() {
        Toast.makeText(mContext, "加载重试", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.test, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.test:
                mBasePresenter.testPersenter();
                break;
            case R.id.btn1:
                changePageState(PageState.NORMAL);
                break;
            case R.id.btn2:
                changePageState(PageState.ERROR);
                setPageStateText("数据加载失败，请重试...");
                break;
            case R.id.btn3:
                changePageState(PageState.NETERROR);
                setPageStateText("网络异常检测网络");
                break;
            case R.id.btn4:
                changePageState(PageState.EMPTY);
                setPageStateText("无数据");
                break;
        }
    }
}
