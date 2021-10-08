package com.example.cookiemakerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cookiemakerapp.fragments.DetalleRecetaFragment
import com.example.cookiemakerapp.fragments.NuevaRecetaFragment
import com.example.cookiemakerapp.fragments.RecetasFragment
import com.example.cookiemakerapp.fragments.SeleccionarFragment
import pe.edu.ulima.pm.ulgamestore.model.Ingrediente
import pe.edu.ulima.pm.ulgamestore.model.Receta
import pe.edu.ulima.pm.ulgamestore.model.RecetasManager

class MainActivity : AppCompatActivity(),
    RecetasFragment.OnProductSelectedListener,
    NuevaRecetaFragment.OnButtonListener,
        SeleccionarFragment.OnIngredienteListener
{
    var username: String? = null
    var recetasManager: RecetasManager? = null
    var IngredientesN = ArrayList<Ingrediente>()
    var NuevaRecetaNombre : String? = null
    var currentFragment: String = "inicio"
    private var fragments = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        username = intent.getBundleExtra("datalogin")?.getString("username")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(username)

        if(fragments.size == 0){
            fragments.add(RecetasFragment())
        }
        recetasManager = RecetasManager().getInstance()
        this.setTitle("Recetas")
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frlayoutMain,fragment)
        ft.commit()
        // añadiendo recetas de ejemplo
        if(RecetasManager().getInstance().getRecetas().size == 0) {
            var r1: Receta = Receta(1,"Recetita 1", "Ejemplo", listOf(RecetasManager().getInstance().getIngredientes().get(0), RecetasManager().getInstance().getIngredientes().get(1)),"https://i.ibb.co/7X4J65H/g1.jpg")
            var r2: Receta = Receta(2,"Recetita 2", "Ejemplo", listOf(RecetasManager().getInstance().getIngredientes().get(0), RecetasManager().getInstance().getIngredientes().get(1)),"https://i.ibb.co/JQDwJFY/g2.jpg")
            recetasManager?.addReceta(r1)
            recetasManager?.addReceta(r2)
        }
    }

    fun changeRecetasFragment(){
        this.setTitle("Recetas")
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.addToBackStack(null)
        ft.commit()
        IngredientesN.clear()
        currentFragment = "inicio"
    }

    fun changeNuevasRecetaFragment(){
        this.setTitle("Nueva Receta")
        val fragment= NuevaRecetaFragment(this.IngredientesN,username!!,this.NuevaRecetaNombre, recetasManager?.getInstance()?.getRecetas()?.size)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
        currentFragment = "nueva receta"

    }

    fun changeDetalleReceta(receta: Receta){
        this.setTitle("Visualizar Receta")
        val fragment = DetalleRecetaFragment(receta)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
        currentFragment = "detalle receta"
    }

    fun changeSeleccionarIngrediente(){

        this.setTitle("Elegir Ingrediente")
        val fragment = SeleccionarFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.addToBackStack(null)
        ft.commit()
        currentFragment = "seleccionar ingrediente"
    }
    override fun onSelect(receta: Receta) {
        changeDetalleReceta(receta)
    }
    override fun onClick() {
        changeNuevasRecetaFragment()
    }
    override fun OnclickIngrediente(name:String?) {
        this.NuevaRecetaNombre=name
        changeSeleccionarIngrediente()
    }
    override fun OnClickGuardar(recetaNueva: Receta) {
        recetasManager?.addReceta(recetaNueva)
        IngredientesN.removeAll(IngredientesN)
        this.NuevaRecetaNombre=null
        changeRecetasFragment()
    }
    override fun onIngredienteA(ingredie: Ingrediente) {
       IngredientesN.add(ingredie)
        changeNuevasRecetaFragment()
    }
    override fun onBackPressed() {
        if(currentFragment == "inicio") {
            intent.setClass(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else if (currentFragment == "detalle receta") changeRecetasFragment()
        else if (currentFragment == "nueva receta") changeRecetasFragment()
        else super.onBackPressed()
    }
}
