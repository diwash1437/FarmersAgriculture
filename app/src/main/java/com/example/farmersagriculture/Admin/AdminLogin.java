package com.example.farmersagriculture.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmersagriculture.Dashboard;
import com.example.farmersagriculture.Prevalent.Prevalent;
import com.example.farmersagriculture.R;
import com.example.farmersagriculture.Users.Userlogin;
import com.example.farmersagriculture.Users.usersignup;
import com.example.farmersagriculture.model.Admin;
import com.example.farmersagriculture.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class AdminLogin extends AppCompatActivity {
    private EditText InputNumber, InputPassword;
    private Button AdminButton;
    private ProgressDialog loadingBar;


    private String parentDBName = "Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        InputNumber=findViewById(R.id.admin_phone);
        InputPassword=findViewById(R.id.admin_psssword);

        AdminButton=findViewById(R.id.admin_btn);

        loadingBar=new ProgressDialog(this);

        AdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminLogin();
            }
        });

    }


    private void AdminLogin()
    {
  String phone=InputNumber.getText().toString();
  String password=InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccounts(phone,password);

        }

    }

    private void AllowAccessToAccounts(final String phone, String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDBName).child(phone).exists())
                {
                    Admin admindata=dataSnapshot.child(parentDBName).child(phone).getValue(Admin.class);
                //retrive data
                    if (admindata.getPhone().equals(phone))
                    {
                        if (admindata.getPassword().equals(phone))
                        {
                            Toast.makeText(AdminLogin.this, "Logged in Successfull", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                            Intent intent=new Intent(AdminLogin.this, AdminCategory.class );
                            startActivity(intent);

                        }
                    }

                }
                else
                {
                    Toast.makeText(AdminLogin.this, "Account with this " +phone +"number do not exist", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
                    Toast.makeText(AdminLogin.this, "Please try again", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}