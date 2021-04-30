package com.example.lottart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.lottart.Admin.Admin_panel;
import com.example.lottart.model.Admin_Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    Button signup_btn;
    private EditText regEmailText;
    private EditText regPassword;
    private EditText regConfirmPassword;

    private Button signupBtn;

    private ProgressBar regProgressBar;

    //firebase
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        regEmailText = (EditText) findViewById(R.id.signup_email);
        regPassword = (EditText) findViewById(R.id.signup_password);
        regConfirmPassword = (EditText) findViewById(R.id.signup_confirm_password);
        signupBtn = (Button)findViewById(R.id.signup_btn);
        regProgressBar = (ProgressBar)findViewById(R.id.reg_progress);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = regEmailText.getText().toString();
                final String password = regPassword.getText().toString();
                final String confirmPassword = regConfirmPassword.getText().toString();
                if(email.equals("admin112")) {
                    startActivity(new Intent(getApplicationContext(), Admin_panel.class));
                    finish();
                }

               else  if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){


                    regProgressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(password,confirmPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                               sendToStatus();
                            } else {

                                String exception = task.getException().getMessage();
                                Snackbar.make(findViewById(R.id.main_reg_layout), "Error : "+exception, Snackbar.LENGTH_SHORT).show();
                            }
                            regProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });



                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            sendToMain();
        }

    }

    private void sendToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

    private void sendToStatus(){

        Intent statusCheckIntent = new Intent(RegisterActivity.this,SetupActivity.class);
        statusCheckIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(statusCheckIntent);
    }
}
