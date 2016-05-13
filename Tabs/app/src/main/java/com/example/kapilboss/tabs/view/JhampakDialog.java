package com.example.kapilboss.tabs.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.example.kapilboss.tabs.R;

/**
 * Created by kapilsharma on 11/05/16.
 */
public class JhampakDialog {

    private final View mView;
    private Context mContext;
    private final ViewGroup mParent;
    public final JhampakView progressBar;

    public JhampakDialog(Context context, ViewGroup viewGroup) {
        this.mContext = context;
        this.mParent = viewGroup;
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        this.mView = inflater.inflate(R.layout.progress_layout, this.mParent, false);
        progressBar = (JhampakView) mView.findViewById(R.id.progress_bar);
        progressBar.mParent = viewGroup;
        this.mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public static JhampakDialog create(View view) {
        JhampakDialog customProgressDialog = new JhampakDialog(view.getContext(), findSuitableParent(view));
        return customProgressDialog;
    }

    /**
     * show on Progress
     */
    public void show() {
        showView();
    }

    public boolean isShown() {
        if (progressBar != null) {
            return progressBar.isShown();
        } else {
            return false;
        }
    }

    final void showView() {
        if (this.mView.getParent() == null) {
            this.mParent.addView(this.mView);
        }
    }

    /**
     * hide on Progress
     */
    public void dismiss() {
//        progressBar.setVisibility(View.GONE);
//        progressBar.dismiss();
        if (this.mView != null) {
            this.mParent.removeView(this.mView);
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
