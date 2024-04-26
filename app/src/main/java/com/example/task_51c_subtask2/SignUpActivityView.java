package com.example.task_51c_subtask2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_view);

//        full name input
        EditText fullNameEditText = findViewById(R.id.signupFullNameTextEdit);

//        username input
        EditText usernameEditText = findViewById(R.id.signupUsernameTextEdit);

//        password input
        EditText passwordEditText = findViewById(R.id.signupPasswordEditText);

//        confirm password input
        EditText confirmPasswordEditText = findViewById(R.id.signupConfirmPasswordTextEdit);

//        create account button
        Button createAccountButton = findViewById(R.id.signupCreateAccountButton);

//        create user account
        createAccountButton.setOnClickListener(v -> {
//            check user entered full name, username, password and confirm password
            if (fullNameEditText.getText().toString().isEmpty() || usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty() || confirmPasswordEditText.getText().toString().isEmpty()) {
                fullNameEditText.setError("Please enter your full name");
                usernameEditText.setError("Please enter a username");
                passwordEditText.setError("Please enter a password");
                confirmPasswordEditText.setError("Please confirm your password");
            } else {
//                check password and confirm password match
                if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
                    passwordEditText.setError("Passwords do not match");
                    confirmPasswordEditText.setError("Passwords do not match");
                    return;
                }
//                check user doesn't already exist
                AuthManager auth = new AuthManager(this);
                if (auth.userExists(usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
                    usernameEditText.setError("Username already exists");
                    return;
                } else {
//                    create account
                    auth.insertUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());

//                    set user as authenticated user, navigate to home activity
                    int newUserId = auth.getUserId(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                    auth.setAuthedUser(newUserId);

                    Intent homeIntent = new Intent(SignUpActivityView.this, HomeActivityView.class);
                    startActivity(homeIntent);
                }
            }
        });

    }
}