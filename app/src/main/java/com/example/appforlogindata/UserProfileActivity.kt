package com.example.appforlogindata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        if(intent.hasExtra("UserUId"))
        {
            //Reading Data from Firebase Realtime Database
           val database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(intent.getStringExtra("UserUId").toString()).get().addOnSuccessListener {
                if (it.exists()){

                    val email = it.child("email").value
                    tw_userFromDatabase.text = email.toString()
                }else{

                    Toast.makeText(applicationContext,
                    "User Doesn't Exist",Toast.LENGTH_SHORT).show()

                }


            }

        }
    }
}