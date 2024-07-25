import kotlin.math.floor
/**
 * Representa una estructura de datos de tabla hash.
 *
 * @param n El tamaño de la tabla hash.
 */
class HashTable(n: Int){
    private val table = Array(n) { mutableListOf<List<String>>() }
    
    // Utilizamos el método de multiplicación como nuestra función de hash para generar el índice del valor
    fun hash(resultado: String) : Int{
        //  Utilizamos el numero áureo como constante K
        val numeroAureo = (Math.sqrt(5.0) - 1) / 2
        val parteFraccionaria = (resultado.toLong() * numeroAureo) % 1.0
        return floor(parteFraccionaria * table.size).toInt()
    }

    // Almacenamos el valor en la tabla
    fun guardar(palabra: String, resultado: String) {
        val index = hash(resultado)
        table[index].add(listOf(resultado, palabra))
    }
    
    // Buscamos el valor en la tabla
    fun buscar(resultado: String): MutableList<String> {
        
        val index = hash(resultado)
        val currentList = table[index]
        var result = mutableListOf<String>()
        currentList.forEach {
            if (it[0] == resultado){
                result.add(it[1])
            }
        }
        return result
    }
    
}