package com.example.lottart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
firebaseAuth=FirebaseAuth.getInstance();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        ConstraintLayout relativeLayout = findViewById(R.id.activity_main);
        relativeLayout.startAnimation(animation);
      // startActivity(new Intent(getApplicationContext(),Welcome.class));
        //Firebase
        mAuth = FirebaseAuth.getInstance();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser!=null){
                    sendToMain();
                }
                else {
                    Intent mainIntent = new Intent(MainActivity2.this,Welcome.class);
                    startActivity(mainIntent);
                    finish();
                }

            }
        },1000);
    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    private void sendToMain(){
        Intent mainIntent = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}