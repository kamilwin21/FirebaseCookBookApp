package com.example.appforlogindata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.appforlogindata.fragments.HomeFragment
import com.example.appforlogindata.fragments.SearchFragment
import com.example.appforlogindata.fragments.UsersFragment
import kotlinx.android.synthetic.main.fragment_users.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val usersFragment = UsersFragment()
    private val searchFragment = SearchFragment()

    lateinit var ref: DatabaseReference
    lateinit var usersList: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)


        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> {
                    replaceFragment(homeFragment)

                }

                R.id.nav_users -> {
                    replaceFragment(usersFragment)

                    usersList = mutableListOf()

                    ref = FirebaseDatabase.getInstance().getReference("Users")

                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            if (snapshot!!.exists()){
                                for (h in snapshot.children){
                                    val user = h.getValue(User::class.java)
                                    usersList.add(user!!)
                                    RecyclerView_Users_Fragment.layoutManager = LinearLayoutManager(applicationContext)
                                    RecyclerView_Users_Fragment.adapter = UsersAdapter(applicationContext,usersList)


                                }


                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }


                    })




                }
                R.id.nav_search -> replaceFragment(searchFragment)
            }
            true

        }


      /*
        btn_logout_MainActivity.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            Toast.makeText(applicationContext,"Pomyślnie wylogowano", Toast.LENGTH_SHORT).show()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()

        }

        */




        //Koniec onCreate
    }


    private fun replaceFragment(fragment: Fragment){
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        }

    }






}