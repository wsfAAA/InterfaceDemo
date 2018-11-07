package test.cmcc.com.networkdemo.basevu.testrun;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import test.cmcc.com.networkdemo.R;
import test.cmcc.com.networkdemo.basevu.mvp.MgBaseVu;


/**
 * Created by wsf on 2018/11/5.
 * 注意：vu  extends BaseVu  butterknife没有注册无效，需 extends MgBaseVu
 */

public class MvpMgBaseVu extends MgBaseVu {

    @BindView(R.id.tv_person)
    TextView tvPerson;

    @Override
    public int getLayoutId() {
        Log.e("wsf", TAG + ":getLayoutId");
        return R.layout.activity_mvp;
    }

    @Override
    public void bindView() {
        super.bindView();
        if (context instanceof MvpMgBaseVuActivity) {
            Intent intent = ((MvpMgBaseVuActivity) context).getIntent();
            Log.e("wsf", TAG + ":bindView   " + tvPerson);
        }
    }

    public void test(){

    }

    @OnClick(R.id.tv_person)
    public void onViewClicked() {
        context.startActivity(new Intent(context, MvpPresenterActivity.class));
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
}
