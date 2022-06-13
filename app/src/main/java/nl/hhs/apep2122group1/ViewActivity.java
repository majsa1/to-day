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
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;

public class ViewActivity extends AppCompatActivity {

    private User user;
    private int taskId;
    private Label label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent = getIntent();
        int taskId = intent.getIntExtra("TASK_ID", 0);
        //taskId= DatabaseFactory.getDatabase().getTask(taskId, );
        Task task = DatabaseFactory.getDatabase().getTask(taskId);
        if (task.getLabelId() != null) {
            label = DatabaseFactory.getDatabase().getLabel(task.getLabelId());
        }

        TextView title = findViewById(R.id.view_name_task_tv);
        TextView deadline = findViewById(R.id.view_deadline_tv);
        TextView labelName = findViewById(R.id.view_label_tv);
        TextView description = findViewById(R.id.view_description_tv);
        TextView completed = findViewById(R.id.view_completed_tv);

        title.setText( task.getTitle());  // nog string maken label??
        labelName.setText(label == null ? "No label" : String.valueOf(label.getTitle()));
       // labelName.setText(label.getTitle());
        description.setText(task.getDescription());
        deadline.setText(String.valueOf(task.getDeadline()));

        completed.setText(task.getCompleted() == null ? "In Progress" : String.valueOf(task.getCompleted()));
        //completed.setText(String.valueOf(task.getCompleted()));



    }
    public void goTotaskListActivity(View view){
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }
    @Override
    public void onStart() {
        super.onStart();
    }


//    public void onClick(View view) {
//        Intent intent = new Intent(this, AddEditActivity.class);
//        int taskId = intent.getIntExtra("TASK_ID", 0);
//        intent.putExtra("TASK_ID", taskId);
//        this.startActivity(intent);
//    }
//    public void onEditTask(View view) {
//        Intent intent = new Intent(this, AddEditActivity.class);
//        int taskId = intent.getIntExtra("TASK_ID", 0);
//        //taskId= DatabaseFactory.getDatabase().getTask(taskId, );
//        Task task = DatabaseFactory.getDatabase().getTask(taskId);
//        if (task.getLabelId() != null) {
//            label = DatabaseFactory.getDatabase().getLabel(task.getLabelId());

       // intent.putExtra("TASK_ID", taskId.getTask(taskId));
        //this.startActivity(intent);
   //     startActivity(new Intent(this, AddEditActivity.class));
//    }}
    public void onAddEditTask(View view) {
        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        this.startActivity(intent);
//        startActivity(new Intent(this, AddEditActivity.class));
    }
    public void onBackBtnPressed(View view){
        finish();
    }

}