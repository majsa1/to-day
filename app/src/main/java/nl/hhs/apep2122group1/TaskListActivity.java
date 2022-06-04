package nl.hhs.apep2122group1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskListActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> tasks;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
//                        tasks.clear();
//                        tasks.addAll(Task.getAll(getApplicationContext()));
                        tasks = Task.getDemo();
                        Collections.sort(tasks);
                        adapter = new TaskAdapter(tasks);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
//        tasks = Task.getAll(this);
        tasks = Task.getDemo();
        recyclerView = findViewById(R.id.task_list_rv_id);
        Collections.sort(tasks);
        adapter = new TaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}