package test.cmcc.com.dialogdemo.basepopupwindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import test.cmcc.com.dialogdemo.R;

/**
 * Created by wsf on 2018/10/29.
 */

public class TestBasePopupWindow extends BasePopupWindow implements View.OnClickListener {

    private View view;

    public TestBasePopupWindow(Activity context, int w, int h) {
        super(context, w, h);
        initView();
    }

    public TestBasePopupWindow(Activity context) {
        super(context);
        initView();
    }

    public TestBasePopupWindow(Activity context, int w, int h, boolean isDismiss) {
        super(context, w, h, isDismiss);
        initView();
    }

    public TestBasePopupWindow(Activity context, int w, int h, boolean isDismiss, int styleId) {
        super(context, w, h, isDismiss, styleId);
        initView();
    }

    private void initView() {
        view.findViewById(R.id.bnt1).setOnClickListener(this);
        view.findViewById(R.id.bnt2).setOnClickListener(this);
        view.findViewById(R.id.bnt3).setOnClickListener(this);
        view.findViewById(R.id.root).setOnClickListener(this);
    }

    @Override
    public View getPopupView() {
//        view=LayoutInflater.from(mContext).inflate(R.layout.popupwindow_test_a_base_layout,null);
        view = View.inflate(mContext,R.layout.popupwindow_test_a_base_layout, null);
        return view;
    }

    @Override
    public View getAnimaView() {
        return null;
    }

    @Override
    public View getInputView() {
        return view.findViewById(R.id.et1);
    }

    @Override
    protected Animation getShowAnimation() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bnt1:
                Toast.makeText(mContext,view.getId()+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bnt2:
                Toast.makeText(mContext,view.getId()+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bnt3:
                Toast.makeText(mContext,view.getId()+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.root:
                Toast.makeText(mContext,"空白",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}
