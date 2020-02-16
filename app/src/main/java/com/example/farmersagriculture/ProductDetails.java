package com.example.farmersagriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.farmersagriculture.Prevalent.Prevalent;
import com.example.farmersagriculture.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {
   private FloatingActionButton addToCartBtn;
    private ImageView productImage;
   private Button buttonadd,buttonsub;
   private  TextView showValue;
    private TextView productPrice, productDescription,productName;
    private String productID="";
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID=getIntent().getStringExtra("pid");

        addToCartBtn=findViewById(R.id.add_product_to_cart_btn);

           showValue=findViewById(R.id.show_value);



        productImage=findViewById(R.id.product_image_details);
        productDescription=findViewById(R.id.product_description_details);
        productPrice=findViewById(R.id.product_price_details);
        productName=findViewById(R.id.product_name_details);

        //retrive the details
        getProductDetails(productID);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCartList();

            }
        });






    }

    public void countIN(View view)
    {
        counter++;
        showValue.setText(Integer.toString(counter));

    }
    public void countDEC(View view)
    {
        counter--;
        showValue.setText(Integer.toString(counter));

    }



    private void addingToCartList()
    {
        int number;
        number = Integer.parseInt(showValue.getText().toString());
        String saveCurrentTime, saveCurrentDate;
        Calendar calfordate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate =  currentDate.format(calfordate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentDate.format(calfordate.getTime());


       final DatabaseReference cartList=FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap=new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productName.getText().toString());
        cartMap.put("price",productPrice.getText().toString());
        cartMap.put("date",saveCurrentDate);
        cartMap.put("time",saveCurrentTime);
        cartMap.put("quantity",number);
        cartMap.put("discount","");

        cartList.child("User View").child(Prevalent.currentUsers.getPhone()).child("Products")
                .child(productID).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            cartList.child("Admin View").child(Prevalent.currentUsers.getPhone()).child("Products")
                                    .child(productID).updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                Toast.makeText(ProductDetails.this, "Added to cart list", Toast.LENGTH_SHORT).show();

                                                Intent intent=new Intent(ProductDetails.this,Dashboard.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }

                    }
                });

    }





    private void getProductDetails(String productID) {
        DatabaseReference productRef= FirebaseDatabase.getInstance().getReference().child("Products");

        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    Products products=dataSnapshot.getValue(Products.class);

                    productName.setText(products.getPname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    Picasso.get().load(products.getImage()).into(productImage);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
