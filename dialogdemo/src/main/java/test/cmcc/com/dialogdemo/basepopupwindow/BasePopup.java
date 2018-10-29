package test.cmcc.com.dialogdemo.basepopupwindow;

import android.view.View;

/**
 * Created by wsf on 2018/10/29.
 */

public interface BasePopup {
    /**
     * popupwindow布局view
     * @return
     */
    View getPopupView();

    /**
     * 执行动画view
     * @return
     */
    View getAnimaView();
}
