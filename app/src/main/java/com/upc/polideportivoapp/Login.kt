package com.upc.polideportivoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {

    private lateinit var btnRegistrar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        asignarReferencias()
    }

    private fun asignarReferencias(){
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            val intent =  Intent(this,RegistrarLogin::class.java)
            startActivity(intent)
        }

    }
}