package com.example.househunt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class otpVerification2 extends AppCompatActivity {

    String phoneNumber,otp;


    EditText otp1,otp2,otp3,otp4;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification2);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.BlackColour));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.BlackColour));
        }

        phoneNumber= getIntent().getStringExtra("PhoneNumber");
        otp = getIntent().getStringExtra("Otp");


        otp1 =(EditText) findViewById(R.id.Otp1);
        otp2 =(EditText) findViewById(R.id.Otp2);
        otp3 =(EditText) findViewById(R.id.Otp3);
        otp4 =(EditText) findViewById(R.id.Otp4);


        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(otp1.getText().toString().length()==1)
                {
                    otp2.requestFocus();
                }

                else
                {

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(otp2.getText().toString().length()==1)
                {
                    otp3.requestFocus();
                }

                else
                {
                    otp1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(otp3.getText().toString().length()==1)
                {
                    otp4.requestFocus();
                }

                else
                {
                    otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(otp4.getText().toString().length()==1)
                {

                }

                else
                {
                    otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    public void Submit(View view) {
        if(otp1.getText().toString().length()==1)
        {
            if(otp2.getText().toString().length()==1)
            {
                if(otp3.getText().toString().length()==1)
                {
                    if(otp4.getText().toString().length()==1)
                    {
                        if(otp.equals(otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()))
                        {
                            Toast.makeText(this, "Otp verification Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(otpVerification2.this,SignUp.class);
                            intent.putExtra("phoneNumber",phoneNumber);

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(this, "Wrong Otp", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        otp4.requestFocus();
                    }

                }
                else
                {
                    otp3.requestFocus();
                }

            }
            else
            {
                otp2.requestFocus();
            }

        }
        else
        {
            otp1.requestFocus();
        }

    }
}
