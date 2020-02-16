package com.example.farmersagriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmersagriculture.Prevalent.Prevalent;

import com.example.farmersagriculture.Users.Userlogin;
import com.example.farmersagriculture.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.PasswordAuthentication;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {
    TextView create_account;

       private Button btnflogin;
       private Button btnulogin;
    private ProgressDialog loadingBars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBars=new ProgressDialog(this);
         Paper.init(this);





        btnulogin=findViewById(R.id.btn_ulogin);

        btnulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Userlogin.class );
                startActivity(intent);
            }
        });







     String UserPhonekey=Paper.book().read(Prevalent.UserPhoneKey);
     String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);
     if (UserPhonekey != "" && UserPasswordKey !="")
     {
         if (!TextUtils.isEmpty(UserPhonekey) && !TextUtils.isEmpty(UserPasswordKey))
         {
             AllowAccess(UserPhonekey,UserPasswordKey);

             loadingBars.setTitle("Already Logged in");
             loadingBars.setMessage("Please wait........");
             loadingBars.setCanceledOnTouchOutside(false);
             loadingBars.show();
         }
     }
    }

    private void AllowAccess( final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users usersData=dataSnapshot.child("Users").child(phone).getValue(Users.class);
                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this, "Please Wait while loading to your account.....", Toast.LENGTH_SHORT).show();
                            loadingBars.dismiss();

                            Intent intent = new Intent(MainActivity.this, Dashboard.class);
                            Prevalent.currentUsers= usersData;
                            startActivity(intent);

                        }
                        else
                        {
                            loadingBars.dismiss();
                            Toast.makeText(MainActivity.this, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with thus" +phone+"number do not exist", Toast.LENGTH_SHORT).show();
                    loadingBars.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
