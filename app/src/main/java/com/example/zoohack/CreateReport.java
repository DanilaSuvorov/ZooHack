package com.example.zoohack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateReport extends Fragment {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private String type;
    private String place;
    private String affected;
    private String count;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_create_report, container, false);
        Button report = rootView.findViewById(R.id.button2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1 = rootView.findViewById(R.id.type);
                type = editText1.getText().toString();
                editText2 = rootView.findViewById(R.id.place);
                place = editText2.getText().toString();
                editText3 = rootView.findViewById(R.id.affected);
                affected = editText3.getText().toString();
                editText4 = rootView.findViewById(R.id.count);
                count = editText4.getText().toString();
                myRef.child(type).child("problem").child("type").setValue(type); // Value
                myRef.child(type).child("problem").child("place").setValue(place); // Value
                myRef.child(type).child("problem").child("affected").setValue(affected); // Value
                myRef.child(type).child("problem").child("count").setValue(count); // Value
            }
        });
        return rootView;
    }
}