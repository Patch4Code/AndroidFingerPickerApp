package com.example.fingerpicker;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class FingerPicker extends View {

    private Paint paint;
    private Random rgen = new Random();
    private boolean isDrawing;

    private int numberOfPointers;
    private int []x = new int[10];
    private int []y = new int[10];




    public FingerPicker(Context context) {
        super(context);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        isDrawing = false;
    }

    protected void onDraw(Canvas canvas) {

        if(isDrawing){
            paint.setColor(Color.RED);
            canvas.drawCircle(x[numberOfPointers], y[numberOfPointers], 200, paint);

        }
        invalidate();
    }


    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                x[pointerId] = (int) event.getX(pointerIndex);
                y[pointerId] = (int) event.getY(pointerIndex);
                numberOfPointers++;
                isDrawing = true;
                Log.i(TAG, "ACTION_DOWN");
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                x[pointerId] = (int) event.getX(pointerIndex);
                y[pointerId] = (int) event.getY(pointerIndex);
                Log.i(TAG, "ACTION_MOVE");
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                x[pointerId] = (int) event.getX(pointerIndex);
                y[pointerId] = (int) event.getY(pointerIndex);
                numberOfPointers--;
                isDrawing = false;
                Log.i(TAG, "ACTION_UP");
                invalidate();
                break;
        }
        return true;

    }
}
