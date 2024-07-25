class TablaHash(tamaño: Int) {
    private val tamaño = tamaño
    private val tabla = Array<NodoLDEC<Int>?>(tamaño) { null }

    private fun hash(dato: Int): Int {
        return dato % tamaño
    }

    fun insertar(dato: Int, nodo: NodoLDEC<Int>): NodoLDEC<Int> {
        val indice = hash(dato)
        tabla[indice] = nodo
        return nodo
    }

    fun obtener(dato: Int): NodoLDEC<Int>? {
        val indice = hash(dato)
        return tabla[indice]
    }
}