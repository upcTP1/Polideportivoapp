package com.upc.polideportivoapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.upc.polideportivoapp.util.Usuario
import java.util.regex.Pattern

class RegistrarLogin : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtCorreoRegistro: EditText
    private lateinit var txtPasswordRegistro: EditText
    private lateinit var txtConfirmarPassword: EditText

    private val db = Firebase.database

    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    private lateinit var btnRegistrarUsuario: Button
    private lateinit var btnIrLogin: Button
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

    private fun asignarReferencias() {
        txtNombre = findViewById(R.id.txtNombre)
        txtCorreoRegistro = findViewById(R.id.txtCorreoRegistro)
        txtPasswordRegistro = findViewById(R.id.txtPasswordRegistro)
        txtConfirmarPassword = findViewById(R.id.txtConfirmarPassword)
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario)

        progressBar = findViewById(R.id.progressBar)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        // dbReference = database.reference.child("Usuario")

        btnRegistrarUsuario.setOnClickListener {
            guardar()
        }
    }

    // fun register(view: View) {
    //     guardar()
    //  }


    private fun guardar() {

        // val referencia = db.getReference("usuario")

        val referencia = db.getReference("Usuario")
        val nombres = txtNombre.text.toString()
        val correo = txtCorreoRegistro.text.toString()
        val password = txtPasswordRegistro.text.toString()

        val passwordRegex = Pattern.compile("^" +
                "(?=.*[‐@#$%^&+=])" +     // Al menos 1 carácter especial
                ".{8,}" +                // Al menos 8 caracteres
                "$")
                /*
                "(?=.[A-Z])" + // Al menos 1 mayuscula
                "(?=.*?[a-z])" +// Al menos 1 minuscula
                "(?=.*?[0-9])" +// Al menos 1 numero
                "(?=.?[#?!@$%^&-])" + // Al menos 1 carácter especial
                ".{8,}" +// Al menos 8 caracteres
                "$")*/
        val confirmarPassword = txtPasswordRegistro.text.toString()

        val usuario = Usuario(nombres, correo, password)

        if (nombres.isEmpty() || correo.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {


            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_LONG).show()
        } else {
            if (txtPasswordRegistro.text.toString() != txtConfirmarPassword.text.toString()) {
                Toast.makeText(this, "la constraseña son diferentes", Toast.LENGTH_SHORT).show()
            } else {
                //retornoa principal
                if (password.isEmpty() || !passwordRegex.matcher(password).matches()){
                    Toast.makeText(this, "La contraseña es debil.",
                        Toast.LENGTH_SHORT).show()
                }else{
                    progressBar.visibility = View.VISIBLE

                    auth.createUserWithEmailAndPassword(correo, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isComplete) {
                                val correo: FirebaseUser? = auth.currentUser
                                VerfyEmail(correo)
                                referencia.child(referencia.push().key.toString()).setValue(usuario)
                                finish()
                                // val userBD=dbReference.child(user?.uid.toString())


                                // userBD?.child("nombres")?.setValue(nombres)
                                // userBD?.child("correo")?.setValue(correo)
                                //userBD?.child("password")?.setValue(password)

                                action()
                            }
                        }
                }



                //val user:FirebaseUser?=auth.currentUser
                //VerfyEmail(user)

                //val userBD=dbReference.child(user?.uid!!)
                //val userBD= user?.uid?.let { dbReference.child(it) }

                //userBD?.child("nombres")?.setValue(nombres)
                //userBD?.child("correo")?.setValue(correo)
                //userBD?.child("password")?.setValue(password)








            }

        }
    }




    private fun action(){
        startActivity(Intent(this, Login::class.java))


    }

    private  fun VerfyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener (this){
                    task ->
                if (task.isComplete){
                    Toast.makeText(this,"Email enviado",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error al enviar el Email",Toast.LENGTH_LONG).show()
                }
            }
    }


}