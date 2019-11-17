package com.example.zoohack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActiveReports extends Fragment {
    List<ReportForRecyclerView> phones = new ArrayList<>();

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_active_reports, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phones = new ArrayList<>();
                int count = dataSnapshot.child("problems").child("count").getValue(Integer.class);
                for (int i = 0; i < count;i++){
                    String name = dataSnapshot.child("problems").child(i+"").child("name").getValue(String.class);
                    String place = dataSnapshot.child("problems").child(i+"").child("place").getValue(String.class);
                    String affected = dataSnapshot.child("problems").child(i+"").child("affected").getValue(String.class);
                    String num = String.valueOf(dataSnapshot.child("problems").child(i+"").child("num").getValue(Integer.class));
                    String rating = String.valueOf(dataSnapshot.child("problems").child(i+"").child("rate").getValue(Integer.class));
                    String author = dataSnapshot.child("problems").child(i+"").child("author").getValue(String.class);
                    phones.add(new ReportForRecyclerView (name, place, author,rating,affected,num));
                    DataAdapter adapter = new DataAdapter(getContext(), phones);
                    // устанавливаем для списка адаптер
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });



        return rootView;
    }
}