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

class NuevaRecetaFragment(var Ingred: List<Ingrediente>,val username:String,var name:String?, var cantidad: Int?): Fragment() {
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
        var imagenes = arrayOf("https://i.ibb.co/7X4J65H/g1.jpg",
            "https://i.ibb.co/JQDwJFY/g2.jpg",
            "https://i.ibb.co/NV57Vsj/g3.jpg",
            "https://i.ibb.co/2SWxJC1/g4.jpg",
            "https://i.ibb.co/LQrr1vz/g5.jpg",
            "https://i.ibb.co/TMGhH30/g6.jpg",
            "https://i.ibb.co/WspnXgT/g7.jpg",
            "https://i.ibb.co/V3NG8GV/g8.jpg",
            "https://i.ibb.co/yPKDwRV/g9.jpg",
            "https://i.ibb.co/HDjfZf2/g10.jpg",
        )
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
                cantidad = cantidad?.plus(1)
                RecetaNueva = Receta(cantidad!!,view.findViewById<EditText>(R.id.RecetaName).text.toString(),username, listita,imagenes.random())
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