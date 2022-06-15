package nl.hhs.apep2122group1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

import nl.hhs.apep2122group1.utils.Converter;
import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;


public class AddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Label[] labels;
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






        if (taskId != -1) {
            TextView editHeader = findViewById(R.id.add_edit_title_pt);
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner labelname = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        editHeader.setText(R.string.edit_edit_title_pt);
        title.setText(task.getTitle());
        deadline.setText(task.getDeadline() == null ? getResources().getString(R.string.no_deadline_text) : Converter.timeStampToString(task.getDeadline()));
        description.setText(task.getDescription());
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), labels[position].getTitle(), Toast.LENGTH_LONG).show();
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
            username = task.getOwnerUsername();
        }else{
            setUsernameFromIntent();
        }
        labels = DatabaseFactory.getDatabase().getAllLabels(username);
        Spinner choise = (Spinner) findViewById(R.id.add_edit_label_sp_text);
        ArrayAdapter<Label> dataAdapter = new ArrayAdapter<Label>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choise.setAdapter(dataAdapter);
        if (taskId != -1) {

            if (task.getLabelId() != null) {
                for (int i = 0; i < labels.length; i++) {
                    Label l = labels[i];
                    if (task.getLabelId().equals(l.getId())) {
                        label = l;
                        choise.setSelection(i);
                    }
                }
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

    public void onclear(View view){
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        title.getText().clear();
        deadline.getText().clear();
        //label.getText().clear();
        description.getText().clear();
    }


}