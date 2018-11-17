package com.example.gurmessa.firstapp;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Service.ApiService;
import models.CONSTANTS;
import models.Doctor;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpDoctorActivity extends AppCompatActivity {

    EditText editTextUsername,editTextPassword,editTextPassword2,editTextFirstname,editTextLastName,editTextEmail;
    EditText editTextYearsOfExp,editTextHospital,editTextFieldOfStudy;

    TextInputLayout textInputLayoutUsername,textInputLayoutPassword,textInputLayoutPassword2,textInputLayoutFirstname,textInputLayoutLastName,textInputLayoutEmail;
    TextInputLayout textInputLayoutYearsOfExp,textInputLayoutHospital,textInputLayoutFieldOfStudy;

    String username,password,password2,email,firstname,lastname;
    int yearsOfExp;
    String hospital,fieldOfStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_doctor);


        editTextUsername= (EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextPassword2=(EditText) findViewById(R.id.editTextPassword2);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextFirstname= (EditText) findViewById(R.id.editTextFirstname);
        editTextLastName= (EditText) findViewById(R.id.editTextLastname);
        editTextYearsOfExp= (EditText) findViewById(R.id.edityearsofexp);
        editTextHospital= (EditText) findViewById(R.id.edithospital);
        editTextFieldOfStudy= (EditText) findViewById(R.id.editfieldOfStudy);

        textInputLayoutUsername= (TextInputLayout) findViewById(R.id.inputusername);
        textInputLayoutPassword= (TextInputLayout) findViewById(R.id.inputpassword);
        textInputLayoutPassword2= (TextInputLayout) findViewById(R.id.inputpassword2);
        textInputLayoutEmail= (TextInputLayout) findViewById(R.id.inputemail);
        textInputLayoutFirstname= (TextInputLayout) findViewById(R.id.inputfirstname);
        textInputLayoutLastName= (TextInputLayout) findViewById(R.id.inputlastname);
        textInputLayoutFieldOfStudy= (TextInputLayout) findViewById(R.id.inputfieldofStudy);
        textInputLayoutYearsOfExp= (TextInputLayout) findViewById(R.id.inputyearsofExp);
        textInputLayoutHospital= (TextInputLayout) findViewById(R.id.inputhospital);

    }


    public void validateAndSignUp(View view){

        if(validateuser() && validateDoctor()){
            User user=new User();
            user.setFirst_name(firstname);
            user.setLast_name(lastname);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            Doctor doctor=new Doctor(user);
            doctor.setYearsOfExperiance(yearsOfExp);
            doctor.setFieldOfStudy(fieldOfStudy);
            doctor.setHospital(hospital);

            signup(doctor);
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

        return true;
    }
    private boolean validateDoctor(){
        String yearsOfExpString =editTextYearsOfExp.getText().toString();
        hospital=editTextHospital.getText().toString();
        fieldOfStudy=editTextFieldOfStudy.getText().toString();


        if(hospital.isEmpty()){
            textInputLayoutHospital.setError("Hospital should not be blank");
            return false;
        }else {
            textInputLayoutHospital.setErrorEnabled(false);
        }

        if(fieldOfStudy.isEmpty()){
            textInputLayoutFieldOfStudy.setError("Field of study should not be blank");
            return false;
        }else {
            textInputLayoutFieldOfStudy.setErrorEnabled(false);
        }

        if(yearsOfExpString.isEmpty()){
            textInputLayoutYearsOfExp.setError("should be greater than zero");

            return false;
        }else {
            yearsOfExp=Integer.parseInt(yearsOfExpString);
            textInputLayoutYearsOfExp.setErrorEnabled(false);
        }
        return  true;
    }
    public void signup(Doctor doctor){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(CONSTANTS.base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit=builder.build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<Void> request=apiService.signupDoctor(doctor);
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
