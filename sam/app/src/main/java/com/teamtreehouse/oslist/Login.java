package com.teamtreehouse.oslist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText etUsername, etPassword;
    Button bLogin;
    TextView tvRegsiterLink;
    UserLocalStorage userLocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegsiterLink = (TextView) findViewById(R.id.tvRegsiterLink);

        bLogin.setOnClickListener(this);
        tvRegsiterLink.setOnClickListener(this);

        userLocalStorage = new UserLocalStorage(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:
                System.out.println("Login Button Clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User user = new User(username, password);
                authenticate(user);
                //logUserIn(user);
                break;
            case R.id.tvRegsiterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMesssage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMesssage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect User details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStorage.storeUserData(returnedUser);
        userLocalStorage.setLoggedInStatus(true);
        startActivity(new Intent(Login.this, MainActivity.class));
    }

}
