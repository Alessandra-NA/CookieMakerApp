package com.example.cookiemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pe.edu.ulima.pm.ulgamestore.model.Receta
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R

class RecetasListAdapter(private val recetasList: List<Receta>): RecyclerView.Adapter<RecetasListAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtTituloRecetaItem: TextView
        init{
            txtTituloRecetaItem = view.findViewById(R.id.txtTituloRecetaItem)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecetasListAdapter.ViewHolder, position: Int) {
        holder.txtTituloRecetaItem.text = recetasList[position].nombre
    }

    override fun getItemCount(): Int {
        return recetasList.size
    }

}