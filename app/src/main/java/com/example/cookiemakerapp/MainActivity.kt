package com.example.cookiemakerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cookiemakerapp.fragments.RecetasFragment
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager

class MainActivity : AppCompatActivity() {
    var username: String? = null
    var recetasManager: RecetasManager? = null
    private var fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        username = intent.getBundleExtra("datalogin")?.getString("username")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(username)

        fragments.add(RecetasFragment())
        recetasManager = RecetasManager().getInstance()
        this.setTitle("Recetas")

        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frlayoutMain,fragment)
        ft.commit()
        // añadiendo recetas de ejemplo
        var ingredientes = ArrayList<Ingrediente>()
        ingredientes.add(Ingrediente("ingrediente 1"))
        ingredientes.add(Ingrediente("ingrediente 2"))
        var r1: Receta = Receta(1,"Recetita 1", "Ejemplo", ingredientes)
        var r2: Receta = Receta(2,"Recetita 2", "Ejemplo", ingredientes)
        recetasManager?.addReceta(r1)
        recetasManager?.addReceta(r2)
    }

    fun changeRecetasFragment(){
        this.setTitle("Recetas")
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
    }
}