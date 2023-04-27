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
import java.util.HashMap;

public class FingerPicker extends View {

    private final Paint paint = new Paint();
    private final HashMap<Integer, PointF>listFingerPositions = new HashMap<>();

    public FingerPicker(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {


        for(PointF fingerPosition : listFingerPositions.values()){
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
                listFingerPositions.put(pointerId, fingerPosition);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i=0; i<event.getPointerCount(); i++){
                    int id = event.getPointerId(i);
                    fingerPosition = listFingerPositions.get(id);
                    if(fingerPosition != null){
                        fingerPosition.x = event.getX(i);
                        fingerPosition.y = event.getY(i);
                    }
                }
                //Log.i(TAG, "ACTION_MOVE");
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "ACTION_UP");
                listFingerPositions.remove(pointerId);
                invalidate();
                break;
        }
        return true;

    }
}
