package com.example.farmersagriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.farmersagriculture.Prevalent.Prevalent;
import com.example.farmersagriculture.ViewHolders.CartsViewHolder;
import com.example.farmersagriculture.model.Cart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter_LifecycleAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartTotal extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcess;
    private TextView txtTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_total);

        recyclerView=findViewById(R.id.cart_total_lists);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        NextProcess=findViewById(R.id.next_btn);
        txtTotalAmount=findViewById(R.id.totals_price);


        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart>options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartListRef.child("User View").child(Prevalent.currentUsers.getPhone()).child("Products"),Cart.class)
                        .build();

        CartsViewHolder  adapter = new CartsViewHolder();
        recyclerView.setAdapter(adapter);
        //adapter.startListening();
    }


    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart>options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(Prevalent.currentUsers.getPhone()).child("Products"),Cart.class)
                .build();
/*
       // FirebaseRecyclerAdapter<Cart, CartsViewHolder> adapter=
             //   new FirebaseRecyclerAdapter<Cart, CartsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartsViewHolder holder, int position, @NonNull Cart model)
                    {
                          // holder.txtProductQuantity.setText(model.getQuantity());
                          // holder.txtProductPrice.setText(model.getPrice());
                           holder.txtProductName.setText(model.getPname());
                    }

                    @NonNull
                    @Override
                    public CartsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                    CartsViewHolder holder=new CartsViewHolder(view);
                    return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

*/

    }
}
