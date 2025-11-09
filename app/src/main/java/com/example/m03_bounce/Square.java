package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Square {
    public float x, y;
    public float speedX, speedY;
    public int color;
    public int size = 100;

    private final Paint paint = new Paint();

    public Square(int color, float x, float y, float dx, float dy) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + size, y + size, paint);
    }

    public void moveWithCollisionDetection(Box box) {
        x += speedX;
        y += speedY;
        if (x < 0 || x + size > box.xMax) speedX = -speedX;
        if (y < 0 || y + size > box.yMax) speedY = -speedY;
    }
}
