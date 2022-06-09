package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;


public class AddEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> labelvoorbeeld = new ArrayList<>(Arrays.asList("pets","grocery's","school","car","<geen label> "));



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), labelvoorbeeld.get(position), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner choise = (Spinner) findViewById(R.id.add_edit_label_sp_text);
        //testchoise.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,labelvoorbeeld);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        choise.setAdapter(aa);

        TextInputEditText title = findViewById(R.id.add_edit_name_ti_text );
    }
//    public  void makeTask(){
//Intent intent = new Intent(this, ) //waar gaat de data heen//);

        //EditText deadline = findViewById(R.id.add_edit_deadline_dt);
//        EditText label = findViewById(R.id.add_edit_label_sp_text);
        //TextInputEditText description = findViewById(R.id.add_edit_description_ti_text);

 //       String titleString = title.getText().toString();
//        String labelString = label.getText().toString();
   //     String descriptionString = description.getText().toString();
        // deadline nog toevoegen //

 //       if (titleString != null && !titleString.isEmpty()) {
 //           intent.putExtra("TITLE", titleString);
 //       }
 //       startActivity(intent);
//
 //   }

    public void goTotaskListActivity(View view){
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }

//    public void clearTitle(){
//       title.getText().clear();
//    }
//    public void clearDeadline(){
//        //deadline.getText().clear();
//    }
//    public void clearDescription(){
//        description.getText().clear();
//    }
//    public void clearAll(){
//        title.getText().clear();
//        deadline.getText().clear();
//        description.getText();
//    }



}