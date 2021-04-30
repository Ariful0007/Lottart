package com.example.lottart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

public class Coin_Transfer extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String valUe;
    EditText Password_Log,Password_Log1;
    Button tmio_1;
    KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin__transfer);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log1=findViewById(R.id.Password_Log1);
        tmio_1=findViewById(R.id.tmio_1);
        progressHUD = KProgressHUD.create(Coin_Transfer.this);
        firebaseFirestore.collection("User_Balance")
                .document(firebaseAuth.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            valUe=task.getResult().getString("coin");
                            int a=Integer.parseInt(valUe);
                            if (a<10) {
                                tmio_1.setEnabled(false);
                                Toast.makeText(Coin_Transfer.this, "At least 10 coin need for transfer coin", Toast.LENGTH_LONG).show();
                            }
                            else {
                                tmio_1.setEnabled(true);
                            }
                        }

                    }
                });
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emial,coin;

                emial=Password_Log.getText().toString().toLowerCase();
                coin=Password_Log1.getText().toString().toLowerCase();
               if (TextUtils.isEmpty(emial)|| TextUtils.isEmpty(coin)) {
                   tmio_1.setError("Something is wrong!");
                   return;
               }
               else {
                   progress_check();
                   firebaseFirestore.collection("User_Balance")
                           .document(firebaseAuth.getCurrentUser().getEmail())
                           .get()
                           .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                   if (task.isSuccessful()) {
                                       final String get_new;
                                       get_new=task.getResult().getString("coin");
                                       int new_coin=Integer.parseInt(get_new)-Integer.parseInt(coin);
                                       firebaseFirestore.collection("User_Balance")
                                               .document(firebaseAuth.getCurrentUser().getEmail())
                                               .update("coin",""+new_coin)
                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<Void> task) {
                                                       if (task.isSuccessful()) {
                                                           firebaseFirestore.collection("User_Balance")
                                                                   .document(emial)
                                                                   .update("coin",coin)
                                                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<Void> task) {
                                                                           if (task.isSuccessful()) {
                                                                               progressHUD.dismiss();
                                                                               Toast.makeText(Coin_Transfer.this, "Done", Toast.LENGTH_SHORT).show();
                                                                               startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
        //Toast.makeText(this, ""+valUe, Toast.LENGTH_SHORT).show();

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