package com.example.cookiemakerapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiemakerapp.R
import com.example.cookiemakerapp.adapter.IngredienteListAdapter
import com.example.cookiemakerapp.adapter.RecetasListAdapter
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager
import java.util.ArrayList

class NuevaRecetaFragment(val Ingred: List<Ingrediente>,val username:String,var name:String?): Fragment() {


    interface OnButtonListener{
        fun OnclickIngrediente(name:String?)
        fun OnClickGuardar(recetita: Receta)
    }

    private var listener :OnButtonListener?=null
    private var RecetaNueva : Receta? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnButtonListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nuevareceta,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(name!=null){
            view.findViewById<EditText>(R.id.RecetaName).setText(name)
        }


        val butGuardar = view.findViewById<Button>(R.id.buttonGuardar)
        butGuardar.setOnClickListener{
            if(view.findViewById<EditText>(R.id.RecetaName).text.toString() ==""  || Ingred.isEmpty() ){
                //do nothing
                Toast.makeText(context, "Falta informacion", Toast.LENGTH_SHORT).show()
            }else {

                var listita = ArrayList<Ingrediente>()
                for(i in Ingred){
                    listita.add(i)
                }
                RecetaNueva = Receta(3,view.findViewById<EditText>(R.id.RecetaName).text.toString(),username, listita,"https://i.ibb.co/JQDwJFY/g2.jpg")
                listener?.OnClickGuardar(RecetaNueva!!)
            }

        }

        val butIngredientes = view.findViewById<Button>(R.id.buttonIngredientes)
        butIngredientes.setOnClickListener{
            this.name = view.findViewById<EditText>(R.id.RecetaName).text.toString()
            listener?.OnclickIngrediente(this.name)
        }

        val recycListadoIngredi= view.findViewById<RecyclerView>(R.id.recycListadoIngredientes2)


        recycListadoIngredi.adapter = IngredienteListAdapter(Ingred)

    }
}