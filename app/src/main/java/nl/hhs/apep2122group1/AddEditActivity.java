package nl.hhs.apep2122group1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class AddEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
    }
//    public  void makeTask(){
//        Intent intent = new Intent(this, **waar gaat de data heen**);
//
//        EditText title = findViewById(R.id.edit_name_ti_text );
//        String titleString = title.getText().toString();
//
//        if (titleString != null && !titleString.isEmpty()) {
//            intent.putExtra("TITLE", titleString);
//        }
//        startActivity(intent);
//
//    }
}