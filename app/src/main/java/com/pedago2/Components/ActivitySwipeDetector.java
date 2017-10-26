package com.pedago2.Components;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ActivitySwipeDetector implements View.OnTouchListener {

    private Activity activity;
    static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;

    public ActivitySwipeDetector(final Activity activity) {
        this.activity = activity;
    }

    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                //   return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // swipe horizontal?
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        Log.i("LeftToRightSwipe!", "");
                        return true;
                    }
                    if (deltaX > 0) {
                        Log.i("RightToLeftSwipe!", "");
                        return true;
                    }
                } else {
                    Log.i("Swipe was only ", Math.abs(deltaX) + " long, need at least 1 " + MIN_DISTANCE);
                }

                // swipe vertical?
                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // top or down
                    if (deltaY < 0) {
                        Log.i("onTopToBottomSwipe!", "");
                        return true;
                    }
                    if (deltaY > 0) {
                        Log.i("onBottomToTopSwipe!", "");
                        return true;
                    }
                    Toast.makeText(activity, "down x " + downX + "\n down y " + downY, Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("Swipe was only ", Math.abs(deltaX) + " long, need at least 2 " + MIN_DISTANCE);
                }
                //     return true;
            }
        }
        return false;
    }
}