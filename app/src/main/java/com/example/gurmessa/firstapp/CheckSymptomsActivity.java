package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rd.PageIndicatorView;

public class CheckSymptomsActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {
    View view1,view2,view3,view4,view5;
    int position =0;
    Button buttonPrevious,buttonNext,buttonFinish;
    TextView textViewQuestion;
    PageIndicatorView pageIndicatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_symptoms);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        view1=findViewById(R.id.view_1);
        view2=findViewById(R.id.view_2);
        view3=findViewById(R.id.view_3);
        view4=findViewById(R.id.view_4);
        view5=findViewById(R.id.view_5);
        buttonPrevious= (Button) findViewById(R.id.buttonPrevious);
        buttonNext= (Button) findViewById(R.id.buttonNext);
        buttonFinish= (Button) findViewById(R.id.buttonFinish);
        textViewQuestion= (TextView) findViewById(R.id.textViewQuestion);

        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(5); // specify total count of indicators
        pageIndicatorView.setSelection(0);

        updateViews();
    }

    private void updateViews(){
        switch (position){
            case 0:
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);

                textViewQuestion.setText(R.string.info_1);
                buttonPrevious.setVisibility(View.INVISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                pageIndicatorView.setSelection(0);
                break;
            case 1:
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);

                textViewQuestion.setText(R.string.info_2);
                buttonPrevious.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                pageIndicatorView.setSelection(1);
                break;
            case 2:
                view3.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);

                textViewQuestion.setText(R.string.symptom_angina);
                buttonPrevious.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.GONE);
                pageIndicatorView.setSelection(2);
                break;
            case 3:
                textViewQuestion.setText(R.string.symptom_heart_attack);
                view4.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view5.setVisibility(View.GONE);

                buttonPrevious.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.GONE);
                pageIndicatorView.setSelection(3);
                break;
            case 4:
                textViewQuestion.setText(R.string.symptom_dyspnea);
                view5.setVisibility(View.VISIBLE);
                view4.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);

                buttonPrevious.setVisibility(View.VISIBLE);
                buttonNext.setVisibility(View.GONE);
                buttonFinish.setVisibility(View.VISIBLE);
                pageIndicatorView.setSelection(4);
                break;
        }
    }
    public void buttonClick(View view){
        switch (view.getId()){
            case R.id.buttonPrevious:
                if(position>0){
                    position=position-1;
                    updateViews();
                }
                break;
            case R.id.buttonNext:
                if(position<4){
                    position+=1;
                    updateViews();
                }
                break;
            case R.id.buttonFinish:
                int angina=0;



                if(((CheckBox) findViewById(R.id.checkbox_3_1)).isChecked()){
                    angina+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_3_2)).isChecked()){
                    angina+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_3_3)).isChecked()){
                    angina+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_3_4)).isChecked()){
                    angina+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_3_5)).isChecked()){
                    angina+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_3_6)).isChecked()){
                    angina+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_3_7)).isChecked()){
                    angina+=1;
                }

                int heartAttack=0;

                if(((CheckBox) findViewById(R.id.checkbox_4_0)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_1)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_2)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_3)).isChecked()){
                    heartAttack+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_4_4)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_5)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_6)).isChecked()){
                    heartAttack+=1;
                }
                if(((CheckBox) findViewById(R.id.checkbox_4_7)).isChecked()){
                    heartAttack+=1;
                }

                int dyspnea=0;

                if(((CheckBox) findViewById(R.id.checkbox_5_0)).isChecked()){
                    dyspnea+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_5_1)).isChecked()){
                    dyspnea+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_5_2)).isChecked()){
                    dyspnea+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_5_3)).isChecked()){
                    dyspnea+=1;
                }

                if(((CheckBox) findViewById(R.id.checkbox_5_4)).isChecked()){
                    dyspnea+=1;
                }



                Intent intent=new Intent(CheckSymptomsActivity.this,SymptomsResultActivity.class);
                intent.putExtra("heart_attack",heartAttack>3);
                intent.putExtra("dyspnea",dyspnea>0);
                intent.putExtra("angina",angina>5);

                startActivity(intent);

                break;
        }
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
    }
    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {

        }

    }
    public void slideToRight(View view){
        TranslateAnimation animate = new TranslateAnimation(0,view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }
    // To animate view slide out from right to left
    public void slideToLeft(View view){
        TranslateAnimation animate = new TranslateAnimation(0,-view.getWidth(),0,0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //analyze data
            Intent intent=new Intent(CheckSymptomsActivity.this,AnalyzeDataActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            //check symptom

        }
        else if (id == R.id.nav_slideshow) {
            //prevention
            Intent intent=new Intent(CheckSymptomsActivity.this,PreventionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            //Treatment
            Intent intent=new Intent(CheckSymptomsActivity.this,RiskFactorActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(CheckSymptomsActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
