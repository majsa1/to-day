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

        // validate values
        boolean error = false;
        if (!Validators.ValidateStringNotNullOrEmpty(nameField.getText())) {
            nameField.setError("Name cannot be empty");
            error = true;
        }
        if (!Validators.ValidateStringNotNullOrEmpty(usernameField.getText())) {
            usernameField.setError("Username cannot be empty");
            error = true;
        }
        else if (!Validators.ValidateStringDoesNotContainWhitespace(usernameField.getText())) {
            usernameField.setError("Username cannot contain whitespace");
            error = true;
        }
        if (!Validators.ValidateStringNotNullOrEmpty(passwordField.getText())) {
            passwordField.setError("Password cannot be empty");
            error = true;
        }
        else if (!Validators.ValidateMinimumLength(passwordField.getText(), 6)) {
            passwordField.setError("Password should have minimum length of 6");
            error = true;
        }
        else if (!passwordField.getText().toString().equals(retypePasswordField.getText().toString())) {
            passwordField.setError("Passwords not equal");
            retypePasswordField.setError("Passwords not equal");
            error = true;
        }
        if (error) {
            return;
        }

        // values ok, save to DB
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        String name = nameField.getText().toString().trim();
        if (!DatabaseFactory.getDatabase().insertUser(username, password, name)) {
            usernameField.setError("Username already in use!");
        } else {
            Toast.makeText(this, "Added account, you can now log in!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}