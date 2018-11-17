package com.example.gurmessa.firstapp;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import Service.ApiService;
import models.CONSTANTS;
import models.User;
import models.UserData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import top.wefor.circularanim.CircularAnim;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword;
    TextInputLayout textInputLayoutUsername,textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername= (EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutUsername= (TextInputLayout) findViewById(R.id.inputusername);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.inputpassword);
    }

    public void validateAndLogin(View view){
        if(isValidated()){

        }
    }
    public boolean isValidated(){
        String username=editTextUsername.getText().toString();
        String password=editTextPassword.getText().toString();

        if(username.isEmpty()){
            textInputLayoutUsername.setError("Username should not be blank");
            return false;
        }else if (username.length()<4){
            textInputLayoutUsername.setError("minimum 4 characters required");
            return false;
        }else {
            textInputLayoutUsername.setErrorEnabled(false);
        }

        if(password.length()<4){
            textInputLayoutPassword.setError("minimum 4 characters required");
            return false;

        }else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        login(username,password);

        return true;
    }
    public void signup(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.buttonSignupDoctor:
                intent=new Intent(LoginActivity.this,SignUpDoctorActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonSignupOrdinaryUser:
                intent=new Intent(LoginActivity.this,SignUpUserActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void login(String username,String password){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(CONSTANTS.base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        ApiService apiService=retrofit.create(ApiService.class);

        Map map=new HashMap();
        map.put("username",username);
        map.put("password",password);

        final Call<Object> request=apiService.login(map);

        request.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if (response.code()==200){
                    Toast.makeText(getApplicationContext(),"Successfully logged in",Toast.LENGTH_SHORT).show();
                    Log.d("user",response.body().toString());

                    UserData userData=UserData.getInstance(getApplicationContext());
                    userData.login();
                    //userData.setUsername(response.body().getUsername());
                    userData.setToken(response.body().toString());

                    Button buttonLogin= (Button) findViewById(R.id.buttonLogin);
                    CircularAnim.fullActivity(LoginActivity.this, buttonLogin)
                            .colorOrImageRes(R.color.colorPrimary)
                            .go(new CircularAnim.OnAnimationEndListener() {
                                @Override
                                public void onAnimationEnd() {
                                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                }
                            });


                }else {
                    Toast.makeText(getApplicationContext(),"Wrong password",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                Log.d("error",throwable.getMessage());
                Toast.makeText(getApplicationContext(),"unSuccesful",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

