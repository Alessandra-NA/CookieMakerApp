package com.example.cookiemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente

class IngredienteListAdapter(private val ingredientesList: List<Ingrediente>): RecyclerView.Adapter<IngredienteListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtIngrediente: TextView
        init{
            txtIngrediente = view.findViewById(R.id.txtIngrediente)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingrediente, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: IngredienteListAdapter.ViewHolder, position: Int) {
        holder.txtIngrediente.text = ingredientesList[position].nombre
    }

    override fun getItemCount(): Int {
        return ingredientesList.size
    }

}