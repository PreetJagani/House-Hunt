package com.example.househunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class houseAdapter extends RecyclerView.Adapter<houseAdapter.houseViewHolder> {

    ArrayList<houseItem> houseItems = new ArrayList<>();

    houseAdapter(ArrayList<houseItem> houseItems) {
        this.houseItems = houseItems;
    }

    private OnItemClickListner mListner;

    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public void setOnItmeClickListner(OnItemClickListner listner) {
        mListner = listner;
    }


    @NonNull
    @Override
    public houseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_item, parent, false);

        houseViewHolder hvh = new houseViewHolder(v, mListner);
        return hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull houseViewHolder holder, int position) {
        houseItem currentItem = houseItems.get(position);

        Picasso.with(currentItem.getContext()).load(currentItem.getImageUrl()).resize(690, 450).into(holder.image);

        holder.name.setText(currentItem.getName());
        holder.address.setText(currentItem.getAddress());
        holder.price.setText(currentItem.getPrice());
        holder.space.setText(currentItem.getSpace());
        holder.rating.setText(currentItem.getRating());
        holder.phoneNumber.setText(currentItem.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return houseItems.size();
    }

    public class houseViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, price, space, rating, phoneNumber;
        ImageView image;

        public houseViewHolder(@NonNull View itemView, final OnItemClickListner listner) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);

            name = itemView.findViewById(R.id.text_name);
            address = itemView.findViewById(R.id.text_address);
            price = itemView.findViewById(R.id.text_price);
            space = itemView.findViewById(R.id.text_space);
            rating = itemView.findViewById(R.id.text_rating);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listner.onItemClick(position);
                }
            });
        }
    }
}
