// LoginActivity.java
package com.example.myjournallapp2.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import com.example.myjournallapp2.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.equals("admin") && password.equals("admin")) { // Înlocuiți cu logica dvs. de autentificare
            Toast.makeText(this, "Autentificare reușită", Toast.LENGTH_SHORT).show();

            // Lansați MainActivity după autentificare reușită
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Opțional: închideți LoginActivity pentru a împiedica revenirea la acesta cu butonul 'Back'
        } else {
            Toast.makeText(this, "Autentificare eșuată", Toast.LENGTH_SHORT).show();
        }
    }
}
