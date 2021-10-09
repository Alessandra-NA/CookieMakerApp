package com.example.cookiemakerapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        this.setTitle(Html.fromHtml("<font color=\"black\">Recetas</font>"))


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
        this.setTitle(Html.fromHtml("<font color=\"black\">Recetas</font>"))
        val fragment = fragments[0]
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
        IngredientesN.clear()
        currentFragment = "inicio"
    }

    fun changeNuevasRecetaFragment(){
        this.setTitle(Html.fromHtml("<font color=\"black\">Nueva Receta</font>"))
        val fragment= NuevaRecetaFragment(this.IngredientesN,username!!,this.NuevaRecetaNombre)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
        currentFragment = "nueva receta"
    }

    fun changeDetalleReceta(receta: Receta){
        this.setTitle(Html.fromHtml("<font color=\"black\">Visualizar Receta</font>"))
        val fragment = DetalleRecetaFragment(receta)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.commit()
        currentFragment = "detalle receta"
    }

    fun changeSeleccionarIngrediente(){
        this.setTitle(Html.fromHtml("<font color=\"black\">Elegir Ingredientes</font>"))
        val fragment = SeleccionarFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frlayoutMain,fragment)
        ft.addToBackStack(null)
        ft.commit()
        currentFragment = "seleccionar ingrediente"
    }
//nota: por alguna razon cuando retrocedes con el botn del cel, se muestran pantallas en un orden que no es -- SOLUCIONADO

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
//copiar elementos antes
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
        else if (currentFragment == "nueva receta") {
            IngredientesN.removeAll(IngredientesN)
            this.NuevaRecetaNombre=null
            changeRecetasFragment()
        }
        else super.onBackPressed()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(currentFragment == "inicio"){
            val recycListadoRecetas= findViewById<RecyclerView>(R.id.recycListadoRecetas)
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recycListadoRecetas.layoutManager = GridLayoutManager(this, 2)
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                recycListadoRecetas.layoutManager = GridLayoutManager(this, 1)
            }
        }
    }
}

