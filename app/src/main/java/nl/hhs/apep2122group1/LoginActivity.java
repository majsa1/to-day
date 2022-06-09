package nl.hhs.apep2122group1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onNavigateToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // get fields
        TextInputEditText usernameField = findViewById(R.id.username_et);
        TextInputEditText passwordField = findViewById(R.id.password_et);

        // clear their text
        usernameField.setText("");
        passwordField.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();

        // set focus to username field for convenience
        TextInputEditText usernameField = findViewById(R.id.username_et);
        usernameField.requestFocus();
    }

    public void onLogin(View view) {
        // get fields
        TextInputEditText usernameField = findViewById(R.id.username_et);
        TextInputEditText passwordField = findViewById(R.id.password_et);

        // get values from fields
        String username = usernameField.getText() != null ? usernameField.getText().toString() : null;
        String password = passwordField.getText() != null ? passwordField.getText().toString() : null;

        // validate values
        boolean error = false;
        if (username == null || username.isEmpty()) {
            usernameField.setError("Username cannot be empty");
            error = true;
        }
        if (password == null || password.isEmpty()) {
            passwordField.setError("Password cannot be empty");
            error = true;
        }
        if (error) {
            return;
        }

        // values ok, save to DB
        User user = DatabaseFactory.getDatabase().getUser(username, password);
        if (user == null) {
            usernameField.setError("Incorrect credentials!");
            passwordField.setError("Incorrect credentials!");
        } else {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, TaskListActivity.class));
        }
    }

}