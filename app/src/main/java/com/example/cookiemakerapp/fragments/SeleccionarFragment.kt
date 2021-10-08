package com.example.cookiemakerapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import com.example.cookiemakerapp.adapter.Ingrediente2ListAdapter
import com.example.cookiemakerapp.adapter.RecetasListAdapter
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager

class SeleccionarFragment: Fragment() {
    interface OnIngredienteListener{
        fun onIngredienteA(ingredie:Ingrediente)
    }

    private var listener : OnIngredienteListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnIngredienteListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccionar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycListadoIngredientes = view.findViewById<RecyclerView>(R.id.recycListadoIngredientes2)
        recycListadoIngredientes.adapter =Ingrediente2ListAdapter(
            RecetasManager().getIngredientes(),this
        ){ ingrediente:Ingrediente ->
            Log.i("IngredienteFragment",ingrediente.nombre)

            listener?.onIngredienteA(ingrediente)
        }
//        val recycListadoRecetas= view.findViewById<RecyclerView>(R.id.recycListadoRecetas)
//        recycListadoRecetas.adapter = RecetasListAdapter(
//            RecetasManager().getInstance().getRecetas(),
//            this
//        ) { receta: Receta ->
//            Log.i("RecetaFragment", receta.nombre)
//            listener?.onSelect(receta)
//        }

    }
}