package com.example.appforlogindata.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appforlogindata.MainActivity.Classes.Recipes
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_recipe.*
import java.util.*

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)


        if (intent.hasExtra("kategoria1")) tvKategoria_dodajPrzepis.text = intent.getStringExtra("kategoria1")
        if (intent.hasExtra("idR"))
        {

            btn_dodajPrzepis.setText("Edytuj przepis")
            etNazwaPrzepisu_dodajPrzepis.setText(intent.getStringExtra("nazwaR"))
            etOpis_dodajPrzepis.setText(intent.getStringExtra("opisR"))
        }else{
            btn_dodajPrzepis.setText("Dodaj przepis")
        }


        btn_dodajPrzepis.setOnClickListener{
            val nazwaPrzepisu = etNazwaPrzepisu_dodajPrzepis.text.toString()
            val opisPrzepisu = etOpis_dodajPrzepis.text.toString()
            if(intent.hasExtra("kategoria1"))
            {
                val id = Date().time
                var database = FirebaseDatabase.getInstance().getReference("Recipes")
                if (intent.hasExtra("idR"))
                {
                    //Edycja przepisu z RecyclerView



                }else{

                    //Dodawanie przepisu do RecyclerView

                    val recipe = Recipes(id.toString(), FirebaseAuth.getInstance().currentUser!!.uid, nazwaPrzepisu, intent.getStringExtra("kategoria1").toString(),opisPrzepisu)
                    database.child(FirebaseAuth.getInstance().currentUser!!.uid).child(id.toString()).setValue(recipe)
                    Toast.makeText(applicationContext,"Dodano przepis", Toast.LENGTH_SHORT).show()
                    onBackPressed()

                }


            }


            /*
            var id = Date().time
            Toast.makeText(applicationContext,"Dodaj notatkę", Toast.LENGTH_SHORT).show()
            var database = FirebaseDatabase.getInstance().getReference("Recipes")
            if (intent.hasExtra("kategoria"))
            {
                val recipe = Recipes(id.toString(), FirebaseAuth.getInstance().currentUser!!.uid,"Makaron z kurczakiem",intent.getStringExtra("kategoria").toString(),"Najlepsze")
                database.child(FirebaseAuth.getInstance().currentUser!!.uid).child(id.toString()).setValue(recipe)

            }

             */




        }
    }
}