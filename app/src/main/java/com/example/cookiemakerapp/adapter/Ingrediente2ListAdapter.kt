package com.example.cookiemakerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente


class Ingrediente2ListAdapter(
    private val IngredieList: List<Ingrediente>,
    private val fragment : Fragment,
    private val listener : (Ingrediente) -> Unit
) : RecyclerView.Adapter<Ingrediente2ListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val listener: (Ingrediente)->Unit , val IngredieList: List<Ingrediente>):
        RecyclerView.ViewHolder(view),View.OnClickListener{
        val txtIngrediente: TextView
        init{
            txtIngrediente = view.findViewById(R.id.txtIngrediente)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(IngredieList[adapterPosition])
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
        val viewHolder = Ingrediente2ListAdapter.ViewHolder(view,listener,IngredieList)

        return viewHolder

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        //        val viewHolder = ViewHolder(view, listener, recetasList)
        //        return viewHolder
    }

//    override fun onBindViewHolder(holder: IngredienteListAdapter.ViewHolder, position: Int) {
//        holder.txtIngrediente.text = ingredientesList[position].nombre
//    }

    override fun onBindViewHolder(holder: Ingrediente2ListAdapter.ViewHolder, position: Int) {
        //holder.txtTituloRecetaItem.text = recetasList[position].nombre
        //        holder.txtAutorRecetaItem.text = recetasList[position].usuario
        //        Glide.with(fragment)
        //            .load(recetasList[position].imagen)
        //            .fitCenter()
        //            .into(holder.imgReceta)

        holder.txtIngrediente.text=IngredieList[position].nombre

    }


    override fun getItemCount(): Int {
        return this.IngredieList.size
    }


}