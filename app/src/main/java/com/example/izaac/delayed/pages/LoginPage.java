package com.example.izaac.delayed.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.izaac.delayed.R;
import com.example.izaac.delayed.interfaces.DelayApi;
import com.example.izaac.delayed.models.AuthTokens;
import com.example.izaac.delayed.models.CreateUser;
import com.example.izaac.delayed.models.DelayListData;
import com.example.izaac.delayed.models.Login;
import com.example.izaac.delayed.models.NotificationToken;
import com.example.izaac.delayed.models.TokenResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.PendingIntent.getActivity;

public class LoginPage extends AppCompatActivity {

    private EditText UserEmail;
    private EditText UserPassword;
    private String LoginEmail;
    private String LoginPassword;
    private String LoginName;
    public static Boolean DelayTotal;
    private Boolean annon;
    private static final String DEFAULT = "N/A";
    private String AUTH_TOKEN;
    //private String NOTIFICATION_TOKEN;
    private Button LoginLaterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.println("Hello");
        System.out.println("LoginPage.onCreate: " + FirebaseInstanceId.getInstance().getToken());

        SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
        AUTH_TOKEN = sharedPreferences.getString("AUTH_TOKEN", DEFAULT);

        if(AUTH_TOKEN.equalsIgnoreCase(DEFAULT)) {

            UserEmail = (EditText) findViewById(R.id.Email);
            UserPassword = (EditText) findViewById(R.id.Password);

            UserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            Button LoginButton = (Button) findViewById(R.id.Login);
            Button NewAccount = (Button) findViewById(R.id.NewAccount);
            LoginLaterButton = (Button) findViewById(R.id.LoginLaterButton);

            NewAccount.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginPage.this, createAccount.class);
                    startActivity(intent);

                }
            });

            LoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // PostNotificationToken();
                    login();
                }
            });

            LoginLaterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // annon = true;
                   // PostNotificationToken();
                    annonUser();
                }
            });
        }
        else {
            Intent intent = new Intent(LoginPage.this, homePage.class);
            startActivity(intent);
        }

    }

    public static String token;

    private void login() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        LoginEmail  =  UserEmail.getText().toString().trim();
        LoginPassword = UserPassword.getText().toString().trim();

        Login login = new Login(LoginEmail, LoginPassword);

        Call<TokenResponse> call = delayApi.login(login);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(LoginPage.this, "Login Correct", Toast.LENGTH_SHORT).show();
                    token = response.body().getResult().getAuthToken();
                    DelayTotal = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("AUTH_TOKEN", token);
                    editor.commit();


                   // AuthTokens authTokens = new AuthTokens(response.body().getResult().getAuthToken());

                    Intent intent = new Intent(LoginPage.this, homePage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginPage.this, "Login not correct", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginPage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void annonUser() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://dev.delayed.nz")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        DelayApi delayApi = retrofit.create(DelayApi.class);

        LoginName = "";
        LoginEmail  =  "";
        LoginPassword = "";

        //Login login = new Login(LoginEmail, LoginPassword);

        //Call<TokenResponse> call = delayApi.login(login);

        CreateUser createUser = new CreateUser(LoginName, LoginEmail, LoginPassword);
        Call<TokenResponse> call = delayApi.createUser(createUser);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                System.out.println("Hello");
                if (response.isSuccessful()) {
                    Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    token = response.body().getResult().getAuthToken();
                    DelayTotal = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("Auth Tokens", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("AUTH_TOKEN", token);
                    editor.commit();

                    Intent intent = new Intent(LoginPage.this, homePage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginPage.this, "Login not correct", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(LoginPage.this, "Error......", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
