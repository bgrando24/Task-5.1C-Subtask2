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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        username input
        EditText usernameEditText = findViewById(R.id.usernameEditText);

//        password input
        EditText passwordEditText = findViewById(R.id.passwordEditText);

//        login button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
//            check user entered username and password
            if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                usernameEditText.setError("Please enter a username");
                passwordEditText.setError("Please enter a password");
            } else {
//                check user authentication
                AuthManager auth = new AuthManager(this);
                if (!auth.userExists(usernameEditText.getText().toString(), passwordEditText.getText().toString())) {
                    usernameEditText.setError("Invalid username or password");
                    passwordEditText.setError("Invalid username or password");
                    return;
                }

//                navigate to home activity
                Intent homeIntent = new Intent(MainActivity.this, HomeActivityView.class);
                startActivity(homeIntent);
            }
        });

//        sign up button
        Button signUpButton = findViewById(R.id.signUpbutton);
        signUpButton.setOnClickListener(v -> {
//            navigate to sign up activity
            Intent signUpIntent = new Intent(MainActivity.this, SignUpActivityView.class);
            startActivity(signUpIntent);
        });
    }
}