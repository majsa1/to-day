package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
    }
//    public  void makeTask(){
//        Intent intent = new Intent(this, //waar gaat de data heen//);
//
//        EditText title = findViewById(R.id.edit_name_ti_text );
//        EditText deadline = findViewById(R.id.add_edit_dt);
//        EditText label = findViewById(R.id.add_edit_label_sp_text);
//        EditText description = findViewById(R.id.add_edit_description_ti_text);

//        String titleString = title.getText().toString();
//        String labelString = label.getText().toString();
//        String descriptionString = description.getText().toString();
//        // deadline nog toevoegen //

//        if (titleString != null && !titleString.isEmpty()) {
//            intent.putExtra("TITLE", titleString);
//        }
//        startActivity(intent);
//
//    }
    public void goTotaskListActivity(View view){
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }
    public void clearTitle(){
        //title.getText().clear();
    }
    public void clearDeadline(){
        //deadline.getText().clear();
    }
    public void clearDescription(){
        //description.getText();
    }
    public void clearAll(){
        //title.getText().clear();
        //deadline.getText().clear();
        //description.getText();
    }

}