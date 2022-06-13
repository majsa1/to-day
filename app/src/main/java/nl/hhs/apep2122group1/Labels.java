package nl.hhs.apep2122group1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;

public class Labels extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labels);

        username = getIntent().getStringExtra("USERNAME");
        username = "test"; // TODO: remove
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshLabels();
    }

    private void refreshLabels() {
        ChipGroup chipGroup = findViewById(R.id.labels_cg);

        // clear any previous chips/labels and retrieve current labels
        chipGroup.removeViews(0, chipGroup.getChildCount());
        Label[] labels = DatabaseFactory.getDatabase().getAllLabels(username);

        // create all the chips, add to ChipGroup
        for (Label l : labels) {
            // create chip
            Chip chip = new Chip(this);
            chip.setText(l.getTitle());

            // set color from database
            int color = Color.parseColor(l.getColorCode());
            ColorStateList colorStateList = ColorStateList.valueOf(color);
            chip.setChipBackgroundColor(colorStateList);

            // attach event handler using lambda, so we can detect clicks on the chip
            chip.setOnClickListener(view -> onChipClick(l));

            // add to ChipGroup, this makes it actually visible in the UI
            chipGroup.addView(chip);
        }
    }

    public void onNewChipClick(View view) {

    }

    public void onChipClick(Label label) {
        // create dialog builder and view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View labelEditDialogView = inflater.inflate(R.layout.label_edit_dialog, null);
        builder.setView(labelEditDialogView);
        Dialog dialog = builder.create();

        // set dialog title
        TextView titleLabel = labelEditDialogView.findViewById(R.id.label_edit_dialog_title);
        titleLabel.setText(label.getTitle());

        // set button handlers
        Button deleteBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_delete_label_btn);
        deleteBtn.setOnClickListener(view -> onDeleteButtonClick(label));
        Button cancelBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_cancel_btn);
        cancelBtn.setOnClickListener(view -> dialog.cancel());
        TextInputEditText newLabelEditText = labelEditDialogView.findViewById(R.id.label_edit_dialog_new_title_et);
        Button saveBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_save_btn);
        saveBtn.setOnClickListener(view -> onSaveButtonClick(label, newLabelEditText, dialog));

        // actually show dialog
        dialog.show();
    }

    public void onDeleteButtonClick(Label label) {
        // create dialog builder and view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View labelEditDialogView = inflater.inflate(R.layout.label_delete_dialog, null);
        builder.setView(labelEditDialogView);
        Dialog dialog = builder.create();

        // set dialog title
        TextView titleLabel = labelEditDialogView.findViewById(R.id.label_delete_dialog_title);
        titleLabel.setText(label.getTitle());

        // set button handlers
        Button cancelBtn = labelEditDialogView.findViewById(R.id.label_delete_dialog_cancel_btn);
        cancelBtn.setOnClickListener(view -> dialog.cancel());

        // actually show dialog
        dialog.show();
    }

    public void onSaveButtonClick(Label label, TextInputEditText editText, Dialog dialog) {
        // check if value valid

        // then submit and close dialog
        dialog.dismiss();

        // else set error to editText
    }

}