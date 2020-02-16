package com.example.farmersagriculture.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmersagriculture.MainActivity;
import com.example.farmersagriculture.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class usersignup extends AppCompatActivity {
    private Button btnuser;
    MaterialEditText username, useremail, userpassword, userphone;
    private ProgressDialog loadingbar;
    private  ProgressDialog loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);
        username=findViewById(R.id.Username);
        useremail=findViewById(R.id.Useremail);
        userphone=findViewById(R.id.Userphone);
        userpassword=findViewById(R.id.Userpassword);
        btnuser=findViewById(R.id.Usersignup);
        loadbar=new ProgressDialog(this);


        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

    }

    private void CreateAccount()
    {
        String name=username.getText().toString();
        String email=useremail.getText().toString();
        String phone=userphone.getText().toString();
        String password=userpassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        }
        else

        {

            loadbar.setTitle("Create Account");
            loadbar.setMessage("Please wait, while we are checking.");
            loadbar.setCanceledOnTouchOutside(false);
            loadbar.show();

            ValidatePhonenumber(name,email,phone,password);

        }



    }

    private void ValidatePhonenumber(final String name, final String email, final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

       RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //maile ya ! yo nahalera error vo
               if (!(dataSnapshot.child("Users").child(phone).exists()))
               {
                   HashMap<String, Object> userdata=new HashMap<>();
                   userdata.put("username", name);
                   userdata.put("email", email);
                   userdata.put("phone", phone);
                   userdata.put("password", password);

                   RootRef.child("Users").child(phone).updateChildren(userdata)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful())
                                   {
                                       Toast.makeText(usersignup.this, "Congratulaton you account has been created!.", Toast.LENGTH_SHORT).show();
                                       loadbar.dismiss();

                                       Intent intent=new Intent(usersignup.this, Userlogin.class );
                                       startActivity(intent);

                                   }
                                   else
                                   {
                                       loadbar.dismiss();
                                       Toast.makeText(usersignup.this, "Network Error: Please Try Again", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });



               }

               else
               {
                   Toast.makeText(usersignup.this, "This" +phone+"Already exosts", Toast.LENGTH_SHORT).show();
                   loadbar.dismiss();
                   Toast.makeText(usersignup.this, "Please try using another phone number", Toast.LENGTH_SHORT).show();

                   Intent intent=new Intent(usersignup.this, MainActivity.class );
                   startActivity(intent);



               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }
}
