package com.upc.polideportivoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MainFutbol : AppCompatActivity(){

    private val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futbol)

        val carousel: ImageCarousel = findViewById(R.id.carousel)
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha))
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha2))
        list.add(CarouselItem(imageDrawable = R.drawable.photo_cancha3))
        carousel.addData(list)
    }
}