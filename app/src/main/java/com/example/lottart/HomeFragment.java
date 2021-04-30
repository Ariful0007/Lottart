package com.example.lottart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lottart.adapter.AllUserAdapter;
import com.example.lottart.adapter.Winer_Adapter;
import com.example.lottart.model.Winer_model;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class HomeFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    Winer_Adapter getDataAdapter1;
    List<Winer_model> getList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        getList=new ArrayList<>();
        getDataAdapter1=new Winer_Adapter(getList);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection("Winner_").document();
        recyclerView=view.findViewById(R.id.blog_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(getDataAdapter1);
        reciveData();
        return view;
    }
    private void reciveData() {
        firebaseFirestore.collection("Winner_").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange ds:queryDocumentSnapshots.getDocumentChanges()) {
                    if (ds.getType()== DocumentChange.Type.ADDED) {
                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                        Winer_model get=ds.getDocument().toObject(Winer_model.class);
                        getList.add(get);
                        getDataAdapter1.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}