package test.cmcc.com.basedemo.basemvp;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import test.cmcc.com.basedemo.R;
import test.cmcc.com.basedemo.basemvp.base.BaseActivity;

public class TestActivity extends BaseActivity<TestPersenter> {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasePresenter.testPersenter();
            }
        });
    }

    protected void testview() {
        Toast.makeText(mContext, "回调成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("wsf", "TestActivity-->  onDestroy");
    }
}
