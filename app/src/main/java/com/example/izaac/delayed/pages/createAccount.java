package com.example.izaac.delayed.pages;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.TokenRequest;
import com.example.izaac.delayed.models.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createAccount extends AppCompatActivity {

    private ProgressBar progressBar;
    private DelayApi service;
    private EditText NameText;
    private EditText EmailText;
    private EditText PasswordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


        NameText = (EditText) findViewById(R.id.Name);
        EmailText = (EditText) findViewById(R.id.Email);
        PasswordText = (EditText) findViewById(R.id.Password);

        Button CreateAccount = (Button) findViewById(R.id.CreateAccountButton);

    }

    private void createUser() {
        String fName = NameText.getText().toString().trim();
        String fEmail = EmailText.getText().toString().trim();
        String fPassword = PasswordText.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DelayApi.class);

        NameText.setError(null);
        EmailText.setError(null);
        PasswordText.setError(null);

        if(TextUtils.isEmpty(fName)) {
            // Toast.makeText(this, "Please Enter your name.", Toast.LENGTH_SHORT).show();
            NameText.setError("Please Enter your name");
            return;

        }
        if(TextUtils.isEmpty(fEmail)) {
            //Toast.makeText(this, "Please Enter Your Email.", Toast.LENGTH_SHORT).show();
            EmailText.setError("Please Enter your Email");
            return;
        }
        if(TextUtils.isEmpty(fPassword)){
            //Toast.makeText(this, "Please Enter Your Password.", Toast.LENGTH_SHORT).show();
            PasswordText.setError("Pleas Enter Your Password");
            return;
        }
        else {

            progressBar.setVisibility(View.VISIBLE);

            TokenRequest tokenRequest = new TokenRequest();

            tokenRequest.setName(NameText.getText().toString());
            tokenRequest.setEmail(EmailText.getText().toString());
            tokenRequest.setPassword(PasswordText.getText().toString());


        }



    }

    public void onClick(View view) {




    }


}
