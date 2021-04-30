package com.example.lottart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lottart.adapter.AllUserAdapter;
import com.example.lottart.adapter.Ticket_Adapter;
import com.example.lottart.model.Ticket_model;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class AllUsersActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    Ticket_Adapter getDataAdapter1;
    List<Ticket_model> getList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);


        getList=new ArrayList<>();
        getDataAdapter1=new Ticket_Adapter(getList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection("Request").document();
        recyclerView=findViewById(R.id.blog_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
    }
    private void reciveData() {
        firebaseFirestore.collection("Request").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds:queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType()== DocumentChange.Type.ADDED) {
                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        Ticket_model get=ds.getDocument().toObject(Ticket_model.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}