package com.example.househunt;

import android.content.Context;

public class houseItem{

    private String imageUrl,name,address,price,space,rating,phoneNumber,rent,exclusive,des;

    private Context context;

    public houseItem()
    {

    }

    public houseItem(String imageUrl,String name,String address,String price,String space,String rating,String phoneNumber,String des)
    {
        this.imageUrl=imageUrl;
        this.name=name;
        this.address=address;
        this.price=price;
        this.space=space;
        this.rating=rating;
        this.phoneNumber=phoneNumber;
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public void setExclusive(String exclusive) {
        this.exclusive = exclusive;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public String getSpace() {
        return space;
    }

    public String getRating() {
        return rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getdes() {
        return des;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
