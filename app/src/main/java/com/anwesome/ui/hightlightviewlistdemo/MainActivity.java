package com.anwesome.ui.hightlightviewlistdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.highlistviewlist.HighlightViewList;
import com.anwesome.ui.highlistviewlist.SelectableImageViewList;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back3);
        }
        HighlightViewList highlightViewList = new HighlightViewList(this);
        for(int i=0;i<12;i++) {
            highlightViewList.addImage(bitmap);
        }
        highlightViewList.show();
    }
}
