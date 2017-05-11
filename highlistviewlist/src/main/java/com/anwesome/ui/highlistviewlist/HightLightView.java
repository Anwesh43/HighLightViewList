package com.anwesome.ui.highlistviewlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class HightLightView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int w,h,time;
    private float y = 0,initY;
    private List<ImageContainer> imageContainers = new ArrayList<>();
    public HightLightView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            initY = y;
        }
        canvas.save();
        canvas.translate(0,y);
        for(ImageContainer imageContainer:imageContainers) {
            imageContainer.draw(canvas);
        }
        canvas.restore();
        time++;
    }
    public void update(float factor) {
        y = initY-h*factor;
        postInvalidate();
    }
    public void addBitmap(Bitmap bitmap) {
        float y = this.y;
        if(imageContainers.size() > 0) {
            ImageContainer imageContainer = imageContainers.get(0);
            y = imageContainer.y;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
        ImageContainer newImageContainer = new ImageContainer(bitmap,y+h);
        imageContainers.add(newImageContainer);
    }
    public void stop() {
        if(imageContainers.size()  == 2) {
            imageContainers.remove(0);
        }
        initY = y;

    }
    private class ImageContainer {
        private Bitmap bitmap;
        private float y = 0;
        public ImageContainer(Bitmap bitmap,float y) {
            this.bitmap = bitmap;
            this.y = y;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(0,y);
            canvas.drawBitmap(bitmap,0,0,paint);
            canvas.restore();
        }
    }
}
