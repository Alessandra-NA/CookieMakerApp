package com.example.cookiemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta

class IngredienteListAdapter(private val ingredientesList: List<Ingrediente>): RecyclerView.Adapter<IngredienteListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val txtIngrediente: TextView
        init{
            txtIngrediente = view.findViewById(R.id.txtIngrediente)
        }
    }

//    class ViewHolder(view: View, val listener : (Receta) -> Unit, val recetasList : List<Receta>):
//        RecyclerView.ViewHolder(view), View.OnClickListener
//    {
//        val imgReceta: ImageView
//        val txtTituloRecetaItem: TextView
//        val txtAutorRecetaItem: TextView
//        init{
//            imgReceta = view.findViewById(R.id.imgReceta)
//            txtTituloRecetaItem = view.findViewById(R.id.txtTituloRecetaItem)
//            txtAutorRecetaItem = view.findViewById(R.id.txtAutorRecetaItem)
//            view.setOnClickListener(this)
//        }
//        override fun onClick(v: View?) {
//            listener(recetasList[adapterPosition])
//        }
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingrediente, parent, false)
        val viewHolder = IngredienteListAdapter.ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: IngredienteListAdapter.ViewHolder, position: Int) {
        holder.txtIngrediente.text = ingredientesList[position].nombre
    }

    override fun getItemCount(): Int {
        return ingredientesList.size
    }

}