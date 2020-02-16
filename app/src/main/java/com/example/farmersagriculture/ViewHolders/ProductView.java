package com.example.farmersagriculture.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersagriculture.Interface.ItemClick;
import com.example.farmersagriculture.R;

public class ProductView extends RecyclerView.ViewHolder implements View.OnClickListener {

     public TextView ProductName,ProductDesc,Productsprice;
     public ImageView imageView;
     public ItemClick listenerr;


    public ProductView(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.products_image);
        ProductName=itemView.findViewById(R.id. Product_name);
        ProductDesc=itemView.findViewById(R.id.Product_Desc);
        Productsprice=itemView.findViewById(R.id.Product_price);



    }

    public void  setItemClickListner(ItemClick listner)
    {
        this.listenerr=listner;
    }

    @Override
    public void onClick(View view)
    {
        listenerr.onClick(view, getAdapterPosition(), false);

    }
}
