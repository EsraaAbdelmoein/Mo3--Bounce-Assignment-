package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class BouncingBallView extends View {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Square> squares = new ArrayList<>();
    private Box box;
    private float previousX, previousY;
    private Random rand = new Random();

    private RectF targetRect = new RectF();
    private Paint targetPaint = new Paint();
    private int score = 0;

    private Paint hud = new Paint();
    private Paint bgPaint = new Paint();
    private boolean tealTheme = false;
    private LinearGradient grad1, grad2;

    private final int[] palette = new int[] {
            Color.MAGENTA,
            Color.parseColor("#00BCD4"),
            Color.parseColor("#7C4DFF"),
            Color.parseColor("#FF4081"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#4CAF50")
    };

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        box = new Box(Color.YELLOW);
        balls.add(new Ball(Color.RED, 120, 180, 5, 3));
        balls.add(new Ball(Color.BLUE, 320, 420, -4, 6));
        targetPaint.setColor(Color.BLACK);
        hud.setColor(Color.WHITE);
        hud.setTextSize(52f);
        hud.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (box != null) box.set(w, h);
        float rectW = 80f;
        float rectH = 80f;
        float cx = w * 0.5f;
        float cy = h * 0.75f;
        targetRect.set(cx - rectW / 2f, cy - rectH / 2f, cx + rectW / 2f, cy + rectH / 2f);
        grad1 = new LinearGradient(0, 0, w, h, Color.parseColor("#FFF176"), Color.parseColor("#FFD54F"), Shader.TileMode.CLAMP);
        grad2 = new LinearGradient(0, 0, w, h, Color.parseColor("#4FC3C2"), Color.parseColor("#00838F"), Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        bgPaint.setShader(tealTheme ? grad2 : grad1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

        canvas.drawRect(targetRect, targetPaint);

        for (Ball b : balls) {
            b.draw(canvas);
            b.moveWithCollisionDetection(box);
            if (circleIntersectsRect(b.x, b.y, b.radius, targetRect)) {
                score++;
                Log.d("BounceGame", "Ball hit rectangle! Score = " + score);
                b.speedX = -b.speedX;
                b.speedY = -b.speedY;
            }
        }

        for (Square s : squares) {
            s.draw(canvas);
            s.moveWithCollisionDetection(box);
            RectF sRect = new RectF(s.x, s.y, s.x + s.size, s.y + s.size);
            if (RectF.intersects(sRect, targetRect)) {
                score++;
                Log.d("BounceGame", "Square hit rectangle! Score = " + score);
                s.speedX = -s.speedX;
                s.speedY = -s.speedY;
            }
        }

        canvas.drawText("Score: " + score, 32f, 72f, hud);
        canvas.drawText(tealTheme ? "Theme: Teal" : "Theme: Yellow", 32f, 132f, hud);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                previousX = x; previousY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dx = x - previousX;
                float dy = y - previousY;
                float speed = Math.abs(dx) + Math.abs(dy);
                int color = palette[rand.nextInt(palette.length)];
                if (speed > 8f) {
                    squares.add(new Square(color, previousX, previousY, dx * 1.4f, dy * 1.4f));
                } else {
                    balls.add(new Ball(color, previousX, previousY, dx, dy));
                }
                previousX = x; previousY = y;
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void russButtonPressed() {
        int cx = box.xMax / 2;
        int cy = box.yMax / 2;
        balls.add(new Ball(Color.RED, cx, cy, 8, 6));
        invalidate();
    }

    public void toggleTheme(View v) {
        tealTheme = !tealTheme;
        invalidate();
    }

    private boolean circleIntersectsRect(float cx, float cy, float r, RectF rect) {
        float clampedX = Math.max(rect.left, Math.min(cx, rect.right));
        float clampedY = Math.max(rect.top, Math.min(cy, rect.bottom));
        float dx = cx - clampedX;
        float dy = cy - clampedY;
        return dx * dx + dy * dy <= r * r;
    }
}
