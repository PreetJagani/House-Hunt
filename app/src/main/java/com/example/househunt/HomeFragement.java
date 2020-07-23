package com.example.househunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeFragement extends Fragment {

    ProgressBar progressBar;
    TextView textView1,textView2,textView3,textView4;

    ArrayList<houseItem> houseHuntExclusive ;
    RecyclerView houseHuntExclusiveRecyclerView;
    houseAdapter houseHuntExclusiveAdapter;
    RecyclerView.LayoutManager houseHuntExclusiveLayoutManager;

    ArrayList<houseItem> houseHuntTopRated ;
    RecyclerView houseHuntTopRatedRecyclerView;
    houseAdapter houseHuntTopRatedAdapter;
    RecyclerView.LayoutManager houseHuntTopRatedLayoutManager;

    ArrayList<houseItem> houseHuntRent ;
    RecyclerView houseHuntRentRecyclerView;
    houseAdapter houseHuntRentAdapter;
    RecyclerView.LayoutManager houseHuntRentLayoutManager;

    ArrayList<houseItem> houseHuntSell ;
    RecyclerView houseHuntSellRecyclerView;
    houseAdapter houseHuntSellAdapter;
    RecyclerView.LayoutManager houseHuntSellLayoutManager;



    FirebaseFirestore db;
    CollectionReference houseRef;

    View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.home_fragement,container,false);

        progressBar = v.findViewById(R.id.progress);
        textView1 = v.findViewById(R.id.textView1);
        textView2 = v.findViewById(R.id.textView2);
        textView3 = v.findViewById(R.id.textView3);
        textView4 = v.findViewById(R.id.textView4);

        houseHuntExclusive = new ArrayList<>();
        houseHuntTopRated = new ArrayList<>();
        houseHuntRent = new ArrayList<>();
        houseHuntSell = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        houseRef = db.collection("House");


        houseRef.whereEqualTo("exclusive","true")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            houseItem house = documentSnapshot.toObject(houseItem.class);

                            house.setContext(getActivity().getApplicationContext());

                            houseHuntExclusive.add(house);

                        }

                        showExclusive();

                    }
                });



        return v;
    }

    public void showExclusive()
    {
        houseHuntExclusiveRecyclerView = v.findViewById(R.id.recyclerView1);

        progressBar.setVisibility(View.GONE);
        textView1.setVisibility(View.VISIBLE);
        houseHuntExclusiveRecyclerView.setVisibility(View.VISIBLE);


        houseHuntExclusiveLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);


        houseHuntExclusiveAdapter = new houseAdapter(houseHuntExclusive);

        houseHuntExclusiveRecyclerView.setLayoutManager(houseHuntExclusiveLayoutManager);
        houseHuntExclusiveRecyclerView.setAdapter(houseHuntExclusiveAdapter);

        houseHuntExclusiveAdapter.setOnItmeClickListner(new houseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getActivity().getApplicationContext(),houseDetail.class);

                intent.putExtra("Name",houseHuntExclusive.get(position).getName());
                intent.putExtra("Address",houseHuntExclusive.get(position).getAddress());
                intent.putExtra("Price",houseHuntExclusive.get(position).getPrice());
                intent.putExtra("Space",houseHuntExclusive.get(position).getSpace());
                intent.putExtra("Rating",houseHuntExclusive.get(position).getRating());
                intent.putExtra("PhoneNumber",houseHuntExclusive.get(position).getPhoneNumber());
                intent.putExtra("ImageUrl",houseHuntExclusive.get(position).getImageUrl());

                intent.putExtra("Des",houseHuntExclusive.get(position).getdes());

                startActivity(intent);


            }
        });

        //showing top rated
        houseRef
                .whereGreaterThan("rating","4.5")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            houseItem houseTopRated = documentSnapshot.toObject(houseItem.class);

                            houseTopRated.setContext(getActivity().getApplicationContext());

                            houseHuntTopRated.add(houseTopRated);

                        }

                        showTopRated();

                    }
                });

    }

    public void showTopRated()
    {
        textView2.setVisibility(View.VISIBLE);

        houseHuntTopRatedRecyclerView = v.findViewById(R.id.recyclerView2);

        houseHuntTopRatedRecyclerView.setVisibility(View.VISIBLE);

        houseHuntTopRatedLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);


        houseHuntTopRatedAdapter = new houseAdapter(houseHuntTopRated);

        houseHuntTopRatedRecyclerView.setLayoutManager(houseHuntTopRatedLayoutManager);
        houseHuntTopRatedRecyclerView.setAdapter(houseHuntTopRatedAdapter);

        houseHuntTopRatedAdapter.setOnItmeClickListner(new houseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(),houseDetail.class);

                intent.putExtra("Name",houseHuntTopRated.get(position).getName());
                intent.putExtra("Address",houseHuntTopRated.get(position).getAddress());
                intent.putExtra("Price",houseHuntTopRated.get(position).getPrice());
                intent.putExtra("Space",houseHuntTopRated.get(position).getSpace());
                intent.putExtra("Rating",houseHuntTopRated.get(position).getRating());
                intent.putExtra("PhoneNumber",houseHuntTopRated.get(position).getPhoneNumber());
                intent.putExtra("ImageUrl",houseHuntTopRated.get(position).getImageUrl());


                intent.putExtra("Des",houseHuntTopRated.get(position).getdes());

                startActivity(intent);

            }
        });

        //for rent

        houseRef
                .whereEqualTo("rent","true")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            houseItem houseRent = documentSnapshot.toObject(houseItem.class);

                            houseRent.setContext(getActivity().getApplicationContext());

                            houseHuntRent.add(houseRent);

                        }

                        showRent();

                    }
                });


    }

    public void showRent()
    {
        textView3.setVisibility(View.VISIBLE);

        houseHuntRentRecyclerView = v.findViewById(R.id.recyclerView3);

        houseHuntRentRecyclerView.setVisibility(View.VISIBLE);

        houseHuntRentLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);


        houseHuntRentAdapter = new houseAdapter(houseHuntRent);

        houseHuntRentRecyclerView.setLayoutManager(houseHuntRentLayoutManager);
        houseHuntRentRecyclerView.setAdapter(houseHuntRentAdapter);

        houseHuntRentAdapter.setOnItmeClickListner(new houseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(),houseDetail.class);

                intent.putExtra("Name",houseHuntRent.get(position).getName());
                intent.putExtra("Address",houseHuntRent.get(position).getAddress());
                intent.putExtra("Price",houseHuntRent.get(position).getPrice());
                intent.putExtra("Space",houseHuntRent.get(position).getSpace());
                intent.putExtra("Rating",houseHuntRent.get(position).getRating());
                intent.putExtra("PhoneNumber",houseHuntRent.get(position).getPhoneNumber());
                intent.putExtra("ImageUrl",houseHuntRent.get(position).getImageUrl());


                intent.putExtra("Des",houseHuntRent.get(position).getdes());

                startActivity(intent);

            }
        });

        //for sell

        houseRef
                .whereEqualTo("rent","false")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            houseItem houseSell = documentSnapshot.toObject(houseItem.class);

                            houseSell.setContext(getActivity().getApplicationContext());

                            houseHuntSell.add(houseSell);

                        }

                        showSell();
                        setFlag();

                    }
                });

    }

    private void setFlag() {
        ((GlobalArraylist)this.getActivity().getApplication()).setFlag(1);
    }

    public void showSell()
    {
        textView4.setVisibility(View.VISIBLE);

        houseHuntSellRecyclerView = v.findViewById(R.id.recyclerView4);

        houseHuntSellRecyclerView.setVisibility(View.VISIBLE);

        houseHuntSellLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);


        houseHuntSellAdapter = new houseAdapter(houseHuntSell);

        houseHuntSellRecyclerView.setLayoutManager(houseHuntSellLayoutManager);
        houseHuntSellRecyclerView.setAdapter(houseHuntSellAdapter);

        houseHuntSellAdapter.setOnItmeClickListner(new houseAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(),houseDetail.class);

                intent.putExtra("Name",houseHuntSell.get(position).getName());
                intent.putExtra("Address",houseHuntSell.get(position).getAddress());
                intent.putExtra("Price",houseHuntSell.get(position).getPrice());
                intent.putExtra("Space",houseHuntSell.get(position).getSpace());
                intent.putExtra("Rating",houseHuntSell.get(position).getRating());
                intent.putExtra("PhoneNumber",houseHuntSell.get(position).getPhoneNumber());
                intent.putExtra("ImageUrl",houseHuntSell.get(position).getImageUrl());


                intent.putExtra("Des",houseHuntSell.get(position).getdes());

                startActivity(intent);

            }
        });



    }


}

