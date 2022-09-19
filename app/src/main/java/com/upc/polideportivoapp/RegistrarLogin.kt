package com.upc.polideportivoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.upc.polideportivoapp.util.Usuario

class RegistrarLogin : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtCorreoRegistro: EditText
    private lateinit var txtPasswordRegistro: EditText
    private lateinit var btnRegistrarUsuario:Button
    private val db = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_login)

        asignarReferencias()
    }
    private fun asignarReferencias(){
        txtNombre = findViewById(R.id.txtNombre)
        txtCorreoRegistro = findViewById(R.id.txtCorreoRegistro)
        txtPasswordRegistro = findViewById(R.id.txtPasswordRegistro)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnRegistrarUsuario.setOnClickListener {
            guardar();
        }
    }
    private fun guardar(){
        val referencia = db.getReference("usuario")
        val nombres = txtNombre.text.toString()
        val correo = txtCorreoRegistro.text.toString()
        val password = txtPasswordRegistro.text.toString()
        val persona = Usuario(nombres, correo, password)

        referencia.child(referencia.push().key.toString()).setValue(persona)
        finish()
    }


}