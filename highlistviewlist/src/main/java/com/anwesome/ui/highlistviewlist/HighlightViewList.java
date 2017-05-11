package com.anwesome.ui.highlistviewlist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
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
        relativeLayout = new RelativeLayout(activity);
        scrollView.addView(selectableImageViewList,new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        hightLightView = new HightLightView(activity);
        animationHandler = new AnimationHandler(hightLightView);
        initViews();
    }
    private void initViews() {
        DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            relativeLayout.addView(hightLightView,new ViewGroup.LayoutParams(size.x/2,size.x/2));
            relativeLayout.addView(scrollView,new ViewGroup.LayoutParams(size.x,size.y-size.x/2-size.y/10));
            hightLightView.setY(size.y/20);
            hightLightView.setX(size.x/4);
            scrollView.setY(size.x/2+size.y/10);
        }
    }
    public void addImage(Bitmap bitmap) {
        if(!isShown) {
            selectableImageViewList.addImage(bitmap, new SelectableImageView.OnTapListener() {
                @Override
                public void onTap(SelectableImageView selectableImageView) {
                    hightLightView.addBitmap(selectableImageView.getBitmap());
                    animationHandler.startAnimatingForNewView(selectableImageView);
                }
            });
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(relativeLayout);
            isShown = true;
        }
    }
}
