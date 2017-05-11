package com.anwesome.ui.highlistviewlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class SelectableImageView extends View {
    private Bitmap bitmap;
    private int w,h,render = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public SelectableImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(render == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,4*h/5,4*h/5,true);
        }

        render++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
