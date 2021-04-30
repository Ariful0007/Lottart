package com.example.lottart.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lottart.MainActivity;
import com.example.lottart.R;
import com.example.lottart.WithDraw;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Add_Coin extends AppCompatActivity {
    EditText Password_Log,Password_Log1;
    Button tmio_1;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    KProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__coin);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log1=findViewById(R.id.Password_Log1);
        tmio_1=findViewById(R.id.tmio_1);
        progressHUD = KProgressHUD.create(Add_Coin.this);
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email,coin1;

                email=Password_Log.getText().toString().toLowerCase();
                coin1=Password_Log1.getText().toString().toLowerCase();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(coin1)) {
                    tmio_1.setError("Empty Field");
                    return;
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
                                        if (task.getResult().exists()) {
                                            String coin_get=task.getResult().getString("coin");
                                            int total=Integer.parseInt(coin_get)+Integer.parseInt(coin1);
                                            firebaseFirestore.collection("User_Balance")
                                                    .document(email)
                                                    .update("coin",""+total)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                progressHUD.dismiss();
                                                                Toast.makeText(Add_Coin.this, "Coin Adding Successfully Done", Toast.LENGTH_SHORT).show();
                                                                startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                                                finish();
                                                            }

                                                        }
                                                    });
                                        }
                                        else {
                                            progressHUD.dismiss();
                                            Toast.makeText(Add_Coin.this, "Email is invaild", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressHUD.dismiss();
                            Toast.makeText(Add_Coin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }


            }
        });


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