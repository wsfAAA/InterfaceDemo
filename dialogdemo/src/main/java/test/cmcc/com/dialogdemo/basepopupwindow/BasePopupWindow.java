package test.cmcc.com.dialogdemo.basepopupwindow;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.PopupWindow;

import java.lang.reflect.Method;

/**
 * Created by wsf on 2018/10/29.
 * 已有功能：
 * 一、支持三种动画方式：
 * 方式一：Animation 代码补间动画
 * 方式二：Animator  代码属性动画
 * 方式三：style形式补间动画
 * 二、支持PopupWindow全屏和指定大小
 * 三、支持PopupWindow点击popupwindow空白区域消失于不消失
 * 四、支持输入框软键盘自动弹出
 * 五、支持显示方式位于某id或view显示以及x、y轴偏移量
 */
public abstract class BasePopupWindow implements BasePopup {
    protected PopupWindow mPopupWindow;
    protected View mPopupView;//PopupView视图
    protected View mAnimaView;//执行动画View
    protected Activity mContext;
    private boolean autoShowInputMethod = false; //是否自动弹出输入框(default:false)

    //补间动画
    protected Animation curAnima;
    protected Animation curExitAnima;
    //属性动画
    protected Animator curExitAnimator;
    protected Animator curAnimator;

    /**
     * PopupWindow 全屏
     */
    public BasePopupWindow(Activity context) {
        initView(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true, 0);
    }

    /**
     * 指定PopupWindow大小
     */
    public BasePopupWindow(Activity context, int w, int h) {
        initView(context, w, h, true, 0);
    }

    /**
     * 点击PopupWindow空白区域示是否消失
     */
    public BasePopupWindow(Activity context, int w, int h, boolean isDismiss) {
        initView(context, w, h, isDismiss, 0);
    }

    /**
     * PopupWindow支持style形式动画、PopupWindow空白区域示是否消失
     */
    public BasePopupWindow(Activity context, int w, int h, boolean isDismiss, int styleId) {
        initView(context, w, h, isDismiss, styleId);
    }

    /**
     * 初始化PopupWindow相关配置
     *
     * @param context
     * @param w         PopupWindow宽度
     * @param h         PopupWindow高度
     * @param isDismiss PopupWindow空白区域点击是否消失，true消失、false不消失,默认消失
     * @param styleId   PopupWindow支持style形式动画 0是无需动画(默认)
     */
    private void initView(Activity context, int w, int h, boolean isDismiss, int styleId) {
        mContext = context;

        mPopupView = getPopupView();
        mPopupView.setFocusableInTouchMode(true);

        mPopupWindow = new PopupWindow(mPopupView, w, h);  //默认占满全屏
        //指定透明背景，back键、点击popupWindow区域外是否消失
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setFocusable(true);// false后输入框无法弹出不能设置焦点
        mPopupWindow.setTouchable(true);
        if (isDismiss) {//默认消失
            mPopupWindow.setOutsideTouchable(true);
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { //SDK_INT版本号,M表示6.0
                mPopupWindow.setOutsideTouchable(false);   //6.0以上失效 使用下面方案
            } else {
                try {
                    Method method = PopupWindow.class.getDeclaredMethod("setTouchModal", boolean.class);
                    method.setAccessible(true);
                    method.invoke(mPopupWindow, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mPopupWindow.setAnimationStyle(styleId); // 0无需动画

        mAnimaView = getAnimaView();

        //获取补间动画和属性动画
        curAnima = getShowAnimation();
        curExitAnima = getExitAnimation();
        curAnimator = getShowAnimator();
        curExitAnimator = getExitAnimator();
    }

    /**
     * 显示父容器之上
     *
     * @param showLocation
     */
    public void showPopupWindow(int showLocation) {
        try {
            tryToShowPopup(0, null, showLocation, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据某view id显示
     *
     * @param res
     * @param showLocation
     */
    public void showPopupWindow(int res, int showLocation) {
        try {
            tryToShowPopup(res, null, showLocation, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据view显示
     *
     * @param v
     * @param showLocation
     */
    public void showPopupWindow(View v, int showLocation) {
        try {
            tryToShowPopup(0, v, showLocation, 0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 据view显示，添加x、y轴偏移量
     *
     * @param v            view
     * @param showLocation 显示位置
     * @param x            x轴偏移量
     * @param y            y轴偏移量
     */
    public void showPopupWindowXY(View v, int showLocation, int x, int y) {
        try {
            tryToShowPopup(0, v, showLocation, x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * popupWindow显示位置
     *
     * @param res          view资源
     * @param v            view
     * @param showLocation 显示位置
     * @throws Exception
     */
    private void tryToShowPopup(int res, View v, int showLocation, int x, int y) throws Exception {
        //传递了view
        if (res == 0 && v != null) {
            mPopupWindow.showAtLocation(v, showLocation, x, y);
        }
        //传递了res
        if (res != 0 && v == null) {
            mPopupWindow.showAtLocation(mContext.findViewById(res), showLocation, x, y);
        }
        //什么都没传递，取顶级view的id
        if (res == 0 && v == null) {
            //android.R.id.content是decorView的contnet的id，也就是我们setContentView的父类id。
            mPopupWindow.showAtLocation(mContext.findViewById(android.R.id.content), showLocation, x, y);
        }

        //执行动画
        if (curAnima != null && mAnimaView != null) {
            mAnimaView.clearAnimation();
            mAnimaView.startAnimation(curAnima);
        }
        if (curAnima == null && curAnimator != null && mAnimaView != null) {
            curAnimator.start();
        }

        //有输入框,自动弹出键盘
        if (autoShowInputMethod && getInputView() != null) {
            getInputView().requestFocus();
            InputMethodUtils.showInputMethod(mContext);
        }
    }

    /**
     * 是否自动弹出软键盘，需在showPopupWindow之前调用
     *
     * @param autoShowInputMethod
     */
    public void setAutoShowInputMethod(boolean autoShowInputMethod) {
        this.autoShowInputMethod = autoShowInputMethod;
    }

    /**
     * 监听动画消失后dismiss
     */
    protected void dismiss() {
        try {
            if (curExitAnima != null) {
                curExitAnima.setAnimationListener(mAnimationListener);
                mAnimaView.clearAnimation();
                mAnimaView.startAnimation(curExitAnima);
            } else if (curExitAnimator != null) {
                curExitAnimator.removeListener(mAnimatorListener);
                curExitAnimator.addListener(mAnimatorListener);
                curExitAnimator.start();
            } else {
                mPopupWindow.dismiss();
            }
        } catch (Exception e) {
        }
    }

    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mPopupWindow.dismiss();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    /**
     * 获取动画两种方式：
     * 方式一：abstract修饰必须重写的Animation动画
     * 方式二：使用public修饰，子类可选重写的Animator动画方式
     *
     * @return
     */
    protected abstract Animation getShowAnimation();

    public Animator getShowAnimator() {
        return null;
    }

    /**
     * 获取输入框view
     *
     * @return
     */
    public View getInputView() {
        return null;
    }

    /**
     * 两种获取退出动画的方式：都采用public修饰 需子类重写的动画Animation和Animator
     *
     * @return
     */
    public Animation getExitAnimation() {
        return null;
    }

    public Animator getExitAnimator() {
        return null;
    }
}
