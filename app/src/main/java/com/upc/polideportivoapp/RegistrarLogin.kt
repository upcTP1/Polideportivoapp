package com.upc.polideportivoapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.upc.polideportivoapp.util.Usuario

class RegistrarLogin : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtCorreoRegistro: EditText
    private lateinit var txtPasswordRegistro: EditText
    private lateinit var txtConfirmarPassword: EditText
    private lateinit var btnRegistrarUsuario:Button
    private val db = Firebase.database

    private lateinit var btnIrLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_login)

        asignarReferencias()
        btnIrLogin = findViewById(R.id.btnIrLogin)
        btnIrLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
    private fun asignarReferencias(){
        txtNombre = findViewById(R.id.txtNombre)
        txtCorreoRegistro = findViewById(R.id.txtCorreoRegistro)
        txtPasswordRegistro = findViewById(R.id.txtPasswordRegistro)
        txtConfirmarPassword=findViewById(R.id.txtConfirmarPassword)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)
        btnRegistrarUsuario.setOnClickListener {
            guardar()
        }
    }
    private fun guardar(){

        val referencia = db.getReference("usuario")
        val nombres = txtNombre.text.toString()
        val correo = txtCorreoRegistro.text.toString()
        val password = txtPasswordRegistro.text.toString()
        val confirmarPassword = txtPasswordRegistro.text.toString()
        val usuario = Usuario(nombres, correo, password)

        if(nombres.isEmpty() || correo.isEmpty() || password.isEmpty() ||confirmarPassword.isEmpty()) {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_LONG).show()
        }else {
            if(txtPasswordRegistro.text.toString() !=txtConfirmarPassword.text.toString()){
                Toast.makeText(this, "la constraseña son diferentes", Toast.LENGTH_LONG).show()
            } else {
                referencia.child(referencia.push().key.toString()).setValue(usuario)
                finish()
            }
        }
    }


}