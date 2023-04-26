package com.example.fingerpicker;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class FingerPicker extends View {

    private final Random rgen = new Random();
    private final Paint paint = new Paint();

    private final ArrayList<PointF>listFingerPositions = new ArrayList<>();



    public FingerPicker(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {


        for(int i=0; i< listFingerPositions.size(); i++){
            PointF fingerPosition = listFingerPositions.get(i);
            paint.setColor(Color.RED);
            canvas.drawCircle(fingerPosition.x,fingerPosition.y, 200f, paint);
        }

        invalidate();
    }


    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {

        //Index der Arrays in der die Fingerposition (x und y) gespeichert sind
        int pointerIndex = event.getActionIndex();
        //Eindeutige Kennung die einem Finger zugeordnet wird
        int pointerId = event.getPointerId(pointerIndex);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF fingerPosition = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
                listFingerPositions.add(pointerId, fingerPosition);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i=0; i<event.getPointerCount(); i++){
                    int id = event.getPointerId(i);
                    fingerPosition = listFingerPositions.get(id);
                    fingerPosition.x = event.getX(i);
                    fingerPosition.y = event.getY(i);
                }
                //Log.i(TAG, "ACTION_MOVE");
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "ACTION_UP");

                Log.i(TAG, "REMOVE: " + pointerId);
                listFingerPositions.remove(pointerId);



                invalidate();
                break;
        }
        return true;

    }
}
