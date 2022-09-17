package com.upc.polideportivoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MainActivity : AppCompatActivity() {

    private lateinit var btnIngresoFutbol:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnIngresoFutbol = findViewById(R.id.btnIngresoFutbol)
        btnIngresoFutbol.setOnClickListener{
            val intent = Intent(this,MainFutbol::class.java)
            startActivity(intent)
        }
       }

}