package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Box {
    int color;
    int xMax, yMax;
    private Paint paint;

    public Box(int color) {
        this.color = color;
        paint = new Paint();
        paint.setColor(color);
    }

    public void set(int width, int height) {
        xMax = width;
        yMax = height;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0, 0, xMax, yMax, paint);
    }
}
