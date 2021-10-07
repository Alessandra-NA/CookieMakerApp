package com.example.cookiemakerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.setTitle("Login")
        val txtareaUsername: EditText = findViewById(R.id.txtareaUsername)

        val btnLogin : Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
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