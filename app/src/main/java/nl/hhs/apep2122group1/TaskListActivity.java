package nl.hhs.apep2122group1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskListActivity extends AppCompatActivity {
    private TaskAdapter adapter;
    private List<Task> tasks;
    private List<Task> sortedTasks;
    private List<Task> filteredTasks; // to be used for filtering by labels

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        setTaskList();
    }

//    private methods:

    private void setTaskList() {
        setTasks();
        sortTasksByStatus(true); // tasks to do as default on start - how about when returning from (done) task detail?
        setList();
    }

    private void setTasks() {
        tasks = Task.getDemo();
        sortedTasks = new ArrayList<>();
    }

    private void sortTasksByStatus(boolean toDo) {
        sortedTasks.clear();
        for (Task task : tasks) {
            if (toDo && task.getCompleted() == null) {
                sortedTasks.add(task);
            }
            if (!toDo && task.getCompleted() != null) {
                sortedTasks.add(task);
            }
        }
        if (toDo) {
            Collections.sort(sortedTasks);
        } else {
            sortedTasks.sort(Collections.reverseOrder());
        }
    }

    private void setList() {
        RecyclerView recyclerView = findViewById(R.id.task_list_rv_id);
        adapter = new TaskAdapter(this, sortedTasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

//    public methods for views:

    public void onSortByDeadline(View view) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);

        popupMenu.getMenuInflater().inflate(R.menu.popup_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.popup_sort_ascending) {
                Collections.sort(sortedTasks);
                setList();
                return true;
            }
            if (menuItem.getItemId() == R.id.popup_sort_descending) {
                sortedTasks.sort(Collections.reverseOrder());
                setList();
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public void onToDo(View view) {
        sortTasksByStatus(true);
        adapter.notifyDataSetChanged();
    }

    public void onDone(View view) {
        sortTasksByStatus(false);
        adapter.notifyDataSetChanged();
    }

    public void onCheckChanged(View view) {
        MaterialButton toDoBtn = findViewById(R.id.task_list_todo_mb_id);
        if (toDoBtn.isChecked()) {
            onToDo(view);
        } else {
            onDone(view);
        }
    }

    public void onAddTask(View view) {
        startActivity(new Intent(this, AddEditActivity.class));
    }

    public void onLogout(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.task_list_logout_btn_title)
                .setCancelable(false) // cannot be cancelled by pressing outside of dialog
                .setPositiveButton(R.string.task_list_logout_btn_confirm, (dialog, id) -> finish())
                .setNegativeButton(R.string.task_list_logout_btn_cancel, (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}