package com.example.zoohack;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private String type;
    private String place;
    private String affected;
    private String num;
    private String description;
    private  String count;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button report = rootView.findViewById(R.id.button2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("problems");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                count = dataSnapshot.child("problems").child("count").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1 = rootView.findViewById(R.id.name);
                type = editText1.getText().toString();
                editText2 = rootView.findViewById(R.id.aff);
                place = editText2.getText().toString();
                editText3 = rootView.findViewById(R.id.place);
                affected = editText3.getText().toString();
                editText4 = rootView.findViewById(R.id.num);
                num = editText4.getText().toString();
                editText5 = rootView.findViewById(R.id.desc);
                description = editText5.getText().toString();
                myRef.child(count+1).child("type").setValue(type); // Value
                myRef.child(count+1).child("place").setValue(place); // Value
                myRef.child(count+1).child("affected").setValue(affected); // Value
                myRef.child(count+1).child("count").setValue(num); // Value
                myRef.child(count+1).child("dis").setValue(description);
                myRef.child("count").setValue(count+1);// Value
            }
        });

        Button call = rootView.findViewById(R.id.call); // authorisation

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "89670898899", null)));
            }
        });

        return rootView;




    }
}