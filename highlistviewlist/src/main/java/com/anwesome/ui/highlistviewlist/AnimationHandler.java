package com.anwesome.ui.highlistviewlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 11/05/17.
 */
public class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
    private ValueAnimator mainAnimator = ValueAnimator.ofFloat(0,1);
    {{
        mainAnimator.setDuration(1000);
        mainAnimator.addUpdateListener(this);
    }}
    private SelectableImageView prevView,currView;
    private boolean isAnimating = false;
    private void setView(SelectableImageView currView) {
        if(this.currView != null) {
            prevView = this.currView;
        }
        this.currView = currView;
    }
    public void onAnimationEnd(Animator animator) {
        if(!isAnimating) {
            isAnimating = true;
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float factor = (float)valueAnimator.getAnimatedValue();
        if(currView!=null) {
            currView.up(factor);
        }
        if(prevView != null) {
            prevView.down(factor);
        }
    }
    private void animate() {
        mainAnimator.start();
    }
    public void startAnimatingForNewView(SelectableImageView view) {
        if(!isAnimating) {
            setView(view);
            animate();
            isAnimating = true;
        }
    }
}
