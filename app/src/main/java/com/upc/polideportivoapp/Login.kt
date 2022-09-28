package com.upc.polideportivoapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.upc.polideportivoapp.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {

    private  var auth =FirebaseAuth.getInstance()
    private  var mAuth =FirebaseAuth.getInstance()

    private lateinit var binding:ActivityLoginBinding

    //private lateinit var btnRegistrar: Button
    //private  var databaseReference =FirebaseDatabase.getInstance().getReference("usuario")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // btnRegistrar = findViewById(R.id.btnRegistrarUsuario)

        binding=ActivityLoginBinding.inflate(layoutInflater )
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)
        auth = Firebase.auth
        mAuth = FirebaseAuth.getInstance()

        binding.btnIniciarSession.setOnClickListener {
            val txtCorreo=binding.txtCorreo.text.toString()
            val txtPassword=binding.txtPassword.text.toString()
            when{
                txtCorreo.isEmpty() ||txtPassword.isEmpty() -> {
                    Toast.makeText(baseContext,"Correo o Contraseña Nulos",Toast.LENGTH_SHORT).show()

                }else -> {
                signIn(txtCorreo,txtPassword)
            }
            }

        }

        binding.btnRegistrar.setOnClickListener{
            val intent=Intent(this,RegistrarLogin::class.java)
            this.startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }
    private fun signIn(txtCorreo:String,txtPassword:String) {


        auth.signInWithEmailAndPassword(txtCorreo, txtPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {


                    //val user = mAuth.currentUser
                    // val uid = user!!.uid
                    // mDatabase.child(uid).child("Name").setValue(name)
                    //startActivity(Intent(this, MainActivity::class.java))

                    Log.d(TAG, "signInWithEmail:success")

                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }


    }

    private fun SignOut(){
        Firebase.auth.signOut()

        val intent=Intent(this,Login::class.java)
        startActivity(intent )

    }
    private fun reload(){
        val intent=Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }

}