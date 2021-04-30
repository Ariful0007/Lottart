package com.example.lottart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lottart.model.Admin_Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class WithDraw extends AppCompatActivity {
    private static final int READCODE = 1;
    private static final int WRITECODE = 2;

    private Uri mainImageUri = null;

    private CircleImageView setupImage;
    private EditText setupName;
    private TextView changePhoto;
    private EditText setupSkills;
    private ImageView setupButton;
    private ProgressBar setupProgress;
    private Boolean isChanged = false;
    private String current_user_id;
    private EditText aboutYou;
    private EditText emailUser;
    private EditText phoneNumber;
    private Bitmap compressedUserImage;
    private Toolbar mToolbar;

    String user_id = "";

    //firebase
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseFirestoreSettings settings;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    private String intentThatStartedThisActivity;
    TextView changeProfilePhoto;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;//Firebase
    DatabaseReference mDatabase;
    Button signup_btn;
    TextView Password_Log,Password_Log3;
    Button tmio_1;
    String name,email,phonenumber1,total_mm, message;
    KProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getEmail();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        progressHUD = KProgressHUD.create(WithDraw.this);
        firebaseFirestore.setFirestoreSettings(settings);
        firebaseDatabase = FirebaseDatabase.getInstance();
        Password_Log=findViewById(R.id.Password_Log);
        Password_Log3=findViewById(R.id.Password_Log3);
        tmio_1=findViewById(R.id.tmio_1);
        tmio_1.setEnabled(false);
        firebaseFirestore.collection("User_Balance").document(user_id)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    String coin=task.getResult().getString("coin");
                    name=task.getResult().getString("finalUserName");
                    phonenumber1=task.getResult().getString("finalUserPhone");
                    Password_Log.setText(coin);
                    int a;
                    a=Integer.parseInt(coin);
                    if (a<500) {
                        Password_Log3.setText("Not enough money.");
                    }
                    else {
                      int get=a*2;
                        Password_Log3.setText(""+get);
                        total_mm=""+get;
                        tmio_1.setEnabled(true);
                    }
                }
            }
        });
        tmio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_check();
                 message="New Payment Request Arrive"+"\nPhone Number : "+
                         phonenumber1+"\n Total Ammount : "+total_mm+"\n" +
                        "Thank You";
                AlertDialog.Builder warning = new AlertDialog.Builder(WithDraw.this);
                warning.setTitle("Payment Information.");
                warning.setMessage("Your payment will give within 24 hours");
                warning.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Admin_Request andmin=new Admin_Request(firebaseAuth.getCurrentUser().getEmail(),
                                phonenumber1,total_mm);
                        firebaseFirestore.collection("Payment_Request")
                                .document(firebaseAuth.getCurrentUser().getEmail())
                                .set(andmin)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            firebaseFirestore.collection("User_Balance")
                                                    .document(firebaseAuth.getCurrentUser().getEmail())
                                                    .update("coin","0")
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                progressHUD.dismiss();
                                                                textSend_user();
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                });



                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressHUD.dismiss();
                        dialog.dismiss();

                    }
                });



                warning.create().show();


            }
        });
    }
    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(WithDraw.this, Manifest.permission.SEND_SMS);
        if (permission== PackageManager.PERMISSION_GRANTED) {
            sending();
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
        }
    }
    private void sending() {

        ///+66
        String phone_number1233="+8801704623798";
        String sm333s=message;
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(phone_number1233,null,sm333s,null,null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    sending();;
                }
                else {
                    Toast.makeText(this, "Don't  Have permission", Toast.LENGTH_SHORT).show();
                }
                break;

        }

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