package com.example.cookiemakerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cookiemakerapp.fragments.DetalleRecetaFragment
import com.example.cookiemakerapp.fragments.NuevaRecetaFragment
import com.example.cookiemakerapp.fragments.RecetasFragment
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager

class MainActivity : AppCompatActivity(), RecetasFragment.OnProductSelectedListener {
    var username: String? = null
    var recetasManager: RecetasManager? = null

    private var fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        username = intent.getBundleExtra("datalogin")?.getString("username")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(username)

        if(fragments.size == 0){
            fragments.add(RecetasFragment())
            fragments.add(NuevaRecetaFragment())
        }

        recetasManager = RecetasManager().getInstance()
        this.setTitle("Recetas")

        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frlayoutMain,fragment)
        ft.commit()
        // añadiendo recetas de ejemplo
        if(RecetasManager().getInstance().getRecetas().size == 0) {
            var r1: Receta = Receta(1,"Recetita 1", username!!, listOf(RecetasManager().getInstance().getIngredientes().get(0), RecetasManager().getInstance().getIngredientes().get(1)),"https://i.ibb.co/7X4J65H/g1.jpg")
            var r2: Receta = Receta(2,"Recetita 2", username!!, listOf(RecetasManager().getInstance().getIngredientes().get(0), RecetasManager().getInstance().getIngredientes().get(1)),"https://i.ibb.co/JQDwJFY/g2.jpg")
            recetasManager?.addReceta(r1)
            recetasManager?.addReceta(r2)
        }
    }

    fun changeRecetasFragment(){
        this.setTitle("Recetas")
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
    }

    fun changeNuevasRecetaFragment(){
        val fragment = fragments[1]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
    }

    fun changeDetalleReceta(receta: Receta){
        this.setTitle("Visualizar Receta")
        val fragment = DetalleRecetaFragment(receta)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
    override fun onSelect(receta: Receta) {
        changeDetalleReceta(receta)
    }

}


/*var list = arrayOf("https://i.ibb.co/7X4J65H/g1.jpg",
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
  println(list.random())
        */