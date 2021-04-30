package com.example.lottart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lottart.Admin.Add_winer;
import com.example.lottart.model.Ticket_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Loterry_list extends AppCompatActivity {
    Button signup_btn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loterry_list);
        signup_btn = findViewById(R.id.signup_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressHUD = KProgressHUD.create(Loterry_list.this);
        firebaseFirestore.collection("Request")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            if (count == 11) {
                                signup_btn.setText("No Ticket");
                                signup_btn.setEnabled(false);
                            } else if (count == 10) {
                                textSend_user();
                            } else {
                                signup_btn.setEnabled(true);
                            }
                            Toast.makeText(Loterry_list.this, "Total ticket is : " + count, Toast.LENGTH_LONG).show();
                        }

                    }
                });
        firebaseFirestore.collection("User_Balance")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            String coin = task.getResult().getString("coin");
                            int a = Integer.parseInt(coin);
                            if (a < 10) {
                                signup_btn.setEnabled(false);
                                signup_btn.setError("Need Coin");
                                Toast.makeText(Loterry_list.this, "Need Minumim 10 Coin", Toast.LENGTH_LONG).show();
                            } else {
                                signup_btn.setEnabled(true);
                            }


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Loterry_list.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_check();
                firebaseFirestore.collection("User_Balance")
                        .document(firebaseAuth.getCurrentUser().getEmail())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    final String coin = task.getResult().getString("coin");
                                    final String name = task.getResult().getString("finalUserName");
                                    int get = Integer.parseInt(coin) - 10;
                                    firebaseFirestore.collection("User_Balance")
                                            .document(firebaseAuth.getCurrentUser().getEmail())
                                            .update("coin", "" + get)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Ticket_model ticket_model = new Ticket_model(firebaseAuth.getCurrentUser().getEmail(),
                                                                name, coin);
                                                        firebaseFirestore.collection("Request")
                                                                .document(firebaseAuth.getCurrentUser().getEmail())
                                                                .set(ticket_model)
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            progressHUD.dismiss();
                                                                            Toast.makeText(Loterry_list.this, "Request Send", Toast.LENGTH_SHORT).show();
                                                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                                        }
                                                                    }
                                                                }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                progressHUD.dismiss();
                                                                Toast.makeText(Loterry_list.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressHUD.dismiss();
                                            Toast.makeText(Loterry_list.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressHUD.dismiss();
                        Toast.makeText(Loterry_list.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

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

    private void textSend_user() {
        int permission = ContextCompat.checkSelfPermission(Loterry_list.this, Manifest.permission.SEND_SMS);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            sending();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }
    }

    private void sending() {

        ///+66
        String phone_number1233 = "+8801704623798";
        String sm333s = "Lottery is fill up.\n10 Ticket fill up.";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number1233, null, sm333s, null, null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sending();
                    ;
                } else {
                    Toast.makeText(this, "Don't  Have permission", Toast.LENGTH_SHORT).show();
                }
                break;

        }


    }
}