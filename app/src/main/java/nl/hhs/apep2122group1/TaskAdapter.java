package nl.hhs.apep2122group1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.taskTitleTv.setText(task.getTitle());

        // check in comparison to status + parsing of date:
        String taskDate = task.getCompleted() == null ? String.valueOf(task.getDeadline()) : String.valueOf(task.getCompleted());

        holder.taskDateTv.setText(taskDate);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView taskTitleTv;
        private TextView taskDateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitleTv = itemView.findViewById(R.id.row_title_tv_id);
            taskDateTv = itemView.findViewById(R.id.row_date_tv_id);
        }
    }
}

