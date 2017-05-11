package com.anwesome.ui.highlistviewlist;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class HighlightViewList {
    private RelativeLayout relativeLayout;
    private ScrollView scrollView;
    private SelectableImageViewList selectableImageViewList;
    private Activity activity;
    private HightLightView hightLightView;
    private boolean isShown = false;
    private AnimationHandler animationHandler;
    public HighlightViewList(Activity activity) {
        this.activity = activity;
        selectableImageViewList = new SelectableImageViewList(activity);
        scrollView = new ScrollView(activity);
        scrollView.addView(selectableImageViewList,new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        hightLightView = new HightLightView(activity);
        animationHandler = new AnimationHandler();

    }
    public void addImage(Bitmap bitmap) {
        if(!isShown) {
            selectableImageViewList.addImage(bitmap, new SelectableImageView.OnTapListener() {
                @Override
                public void onTap(SelectableImageView selectableImageView) {
                    animationHandler.startAnimatingForNewView(selectableImageView);
                }
            });
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
}
