package test.cmcc.com.dialogdemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;

import test.cmcc.com.dialogdemo.R;

/**
 * Created by wsf on 2018/10/30.
 */
public class DialogTest extends AppCompatDialog {//建议extends AppCompatDialog 而非Dialog,兼容样式

    public DialogTest(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        private Activity mActivity;
        private ClickListener mClickListener;
        private DialogTest mDialog;

        public Builder(Activity activity){
            this.mActivity = activity;
        }

        public DialogTest create(){
            mDialog = new DialogTest(mActivity, R.style.custom_dialog);
            View rootView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_layout, null);
            rootView.findViewById(R.id.bnt1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener!=null){
                        mClickListener.clickYes();
                    }
                }
            });
            rootView.findViewById(R.id.bnt2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener!=null){
                        mClickListener.clickCancel();
                    }
                }
            });
            mDialog.setContentView(rootView);  //Dialog使用setContentView
            mDialog.setCanceledOnTouchOutside(true);//设置在窗口边界外触摸时是否取消此对话框。如果设置为true，则对话框设置为可取消
            mDialog.setCancelable(true);//设置此对话框是否可以使用BACK键取消,true取消，false不取消
            return mDialog;
        }

        public void setClickListener(ClickListener clickListener){
            mClickListener = clickListener;
        }

        public interface ClickListener{
            void clickYes();
            void clickCancel();
        }
    }
}
