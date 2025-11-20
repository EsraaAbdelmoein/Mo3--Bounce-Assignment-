package com.example.m03_bounce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BouncingBallView extends View {

    private final ArrayList<Ball> balls = new ArrayList<>();
    private Box box;
    private DBClass DBtest;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
<<<<<<< HEAD
        box = new Box(Color.BLACK);
        DBtest = new DBClass(context);
        List<DataModel> all = DBtest.findAll();
        Log.d("VIEW", "Loaded balls from DB: " + (all == null ? 0 : all.size()));
        if (all != null) {
            for (DataModel one : all) {
                balls.add(new Ball(
                        one.getColor(),
                        (float) one.getModelX(),
                        (float) one.getModelY(),
                        (float) one.getModelDX(),
                        (float) one.getModelDY()
                ));
            }
        }
        setFocusable(true);
        requestFocus();
        setFocusableInTouchMode(true);
=======
        box = new Box(Color.YELLOW);
        balls.add(new Ball(Color.RED, 120, 180, 5, 3));
        balls.add(new Ball(Color.BLUE, 320, 420, -4, 6));
        targetPaint.setColor(Color.BLACK);
        Ball demoBall = new Ball(android.graphics.Color.RED);

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
>>>>>>> f5959123c0c6bae7c38312429fc6b214d413f36c
    }

    @Override
    protected void onDraw(Canvas canvas) {
        box.draw(canvas);
        for (Ball b : balls) {
            b.draw(canvas);
            b.moveWithCollisionDetection(box);
        }
        invalidate();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        box.set(0, 0, w, h);
        Log.d("VIEW", "onSizeChanged w=" + w + " h=" + h);
    }

    public void RussButtonPressed(int color, float x, float y, float dx, float dy, String name) {
        Log.d("VIEW", "Add ball pressed: x=" + x + ", y=" + y + ", dx=" + dx + ", dy=" + dy + ", color=" + color + ", name=" + name);
        balls.add(new Ball(color, x, y, dx, dy));
        DataModel newBall = new DataModel(x, y, dx, dy, color, name);
        DBtest.save(newBall);
        invalidate();
    }

    public void clearBalls() {
        balls.clear();
        SQLiteDatabase db = DBtest.getWritableDatabase();
        db.delete("sample_table", null, null);
        db.close();
        Log.d("VIEW", "Cleared balls and DB");
        invalidate();
    }
}
