package com.example.lottart.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lottart.Loterry_list;
import com.example.lottart.R;
import com.example.lottart.model.Winer_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.UUID;

public class Add_winer extends AppCompatActivity {
    EditText Password_Log,Password_Lo1g,Password_Log1;
    Button tmio_1;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_winer);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Password_Log=findViewById(R.id.Password_Log);
        Password_Lo1g=findViewById(R.id.Password_Lo1g);
        Password_Log1=findViewById(R.id.Password_Log1);
        copy();
        tmio_1=findViewById(R.id.tmio_1);
        progressHUD = KProgressHUD.create(Add_winer.this);

        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name,email,get_coiin;
                name=Password_Log.getText().toString().toLowerCase();
                email=Password_Lo1g.getText().toString().toLowerCase();
                get_coiin=Password_Log1.getText().toString().toLowerCase();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(get_coiin)) {
                    Toast.makeText(Add_winer.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    progress_check();

                    firebaseFirestore.collection("User_Balance")
                            .document(email)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        final String coin=task.getResult().getString("coin");
                                         int total=Integer.parseInt(coin)+95;
                                         firebaseFirestore.collection("User_Balance")
                                                 .document(email)
                                                 .update("coin",""+total)
                                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<Void> task) {
                                                         if (task.isSuccessful()) {
                                                             Winer_model winer_model=new Winer_model(name,email,coin,UUID.randomUUID().toString());
                                                             firebaseFirestore.collection("Winner_")
                                                                     .document(UUID.randomUUID().toString())
                                                                     .set(winer_model)
                                                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                         @Override
                                                                         public void onComplete(@NonNull Task<Void> task) {
                                                                             if (task.isSuccessful()) {
                                                                                 progressHUD.dismiss();
                                                                                 Toast.makeText(Add_winer.this, "Winner Added", Toast.LENGTH_SHORT).show();
                                                                                 startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                                                                 finish();
                                                                             }
                                                                         }
                                                                     });
                                                         }
                                                     }
                                                 });
                                    }
                                }
                            });
                }

            }
        });
    }

    private void copy() {
        //firebaseFirestore.collection().
    }

    private void progress_check() {
        progressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }
}