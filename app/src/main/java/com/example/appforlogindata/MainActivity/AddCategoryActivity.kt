package com.example.appforlogindata.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appforlogindata.MainActivity.Classes.CategoryRecipes
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_category.*
import java.util.*

class AddCategoryActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        if (intent.hasExtra("CategoryName")) {
            btn_add_name_category.text = "Edytuj kategorię"
            et_nazwakategorii.setText(intent.getStringExtra("CategoryName"))
        }else{
            btn_add_name_category.text = "Dodaj kategorię"

        }
        btn_add_name_category.setOnClickListener {

            //Połączenie z firebase database

            val nazwaKategorii = et_nazwakategorii.text.toString()

            if (intent.hasExtra("CategoryIdC") && intent.hasExtra("CategoryName") && intent.hasExtra("CategoryUIdC"))
            {
                //Edycja kategorii

                database = Firebase.database.getReference("Categories")
                var categoryRecipes = CategoryRecipes(intent.getStringExtra("CategoryIdC").toString(),
                                    nazwaKategorii, FirebaseAuth.getInstance().currentUser!!.uid)
                database.child(FirebaseAuth.getInstance().currentUser!!.uid).child(intent.getStringExtra("CategoryIdC").toString()).setValue(categoryRecipes)
                Toast.makeText(applicationContext, "Zaktualizowano kategorię", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }else{
                //Dodawanie kategorii oraz połączenie z firebase

                var dataID = Date().time
                database = Firebase.database.getReference("Categories")
                var categoryRecipes = CategoryRecipes(dataID.toString(), nazwaKategorii, FirebaseAuth.getInstance().currentUser!!.uid)
                database.child(FirebaseAuth.getInstance().currentUser!!.uid).child(dataID.toString()).setValue(categoryRecipes)
                Toast.makeText(applicationContext, "Dodano kategorię $nazwaKategorii",Toast.LENGTH_SHORT).show()
                onBackPressed()

            }



        }


    }
}