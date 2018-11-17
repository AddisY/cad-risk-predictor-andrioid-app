package com.example.gurmessa.firstapp;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Service.ApiService;
import models.CONSTANTS;
import models.OrdinaryUser;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpUserActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword,editTextPassword2,editTextFirstname,editTextLastName,editTextEmail,editTextPhoneNum;
    TextInputLayout textInputLayoutUsername,textInputLayoutPassword,textInputLayoutPassword2,textInputLayoutFirstname,textInputLayoutLastName,textInputLayoutEmail,textInputLayoutPhoneNum;

    String username,password,password2,email,firstname,lastname;
    String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);


        editTextUsername= (EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2=(EditText) findViewById(R.id.editTextPassword2);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextFirstname= (EditText) findViewById(R.id.editTextFirstname);
        editTextLastName= (EditText) findViewById(R.id.editTextLastname);
        editTextPhoneNum= (EditText) findViewById(R.id.editTextPhoneNum);

        textInputLayoutUsername= (TextInputLayout) findViewById(R.id.inputusername);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.inputpassword);
        textInputLayoutPassword2= (TextInputLayout) findViewById(R.id.inputpassword2);
        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.inputemail);
        textInputLayoutFirstname= (TextInputLayout) findViewById(R.id.inputfirstname);
        textInputLayoutLastName= (TextInputLayout) findViewById(R.id.inputlastname);
        textInputLayoutPhoneNum= (TextInputLayout) findViewById(R.id.inputphonenumber);

    }

    public void validateAndSignUp(View view){

        if(validateuser() && validateOrdinaryuser()){
            User user=new User();
            user.setFirst_name(firstname);
            user.setLast_name(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            OrdinaryUser ordinaryUser=new OrdinaryUser(user);
            ordinaryUser.setPhoneNumber(phonenum);

            signup(ordinaryUser);
        }


    }
    private boolean validateuser(){
        username=editTextUsername.getText().toString();
        password=editTextPassword.getText().toString();
        password2=editTextPassword2.getText().toString();
        email=editTextEmail.getText().toString();
        firstname=editTextFirstname.getText().toString();
        lastname=editTextLastName.getText().toString();

        if(username.isEmpty()){
            textInputLayoutUsername.setError("Username should not be blank");

            return false;
        }else if (username.length()<4){
            textInputLayoutUsername.setError("minimum 4 characters required");

            return false;
        }else {
            textInputLayoutUsername.setErrorEnabled(false);
        }
        if(firstname.isEmpty()){
            textInputLayoutFirstname.setError("Firstname should not be blank");

            return false;
        }else {
            textInputLayoutFirstname.setErrorEnabled(false);
        }
        if(lastname.isEmpty()){
            textInputLayoutLastName.setError("Lastname should not be blank");

            return false;
        }else {
            textInputLayoutLastName.setErrorEnabled(false);
        }
        if(email.isEmpty()){
            textInputLayoutEmail.setError("Email should not be blank");

            return false;
        }else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        if(password.length()<4){
            textInputLayoutPassword.setError("minimum 4 characters required");

            return false;
        }else {
            if(!password.equals(password2)){
                textInputLayoutPassword2.setError("passwords should be the same");

            }else {
                textInputLayoutPassword.setErrorEnabled(false);
                textInputLayoutPassword2.setErrorEnabled(false);
            }
        }

        return  true;
    }
    private boolean validateOrdinaryuser(){
        String stringphonenum=editTextPhoneNum.getText().toString();
        phonenum= PhoneNumberUtils.formatNumber(stringphonenum);
        if(phonenum.isEmpty()){
            textInputLayoutPhoneNum.setError("wrong phone number");
            return false;
        }else {
            textInputLayoutPhoneNum.setErrorEnabled(false);
        }
        return true;
    }

    public void signup(OrdinaryUser user){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(CONSTANTS.base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<Void> request=apiService.signupOrdinaryUser(user);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(),"Succesfuly signed up",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
