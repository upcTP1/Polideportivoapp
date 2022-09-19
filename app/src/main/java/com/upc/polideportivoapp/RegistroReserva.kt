package com.upc.polideportivoapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistroReserva : AppCompatActivity() {

        private lateinit var dtDatePicker1:EditText
        private lateinit var spinner1:Spinner
        private lateinit var txtNombresRR:EditText
        private lateinit var txtTelefonoRR:EditText
        private lateinit var txtEmailRR:EditText
        private lateinit var btnGuardarRR:Button
        private val db = Firebase.database



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_reserva)
        asignarReferencias();


        //calendario
        dtDatePicker1 = findViewById(R.id.edtDatePicker1)
        dtDatePicker1.setOnClickListener { showDatePickerDialog()
        }

        // spinner
        spinner1 = findViewById(R.id.spinner1)
        ArrayAdapter.createFromResource(
            this,R.array.horas,
            android.R.layout.simple_spinner_dropdown_item)
            .also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner1.adapter = adapter
            }

    }
    //calendario
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{ day , month, year -> onDateSelected(day,month,year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }
    fun onDateSelected(day:Int, month:Int, year:Int){
        dtDatePicker1.setText("$day-$month-$year ")


    }
    //guardar
    private fun asignarReferencias(){
        txtNombresRR =findViewById(R.id.txtNombresRR)
        txtTelefonoRR = findViewById(R.id.txtTelefonoRR)
        txtEmailRR = findViewById(R.id.txtEmailRR)
        btnGuardarRR = findViewById(R.id.btnGuardarRR)
        spinner1 = findViewById(R.id.spinner1)
        dtDatePicker1 = findViewById(R.id.edtDatePicker1)
        btnGuardarRR.setOnClickListener {
            guardar();
        }
    }
    private fun guardar(){
        val referencia = db.getReference("reserva")
        val nombresRR = txtNombresRR.text.toString()
        val telefonoRR = txtTelefonoRR.text.toString()
        val emailRR = txtEmailRR.text.toString()
        val spinner1 = spinner1.toString()
        val dtDatePicker1= dtDatePicker1.toString()

        val persona = PersonaRR(nombresRR, telefonoRR,emailRR,spinner1,dtDatePicker1)

        referencia.child(referencia.push().key.toString()).setValue(persona)
        finish()

    }
}