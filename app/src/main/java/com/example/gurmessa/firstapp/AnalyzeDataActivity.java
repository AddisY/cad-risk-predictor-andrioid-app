package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;

import Service.ApiService;
import models.AnalysisResult;
import models.CONSTANTS;
import models.Data;
import models.OrdinaryUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.wefor.circularanim.CircularAnim;

public class AnalyzeDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button buttonPrevious,buttonNext,buttonFinish;
    View layout_1,layout_2;
    Spinner spinnerSex,spinnerChestPain,spinnerRestecg,spinnerFbs,spinnerThal,spinnerSlope,spinnerExang;
    EditText editTextAge,editTexttrestbps,editTextchol,editTextthalach,editTextoldpeak,editTextca;
    TextInputLayout inputAge,inputtrestbps,inputchol,inputthalach,inputoldpeak,inputTextca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        layout_1=findViewById(R.id.layout_1);
        layout_2=findViewById(R.id.layout_2);


        inputAge= (TextInputLayout) findViewById(R.id.inputAge);
        inputtrestbps= (TextInputLayout) findViewById(R.id.inputtrestbps);
        inputchol= (TextInputLayout) findViewById(R.id.inputchol);
        inputthalach= (TextInputLayout) findViewById(R.id.inputthalach);
        inputoldpeak= (TextInputLayout) findViewById(R.id.inputoldpeak);
        inputTextca= (TextInputLayout) findViewById(R.id.inputca);

        editTextAge= (EditText) findViewById(R.id.editTextAge);
        editTexttrestbps= (EditText) findViewById(R.id.editTexttrestbps);
        editTextchol= (EditText) findViewById(R.id.editTextchol);
        editTextthalach= (EditText) findViewById(R.id.editTextthalach);
        editTextoldpeak= (EditText) findViewById(R.id.editTextoldpeak);
        editTextca= (EditText) findViewById(R.id.editTextca);


        buttonPrevious= (Button) findViewById(R.id.buttonPrevious);
        buttonNext= (Button) findViewById(R.id.buttonNext);
        buttonFinish= (Button) findViewById(R.id.buttonFinish);

        spinnerSex = (Spinner) findViewById(R.id.spinner_sex);
        spinnerChestPain= (Spinner) findViewById(R.id.spinner_chest_pain);
        spinnerFbs= (Spinner) findViewById(R.id.spinner_fbs);
        spinnerRestecg= (Spinner) findViewById(R.id.spinner_restecg);

        spinnerThal= (Spinner) findViewById(R.id.spinner_thal);
        spinnerSlope= (Spinner) findViewById(R.id.spinner_slope);
        spinnerExang= (Spinner) findViewById(R.id.spinner_exang);

        ArrayAdapter<CharSequence> adapterSex = ArrayAdapter.createFromResource(this,
                R.array.SEX, android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterSex);

        ArrayAdapter<CharSequence> adapterChestPain = ArrayAdapter.createFromResource(this,
                R.array.CHEST_PAIN_TYPE, android.R.layout.simple_spinner_item);
        adapterChestPain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChestPain.setAdapter(adapterChestPain);

        ArrayAdapter<CharSequence> adapterFbs = ArrayAdapter.createFromResource(this,
                R.array.FASTING_BLOOD_SUGER, android.R.layout.simple_spinner_item);
        adapterFbs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFbs.setAdapter(adapterFbs);


        ArrayAdapter<CharSequence> adapterRestecg = ArrayAdapter.createFromResource(this,
                R.array.REST_ECG, android.R.layout.simple_spinner_item);
        adapterRestecg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRestecg.setAdapter(adapterRestecg);

