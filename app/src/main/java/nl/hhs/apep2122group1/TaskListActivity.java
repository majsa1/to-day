package nl.hhs.apep2122group1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskListActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private AlertDialog.Builder builder;
    private List<Task> tasks;
    private List<Task> sortedTasks;
    private List<Task> filteredTasks; // to be used for filtering by labels

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        prepareTaskList();
                        adapter.notifyDataSetChanged();
                        // which materialbutton is checked on return?
                    }
                });

        prepareTaskList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // there must be a better way...
        MaterialButton toDoBtn = findViewById(R.id.task_list_todo_mb_id);
        toDoBtn.performClick();

        prepareSort();
        prepareLogout();
    }

    private void prepareTaskList() {
        prepareTasks();
        prepareList();
    }

    private void prepareTasks() {
        tasks = Task.getDemo();
        sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks);
    }

    public void prepareList() {
        recyclerView = findViewById(R.id.task_list_rv_id);
        adapter = new TaskAdapter(sortedTasks);
        recyclerView.setAdapter(adapter);
    }

    private void prepareLogout() {
        builder = new AlertDialog.Builder(this);
        ImageButton logout = findViewById(R.id.task_list_logout_btn_id);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle(R.string.task_list_logout_btn_title)
                        .setCancelable(false) // cannot be cancelled by pressing outside of dialog
                        .setPositiveButton(R.string.task_list_logout_btn_confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.task_list_logout_btn_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void prepareSort() {
        ImageButton sortBtn = (ImageButton) findViewById(R.id.task_list_sort_btn_id);

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), sortBtn);

                popupMenu.getMenuInflater().inflate(R.menu.popup_sort, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.popup_sort_ascending) {
                            Collections.sort(sortedTasks);
                            prepareList();
                            return true;
                        }
                        if (menuItem.getItemId() == R.id.popup_sort_descending) {
                            Collections.sort(sortedTasks, Collections.reverseOrder());
                            prepareList();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
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
        MaterialButton toDoBtn = findViewById(R.id.task_list_todo_mb_id);
        MaterialButton doneBtn = findViewById(R.id.task_list_done_mb_id);
        if (toDoBtn.isChecked()) {
            toDoBtn.performClick();
        } else {
            doneBtn.performClick();
        }
    }

    public void onAdd(View view) {
        System.out.println("Adding item");
        startActivity(new Intent(this, AddEditActivity.class));
    }

    public void getDetail(View view) { // will be replaced
        System.out.println("To detail view");
    }
}