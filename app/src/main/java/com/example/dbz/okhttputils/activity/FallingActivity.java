package com.example.dbz.okhttputils.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dbz.okhttputils.FallingView.FallObject;
import com.example.dbz.okhttputils.FallingView.FallingView;
import com.example.dbz.okhttputils.R;

public class FallingActivity extends AppCompatActivity implements View.OnClickListener{

    private FallingView fallingView;
    private String url;
    private Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_falling);

        url = getIntent().getStringExtra("bitmap");

        btnStart = findViewById(R.id.mStart);
        btnStop = findViewById(R.id.mStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.back);
        fallingView = findViewById(R.id.falling);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mStart:
                fallingView.setVisibility(View.VISIBLE);
                start();
                break;
            case R.id.mStop:
                fallingView.setVisibility(View.GONE);
                break;
        }
    }

    private void start(){
        //初始化一个雪花样式的fallObject
        FallObject.Builder builder = new FallObject.Builder(getResources(), R.mipmap.hongbao);
        FallObject fallObject = builder
                .setSpeed(7,true)
                .setSize(50,50,true)
                .setWind(5,true,true)
                .build();


        fallingView.addFallObject(fallObject,50);//添加50个下落物体对象
    }
}
