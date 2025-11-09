package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {
    float x, y;
    float speedX, speedY;
    float radius = 50f;
    private final int color;
    private final Paint paint;

    public Ball(int color) {
        this.color = color;
        this.x = radius + 20;
        this.y = radius + 40;
        this.speedX = 5;
        this.speedY = 3;
        paint = new Paint();
        paint.setColor(this.color);
    }

    public Ball(int color, float x, float y, float dx, float dy) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void moveWithCollisionDetection(Box box) {
        x += speedX;
        y += speedY;
        if (x - radius < 0 || x + radius > box.xMax) speedX = -speedX;
        if (y - radius < 0 || y + radius > box.yMax) speedY = -speedY;
    }
}
