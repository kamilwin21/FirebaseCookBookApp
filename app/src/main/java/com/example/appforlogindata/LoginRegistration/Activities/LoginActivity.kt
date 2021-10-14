package com.example.appforlogindata.LoginRegistration.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appforlogindata.MainActivity.MainActivity
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.collections.ArrayList

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textView_register_Login.setOnClickListener{
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)


        }

        tw_forgotPassword_Login.setOnClickListener{
            val intent = Intent(applicationContext, ForgotPassword::class.java)
            startActivity(intent)
        }

        btn_signIn_Login.setOnClickListener {

            //Wczytywanie danych do logowania z edittext
           // val email:String = editText_EmailAdress_Login.text.toString()
          //  val password:String = editText_password_Login.text.toString()
            val email:String = "kamilwin21@gmail.com"
            val password:String = "1234567"
            var tablica: ArrayList<Char> = ArrayList<Char>()
            for (i in email)
            {
                tablica.add(i)
            }
            var warning:Int = 0
            for (i in tablica)
            {
                if(i == '@')
                {
                    warning += 1

                }
            }
            if (warning >1 || warning == 0)
            {
                Toast.makeText(applicationContext,
                    "Nieprawidłowy format maila!",
                    Toast.LENGTH_SHORT).show()

            }else{

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){ task ->
                        if(task.isSuccessful){


                            Toast.makeText(applicationContext,
                                "Pomyślnie zalogowano",
                                Toast.LENGTH_SHORT).show()

                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(applicationContext,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT).show()

                        }

                    }

            }
            warning = 0





        }

    }
}