package com.shreyans.poddar.firebase_emailauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String email ;
    String password ;
    String confirmPass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn_login = findViewById(R.id.button_login);
        Button btn_register = findViewById(R.id.button_register);
        Button google = findViewById(R.id.button_google);
        final TextView txtEmail= findViewById(R.id.textemail);
        final TextView enterPassword = findViewById(R.id.Password);
        final TextView confirmPassword = findViewById(R.id.PasswordConfirm);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SignUp.this,Login.class);
                startActivity(login);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = txtEmail.getText().toString().trim();
                 password = enterPassword.getText().toString().trim();
                 confirmPass = confirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this,"Please Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confirmPass)){
                    Toast.makeText(SignUp.this,"Please Confirm Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(SignUp.this,"Please Too Short",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals(confirmPass)) {
                    authy();
                }
            }
        });



    }

    private void  authy(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(SignUp.this,"Registration complete",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SignUp.this,"Authentication Failed",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}