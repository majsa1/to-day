package nl.hhs.apep2122group1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import nl.hhs.apep2122group1.database.FileDatabase;
import nl.hhs.apep2122group1.utils.Converter;
import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;
import nl.hhs.apep2122group1.utils.Validators;


public class AddEditActivity extends AppCompatActivity {

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

        if (taskId != -1) {
            TextView editHeader = findViewById(R.id.add_edit_title_pt);
            TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
            TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
            Spinner labelName = findViewById(R.id.add_edit_label_sp_text);
            TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

            editHeader.setText(R.string.edit_edit_title_pt);
            title.setText(task.getTitle());
            deadline.setText(task.getDeadline() == null ? getResources().getString(R.string.no_deadline_text) : Converter.timeStampToString(task.getDeadline()));
            description.setText(task.getDescription());
        }
    }

    private void setUsernameFromIntent() {
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
    }

    private void setTaskFromIntent(){ // ToDo: check if method can be split / renamed
        FileDatabase db = FileDatabase.getDatabase(this);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("TASK_ID", -1);

        if (taskId != -1) {
            task = db.getTask(taskId);
            username = task.getUserUsername();
        }else{
            setUsernameFromIntent();
        }

        Label noLabel = new Label("<" + getApplicationContext().getResources().
                getString(R.string.no_label_text) + ">", "", ""); ;
        Label[] tempList = db.getAllLabels(username);
        labels = new Label[tempList.length + 1];
        for (int i = 0; i < labels.length -1; i++) {
            labels[i] = tempList[i];
        }
        labels[labels.length -1] = noLabel;

        Spinner choice = (Spinner) findViewById(R.id.add_edit_label_sp_text);
        ArrayAdapter<Label> dataAdapter = new ArrayAdapter<Label>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choice.setAdapter(dataAdapter);
        if (taskId != -1) {

            if (task.getLabelId() != null) {
                for (int i = 0; i < labels.length; i++) {
                    Label l = labels[i];
                    if (task.getLabelId().equals(l.getId())) {
                        label = l;
                        choice.setSelection(i);
                    }
                }
            }
            else {
                choice.setSelection(labels.length - 1);
            }
        } else {
            choice.setSelection(labels.length - 1);
        }
    }

    public void upsertTask(View view) {
        if (taskId == -1) {
            makeTask();
        } else {
            updateTask();
        }
    }

    private void updateTask() {
        // TODO: dubble finish??
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();

        // TODO: use same functionality as in login/register, add more validation (see Validators)
        if (Validators.validateStringNotNullOrEmpty(titleString)) {
            Label selectedLabel = (Label) label.getSelectedItem();

            task.setTitle(titleString);
            task.setDescription(descriptionString);
            task.setLabelId(selectedLabel.getId());

            FileDatabase.getDatabase(this).upsertTask(task);
            Toast.makeText(this, R.string.add_edit_save_btn_id, Toast.LENGTH_SHORT)
                    .show();
            finish();
        } else {
            Toast.makeText(this, (R.string.toast_error_text), Toast.LENGTH_SHORT).show(); // TODO: use resource
        }
    }

    private void makeTask() {

        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();
        String deadlineString = deadline.getText().toString();

        // TODO: does description need to be required?
        // TODO: use same functionality as in login/register
        if (Validators.validateStringNotNullOrEmpty(titleString) && Validators.validateStringNotNullOrEmpty(descriptionString)) {
            Label selectedLabel = (Label) label.getSelectedItem();
            Task task = new Task(titleString, null, descriptionString, username, selectedLabel.getId());
            FileDatabase.getDatabase(this).upsertTask(task);
            Toast.makeText(this, R.string.add_edit_save_btn_id, Toast.LENGTH_SHORT)
                    .show();
            finish();
        } else {
            Toast.makeText(this, (R.string.toast_error_text), Toast.LENGTH_SHORT).show(); // TODO: use resource
        }
    }

    public void onBackBtnPressed(View view) {
        finish();
        Toast.makeText(this, R.string.add_edit_back_btn, Toast.LENGTH_SHORT)
                .show();
    }

    public void onClear(View view){
        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text);
        TextInputEditText deadline = findViewById(R.id.add_edit_deadline_dt);
        Spinner label = findViewById(R.id.add_edit_label_sp_text);
        TextInputEditText description = findViewById(R.id.add_edit_description_etn_et);

        title.getText().clear();
        deadline.getText().clear();
        label.setSelection(labels.length - 1);
        description.getText().clear();

        Toast.makeText(this, R.string.add_edit_clear_btn_id, Toast.LENGTH_SHORT)
                .show();
    }
}