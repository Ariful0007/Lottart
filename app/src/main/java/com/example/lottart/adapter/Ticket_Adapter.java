package com.example.lottart.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottart.Admin.Admin_panel;
import com.example.lottart.R;
import com.example.lottart.WithDraw;
import com.example.lottart.model.Ticket_model;
import com.example.lottart.model.Winer_model;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Ticket_Adapter extends RecyclerView.Adapter<Ticket_Adapter.myView> {
    private List<Ticket_model> data;
    FirebaseFirestore firebaseFirestore;

    public Ticket_Adapter(List<Ticket_model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.investment_list,parent,false);
        return new Ticket_Adapter.myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myView holder, final int position) {
        holder.request_name_frag.setText(data.get(position).getName());
        holder.reqTime.setText(data.get(position).getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                firebaseFirestore= FirebaseFirestore.getInstance();

                androidx.appcompat.app.AlertDialog.Builder warning = new AlertDialog.Builder(v.getContext());
                warning.setTitle("Delete.");
                warning.setMessage("Delete this user");
                warning.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        firebaseFirestore.collection("Request")
                                .document(data.get(position).getEmail()).delete();
                        v.getContext().startActivity(new Intent(v.getContext(), Admin_panel.class));
                    }
                });
                warning.create().show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myView extends RecyclerView.ViewHolder{

        TextView request_name_frag,reqTime;

        public myView(@NonNull View itemView) {
            super(itemView);

            request_name_frag=itemView.findViewById(R.id.ammount);
            reqTime=itemView.findViewById(R.id.date);
        }
    }
}