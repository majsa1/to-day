package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onNavigateToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, TaskListActivity.class));
    }

}