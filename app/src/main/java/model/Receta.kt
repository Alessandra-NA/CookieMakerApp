package pe.edu.ulima.pm.ulgamestore.model

data class Receta(
    val id : Int,
    val nombre : String,
    val usuario : String,
    val ingredientes : List<Ingrediente> = arrayListOf()
)