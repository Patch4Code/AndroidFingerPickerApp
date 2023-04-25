package com.example.fingerpicker;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class FingerPicker extends View {

    private Paint paint;
    private Random rgen = new Random();
    private boolean isDrawing;

    //private int numberOfPointers;
    //private int []x = new int[10];
    //private int []y = new int[10];
    private ArrayList<PointF>listFingerPositions = new ArrayList<>();



    public FingerPicker(Context context) {
        super(context);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        //isDrawing = false;
    }

    protected void onDraw(Canvas canvas) {

        for(int i=0; i< listFingerPositions.size(); i++){
            PointF fingerPosition3 = listFingerPositions.get(i);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            canvas.drawCircle(fingerPosition3.x,fingerPosition3.y, 200, paint);
        }
        invalidate();
    }


    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF fingerPosition = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
                listFingerPositions.add(pointerId, fingerPosition);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i=0; i<event.getPointerCount(); i++){
                    int id = event.getPointerId(i);
                    PointF fingerPosition2 = listFingerPositions.get(id);
                    fingerPosition2.x = event.getX(i);
                    fingerPosition2.y = event.getY(i);
                }
                //Log.i(TAG, "ACTION_MOVE");
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                listFingerPositions.remove(pointerId);
                //Log.i(TAG, "ACTION_UP");
                invalidate();
                break;
        }
        return true;

    }
}
