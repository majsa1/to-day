package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nl.hhs.apep2122group1.database.DatabaseFactory;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onNavigateToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    // TODO: validations should be translatable
    public void onRegister(View view) {
        // get fields
        TextInputEditText nameField = findViewById(R.id.name_et);
        TextInputEditText usernameField = findViewById(R.id.username_et);
        TextInputEditText passwordField = findViewById(R.id.password_et);
        TextInputEditText retypePasswordField = findViewById(R.id.retype_password_et);

        // get values from fields
        String name = nameField.getText() != null ? nameField.getText().toString() : null;
        String username = usernameField.getText() != null ? usernameField.getText().toString() : null;
        String password = passwordField.getText() != null ? passwordField.getText().toString() : null;
        String retypePassword = retypePasswordField.getText() != null ? retypePasswordField.getText().toString() : null;

        // validate values
        boolean error = false;
        if (name == null || name.isEmpty()) {
            nameField.setError("Name cannot be empty");
            error = true;
        }
        if (username == null || username.isEmpty()) {
            usernameField.setError("Username cannot be empty");
            error = true;
        }
        if (password == null || password.isEmpty()) {
            passwordField.setError("Password cannot be empty");
            error = true;
        }
        else if (password.length() < 6) {
            passwordField.setError("Password should have minimum length of 6");
            error = true;
        }
        else if (!password.equals(retypePassword)) {
            passwordField.setError("Passwords not equal");
            retypePasswordField.setError("Passwords not equal");
            error = true;
        }
        if (error) {
            return;
        }

        // values ok, save to DB
        if (!DatabaseFactory.getDatabase().insertUser(username, password, name)) {
            usernameField.setError("Username already in use!");
        } else {
            Toast.makeText(this, "Added account, you can now log in!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}