///new
        ArrayAdapter<CharSequence> adapterExang = ArrayAdapter.createFromResource(this,
                R.array.EXERCISE_INDUCED_ANGINA, android.R.layout.simple_spinner_item);
        adapterExang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExang.setAdapter(adapterExang);

        ArrayAdapter<CharSequence> adapterSlope = ArrayAdapter.createFromResource(this,
                R.array.SLOPE, android.R.layout.simple_spinner_item);
        adapterSlope.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSlope.setAdapter(adapterSlope);

        ArrayAdapter<CharSequence> adapterThal = ArrayAdapter.createFromResource(this,
                R.array.THAL, android.R.layout.simple_spinner_item);
        adapterThal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThal.setAdapter(adapterThal);




        //spinner.setOnItemSelectedListener(this);
    }



    public void buttonClick(View view){
        switch (view.getId()){
            case R.id.buttonPrevious:
                slideDown(layout_2);
                slideUp(layout_1);
                buttonNext.setVisibility(View.VISIBLE);
                buttonFinish.setVisibility(View.GONE);
                //buttonPrevious.setVisibility(View.INVISIBLE);
                CircularAnim.hide(buttonPrevious).go();
                layout_1.setVisibility(View.VISIBLE);
                layout_2.setVisibility(View.GONE);
                break;
            case R.id.buttonNext:
                slideDown(layout_1);
                slideUp(layout_2);
                buttonNext.setVisibility(View.GONE);
                buttonFinish.setVisibility(View.VISIBLE);
                buttonPrevious.setVisibility(View.VISIBLE);
                layout_1.setVisibility(View.GONE);
                layout_2.setVisibility(View.VISIBLE);
                break;
            case  R.id.buttonFinish:

                if (isValid()){
                    Data data=new Data();


                    String age=editTextAge.getText().toString();
                    data.setAge(Integer.parseInt(age));

                    switch (spinnerSex.getSelectedItemPosition()){
                        case 0:
                            data.setSex('M');
                            break;
                        case 1:
                            data.setSex('F');
                            break;
                    }

                    switch (spinnerChestPain.getSelectedItemPosition()){
                        case 0:
                            data.setCp(1);
                            break;
                        case 1:
                            data.setCp(2);
                            break;
                        case 2:
                            data.setCp(3);
                            break;
                        case 3:
                            data.setCp(4);
                            break;
                    }


                    String trestbps=editTexttrestbps.getText().toString();
                    data.setTrestbps(Integer.parseInt(trestbps));

                    String chol=editTextchol.getText().toString();
                    data.setChol(Integer.parseInt(chol));

                    switch (spinnerFbs.getSelectedItemPosition()){
                        case 0:
                            data.setFbs(1);
                            break;
                        case 1:
                            data.setFbs(0);
                            break;
                    }
                    switch (spinnerRestecg.getSelectedItemPosition()){
                        case 0:
                            data.setRestecg(0);
                            break;
                        case 1:
                            data.setRestecg(1);
                            break;
                        case 2:
                            data.setRestecg(2);
                            break;
                    }
                    String thalach=editTextthalach.getText().toString();
                    data.setThalach(Integer.parseInt(thalach));

                    switch (spinnerExang.getSelectedItemPosition()){
                        case 0:
                            data.setExang(1);
                            break;
                        case 1:
                            data.setExang(0);
                            break;
                    }

                    String oldpeak=editTextoldpeak.getText().toString();
                    data.setOldpeak(Integer.parseInt(oldpeak));

                    switch (spinnerChestPain.getSelectedItemPosition()){
                        case 0:
                            data.setSlope(1);
                            break;
                        case 1:
                            data.setSlope(2);
                            break;
                        case 2:
                            data.setSlope(3);
                            break;
                    }

                    String ca=editTextca.getText().toString();
                    data.setCa(Integer.parseInt(ca));

                    switch (spinnerThal.getSelectedItemPosition()){
                        case 0:
                            data.setThal(3);
                            break;
                        case 1:
                            data.setThal(6);
                            break;
                        case 2:
                            data.setThal(7);
                            break;
                    }

                    analyzeData(data);
                   // Gson gson = new Gson();
                   // Toast.makeText(AnalyzeDataActivity.this,gson.toJson(data),Toast.LENGTH_SHORT).show();;
                }

        }
    }

    // slide the view from below itself to the current position
    public void slideUp(View view){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    // slide the view from its current position to below itself
    public void slideDown(View view){
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //analyze data

        } else if (id == R.id.nav_gallery) {
            //check symptom
            Intent intent=new Intent(AnalyzeDataActivity.this,CheckSymptomsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            //prevention
            Intent intent=new Intent(AnalyzeDataActivity.this,PreventionActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            //Treatment
            Intent intent=new Intent(AnalyzeDataActivity.this,RiskFactorActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_send) {
            Intent intent=new Intent(AnalyzeDataActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isValid(){

        if(editTextAge.getText().toString().isEmpty()){
            inputAge.setError("Should not be blank");
            return false;
        }else {
            inputAge.setErrorEnabled(false);
        }
        if(editTexttrestbps.getText().toString().isEmpty()){
            inputtrestbps.setError("Should not be blank");
            return false;
        }else {
            inputtrestbps.setErrorEnabled(false);
        }
        if(editTextchol.getText().toString().isEmpty()){
            inputchol.setError("Should not be blank");
            return false;
        }else {
            inputchol.setErrorEnabled(false);
        }
        if(editTextthalach.getText().toString().isEmpty()){
            inputAge.setError("Should not be blank");
            return false;
        }else {
            inputthalach.setErrorEnabled(false);
        }
        if(editTextoldpeak.getText().toString().isEmpty()){
            inputoldpeak.setError("Should not be blank");
            return false;
        }
        else {
            inputoldpeak.setErrorEnabled(false);
        }
        if(editTextca.getText().toString().isEmpty()){
            inputTextca.setError("Should not be blank");
            return false;
        }else {
            inputTextca.setErrorEnabled(false);
        }
        return true;
    }

    private void analyzeData(Data mydata){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(CONSTANTS.base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<AnalysisResult> request=apiService.analyzeData(mydata);
        request.enqueue(new Callback<AnalysisResult>() {
            @Override
            public void onResponse(Call<AnalysisResult> call, Response<AnalysisResult> response) {
                String message=response.body().getMessage();
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(AnalyzeDataActivity.this,AnalysisResultActivity.class);
                intent.putExtra("message",message);
                startActivity(intent);


            }

            @Override
            public void onFailure(Call<AnalysisResult> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(),"Failed to connect ",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public AlertDialog getDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        builder.setMessage(message)
                .setTitle("Analysis result");

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
