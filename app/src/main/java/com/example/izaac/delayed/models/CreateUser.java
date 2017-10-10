package com.example.izaac.delayed.models;

/**
 * Created by izaac on 10/10/2017.
 */

public class CreateUser {
    private String name;
    private String email;
    private String password;

    public CreateUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
