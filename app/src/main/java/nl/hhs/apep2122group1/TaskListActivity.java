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
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskListActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> tasks;
    private List<Task> sortedTasks;

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
                        tasks = Task.getDemo();
                        sortedTasks = new ArrayList<>(tasks);
                        Collections.sort(sortedTasks);
                        adapter = new TaskAdapter(sortedTasks);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
        tasks = Task.getDemo();
        sortedTasks = new ArrayList<>(tasks);
        recyclerView = findViewById(R.id.task_list_rv_id);
        Collections.sort(sortedTasks);
        adapter = new TaskAdapter(sortedTasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getToDoTasks(View view) {
        sortedTasks.clear();
        for (Task task : tasks) {
            if (task.getCompleted() == null) {
                sortedTasks.add(task);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void getDoneTasks(View view) {
        sortedTasks.clear();
        for (Task task : tasks) {
            if (task.getCompleted() != null) {
                sortedTasks.add(task);
            }
        }
        adapter.notifyDataSetChanged();
    }
}