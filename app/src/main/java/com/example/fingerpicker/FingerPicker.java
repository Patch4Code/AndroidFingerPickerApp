package com.example.fingerpicker;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FingerPicker extends View {
    private final Paint paint = new Paint();
    private final Paint blackPaint = new Paint();
    private final HashMap<Integer, Circle> listFingerCircles = new HashMap<>();
    private final Random random = new Random();

    private final Handler handler = new Handler();

    private boolean winnerSelected = false;


    public FingerPicker(Context context) {
        super(context);
    }

    private void selectWinner() {
        Log.d("FingerPicker", "Select Winner Aufruf");
        if (listFingerCircles.size() >= 2) {
            Log.d("FingerPicker", "Select Winner if Aufruf erfüllt");
            ArrayList<Integer> indexList = new ArrayList<>(listFingerCircles.keySet());
            int randomIndex = indexList.get(random.nextInt(indexList.size()));
            Circle winnerCircle = listFingerCircles.get(randomIndex);

            ArrayList<Integer> keysToKeep = new ArrayList<>();
            keysToKeep.add(randomIndex);
            listFingerCircles.keySet().retainAll(keysToKeep);

            assert winnerCircle != null;
            setBackgroundColor(winnerCircle.getColor());

            winnerSelected = true;
        }
    }

    protected void onDraw(Canvas canvas) {
        for (Circle circle : listFingerCircles.values()) {
            paint.setColor(circle.getColor());
            canvas.drawCircle(circle.getX(), circle.getY(), 230f, blackPaint);
            canvas.drawCircle(circle.getX(), circle.getY(), 200f, paint);
        }


        invalidate();
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("FingerPicker", "ACTION_POINTER_DOWN");
                PointF fingerPosition = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
                int randomColor = generateRandomColor();
                Circle circle = new Circle(fingerPosition.x, fingerPosition.y, randomColor);
                listFingerCircles.put(pointerId, circle);
                invalidate();

                handler.removeCallbacksAndMessages(null);

                //draw gif


                handler.postDelayed(() -> {
                    //remove gif


                    Log.d("FingerPicker", "run Aufruf");
                    if (!winnerSelected) {
                        Log.d("FingerPicker", "run if erfüllt");
                        selectWinner();
                    }
                }, 3000);

                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int id = event.getPointerId(i);
                    Circle circle1 = listFingerCircles.get(id);
                    if (circle1 != null) {
                        circle1.setX(event.getX(i));
                        circle1.setY(event.getY(i));
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("FingerPicker", "ACTION_POINTER_UP");
                listFingerCircles.remove(pointerId);

                if (listFingerCircles.size() < 1) {

                    winnerSelected = false;
                    setBackgroundColor(Color.BLACK);
                    Log.d("FingerPicker", "winnerSelected = false gesetzt");
                }

                invalidate();
                break;
        }
        return true;
    }

    private int generateRandomColor() {
        int red = random.nextInt(220);
        int green = random.nextInt(220);
        int blue = random.nextInt(220);
        return Color.rgb(red, green, blue);
    }

    private static class Circle {
        private float x;
        private float y;
        private final int color;

        public Circle(float x, float y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public int getColor() {
            return color;
        }
    }
}
