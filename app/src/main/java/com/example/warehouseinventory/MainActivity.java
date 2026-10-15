package com.example.warehouseinventory;

import android.content.Intent; // Added this import
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    handleLogin(email, password);
                }
            }
        });
    }

    private void handleLogin(String email, String password) {
        // Hardcoded credentials for your warehouse admin
        if (email.equals("admin@mail.com") && password.equals("1234")) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

            // --- NEW CODE STARTS HERE ---
            // Create an Intent to start the DashboardActivity
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);

            // finish() prevents the user from going back to the login screen when pressing 'Back'
            finish();
            // --- NEW CODE ENDS HERE ---

        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}