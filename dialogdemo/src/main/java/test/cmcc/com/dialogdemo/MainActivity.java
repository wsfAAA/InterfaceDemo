package test.cmcc.com.dialogdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import test.cmcc.com.dialogdemo.popwiond.PopwiondButton;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llRoot = findViewById(R.id.ll_root);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popOne();
            }
        });
    }

    /**
     * popwindow底部弹出
     */
    private void popOne() {
        PopwiondButton popwiondButton = new PopwiondButton(this);
        if (!popwiondButton.isShowing()) {
            /**
             *  showAtLocation:在一个弹出窗口中的指定位置显示内容视图。
             *  parent:View父视图
             *  gravity:弹出窗口位置的重力值
             *  x: 弹出窗口的x坐标偏移
             *  y:弹出窗口的y坐标偏移
             */
//            popwiondButton.showAtLocation(llRoot, Gravity.BOTTOM, 0, 0);

            /**
             *在所附着的视图的左下角的一个弹出窗口中显示内容视图。
             */
//            popwiondButton.showAsDropDown(llRoot);
        }
    }
}
