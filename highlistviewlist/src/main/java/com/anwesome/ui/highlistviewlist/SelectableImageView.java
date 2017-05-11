package com.anwesome.ui.highlistviewlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class SelectableImageView extends View {
    private Bitmap bitmap;
    private int w,h,render = 0;
    private ColorFilterImage colorFilterImage;
    private int color = Color.parseColor("#FF6F00");
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private OnTapListener onTapListener;
    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }
    public SelectableImageView(Context context,Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void onDraw(Canvas canvas) {
        if(render == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            int size = Math.min(w,h);
            bitmap = Bitmap.createScaledBitmap(bitmap,4*size/5,4*size/5,true);
            colorFilterImage = new ColorFilterImage();
        }
        colorFilterImage.draw(canvas);
        render++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && onTapListener != null) {
            onTapListener.onTap();
        }
        return true;
    }
    public void up(float factor) {
        colorFilterImage.up(factor);
        postInvalidate();
    }
    public void down(float factor) {
        colorFilterImage.down(factor);
        postInvalidate();
    }
    private class ColorFilterImage {
        private float r,deg = 0;
        public ColorFilterImage() {
            r = 2*h/5;
        }
        public void draw(Canvas canvas) {
            canvas.drawBitmap(bitmap,w/2-r,h/10,paint);
            paint.setColor(Color.argb(150,Color.red(color),Color.green(color),Color.blue(color)));
            canvas.drawArc(new RectF(w/2-r,h/10,w/2+r,h/10+2*r),0,deg,true,paint);
        }
        public void up(float factor) {
            deg = 360*factor;
        }
        public void down(float factor) {
            deg = 360*(1-factor);
        }
    }
    public interface OnTapListener {
        void onTap();
    }
}
