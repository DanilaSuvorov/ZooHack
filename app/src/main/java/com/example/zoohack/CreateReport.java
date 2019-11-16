package com.example.zoohack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateReport extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    String type;
    String place;
    String affected;
    String count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        Button report = findViewById(R.id.button2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("problems");

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1 = findViewById(R.id.type);
                type = editText1.getText().toString();
                editText2 = findViewById(R.id.place);
                place = editText2.getText().toString();
                editText3 = findViewById(R.id.affected);
                affected = editText3.getText().toString();
                editText4 = findViewById(R.id.count);
                count = editText4.getText().toString();
                myRef.child("problem").child("type").setValue(type); // Value
                myRef.child("problem").child("place").setValue(place); // Value
                myRef.child("problem").child("affected").setValue(affected); // Value
                myRef.child("problem").child("count").setValue(count); // Value
            }
        });

    }
}
