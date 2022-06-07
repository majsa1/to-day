package nl.hhs.apep2122group1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        tasks = Task.getDemo();
                        sortedTasks = new ArrayList<>(tasks);
                        Collections.sort(sortedTasks);
                        adapter = new TaskAdapter(sortedTasks);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        // which one is checked (toDo/Done) on return?
                    }
                });
        tasks = Task.getDemo();
        sortedTasks = new ArrayList<>(tasks);
        recyclerView = findViewById(R.id.task_list_rv_id);
        Collections.sort(sortedTasks);
        adapter = new TaskAdapter(sortedTasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MaterialButton toDo = findViewById(R.id.task_list_todo_mb_id); // is there a better way?
        toDo.performClick();
    }

    public void getToDoTasks(View view) {
        sortedTasks.clear();
        for (Task task : tasks) {
            if (task.getCompleted() == null) {
                sortedTasks.add(task);
                Collections.sort(sortedTasks);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void getDoneTasks(View view) {
        sortedTasks.clear();
        for (Task task : tasks) {
            if (task.getCompleted() != null) {
                sortedTasks.add(task);
                Collections.sort(sortedTasks, Collections.reverseOrder());
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void refresh(View view) { // is there a better way? Now bound to checkbox!
        MaterialButton toDo = findViewById(R.id.task_list_todo_mb_id);
        MaterialButton done = findViewById(R.id.task_list_done_mb_id);
        if (toDo.isChecked()) {
            toDo.performClick();
        } else {
            done.performClick();
        }
    }

    public void getDetail(View view) {
        System.out.println("Proceed to detail view");
    }
}