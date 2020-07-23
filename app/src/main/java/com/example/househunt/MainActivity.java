package com.example.househunt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(getColor(R.color.BlackColour));
        }
        else
        {
            window.setStatusBarColor(getResources().getColor(R.color.BlackColour));
        }


        CountDownTimer Timer = new CountDownTimer(3000,1000)
        {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser User=mAuth.getCurrentUser();

                if(User == null)
                {
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);

                }
                else
                {
                    Intent i = new Intent(MainActivity.this,Home.class);
                    startActivity(i);
                }


            }
        };

        Timer.start();
    }





}
