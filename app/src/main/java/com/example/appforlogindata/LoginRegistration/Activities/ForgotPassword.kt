package com.example.appforlogindata.LoginRegistration.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appforlogindata.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)


        btn_forgotPassword_ForgotPassword.setOnClickListener {

            val email:String = et_forgotPassword_ForgotPassword.text.toString()
            if (email.isEmpty())
            {
                Toast.makeText(applicationContext,
                "Proszę wprowadź adres email.",Toast.LENGTH_SHORT).show()

            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful)
                        {
                            Toast.makeText(applicationContext,
                            "Pomyślnie wysłano email do zmiany twojego hasła.",
                            Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(applicationContext,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT).show()

                        }


                    }

            }

        }
    }
}