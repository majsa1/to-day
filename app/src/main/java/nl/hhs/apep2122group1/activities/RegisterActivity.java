package nl.hhs.apep2122group1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.utils.Validators;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void onNavigateToLogin(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // set focus to name field for convenience
        TextInputEditText nameField = findViewById(R.id.name_et);
        nameField.requestFocus();
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
        if (!Validators.validateStringNotNullOrEmpty(name)) {
            nameField.setError("Name cannot be empty");
            error = true;
        }
        if (!Validators.validateStringNotNullOrEmpty(username)) {
            usernameField.setError("Username cannot be empty");
            error = true;
        }
        else if (!Validators.validateStringDoesNotContainWhitespace(username)) {
            usernameField.setError("Username cannot contain whitespace");
            error = true;
        }
        if (!Validators.validateStringNotNullOrEmpty(password)) {
            passwordField.setError("Password cannot be empty");
            error = true;
        }
        else if (!Validators.validatePasswordComplexity(password)) {
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
        if (!DatabaseFactory.getDatabase().insertUser(username, password, name.trim())) {
            usernameField.setError("Username already in use!");
        } else {
            Toast.makeText(this, "Added account, you can now log in!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}