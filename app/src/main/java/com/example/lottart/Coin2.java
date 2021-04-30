package com.example.lottart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.j2objc.annotations.ObjectiveCName;

public class Coin2 extends AppCompatActivity {
    CardView card_view,card_view1,card_view3,card_view2;
    Toolbar toolbar;
    FirebaseAuth firebaseAuth;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin2);
        firebaseAuth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("   Buy Coin");
        card_view=findViewById(R.id.card_view);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                 message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 50 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798 \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();
            }
        });
        card_view1=findViewById(R.id.card_view1);
        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 100 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798vvvvvvvvv \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();

            }
        });
        card_view3=findViewById(R.id.card_view3);
        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 150 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798 \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();

            }
        });
        card_view2=findViewById(R.id.card_view2);
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 200 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798 \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();

            }
        });
        CardView card_view5=findViewById(R.id.card_view5);
        card_view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 250 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798 \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();
            }
        });
        //
        CardView card_view6=findViewById(R.id.card_view6);
        card_view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(Coin2.this);
                message="New Coin Request Arrive"+"\nEmail Address : "+firebaseAuth.getCurrentUser().getEmail()+"\n" +
                        "Number of Coin : 500 \n Thank you";
                warning.setTitle("Buy Coin.");
                warning.setCancelable(false);
                warning.setMessage("Admin Number : 01704623798 \nAdmin will this this coin in your profile.\nWhen you give payment.");
                warning.setPositiveButton("Send Request", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textSend_user();
                        dialog.dismiss();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                warning.create().show();
            }
        });
    }
    private void textSend_user() {
        int permission= ContextCompat.checkSelfPermission(Coin2.this, Manifest.permission.SEND_SMS);
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

}