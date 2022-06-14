package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;


public class AddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // nog string maken label??
    ArrayList<String> labelvoorbeeld = new ArrayList<>(Arrays.asList("select label", "pets", "grocery's", "school", "car", "<geen label> "));
    Task task;
    String username;
    Label label;
    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        setTaskFromIntent();
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner choise = (Spinner) findViewById(R.id.add_edit_label_sp_text);
        //testchoise.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, labelvoorbeeld);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        choise.setAdapter(aa);


        if (taskId != -1) {
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner labelname = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        title.setText(task.getTitle());
        deadline.setText(task.getDeadline() == null ? getResources().getString(R.string.no_deadline_text) : Converter.dateStampToString(this, task.getDeadline()));
        //TODO label
        description.setText(task.getDescription());
        }
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

    private void setTaskFromIntent(){
        Intent intent = getIntent();
        taskId = intent.getIntExtra("TASK_ID", -1);

        if (taskId != -1) {
            task = DatabaseFactory.getDatabase().getTask(taskId);
            if (task.getLabelId() != null) {
                label = DatabaseFactory.getDatabase().getLabel(task.getLabelId());
            }
        }
    }

    public void upsertTask(View view) {
        if (taskId == -1) {
            makeTask();
        } else {
            updateTask();
        }
        finish();
    }

    private void updateTask() {
        // TODO: save

    }

    private void makeTask() {
        setUsernameFromIntent();

        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        String titleString = title.getText().toString();
        String labelString = label.getSelectedItem().toString();
        String descriptionString = description.getText().toString();
        String deadlineString = deadline.getText().toString();

        Task task = new Task(titleString, null, descriptionString, username, null);
        DatabaseFactory.getDatabase().upsertTask(task);
        System.out.println("task to database");
    }

    public void onBackBtnPressed(View view) {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }




}