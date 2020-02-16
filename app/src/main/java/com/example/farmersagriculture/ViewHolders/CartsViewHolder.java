package com.example.farmersagriculture.ViewHolders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersagriculture.Interface.ItemClick;
import com.example.farmersagriculture.R;

public class CartsViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener
{
    public TextView txtProductName, txtProductPrice, txtProductQuantity;
    private ItemClick itemClickListner;


    public void setItemClickListner(ItemClick itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }


}
