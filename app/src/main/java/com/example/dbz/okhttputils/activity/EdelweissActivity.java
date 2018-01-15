package com.example.dbz.okhttputils.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dbz.okhttputils.R;
import com.example.dbz.okhttputils.edelweiss.EdelweissView;

public class EdelweissActivity extends AppCompatActivity {

    private EdelweissView edelweiss;
    private Button mStart, mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edelweiss);

        edelweiss = findViewById(R.id.edelweiss);
        mStart = findViewById(R.id.start);
        mStop = findViewById(R.id.stop);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edelweiss.setVisibility(View.VISIBLE);
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edelweiss.setVisibility(View.GONE);
            }
        });

    }
}
