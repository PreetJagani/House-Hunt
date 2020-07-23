package com.example.househunt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class otpVerification extends AppCompatActivity {

    TextInputLayout phoneNumber;
    ProgressBar progressBar;


    int otp;
    String Otp;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.LightBlueColor));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.LightBlueColor));
        }

         phoneNumber = (TextInputLayout) findViewById(R.id.phoneNumber);

        progressBar =(ProgressBar) findViewById(R.id.progress);



        phoneNumber.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    phoneNumber.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }


    public void GetOtp(View view) {

        if(phoneNumber.getEditText().getText().toString().equals(""))
        {
            phoneNumber.setError("Please enter valid 10 digit number");
        }

        else if(phoneNumber.getEditText().getText().toString().length()!=10)
        {
            phoneNumber.setError("Please enter valid 10 digit number");
        }
        else
        {
            otp=(int)(Math.random()*9000+1000);
            Otp = ""+otp;

            progressBar.setVisibility(View.VISIBLE);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url("https://www.fast2sms.com/dev/bulk?authorization=GAQIPndP0W8efDcrcRtYAy3uIQ8sCH3RaDzgoTYIowSQReAt9sNKvxhlZLng&sender_id=FSTSMS&language=english&route=qt&numbers="+phoneNumber.getEditText().getText().toString()+"&message=4741&variables={AA}&variables_values="+Otp)
                .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });

            CountDownTimer timer = new CountDownTimer(2000,1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(otpVerification.this, "OTP send Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(otpVerification.this,otpVerification2.class);
                    intent.putExtra("PhoneNumber",phoneNumber.getEditText().getText().toString());
                    intent.putExtra("Otp",Otp);

                    startActivity(intent);
                }
            };

            timer.start();

        }
    }
}
