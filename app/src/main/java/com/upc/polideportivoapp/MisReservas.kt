package com.upc.polideportivoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MisReservas : AppCompatActivity() {

    private lateinit var rvReservas:RecyclerView
    private val listadodeReservas:MutableList<PersonaRR> = ArrayList()

    private val db = Firebase.database
    val referencia = db.getReference("reserva")
    private lateinit var messagesListener: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas)
        cargarDatos()
        asignarReferencias()
    }
    private fun cargarDatos(){
        listadodeReservas.clear()
        configurarRecycler()
    }

    private fun configurarRecycler(){
        messagesListener = object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listadodeReservas.clear()
                snapshot.children.forEach { item->
                    val personaRR:PersonaRR = PersonaRR(
                        item.child("nombres").getValue().toString(),
                        item.child("telefono").getValue().toString(),
                        item.child("email").getValue().toString(),
                        item.child("turno").getValue().toString(),
                        item.child("fecha").getValue().toString()

                    )
                    personaRR?.let{ listadodeReservas.add(it)}
                }
                Log.d("==>", listadodeReservas.size.toString())
                rvReservas.layoutManager = LinearLayoutManager(this@MisReservas)
                rvReservas.adapter = AdaptadorPersonalizadoLisReservas(listadodeReservas)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("==>",error.message)
            }
        }
        referencia.addValueEventListener(messagesListener)
    }
    private fun asignarReferencias(){
        rvReservas = findViewById(R.id.rvReservas)
    }
}