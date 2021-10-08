package pe.edu.ulima.pm.ulgamestore.model

class RecetasManager {

    private val mIngredientes = arrayListOf<Ingrediente>()
    private val mRecetas = arrayListOf<Receta>()

    init {
        mIngredientes.add(Ingrediente("Mantequilla"))
        mIngredientes.add(Ingrediente("Azucar"))
        mIngredientes.add(Ingrediente("Harina"))
        mIngredientes.add(Ingrediente("Vainilla"))
        mIngredientes.add(Ingrediente("Polvo de Hornear"))
        //una comida bien hecha necesita amor de abuelita o de madre
        mIngredientes.add(Ingrediente("Amor de abuela"))
    }

    companion object {
        private var instance : RecetasManager? = null
    }

    fun getInstance() : RecetasManager {
        if (instance == null) {
            instance = RecetasManager()
        }
        return instance!!
    }

    fun getRecetas() : List<Receta> {
        return mRecetas
    }

    fun addReceta(receta : Receta) {
        mRecetas.add(receta)
    }

    fun getReceta(id : Int?) : Receta? {
        return mRecetas[id!!]
    }

    fun getIngredientes() : List<Ingrediente> {
        return mIngredientes
    }


}