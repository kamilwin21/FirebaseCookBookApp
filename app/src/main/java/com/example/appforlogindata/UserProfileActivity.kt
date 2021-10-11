package com.example.appforlogindata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appforlogindata.fragments.UsersFragment
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.fragment_home.*

class UserProfileActivity : AppCompatActivity() {

    private var usersFragment = UsersFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val userUId = intent.hasExtra("UserUId")
        val userEmail = intent.hasExtra("UserEmail")
        val userName = intent.hasExtra("UserName")
        val userAge = intent.hasExtra("UserAge")

        if(userUId && userEmail && userName && userAge)
        {
            //Reading Data from Firebase Realtime Database
           val database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(intent.getStringExtra("UserUId").toString()).get().addOnSuccessListener {
                if (it.exists()){


                    var surname:String = it.child("surname").value.toString()
                    if (surname == null) surname = " "
                    var name:String = it.child("name").value.toString()
                    name += " "+ surname
                    tw_UserName_ActivityUserProfile.text = name

                    val email = it.child("email").value
                    tw_UserEmail_ActivityUserProfile.text = email.toString()
                    val age = it.child("age").value
                    tw_UserAge_ActivityUserProfile.text = age.toString()
                  //  tw_userFromDatabase.text = email.toString()
                }else{

                    Toast.makeText(applicationContext,
                    "User Doesn't Exist",Toast.LENGTH_SHORT).show()

                }


            }

        }
    }

    override fun onBackPressed() {
       // Toast.makeText(applicationContext,"Powrót do MainActivity", Toast.LENGTH_SHORT).show()
        var intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("btnBack", "Back")
        startActivity(intent)

    }








}