package com.example.cookiemakerapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import com.example.cookiemakerapp.adapter.IngredienteListAdapter
import com.example.cookiemakerapp.adapter.RecetasListAdapter
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager
import java.text.FieldPosition

class DetalleRecetaFragment(val receta: Receta): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detallereceta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.txtNameReceta).setText(receta.nombre)
        val recycListadoIngredientes1= view.findViewById<RecyclerView>(R.id.recycListadoIngredientes1)
        recycListadoIngredientes1.adapter = IngredienteListAdapter(receta.ingredientes)
    }
}