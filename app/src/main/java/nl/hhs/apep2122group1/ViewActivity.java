package nl.hhs.apep2122group1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import java.time.LocalDateTime;


import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public class ViewActivity extends AppCompatActivity {

    private User user;
    private int taskId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent = getIntent();
        int taskId = intent.getIntExtra("TASK_ID", 0);
        //taskId= DatabaseFactory.getDatabase().getTask(taskId, );
        Task task = DatabaseFactory.getDatabase().getTask(taskId);


        TextView title = findViewById(R.id.view_name_task_tv);
        TextView deadline = findViewById(R.id.view_deadline_tv);
        TextView label = findViewById(R.id.view_label_tv);
        TextView description = findViewById(R.id.view_description_tv);

        title.setText( task.getTitle());
        label.setText(task.getLabelId());
        description.setText(task.getDescription());
        //deadline.setText(task.getDeadline());




    }
    public void goTotaskListActivity(View view){
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    private void onlyView() {
    }
    public void onEditTask(View view) {
        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        this.startActivity(intent);
        startActivity(new Intent(this, AddEditActivity.class));
    }

}