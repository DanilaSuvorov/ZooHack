package com.example.zoohack;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ReportForRecyclerView> reports;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    DataAdapter(Context context, List<ReportForRecyclerView> reports) {
        this.reports = reports;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        final ReportForRecyclerView report = reports.get(position);
        holder.name.setText(report.getName());
        holder.place.setText(report.getPlace());
        holder.author.setText(report.getAuthor());
        holder.rating.setText(report.getRating());
        holder.affected.setText(report.getPlace());
        holder.num.setText(report.getAuthor());
        holder.refute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rating.setText((Integer.parseInt(report.getRating())-1+""));
                report.setRating(Integer.parseInt(report.getRating())-1+"");
                myRef.child("problems").child(String.valueOf(position)).child("rate").setValue(Integer.parseInt(report.getRating())); // Value

            }
        });
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rating.setText((Integer.parseInt(report.getRating())+1+""));
                report.setRating(Integer.parseInt(report.getRating())+1+"");
                myRef.child("problems").child(String.valueOf(position)).child("rate").setValue(Integer.parseInt(report.getRating())); // Value
            }
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, place, author, rating, affected, num;
        final Button confirm, refute;
        ViewHolder(View view){
            super(view);
            name = (TextView)view.findViewById(R.id.name);
            place = (TextView) view.findViewById(R.id.place);
            author = (TextView) view.findViewById(R.id.author);
            rating = (TextView) view.findViewById(R.id.rating);
            affected = (TextView) view.findViewById(R.id.affected);
            num = (TextView) view.findViewById(R.id.num);
            confirm = (Button) view.findViewById(R.id.confirm);
            refute = (Button) view.findViewById(R.id.refute);
        }
    }
}
