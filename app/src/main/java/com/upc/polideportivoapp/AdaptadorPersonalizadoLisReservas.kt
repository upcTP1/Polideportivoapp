package com.upc.polideportivoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersonalizadoLisReservas(listadeReservas: List<PersonaRR>):RecyclerView.Adapter<AdaptadorPersonalizadoLisReservas.MiViewHolder>() {
    private var listadeReservas:List<PersonaRR> = listadeReservas

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorPersonalizadoLisReservas.MiViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.listadoreservas,parent,false)
        return MiViewHolder(vista)
    }

    override fun onBindViewHolder(holder: AdaptadorPersonalizadoLisReservas.MiViewHolder,position: Int) {
        val  personaItem = listadeReservas[position]
        holder.filareservafecha.text = personaItem.fecha
        holder.filareservaturno.text = personaItem.turno
    }

    override fun getItemCount(): Int {
        return listadeReservas.size
    }
    class MiViewHolder(view:View):RecyclerView.ViewHolder(view){
        val filareservafecha:TextView = view.findViewById(R.id.filareservafecha)
        val filareservaturno:TextView = view.findViewById(R.id.filareservaturno)
    }

}