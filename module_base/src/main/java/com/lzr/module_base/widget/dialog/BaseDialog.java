package com.lzr.module_base.widget.dialog;

import android.app.Activity;
import android.content.DialogInterface;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.lzr.module_base.R;


/**
 * Created by Administrator on 2018/2/11.
 */

public abstract class BaseDialog implements IBaseDialogView {
    private BaseDialog mBaseDialog;
    public Activity mContext;
    protected AlertDialog mAlertDialog;
    private AlertDialog.Builder mBuilder;


    public void initDialog(Activity context) {
        this.mContext = context;
        if (mBaseDialog == null) {
            mBuilder = new AlertDialog.Builder(mContext);
            init();
        }
    }

    private void init() {
        View contentView = getContentViewCus(mContext);
        mBuilder.setView(contentView);
        mAlertDialog = mBuilder.create();
        mAlertDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        //mAlertDialog.setCanceledOnTouchOutside(false);//设置点击外部不消失
        mAlertDialog.setCancelable(true);//设置点击外部和返回键不消失
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                onShowListener();
            }
        });
        mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onDissMissListener();
            }
        });
    }

    //设置点击外部和返回键不消失
    public void setOutsideCancelable(boolean isCan) {
        mAlertDialog.setCancelable(isCan);
    }


    public void show() {
        if (mAlertDialog == null) return;
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_shape_dialog_custom));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = mContext.getResources().getDimensionPixelSize(R.dimen.dp_300);
//        attributes.width= mContext.getResources().getDimensionPixelSize(R.dimen.dp_400);
//        attributes.height=mContext.getResources().getDimensionPixelSize(R.dimen.dp_400);
        window.setAttributes(attributes);
    }

    /**
     * 显示500dp高度的dialog
     */
    public void showHieghtLayout() {
        if (mAlertDialog == null) return;
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_shape_dialog_custom));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = mContext.getResources().getDimensionPixelSize(R.dimen.dp_300);
        attributes.height = mContext.getResources().getDimensionPixelSize(R.dimen.dp_500);
        window.setAttributes(attributes);
    }


    /**
     * 显示没背景的dialog
     */
    public void showNullBackground() {
        if (mAlertDialog == null) return;
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_shape_dialog_tran));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = mContext.getResources().getDimensionPixelSize(R.dimen.dp_300);
        window.setAttributes(attributes);
    }





    public void dismiss() {
        if (mAlertDialog != null)
            mAlertDialog.dismiss();
    }

    public void toNull() {
        if (mBaseDialog != null)
            mBaseDialog = null;

        if (mContext != null)
            mContext = null;

        if (mAlertDialog != null) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    public abstract void onShowListener();

    public abstract void onDissMissListener();
}
