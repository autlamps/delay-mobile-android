package com.example.izaac.delayed.models;

/**
 * Created by izaac on 25/09/2017.
 */

public class AuthTokens {

    private String Token;

    public AuthTokens (String Token) {
        this.Token = Token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

}
