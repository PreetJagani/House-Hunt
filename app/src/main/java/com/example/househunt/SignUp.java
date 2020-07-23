package com.example.househunt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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



public class SignUp extends AppCompatActivity {


    TextInputLayout password1TextBox,password2textBox;
    Button signUpBtn;

    ProgressBar progressBar;

    String phoneNumber;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.BlackColour));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.BlackColour));
        }

        password1TextBox =(TextInputLayout) findViewById(R.id.password1);
        password2textBox =(TextInputLayout) findViewById(R.id.password2);

        progressBar =(ProgressBar) findViewById(R.id.progress);


        signUpBtn=(Button)findViewById(R.id.SignUpBtn);

        phoneNumber = getIntent().getStringExtra("phoneNumber");



        password1TextBox.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password1TextBox.setError(null);

            }
        });

        password2textBox.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password2textBox.setError(null);

            }
        });





    }

    public void SignUp(View view) {


        if(password1TextBox.getEditText().getText().toString().length()>7) {
            if (password1TextBox.getEditText().getText().toString().equals(password2textBox.getEditText().getText().toString())) {

                progressBar.setVisibility(View.VISIBLE);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(phoneNumber+"@gmail.com",password1TextBox.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressBar.setVisibility(View.INVISIBLE);

                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignUp.this,Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SignUp.this, "Sign fail : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });





            }
            else
            {
                password2textBox.setError("Please check your password");
                password2textBox.requestFocus();
            }
        }
        else
        {
            password1TextBox.setError("Please Enter password of minimum length 8");
            password1TextBox.requestFocus();

        }


    }
}
