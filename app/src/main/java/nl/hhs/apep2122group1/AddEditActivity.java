package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
<<<<<<< Updated upstream

public class AddEditActivity extends AppCompatActivity {
=======
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;


public class AddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String task;
    ArrayList<String> labelvoorbeeld = new ArrayList<>(Arrays.asList("select label", "pets", "grocery's", "school", "car", "<geen label> "));
    String username;
    int taskId;
    Label label;
    User user;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
<<<<<<< Updated upstream
    }
=======

        setUsernameFromIntent();

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner choise = (Spinner) findViewById(R.id.add_edit_label_sp_text);
        //testchoise.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, labelvoorbeeld);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        choise.setAdapter(aa);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), labelvoorbeeld.get(position), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "<no label>", Toast.LENGTH_LONG).show();
    }

    private void setUsernameFromIntent() {
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
    }

    public void upsertTask(View view) {
        makeTask();
        finish();
    }

    private void makeTask() {

        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        String titleString = title.getText().toString();
        String labelString = label.getSelectedItem().toString();
        String descriptionString = description.getText().toString();
        String deadlineString = deadline.getText().toString();

        Task task = new Task(titleString, LocalDateTime.now(), descriptionString, username, null);
        DatabaseFactory.getDatabase().upsertTask(task);
        System.out.println("task to database");
    }
    public void finish(View view) {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public void  setTaskfromIntent(){
        Intent intent = getIntent();
        task = intent.getStringExtra("Task_Id");
    }
    public void editTask(){
        setTaskfromIntent();
        Intent intent = getIntent();
        taskId = intent.getIntExtra("TASK_ID", 0);
        //taskId= DatabaseFactory.getDatabase().getTask(taskId, );
        Task task = DatabaseFactory.getDatabase().getTask(taskId);
        if (task.getLabelId() != null) {
            label = DatabaseFactory.getDatabase().getLabel(task.getLabelId());
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        title.setText( task.getTitle());
        //label.setText(label == null ? "No label" : String.valueOf(label.getSelectedItem()));
        //label.setText(label.getTitle());
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, labelvoorbeeld);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
        label.setAdapter(aa);

        description.setText(task.getDescription());
        deadline.setText(String.valueOf(task.getDeadline()));

      //  completed.setText(task.getCompleted() == null ? "In Progress" : String.valueOf(task.getCompleted()));
        //completed.setText(String.valueOf(task.getCompleted()));

     //  Task task = new Task(titleString, LocalDateTime.now(), descriptionString, username, null);
     //   DatabaseFactory.getDatabase().upsertTask(taskid);

    }


    }




>>>>>>> Stashed changes
}