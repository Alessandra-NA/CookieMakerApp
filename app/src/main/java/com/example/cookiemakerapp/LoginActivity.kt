package com.example.cookiemakerapp

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.setTitle(Html.fromHtml("<font color=\"black\">Login</font>"))
        val txtareaUsername: EditText = findViewById(R.id.txtareaUsername)

        val btnLogin : Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            if (txtareaUsername.text.toString() == ""){
                Toast.makeText(applicationContext, "Debe ingresar su nombre.", Toast.LENGTH_SHORT).show()
            } else {
                val intent : Intent = Intent()
                intent.setClass(this, MainActivity::class.java)
                // pasándole el username
                val bundle : Bundle = Bundle()
                bundle.putString("username", txtareaUsername.text.toString())
                intent.putExtra("datalogin",bundle)
                startActivity(intent)
            }
        }
    }
}