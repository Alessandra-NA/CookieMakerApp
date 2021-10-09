package com.example.cookiemakerapp.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import com.example.cookiemakerapp.adapter.RecetasListAdapter
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager

class RecetasFragment: Fragment() {
    interface OnProductSelectedListener {
        fun onSelect(receta : Receta)
        fun onClick()
    }
    private var listener : OnProductSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnProductSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recetas, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val butAgregar = view.findViewById<Button>(R.id.btnAgregarReceta)
        butAgregar.setOnClickListener(){_:View->
//            activity?.setTitle("Nueva Receta")
            listener?.onClick()
//            activity?.setTitle("Nueva Receta")
//            val ft = fragmentManager?.beginTransaction()
//            ft?.replace(R.id.frlayoutMain,NuevaRecetaFragment())
//            ft?.commit()
        }

        val recycListadoRecetas= view.findViewById<RecyclerView>(R.id.recycListadoRecetas)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) recycListadoRecetas.layoutManager = GridLayoutManager(context, 2)
        else recycListadoRecetas.layoutManager = GridLayoutManager(context, 1)
        recycListadoRecetas.adapter = RecetasListAdapter(RecetasManager().getInstance().getRecetas(),
            this
        ) { receta: Receta ->
            Log.i("RecetaFragment", receta.nombre)
            listener?.onSelect(receta)
        }
    }
}