package com.oceansoftwares.foodies;

import android.service.autofill.UserData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    Button signupBtn;
    TextView signup_loginText;
    EditText user_firstname, user_lastname, user_email, user_password, user_mobile;
    EditText user_address, user_country, user_area, user_city, user_postcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user_firstname = findViewById(R.id.user_firstname);
        user_lastname =  findViewById(R.id.user_lastname);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_mobile = findViewById(R.id.user_mobile);
        user_address = findViewById(R.id.address);
        user_country = findViewById(R.id.country);
        user_area = findViewById(R.id.zone);
        user_city = findViewById(R.id.city);
        user_postcode = findViewById(R.id.postcode);
        signupBtn = findViewById(R.id.signupBtn);
        signup_loginText = findViewById(R.id.signup_loginText);

        signupBtn.setOnClickListener(this);

        signup_loginText.setOnClickListener(this);

    }

    private void usersignup(){
        String firstname = user_firstname.getText().toString().trim();
        String lastname = user_lastname.getText().toString().trim();
        String email = user_email.getText().toString().trim();
        String password = user_password.getText().toString().trim();
        String mobile = user_mobile.getText().toString().trim();
        String address = user_address.getText().toString().trim();
        String country = "india";
        String area = "Tamil Nadu";
        String city = user_city.getText().toString().trim();
        String postcode = user_postcode.getText().toString().trim();

        if(email.isEmpty()){
        user_email.setError("Email is Required");
        user_email.requestFocus();
        return;
        }

      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
          user_email.setError("Enter a valid Email");
          user_email.requestFocus();
          return;
      }
        if(password.isEmpty()){
            user_password.setError("Password is Required");
            user_password.requestFocus();
            return;
        }
        if(password.length() <6){
            user_password.setError("Password should be atleast 6 character Long");
            user_password.requestFocus();
            return;
        }
        /* Do Registration Using API Call */

                Call<UserData> call = RetrofitClient
                .getData()
                .getApi()
                .processRegistration(firstname,lastname,email,password,mobile,address);

                call.enqueue(new Callback<UserData>() {
                    @Override
                    public void onResponse(Call<UserData> call, Response<UserData> response) {

                        String s = response.body().toString();
                        Toast.makeText(Signup.this, s, Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<UserData> call, Throwable t) {
                        Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.signupBtn:
                usersignup();
                break;
            case R.id.signup_loginText:
                break;
        }
    }
}
