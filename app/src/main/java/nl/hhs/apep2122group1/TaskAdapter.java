package nl.hhs.apep2122group1;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> tasks;

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.task_list_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        boolean completed = task.getCompleted() != null;

        holder.taskStatusCb.setChecked(completed);
        holder.taskStatusCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) { // check if correct
                    if (tasks.get(holder.getAdapterPosition()).getCompleted() == null) {
                        tasks.get(holder.getAdapterPosition()).markTaskDone();
                    }
                } else {
                    tasks.get(holder.getAdapterPosition()).markTaskToDo();
                }
            }
        });

        holder.taskTitleTv.setText(task.getTitle());

        holder.taskStatusTv.setText(completed ? "Completed: " : "Due: ");

        // check formatting of date:
        String taskDate = completed ? String.valueOf(task.getCompleted()) : String.valueOf(task.getDeadline());
        holder.taskDateTv.setText(taskDate);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // add textviews for Due / Done

        private CheckBox taskStatusCb;
        private TextView taskTitleTv;
        private TextView taskStatusTv;
        private TextView taskDateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskStatusCb = itemView.findViewById(R.id.row_status_cb_id);
            taskTitleTv = itemView.findViewById(R.id.row_title_tv_id);
            taskStatusTv = itemView.findViewById(R.id.row_status_tv_id);
            taskDateTv = itemView.findViewById(R.id.row_date_tv_id);
        }
    }
}

