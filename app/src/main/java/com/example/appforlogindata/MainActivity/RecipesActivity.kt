package com.example.appforlogindata.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appforlogindata.MainActivity.Adapters.AdapterRecipes
import com.example.appforlogindata.MainActivity.Classes.Recipes

import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_recipes.*
import java.util.*
import kotlin.collections.ArrayList

class RecipesActivity : AppCompatActivity() {
    //Aktywność służy do operowania w recyclerView z przepisami
    //AKTYWNOŚĆ TA ZOSTAŁA ZASTĄPIONA FRAGMENTEM AdapterRecyclerView_RecipesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        tv_Przepisu.text = intent.getStringExtra("kategoria")


        var database = FirebaseDatabase.getInstance().getReference("Recipes").child(FirebaseAuth.getInstance().currentUser!!.uid)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {   var pomocList: ArrayList<Recipes> = arrayListOf()
                    var recipesList: ArrayList<Recipes> = arrayListOf()
                    var recipe: Recipes?
                    for (n in snapshot.children)
                    {
                       recipe = n.getValue(Recipes::class.java)
                        pomocList.add(recipe!!)

                    }

                    for (r in pomocList)
                    {
                        if(r.kategoria == tv_Przepisu.text)
                        {
                            recipesList.add(r)
                        }
                    }
                    recyclerView_Recipes.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerView_Recipes.adapter = AdapterRecipes(applicationContext, recipesList)





                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_recipes,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.add_category_toDatabase)
        {
            if(intent.hasExtra("kategoria"))
            {
                val intent = Intent(applicationContext,AddRecipeActivity::class.java)
                intent.putExtra("kategoria1", tv_Przepisu.text.toString() )
                startActivity(intent)
            }

        }


        return super.onOptionsItemSelected(item)
    }

}