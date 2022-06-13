package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.models.User;


public class AddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> labelvoorbeeld = new ArrayList<>(Arrays.asList("select label", "pets", "grocery's", "school", "car", "<geen label> "));
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        getUser();

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

    private void getUser() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        user = DatabaseFactory.getDatabase().getUser(username);
        System.out.println(user.getName());
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

        Task task = new Task(titleString, LocalDateTime.now(), descriptionString, user.getUsername(), null);
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




}