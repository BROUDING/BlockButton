package com.brouding.blockbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.*;
import android.support.v4.BuildConfig;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John on 7/7/17.
 */

public class BlockButton extends LinearLayout {
    private Context mContext;
    private int paddingTop = 0, paddingBottom = 0, paddingLeft = 0, paddingRight = 0, generalPaddingTop = 0, pressedPaddingTop,
                pushDepth = 0,  btnColor = 0, btnGapColor = 0, btnDisabledColor = 0, btnDisabledGapColor = 0;

    public BlockButton(Context context) {
        super(context);
    }

    public BlockButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setButtonLayout(context, attrs);
    }

    public BlockButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setButtonLayout(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BlockButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setButtonLayout(context, attrs);
    }

    private void setButtonLayout(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.paddingTop    = this.getPaddingTop();
        this.paddingBottom = this.getPaddingBottom();
        this.paddingLeft   = this.getPaddingLeft();
        this.paddingRight  = this.getPaddingRight();

        // prevent render is in edit mode
        if( isInEditMode() )
            return;

        if( attrs != null ) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BlockButton);

            btnColor    = getBtnColor(array);
            btnGapColor = getBtnGapColor(array);
            btnDisabledColor    = getBtnDisabledColor(array);
            btnDisabledGapColor = getBtnDisabledGapColor(array);

            pushDepth      = getPXWithDP( getPushDepth(array) );

            array.recycle();

            this.generalPaddingTop = paddingTop - pushDepth/2;
            this.pressedPaddingTop = paddingTop +pushDepth;
        }

        this.setPadding(this.paddingLeft, this.generalPaddingTop, this.paddingRight, this.paddingBottom);

        if(Build.VERSION.SDK_INT <= 15)
            this.setBackgroundDrawable( makeSelector() );
        else
            this.setBackground( makeSelector() );
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        this.setPadding(this.paddingLeft, pressed ? this.pressedPaddingTop : this.generalPaddingTop, this.paddingRight, this.paddingBottom);
    }

    private StateListDrawable makeSelector() {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[] { android.R.attr.state_enabled, -android.R.attr.state_pressed }, setButtonDrawable(R.drawable.bg_enter_round_layer_default_normal,  false));
        res.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_pressed  }, setButtonDrawable(R.drawable.bg_enter_round_layer_default_pressed, true));
        res.addState(new int[] { -android.R.attr.state_enabled }, setButtonDrawable(R.drawable.bg_enter_round_layer_default_disabled, false));
        return res;
    }

    private Drawable setButtonDrawable(int drawableId, boolean isPressed) {
        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(mContext, drawableId);

        GradientDrawable btnDrawable       = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.btn);
        GradientDrawable btnShadowDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.btnShadow);

        if( isPressed ) {
            layerDrawable.setLayerInset(0, 0, pushDepth, 0, 0);
            layerDrawable.setLayerInset(1, 0, pushDepth, 0, 4);
        } else {
            layerDrawable.setLayerInset(1, 0, 0, 0, (pushDepth+4));
        }

        if( drawableId == R.drawable.bg_enter_round_layer_default_disabled ) {
            btnDrawable.setColor(btnDisabledColor);
            btnShadowDrawable.setColor(btnDisabledGapColor);

            return layerDrawable;
        }

        btnDrawable.setColor(btnColor);
        btnShadowDrawable.setColor(btnGapColor);

        return layerDrawable;
    }

    private int getPXWithDP(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    protected int getBtnColor(final TypedArray typedArray) {
        return typedArray.getColor(R.styleable.BlockButton_buttonColor, ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    protected int getBtnGapColor(final TypedArray typedArray) {
        return typedArray.getColor(R.styleable.BlockButton_buttonGapColor, ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
    }

    protected int getBtnDisabledColor(final TypedArray typedArray) {
        return typedArray.getColor(R.styleable.BlockButton_buttonDisabledColor, Color.GRAY);
    }

    protected int getBtnDisabledGapColor(final TypedArray typedArray) {
        return typedArray.getColor(R.styleable.BlockButton_buttonDisabledGapColor, Color.DKGRAY);
    }

    protected int getPushDepth(final TypedArray typedArray) {
        return typedArray.getInt(R.styleable.BlockButton_pushDepthDp, 4);
    }
}
