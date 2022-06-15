package nl.hhs.apep2122group1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nl.hhs.apep2122group1.utils.Converter;
import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.models.Task;


public class ViewActivity extends AppCompatActivity {
    private int taskId;
    private Label label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        taskId = intent.getIntExtra("TASK_ID", -1);
        Task task = DatabaseFactory.getDatabase().getTask(taskId);

        if (task.getLabelId() != null) {
            label = DatabaseFactory.getDatabase().getLabel(task.getLabelId());
        }

        TextView title = findViewById(R.id.view_name_task_tv);
        TextView deadline = findViewById(R.id.view_deadline_tv);
        TextView completed = findViewById(R.id.view_completed_tv);
        TextView labelName = findViewById(R.id.view_label_tv);
        TextView description = findViewById(R.id.view_description_tv);

        title.setText(task.getTitle());
        deadline.setText(
                task.getDeadline() == null ? getResources().getString(R.string.no_deadline_text) : Converter.timeStampToString(task.getDeadline()));
        completed.setText(
                task.getCompleted() == null ? getResources().getString(R.string.view_in_progress_text) : Converter.timeStampToString(task.getCompleted()));
        labelName.setText(
                label == null ? getResources().getString(R.string.no_label_text) : String.valueOf(label.getTitle()));
        description.setText(task.getDescription());
    }

    @Override // TODO: test - does this work without?
    public void onStart() {
        super.onStart();
    }

    public void onEditBtnPressed(View view) {
        Intent intent = new Intent(this, AddEditActivity.class);
        intent.putExtra("TASK_ID", taskId);
        this.startActivity(intent);
    }

    public void onBackBtnPressed(View view) {
        finish();
    }
}