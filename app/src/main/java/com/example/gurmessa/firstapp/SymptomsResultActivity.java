package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SymptomsResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_result);

        Intent intent=getIntent();



        ImageView heartAttack= (ImageView) findViewById(R.id.imageViewHeartAttack);
        ImageView dyspnea= (ImageView) findViewById(R.id.imageViewdyspnea);
        ImageView angina= (ImageView) findViewById(R.id.imageViewangina);

        if(intent.getBooleanExtra("heart_attack",false)){
            heartAttack.setImageDrawable(getResources().getDrawable(R.drawable.right));
        }else {
            heartAttack.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
        }

        if(intent.getBooleanExtra("dyspnea",false)){
            dyspnea.setImageDrawable(getResources().getDrawable(R.drawable.right));
        }else {
            dyspnea.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
        }

        if(intent.getBooleanExtra("angina",false)){
            angina.setImageDrawable(getResources().getDrawable(R.drawable.right));
        }else {
            angina.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
        }
    }
    public void onClickButton(View view){
        Intent intent=new Intent(SymptomsResultActivity.this,AnalyzeDataActivity.class);
        startActivity(intent);
    }
}
