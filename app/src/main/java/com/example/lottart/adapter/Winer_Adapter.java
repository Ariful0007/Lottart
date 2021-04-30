package com.example.lottart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lottart.Message_user;
import com.example.lottart.R;
import com.example.lottart.model.Winer_model;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Winer_Adapter extends RecyclerView.Adapter<Winer_Adapter.myView> {
    private List<Winer_model> data;


    public Winer_Adapter(List<Winer_model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.investment_list,parent,false);
        return new Winer_Adapter.myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myView holder, int position) {
        holder.request_name_frag.setText(data.get(position).getName());
        holder.reqTime.setText(data.get(position).getEmail());


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

