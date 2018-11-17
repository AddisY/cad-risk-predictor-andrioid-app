package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnalysisResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_result);

        TextView textView= (TextView) findViewById(R.id.textViewMessage);
        Intent intent=getIntent();
        String txt=intent.getStringExtra("message");
        textView.setText(txt);
    }
}
