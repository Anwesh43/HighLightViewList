package com.anwesome.ui.highlistviewlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class SelectableImageViewList extends ViewGroup {
    int w ,h;
    public SelectableImageViewList(Context context) {
         super(context);
         initDimension(context);
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void addImage(Bitmap bitmap, SelectableImageView.OnTapListener onTapListener) {
        SelectableImageView imageView = new SelectableImageView(getContext(),bitmap);
        imageView.setOnTapListener(onTapListener);
        addView(imageView,new LayoutParams(w,Math.max(w,h)/5));
        requestLayout();
    }
    public void onMeasure(int wspec,int hspec) {
        int hNew = h/10;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            hNew += Math.max(w,h)/3;
        }
        setMeasuredDimension(w,Math.max(hNew,h));

    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int y = h/10;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(w/20,y,w-w/20,y+h);
            y += Math.max(w,h)/3;
        }
    }
}
