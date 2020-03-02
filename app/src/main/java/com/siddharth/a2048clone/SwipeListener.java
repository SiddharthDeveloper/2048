package com.siddharth.a2048clone;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeListener implements GestureDetector.OnGestureListener {

    private GestureDetector detector;
    private SwipeCallback callback;

    public SwipeListener(Context context, SwipeCallback callback) {
        this.callback = callback;
        detector=new GestureDetector(context,this);
    }

    public void onTouchEvent(MotionEvent e){
        detector.onTouchEvent(e);
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
       // TODO: For Detect motion x is horizontal y is vertical..
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                callback.onSwipe(SwipeCallback.Direction.RIGHT);
            } else {
                callback.onSwipe(SwipeCallback.Direction.LEFT);
            }
        } else {
            if (velocityY > 0) {
                callback.onSwipe(SwipeCallback.Direction.DOWN);
            } else {
                callback.onSwipe(SwipeCallback.Direction.UP);
            }
        }
        return false;
    }
}