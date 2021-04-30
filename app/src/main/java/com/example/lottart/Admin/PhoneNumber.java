package com.example.lottart.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lottart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

public class PhoneNumber extends AppCompatActivity {
    EditText Password_Log;
    Button tmio_1;
    FirebaseFirestore firebaseFirestore;
    KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        Password_Log=findViewById(R.id.Password_Log);
        tmio_1=findViewById(R.id.tmio_1);
        progressHUD = KProgressHUD.create(PhoneNumber.this);
        firebaseFirestore=FirebaseFirestore.getInstance();
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=Password_Log.getText().toString();
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(PhoneNumber.this, "Empty field", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progress_check();
                    Map<String, Object> user = new HashMap<>();

                    user.put("phone", number);
                    firebaseFirestore.collection("PhoneNumber")
                            .add(user)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        progressHUD.dismiss();
                                        Toast.makeText(PhoneNumber.this, "Phone Number Added", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),Admin_panel.class));
                                      finish();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressHUD.dismiss();
                            Toast.makeText(PhoneNumber.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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