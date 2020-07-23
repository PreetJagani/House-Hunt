package com.example.househunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;


import java.lang.reflect.Type;
import java.util.ArrayList;



public class houseDetail extends AppCompatActivity {

    TextView name,address,price,space,rating,des;
    Button phoneNumber;
    ImageView image;

    ArrayList<houseItem> favHouseArrayList;

    houseItem h1;
    int index;



    LikeButton likeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);



        likeButton = findViewById(R.id.likeBtn);



        image = findViewById(R.id.imageView);

        name = findViewById(R.id.text_name);
        address= findViewById(R.id.text_address);
        price= findViewById(R.id.text_price);
        space= findViewById(R.id.text_space);
        rating= findViewById(R.id.text_rating);
        des = findViewById(R.id.text_des);
        phoneNumber = findViewById(R.id.phoneNumber);



        name.setText(getIntent().getStringExtra("Name"));
        address.setText(getIntent().getStringExtra("Address"));
        price.setText(getIntent().getStringExtra("Price"));
        space.setText(getIntent().getStringExtra("Space"));
        rating.setText(getIntent().getStringExtra("Rating"));
        des.setText(getIntent().getStringExtra("Des"));


        favHouseArrayList = ((GlobalArraylist)this.getApplication()).getGlobalArraylist();

        h1 = new houseItem(getIntent().getStringExtra("ImageUrl"),getIntent().getStringExtra("Name"),getIntent().getStringExtra("Address"),getIntent().getStringExtra("Price"),getIntent().getStringExtra("Space"),getIntent().getStringExtra("Rating"),getIntent().getStringExtra("PhoneNumber"),getIntent().getStringExtra("Des"));
        int i;

        if(favHouseArrayList == null)
        {
            favHouseArrayList = new ArrayList<>();
        }
        else
        {
            for(i=0;i<favHouseArrayList.size();i++)
            {
                if(favHouseArrayList.get(i).getName().toString().equals(name.getText().toString()))
                {
                    likeButton.setLiked(true);
                    index = i;
                    break;
                }
            }

        }







        Picasso.with(houseDetail.this).load(getIntent().getStringExtra("ImageUrl")).resize(400,300).into(image);

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", getIntent().getStringExtra("PhoneNumber"), null)));
            }
        });

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                    afterLike();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                afterDislike();
            }
        });

    }

    private void afterDislike() {
        favHouseArrayList.remove(index);
        ((GlobalArraylist)this.getApplication()).setGlobalArraylist(favHouseArrayList);
        Toast.makeText(houseDetail.this, "House removed from favorite", Toast.LENGTH_SHORT).show();
    }


    private void afterLike() {
        index = favHouseArrayList.size();
        favHouseArrayList.add(h1);
        ((GlobalArraylist)this.getApplication()).setGlobalArraylist(favHouseArrayList);
        Toast.makeText(houseDetail.this, "House added to favorite", Toast.LENGTH_SHORT).show();
    }


}
