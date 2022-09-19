package com.upc.polideportivoapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MainFutbol : AppCompatActivity(){

    private val list = mutableListOf<CarouselItem>()
    private lateinit var btnReservar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futbol)

        val carousel: ImageCarousel = findViewById(R.id.carousel)
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha))
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha2))
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha3))
        carousel.addData(list)

        // popupfutbol
        /*
       btnReservar = findViewById(R.id.btnReservar)
        btnReservar.setOnClickListener {
            val dialogBindings = layoutInflater.inflate(R.layout.activity_registro_reserva,null)

            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBindings)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()


        } */
        btnReservar = findViewById(R.id.btnReservar)
        btnReservar.setOnClickListener {
            val intent = Intent(this,RegistroReserva::class.java)
            startActivity(intent)
        }
    }
}