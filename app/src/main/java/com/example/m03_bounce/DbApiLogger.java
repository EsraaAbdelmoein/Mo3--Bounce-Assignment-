package com.example.m03_bounce;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class DbApiLogger {

    private static final String TAG = "DbApiLogger";

    public static void run(Context context) {
        Log.v(TAG, "=== DB API LOGGING TEST START ===");

        DBClass db = new DBClass(context);


        try {
            db.clear();
            Log.v(TAG, "clear() called - all rows removed");
        } catch (Exception e) {
            Log.v(TAG, "clear() failed or not implemented: " + e.getMessage());
        }


        DataModel ball1 = new DataModel(
                20.0f, 20.0f, -4.0f, 4.0f,
                0xFFFF0000,   //red
                "ball1"
        );

        DataModel ball2 = new DataModel(
                50.0f, 80.0f, 3.0f, -2.0f,
                0xFF0000FF,   // blue
                "ball2"
        );

        db.save(ball1);
        Log.v(TAG, "save(ball1) called: " + ball1.toString());

        db.save(ball2);
        Log.v(TAG, "save(ball2) called: " + ball2.toString());

        // 3) Count rows
        int count = db.count();
        Log.v(TAG, "count() -> " + count);

        // 4) Log all rows using findAll()
        List<DataModel> all = db.findAll();
        Log.v(TAG, "findAll() size = " + all.size());
        for (DataModel dm : all) {
            Log.v(TAG, "row: " + dm.toString());
        }

        Log.v(TAG, "=== DB API LOGGING TEST END ===");
    }
}
