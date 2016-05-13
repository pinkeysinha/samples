package com.example.kapilboss.tabs.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.kapilboss.tabs.R;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class JhampakView extends ProgressBar {


    private Context mContext;
    public ViewGroup mParent;
    private CircularProgressDrawable drawable;

    public JhampakView(Context context) {
        super(context);
        mContext = context;
        init(context);
    }


    public JhampakView(Context context, View view) {
        super(context);
        mContext = context;
        init(context);
    }

    public JhampakView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }


    public JhampakView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(context);
    }

    public void dismiss() {
        drawable.stop();
    }

    public void startAnimation() {
        if (this.getParent() == null) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 10);
            this.setLayoutParams(params);
//            this.mParent.addView(this);
        }

        if (drawable != null) {
            drawable.start();
        } else {
            drawable = new CircularProgressDrawable(getResources().getColor(R.color.aqua_blue), 10);
            setIndeterminateDrawable(drawable);
            drawable.start();
        }
        hideKeyboard(mContext);
    }


    private void init(Context context) {
        float borderWidth = 3.3F;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        borderWidth = metrics.density * borderWidth;
        drawable = new CircularProgressDrawable(getResources().getColor(R.color.aqua_blue), borderWidth);
        setIndeterminateDrawable(drawable);
    }

    public static void hideKeyboard(Context ctx) {
        if (ctx != null) {
            InputMethodManager inputManager = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            if (!(ctx instanceof Activity)) {
                return;
            }
            // check if no view has focus:
            View v = ((Activity) ctx).getCurrentFocus();
            if (v == null)
                return;

            inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;

        do {
            if (view instanceof CoordinatorLayout) {
                return (ViewGroup) view;
            }

            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    return (ViewGroup) view;
                }

                fallback = (ViewGroup) view;
            }

            if (view != null) {
                ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        return fallback;
    }

}
