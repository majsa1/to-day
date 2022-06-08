package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        int taskId = intent.getIntExtra("TASK_ID", 0);
    }
    public void goTotaskListActivity(View view){
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }
}