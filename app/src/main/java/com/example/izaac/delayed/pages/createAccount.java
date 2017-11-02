package com.example.izaac.delayed.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.CreateUser;
import com.example.izaac.delayed.models.TokenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.izaac.delayed.pages.LoginPage.token;

public class createAccount extends AppCompatActivity {

    private ProgressBar progressBar;
    private DelayApi service;
    private EditText NameText;
    private EditText EmailText;
    private EditText PasswordText;
    private EditText RePasswordText;
    private Button BackButton;
    private String Name;
    private String Email;
    private String Password;
    private String RePassword;
    private Boolean PasswordMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        System.out.println("stop here");

        NameText = (EditText) findViewById(R.id.Name);
        EmailText = (EditText) findViewById(R.id.Email);
        PasswordText = (EditText) findViewById(R.id.Password);
        RePasswordText = (EditText) findViewById(R.id.RetypePassword);

        Button CreateAccount = (Button) findViewById(R.id.CreateAccountButton);
        BackButton = (Button) findViewById(R.id.BackButton);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerifyUserCredentials();
                if(PasswordMatch == true) {
                    RegisterUser();
                }
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createAccount.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /*this methood is used to register a user*/

    private void RegisterUser() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        Name = NameText.getText().toString().trim();
        Email = EmailText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();

        CreateUser createUser = new CreateUser(Name, Email, Password);
        Call<TokenResponse> call = delayApi.createUser(createUser);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(createAccount.this, "Account Created", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("AUTH_TOKEN", token);
                    editor.commit();

                    Intent intent = new Intent(createAccount.this, homePage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(createAccount.this, "Unable To Create Account", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(createAccount.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

    /*This method checks the users credentials and then returns a boolean value based off if the retyped password correct. */

    private void VerifyUserCredentials() {

        Name = NameText.getText().toString().trim();
        Email = EmailText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();
        RePassword = RePasswordText.getText().toString().trim();
        PasswordMatch = false;

        if(Name.isEmpty() && Email.isEmpty() && Password.isEmpty() && RePassword.isEmpty()) {
            Toast.makeText(createAccount.this, "All Fields Are Empty", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }
        else if(Name.isEmpty()) {
            Toast.makeText(createAccount.this, "Name is Empty", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }
        else if(Email.isEmpty()) {
            Toast.makeText(createAccount.this, "Email is Empty", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }
        else if(Password.isEmpty()) {
            Toast.makeText(createAccount.this, "Password is Empty", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }
        else if(RePassword.isEmpty()) {
            Toast.makeText(createAccount.this, "ReType Password is Empty", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }
        else if(Password.equalsIgnoreCase(RePassword)) {
            PasswordMatch = true;
        }
        else if(PasswordMatch == false) {
            Toast.makeText(createAccount.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            finish();
            startActivity(getIntent());
        }


    }

}
