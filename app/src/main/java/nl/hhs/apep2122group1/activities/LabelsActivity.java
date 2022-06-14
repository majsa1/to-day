package nl.hhs.apep2122group1.activities;

import android.app.Dialog;
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

import java.util.Arrays;
import java.util.Random;

import nl.hhs.apep2122group1.R;
import nl.hhs.apep2122group1.database.DatabaseFactory;
import nl.hhs.apep2122group1.models.Label;
import nl.hhs.apep2122group1.utils.Validators;

public class LabelsActivity extends AppCompatActivity {

    private String username;
    private Label[] labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labels);

        username = getIntent().getStringExtra("USERNAME");
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
        labels = DatabaseFactory.getDatabase().getAllLabels(username);

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

    public void onNewLabelClick(View view) {
        // create dialog builder and view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View labelCreateDialogView = inflater.inflate(R.layout.label_create_dialog, null);
        builder.setView(labelCreateDialogView);
        Dialog dialog = builder.create();

        // set [cancel, create] button handlers
        Button cancelBtn = labelCreateDialogView.findViewById(R.id.label_create_dialog_cancel_btn);
        cancelBtn.setOnClickListener(cancelBtnView -> dialog.cancel());

        TextInputEditText titleEditText = labelCreateDialogView.findViewById(R.id.label_create_dialog_title_et);
        Button createBtn = labelCreateDialogView.findViewById(R.id.label_create_dialog_create_btn);
        createBtn.setOnClickListener(createBtnView -> onCreateButtonClick(titleEditText, dialog));

        // actually show dialog
        dialog.show();
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

        // set [delete, cancel, save] button handlers
        Button deleteBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_delete_label_btn);
        deleteBtn.setOnClickListener(view -> onDeleteButtonClick(label));

        Button cancelBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_cancel_btn);
        cancelBtn.setOnClickListener(view -> dialog.cancel());

        TextInputEditText newTitleEditText = labelEditDialogView.findViewById(R.id.label_edit_dialog_new_title_et);
        Button saveBtn = labelEditDialogView.findViewById(R.id.label_edit_dialog_save_btn);
        saveBtn.setOnClickListener(view -> onSaveButtonClick(label, newTitleEditText, dialog));

        // actually show dialog
        dialog.show();
    }

    public void onDeleteButtonClick(Label label) {
        // create dialog builder and view
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View labelDeleteDialogView = inflater.inflate(R.layout.label_delete_dialog, null);
        builder.setView(labelDeleteDialogView);
        Dialog dialog = builder.create();

        // set dialog title
        TextView titleLabel = labelDeleteDialogView.findViewById(R.id.label_delete_dialog_title);
        titleLabel.setText(label.getTitle());

        // set [cancel, delete] button handlers
        Button cancelBtn = labelDeleteDialogView.findViewById(R.id.label_delete_dialog_cancel_btn);
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

    public void onCreateButtonClick(TextInputEditText editText, Dialog dialog) {
        // check if value valid
        if (!Validators.ValidateStringNotNullOrEmpty(editText.getText())) {
            editText.setError("Empty not allowed!"); // TODO: translatable
            return;
        } else if (!Validators.ValidateStringDoesNotBeginOrEndWithWhitespace(editText.getText())) {
            editText.setError("No spaces at begin of end allowed"); // TODO: translatable
            return;
        } else if (!Validators.ValidateNewLabelTitleUnique(editText.getText(), labels)) {
            editText.setError("Label must be unique"); // TODO: translatable
            return;
        }

        // generate random color (this could be replaced by a color picker in the future)
        String colorCodeFormat = "#%02X%02X%02X";
        Random random = new Random();
        String colorCode = String.format(colorCodeFormat, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        // save new label to DB then close dialog
        Label newLabel = new Label(editText.getText().toString(), colorCode, username);
        DatabaseFactory.getDatabase().upsertLabel(newLabel);
        dialog.dismiss();
        refreshLabels();
    }

}