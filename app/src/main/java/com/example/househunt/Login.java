package com.example.househunt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {



    TextInputLayout phoneNumberTextBox,passwordTextBox;
    Button signIn;
    ProgressBar progressBar;

    int backPress;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumberTextBox =(TextInputLayout) findViewById(R.id.phoneNumber);
        passwordTextBox =(TextInputLayout) findViewById(R.id.password);

        signIn=(Button)findViewById(R.id.sign_in);

        progressBar=(ProgressBar) findViewById(R.id.progress);

        backPress=0;

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.LightBlueColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.LightBlueColor));
        }
    }



    @Override
    public void onBackPressed() {


        if(backPress==1)
        {
            finishAffinity();
        }
        else
        {

            Toast.makeText(this, "Press again to exit...", Toast.LENGTH_SHORT).show();
            backPress=1;

            CountDownTimer t1 = new CountDownTimer(2000,1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    backPress=0;
                }
            };
            t1.start();
        }

    }

    public void Login(View view)
    {
        if(phoneNumberTextBox.getEditText().getText().toString().equals("") | phoneNumberTextBox.getEditText().getText().toString().length()!=10)
        {
            phoneNumberTextBox.setError("Please Enter a valid 10 digit number");
            phoneNumberTextBox.requestFocus();
        }
        else if(passwordTextBox.getEditText().getText().toString().equals("") | passwordTextBox.getEditText().getText().toString().length()<8)
        {
            passwordTextBox.setError("Please Enter a valid password");
            passwordTextBox.requestFocus();

        }
        else
        {

            progressBar.setVisibility(View.VISIBLE);

           FirebaseAuth mAuth = FirebaseAuth.getInstance();

           mAuth.signInWithEmailAndPassword(phoneNumberTextBox.getEditText().getText().toString()+"@gmail.com",passwordTextBox.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {

                   progressBar.setVisibility(View.INVISIBLE);
                   if (task.isSuccessful())
                   {
                       Intent i = new Intent(Login.this,Home.class);
                       startActivity(i);

                       Toast.makeText(Login.this, "Log in successful", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(Login.this, "Log In Fail : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           });

      }

    }


    public void nextActivity(View view) {

        Intent intent = new Intent(Login.this,otpVerification.class);

        startActivity(intent);
    }


}
