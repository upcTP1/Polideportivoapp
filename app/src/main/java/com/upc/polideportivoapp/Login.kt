package com.upc.polideportivoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.upc.polideportivoapp.R.layout.activity_login
import java.security.Provider

class Login : AppCompatActivity() {
    private lateinit var txtCorreo: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIniciarSession: Button
    private lateinit var btnRegistrar: Button
    private val db = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_login)
        asignarReferencias()
        login()
    }

    private fun asignarReferencias() {
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistrarLogin::class.java)
            startActivity(intent)
            btnIniciarSession.setOnClickListener {
                login()
            }


        }

    }

    private fun login() {

        var contenView = R.layout.activity_login
        val referencia = db.getReference("usuario")
        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
        btnIniciarSession = findViewById(R.id.btnIniciarSession)

        var correo = txtCorreo.text.toString().trim()
        var password = txtPassword.text.toString().trim()

        if (correo.isEmpty() || password.isEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(txtCorreo.text.toString(), txtPassword.text.toString())
                .addOnCanceledListener {


                    //if (it.isSuccessfull) {
                    //    showHome ( it.result?. user?.correo ?: "", ProviderType.BASIC )
                   // }else{

                    //}

                    referencia.child(referencia.push().key.toString()).setValue(txtCorreo.text.toString(),txtPassword.text.toString())
                    finish()
                }

        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("se ha producido un error Autenticando al Usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun showHome(correo: String, provider: Provider) {
        val homeIntent = Intent(this, RegistroReserva::class.java).apply {
            putExtra("correo", correo)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }


}