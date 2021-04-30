package com.example.lottart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lottart.adapter.AllUserAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class ChatFragment extends Fragment {


    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    AllUserAdapter getDataAdapter1;
    List<Message_user> getList;
    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        getList=new ArrayList<>();
        getDataAdapter1=new AllUserAdapter(getList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection("User_Data").document();
        recyclerView=view.findViewById(R.id.blog_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
        return view;
    }
    private void reciveData() {
        firebaseFirestore.collection("User_Data").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds:queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType()== DocumentChange.Type.ADDED) {
                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        Message_user get=ds.getDocument().toObject(Message_user.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}