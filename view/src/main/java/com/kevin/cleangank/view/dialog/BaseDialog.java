/**
 * Copyright (C) 2017 zhouwenkai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kevin.cleangank.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * BaseDialog
 *
 * @author zhouwenkai, Created on 2017-07-26 23:29:29.
 *         Major Function：<b>BaseDialog</b>
 *         <p/>
 *         注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！
 * @author mender，Modified Date Modify Content:
 */

public abstract class BaseDialog extends DialogFragment {

    private static final String SAVED_GRAVITY = "SAVED_GRAVITY";
    private static final String SAVED_TOUCH_OUT = "SAVED_TOUCH_OUT";
    private static final String SAVED_CANCELED_BACK = "SAVED_CANCELED_BACK";
    private static final String SAVED_WIDTH = "SAVED_WIDTH";
    private static final String SAVED_PADDING = "SAVED_PADDING";
    private static final String SAVED_ANIM_STYLE = "SAVED_ANIM_STYLE";
    private static final String SAVED_DIM_ENABLED = "SAVED_DIM_ENABLED";
    private static final String SAVED_ALPHA = "SAVED_ALPHA";
    private static final String SAVED_X = "SAVED_X";
    private static final String SAVED_Y = "SAVED_Y";

    private int mGravity = Gravity.CENTER; // 对话框的位置
    private boolean mCanceledOnTouchOutside = true; // 是否触摸外部关闭
    private boolean mCanceledBack = true; // 是否返回键关闭
    private float mWidth = 0.9f; // 对话框宽度，范围：0-1；1整屏宽
    private int[] mPadding; // 对话框与屏幕边缘距离
    private int mAnimStyle; // 显示动画
    private boolean isDimEnabled = true; // 边缘阴影
    private float mAlpha = 1f; // 对话框透明度，范围：0-1；1不透明
    private int mX;
    private int mY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置 无标题 无边框
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        if (savedInstanceState != null) {
            mGravity = savedInstanceState.getInt(SAVED_GRAVITY);
            mCanceledOnTouchOutside = savedInstanceState.getBoolean(SAVED_TOUCH_OUT);
            mCanceledBack = savedInstanceState.getBoolean(SAVED_CANCELED_BACK);
            mWidth = savedInstanceState.getFloat(SAVED_WIDTH);
            mPadding = savedInstanceState.getIntArray(SAVED_PADDING);
            mAnimStyle = savedInstanceState.getInt(SAVED_ANIM_STYLE);
            isDimEnabled = savedInstanceState.getBoolean(SAVED_DIM_ENABLED);
            mAlpha = savedInstanceState.getFloat(SAVED_ALPHA);
            mX = savedInstanceState.getInt(SAVED_X);
            mY = savedInstanceState.getInt(SAVED_Y);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_GRAVITY, mGravity);
        outState.putBoolean(SAVED_TOUCH_OUT, mCanceledOnTouchOutside);
        outState.putBoolean(SAVED_CANCELED_BACK, mCanceledBack);
        outState.putFloat(SAVED_WIDTH, mWidth);
        if (mPadding != null) {
            outState.putIntArray(SAVED_PADDING, mPadding);
        }
        outState.putInt(SAVED_ANIM_STYLE, mAnimStyle);
        outState.putBoolean(SAVED_DIM_ENABLED, isDimEnabled);
        outState.putFloat(SAVED_ALPHA, mAlpha);
        outState.putInt(SAVED_X, mX);
        outState.putInt(SAVED_Y, mY);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = createView(getContext(), inflater, container);
        view.setAlpha(mAlpha);
        return view;
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
            dialog.setCancelable(mCanceledBack);
            setDialogGravity(dialog); // 设置对话框布局
        }
        super.onStart();
    }

    /**
     * 设置对话框底部显示
     *
     * @param dialog
     */
    private void setDialogGravity(Dialog dialog) {
        // 设置宽度为屏宽、靠近屏幕底部
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取屏幕宽
        wlp.width = (int) (dm.widthPixels * mWidth); // 宽度按屏幕大小的百分比设置
        wlp.gravity = mGravity;
        wlp.x = mX;
        wlp.y = mY;
        // 边距
        if (mPadding != null) {
            int[] padding = mPadding;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.getDecorView().setPadding(scaleValue(padding[0]), scaleValue(padding[1]),
                    scaleValue(padding[2]), scaleValue(padding[3]));
        }
        // 动画
        if (mAnimStyle != 0) {
            window.setWindowAnimations(mAnimStyle);
        }

        if (isDimEnabled) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        window.setAttributes(wlp);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isAdded()) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(this, tag);
            transaction.commitAllowingStateLoss();
        }
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    public void remove() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.addToBackStack(null);
    }

    /**
     * 设置对话框位置
     * {@link Gravity#CENTER 默认}
     *
     * @param gravity 位置
     */
    protected void setGravity(int gravity) {
        mGravity = gravity;
    }

    /**
     * 设置对话框点击外部关闭
     *
     * @param cancel true允许
     */
    protected void setCanceledOnTouchOutside(boolean cancel) {
        mCanceledOnTouchOutside = cancel;
    }

    /**
     * 设置对话框返回键关闭关闭
     *
     * @param cancel true允许
     */
    protected void setCanceledBack(boolean cancel) {
        mCanceledBack = cancel;
    }

    /**
     * 设置对话框宽度
     *
     * @param width 0.0 - 1.0
     */
    protected void setWidth(@FloatRange(from = 0.0, to = 1.0) float width) {
        mWidth = width;
    }

    /**
     * 设置边距
     *
     * @param left   px
     * @param top    px
     * @param right  px
     * @param bottom px
     */
    protected void setPadding(int left, int top, int right, int bottom) {
        mPadding = new int[]{left, top, right, bottom};
    }

    /**
     * 弹出对话框的动画
     *
     * @param animStyle StyleRes
     */
    protected void setAnimations(int animStyle) {
        mAnimStyle = animStyle;
    }

    /**
     * 设置背景是否昏暗，默认true
     *
     * @param dimEnabled true昏暗
     */
    protected void setDimEnabled(boolean dimEnabled) {
        isDimEnabled = dimEnabled;
    }

    /**
     * 设置对话框透明度
     *
     * @param alpha 0.0 - 1.0
     */
    protected void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        mAlpha = alpha;
    }

    protected void setX(int x) {
        mX = x;
    }

    protected void setY(int y) {
        mY = y;
    }

    public int scaleValue(int pxVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxVal,
                getContext().getResources().getDisplayMetrics());
    }

    public abstract View createView(Context context, LayoutInflater inflater, ViewGroup container);
}
