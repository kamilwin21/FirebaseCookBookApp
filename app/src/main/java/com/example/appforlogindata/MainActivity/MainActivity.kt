package com.example.appforlogindata.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforlogindata.MainActivity.Fragments.RecipesFragment
import com.example.appforlogindata.MainActivity.Fragments.UsersFragment
import com.example.appforlogindata.R
import com.example.appforlogindata.User
import com.example.appforlogindata.UsersAdapter
import com.example.appforlogindata.fragments.FavoritesFragment
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_users.*

class MainActivity : AppCompatActivity() {

   // private val homeFragment = HomeFragment()
    private val usersFragment = UsersFragment()
    private val recipesFragment = RecipesFragment()
    private val favoritesFragment = FavoritesFragment()

   // private val searchFragment = SearchFragment()
    
    lateinit var ref: DatabaseReference
    lateinit var usersList: MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




          //  replaceFragment(usersFragment)


        replaceFragment(recipesFragment)
        bottom_navigation.setOnNavigationItemSelectedListener{




            when(it.itemId){
                R.id.nav_recipes -> {
                    replaceFragment(recipesFragment)
                   // replaceFragment(fragmentDEMO)
                    //recyclerView_recipesFragment.layoutManager = LinearLayoutManager(applicationContext)
                  //  recyclerView_recipesFragment.adapter = AdapterRecyclerView_RecipesFragment(applicationContext)


                    true

                }
                R.id.nav_favorites -> {
                  //  replaceFragment(favoritesFragment)
                    val intent = Intent(applicationContext, RecipesActivity::class.java)
                    startActivity(intent)

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



                    true
                }
               // R.id.nav_search -> replaceFragment(searchFragment)

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
            transaction.replace(R.id.fragment_container, fragment).addToBackStack(null)
            transaction.addToBackStack(null)
            transaction.commit()




        }

    }

    override fun onBackPressed() {


        super.onBackPressed()
    }






}