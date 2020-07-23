package com.example.househunt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class searchFragement extends Fragment {

    View v;
    AutoCompleteTextView autoCompleteTextView;
    ImageView searchBtn;

    FirebaseFirestore db;
    CollectionReference houseRef;

    ArrayList<houseItem> houseItems;
    ArrayList<String> houseItemForSearch;
    ArrayList<houseItem> houseItemForRecyclerView;

    RecyclerView searchRecyclerView;
    houseAdapterFav searchAdapter;
    RecyclerView.LayoutManager searchLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.search_fragement,container,false);

        autoCompleteTextView = v.findViewById(R.id.autoCompleteTextBox);
        searchBtn = v.findViewById(R.id.searchBtn);

        db = FirebaseFirestore.getInstance();
        houseRef = db.collection("House");

        houseItems = new ArrayList<>();
        houseItemForSearch = new ArrayList<>();
        houseItemForRecyclerView = new ArrayList<>();

        searchRecyclerView = v.findViewById(R.id.recyclerView);
        searchLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL,false);


        houseRef.orderBy("rating", Query.Direction.DESCENDING).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            houseItem house = documentSnapshot.toObject(houseItem.class);
                            house.setContext(getActivity().getApplicationContext());

                            houseItemForSearch.add(house.getName() + " , " + house.getAddress() + " ( " + house.getSpace() + " )");

                            houseItems.add(house);

                        }



                        ArrayAdapter adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,houseItemForSearch);

                        autoCompleteTextView.setAdapter(adapter);

                        setFlag();


                    }
                });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setRcyclerView();
            }
        });



        return v;
    }

    private void setFlag() {
        ((GlobalArraylist)this.getActivity().getApplication()).setFlag(1);
    }


    private void setRcyclerView() {

        houseItemForRecyclerView = new ArrayList<>();

        String keyWord = autoCompleteTextView.getText().toString();

        int i;
        if(keyWord != null)
        {
            for(i=0;i<houseItemForSearch.size();i++)
            {
                if(houseItemForSearch.get(i).toUpperCase().contains(keyWord.toUpperCase()))
                {
                    houseItemForRecyclerView.add(houseItems.get(i));
                }
            }


            searchAdapter = new houseAdapterFav(houseItemForRecyclerView);

            searchRecyclerView.setLayoutManager(searchLayoutManager);
            searchRecyclerView.setAdapter(searchAdapter);

            searchAdapter.setOnItmeClickListner(new houseAdapterFav.OnItemClickListner() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity().getApplicationContext(),houseDetail.class);

                    intent.putExtra("Name",houseItemForRecyclerView.get(position).getName());
                    intent.putExtra("Address",houseItemForRecyclerView.get(position).getAddress());
                    intent.putExtra("Price",houseItemForRecyclerView.get(position).getPrice());
                    intent.putExtra("Space",houseItemForRecyclerView.get(position).getSpace());
                    intent.putExtra("Rating",houseItemForRecyclerView.get(position).getRating());
                    intent.putExtra("PhoneNumber",houseItemForRecyclerView.get(position).getPhoneNumber());
                    intent.putExtra("ImageUrl",houseItemForRecyclerView.get(position).getImageUrl());

                    intent.putExtra("Des",houseItemForRecyclerView.get(position).getdes());

                    startActivity(intent);

                }
            });


        }


    }


}
