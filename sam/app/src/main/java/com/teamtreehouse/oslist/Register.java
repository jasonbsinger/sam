package com.teamtreehouse.oslist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;
    Button bRegister;
    TextView tvLoginLink;
    UserLocalStorage userLocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);

        userLocalStorage = new UserLocalStorage(this);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                System.out.println("Register Button Clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(username, password);

                registerUser(user);

                break;
            case R.id.tvLoginLink:
                startActivity(new Intent(Register.this, Login.class));
                break;
        }
    }

    private void registerUser(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
            }


        });

        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMesssage();
                } else {
                    userLocalStorage.storeUserData(returnedUser);
                    userLocalStorage.setLoggedInStatus(true);
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
            }
        });

    }

    private void showErrorMesssage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
        dialogBuilder.setMessage("That username already exists.");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

}
