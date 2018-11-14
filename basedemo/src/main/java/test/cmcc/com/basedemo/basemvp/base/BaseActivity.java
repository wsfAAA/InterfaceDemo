package test.cmcc.com.basedemo.basemvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import test.cmcc.com.basedemo.R;


/**
 * Created by wsf on 2018/11/6.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected final String TAG = getClass().getSimpleName();

    protected Context mContext;

    View mIncludeTitleBar;   //titleBar
    TextView mTvBack;
    TextView mTvTitle;
    TextView mTvShare;
    TextView mTvLine;

    View mStateLayout;  //数据状态样式
    TextView mTvStateText;     //错误提示
    ImageView mImgRetry;        //重新加载

    LinearLayout mRootBaseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.setContentView(R.layout.activity_base);//父类调用setContentView
        initLayout();
        mContext = this;
        getLayoutResID();
        initTitleBar(false, false, false, "");
        initView();
    }

    /**
     * 父类引用setContentView ButterKnife无法初始化
     */
    private void initLayout() {
        mIncludeTitleBar = findViewById(R.id.m_include_bar);
        mTvBack = findViewById(R.id.m_tv_back);
        mTvTitle = findViewById(R.id.m_tv_title);
        mTvShare = findViewById(R.id.m_tv_share);
        mTvLine = findViewById(R.id.m_tv_line);

        mStateLayout = findViewById(R.id.m_include_style_layout);
        mTvStateText = findViewById(R.id.m_tv_state_text);
        mImgRetry = findViewById(R.id.m_img_retry);
        mRootBaseView = findViewById(R.id.activity_base_root);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (mRootBaseView != null) {
            mRootBaseView.addView(view, lp);
        }
        ButterKnife.bind(this);
    }

    protected abstract void initView();

    protected abstract void getLayoutResID();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_tv_back://返回
                Toast.makeText(this, "返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_tv_share://分享
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_img_retry://重新加载
                reloadClickListener();
                break;
        }
    }

    /**
     * @param titleBarIsShow titleBar是否显示
     * @param tvBackIsShow   返回键是否显示
     * @param middleText     中间的文字
     */
    public void initTitleBar(boolean titleBarIsShow, boolean tvBackIsShow, boolean tvShareIsShow, String middleText) {
        if (titleBarIsShow) {
            if (tvBackIsShow) {
                mTvBack.setVisibility(View.VISIBLE);
                mTvBack.setOnClickListener(this);
                mTvShare.setOnClickListener(this);
            } else {
                mTvBack.setVisibility(View.GONE);
            }
            mTvStateText.setText(middleText);
            if (tvShareIsShow) {
                mTvShare.setVisibility(View.VISIBLE);
            } else {
                mTvShare.setVisibility(View.GONE);
            }
        } else {
            mIncludeTitleBar.setVisibility(View.GONE);
        }
    }

    /**
     * 切换页面的布局
     *
     * @param pageState 页面状态 NORMAL  EMPTY  ERROR
     */
    public void changePageState(PageState pageState) {
        switch (pageState) {
            case NORMAL:
                if (mStateLayout.getVisibility() == View.VISIBLE) {
                    mStateLayout.setVisibility(View.GONE);
                }
                break;
            case ERROR:
                if (mStateLayout.getVisibility() == View.GONE) {
                    mStateLayout.setVisibility(View.VISIBLE);
                    mImgRetry.setVisibility(View.VISIBLE);
                    mTvStateText.setVisibility(View.VISIBLE);
                    mImgRetry.setOnClickListener(this);
                }
                break;
            case EMPTY:
            case NETERROR:
                if (mStateLayout.getVisibility() == View.GONE) {
                    mStateLayout.setVisibility(View.VISIBLE);
                    mImgRetry.setVisibility(View.GONE);
                    mTvStateText.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    /**
     * 数据加载异常 文字提示
     *
     * @param msg
     */
    protected void setPageStateText(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mTvStateText != null) {
            mTvStateText.setText(msg);
        }
    }

    /**
     * 数据加载失败重试
     */
    protected abstract void reloadClickListener();

    public enum PageState {
        /**
         * 数据内容显示正常
         */
        NORMAL,
        /**
         * 数据加载失败
         */
        ERROR,
        /**
         * 数据为空
         */
        EMPTY,
        /**
         * 网络异常
         */
        NETERROR
    }
}
