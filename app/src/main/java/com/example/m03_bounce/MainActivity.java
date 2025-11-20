package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private BouncingBallView bbView;

    private SeekBar seekX;
    private SeekBar seekY;
    private SeekBar seekDX;
    private SeekBar seekDY;

    private EditText edtName;
    private Spinner spnColor;
    private Button btnAdd;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bbView = findViewById(R.id.bbView);

        seekX = findViewById(R.id.seekX);
        seekY = findViewById(R.id.seekY);
        seekDX = findViewById(R.id.seekDX);
        seekDY = findViewById(R.id.seekDY);

        edtName = findViewById(R.id.edtName);
        spnColor = findViewById(R.id.spnColor);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);


        String[] colors = new String[] { "Red", "Green", "Blue", "Yellow", "White" };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colors
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnColor.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float x = seekX.getProgress();
                float y = seekY.getProgress();

                // seekDX / seekDY are 0..20, we shift to -10..10
                float dx = seekDX.getProgress() - 10f;
                float dy = seekDY.getProgress() - 10f;

                String name = edtName.getText().toString().trim();
                if (name.isEmpty()) {
                    name = "ball";
                }

                int color = getSelectedColor();


                bbView.RussButtonPressed(color, x, y, dx, dy, name);
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbView.clearBalls();
            }
        });
    }

    private int getSelectedColor() {
        int pos = spnColor.getSelectedItemPosition();
        switch (pos) {
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }
    }
}
