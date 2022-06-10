package nl.hhs.apep2122group1;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.time.LocalDateTime;
import java.util.List;

import nl.hhs.apep2122group1.models.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
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
                Task task = tasks.get(holder.getAdapterPosition());
                boolean completed = task.getCompleted() != null;
                if (isChecked) {
                    if (!completed) {
                        task.markTaskDone();
                    }
                } else {
                    task.markTaskToDo();
                }
            }
        });

        holder.taskTitleTv.setText(task.getTitle());

        holder.taskStatusTv.setText(completed ? R.string.task_adapter_completed_tv_text : R.string.task_adapter_due_tv_text);

        // check formatting of date:
        String taskDate = completed ? String.valueOf(task.getCompleted()) : String.valueOf(task.getDeadline());
        holder.taskDateTv.setText(taskDate);

        if (task.getDeadline().compareTo(LocalDateTime.now()) < 0 && !completed) {
            holder.taskDateTv.setTextColor(Color.rgb(255,184,28));
        } else {
            holder.taskDateTv.setTextColor(ContextCompat.getColor(context, com.google.android.material.R.color.m3_dark_default_color_primary_text));
            // how to get the right dynamic colour? Some dynamic colours crash
            // force dark mode?
        }

        if (task.getLabel() != null) {
            int color = Color.parseColor(task.getLabel().getColorCode());
            holder.taskLabelCp.setText(task.getLabel().getTitle());
            holder.taskLabelCp.setChipBackgroundColor(ColorStateList.valueOf(color));
            holder.taskLabelCp.setVisibility(View.VISIBLE);
        } else {
            holder.taskLabelCp.setVisibility(View.INVISIBLE);
        }

        holder.taskRowCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewActivity.class);
                int taskId = task.getId();
                intent.putExtra("TASK_ID", taskId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox taskStatusCb;
        private TextView taskTitleTv;
        private TextView taskStatusTv;
        private TextView taskDateTv;
        private CardView taskRowCv;
        private Chip taskLabelCp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskStatusCb = itemView.findViewById(R.id.row_status_cb_id);
            taskTitleTv = itemView.findViewById(R.id.row_title_tv_id);
            taskStatusTv = itemView.findViewById(R.id.row_status_tv_id);
            taskDateTv = itemView.findViewById(R.id.row_date_tv_id);
            taskRowCv = itemView.findViewById(R.id.row_card_cv_id);
            taskLabelCp = itemView.findViewById(R.id.row_label_cp_id);
        }
    }
}

