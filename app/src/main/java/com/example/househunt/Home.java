package com.example.househunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    int backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        backPress=0;
        ((GlobalArraylist)getApplication()).setFlag(0);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragement()).commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(((GlobalArraylist)getApplication()).getFlag() == 1)
                {
                    Fragment fragment = null;

                    switch (menuItem.getItemId())
                    {
                        case R.id.home:
                            ((GlobalArraylist)getApplication()).setFlag(0);
                            fragment = new HomeFragement();
                            break;

                        case R.id.fav:

                            fragment = new favoriteFragement();
                            break;

                        case R.id.search:
                            ((GlobalArraylist)getApplication()).setFlag(0);
                            fragment = new searchFragement();
                            break;
                    }

                    if(fragment != null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

                    return true;
                }
                else
                {
                    return false;
                }

            }
        });



    }

    public void onBackPressed() {


        if(backPress==1)
        {
            finishAffinity();
        }
        else
        {

            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
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

    public void LogOut(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }
}
