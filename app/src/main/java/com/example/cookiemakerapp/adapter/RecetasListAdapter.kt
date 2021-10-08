package com.example.cookiemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulgamestore.model.Receta
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookiemakerapp.R

class RecetasListAdapter(
    private val recetasList: List<Receta>,
    private val fragment : Fragment,
    private val listener : (Receta) -> Unit
): RecyclerView.Adapter<RecetasListAdapter.ViewHolder>(){


    class ViewHolder(view: View, val listener : (Receta) -> Unit,val recetasList : List<Receta>):
        RecyclerView.ViewHolder(view), View.OnClickListener
    {
        val imgReceta: ImageView
        val txtTituloRecetaItem: TextView
        val txtAutorRecetaItem: TextView
        init{
            imgReceta = view.findViewById(R.id.imgReceta)
            txtTituloRecetaItem = view.findViewById(R.id.txtTituloRecetaItem)
            txtAutorRecetaItem = view.findViewById(R.id.txtAutorRecetaItem)
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            listener(recetasList[adapterPosition])
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        val viewHolder = RecetasListAdapter.ViewHolder(view, listener, recetasList)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecetasListAdapter.ViewHolder, position: Int) {
        holder.txtTituloRecetaItem.text = recetasList[position].nombre
        holder.txtAutorRecetaItem.text = recetasList[position].usuario
        Glide.with(fragment)
            .load(recetasList[position].imagen)
            .fitCenter()
            .into(holder.imgReceta)
    }

    override fun getItemCount(): Int {
        return recetasList.size
    }

}