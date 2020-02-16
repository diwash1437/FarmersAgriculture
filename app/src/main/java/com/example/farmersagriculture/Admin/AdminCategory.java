package com.example.farmersagriculture.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.farmersagriculture.R;

public class AdminCategory extends AppCompatActivity {

    private ImageView dairy,meat,crops,fruits;
    private ImageView vegetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        //dairy products
        dairy=findViewById(R.id.dairy);

        dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategory.this, AdminAddProducts.class);
                intent.putExtra("category", "dairy");
                startActivity(intent);

            }
        });
        //Meat
        meat=findViewById(R.id.meat);
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategory.this,AdminAddProducts.class);
                intent.putExtra("category", "meat");
                startActivity(intent);

            }
        });

        //crops
        crops=findViewById(R.id.crops);
        crops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategory.this,AdminAddProducts.class);
                intent.putExtra("category", "crops");
                startActivity(intent);


            }
        });

        //fruits
        fruits=findViewById(R.id.fruits);
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategory.this,AdminAddProducts.class);
                intent.putExtra("category", "fruits");
                startActivity(intent);
            }
        });


        //Vegetables
        vegetables=findViewById(R.id.vegetables);
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminCategory.this,AdminAddProducts.class);
                intent.putExtra("category", "vegetables");
                startActivity(intent);
            }
        });

    }
}
