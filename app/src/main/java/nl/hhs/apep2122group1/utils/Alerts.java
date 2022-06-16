package nl.hhs.apep2122group1.utils;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.database.FileDatabase;
import nl.hhs.apep2122group1.models.Task;

public class Alerts {

    public static void logoutAlert(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.task_list_logout_btn_title_text)
                .setCancelable(false)
                .setPositiveButton(R.string.alert_confirm_text, (dialog, id) -> ((Activity) context).finish())
                .setNegativeButton(R.string.alert_cancel_text, (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void deleteAlert(Context context, Task task, Runnable onPositiveCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.task_delete_title_text);
        builder.setMessage(R.string.task_delete_message_text)
                .setCancelable(false)
                .setPositiveButton(R.string.alert_confirm_text, (dialog, id) -> {
                    FileDatabase.getDatabase(context).deleteTask(task);
                    onPositiveCallback.run();
                })
                .setNegativeButton(R.string.alert_cancel_text, (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
