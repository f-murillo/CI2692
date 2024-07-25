/*
* Clase Nodo que sera usada en la clase ArbolDeDecision
* Representa un nodo del arbol de decision binario para la decodificacion de codigo morse
*/
public class NodoAD(val valor: Char, val codigo: String) {
    // Hijo izquierdo del nodo.
    var izq: NodoAD? = null

    // Hijo derecho del nodo.
    var der: NodoAD? = null

    // Padre del nodo.
    var padre: NodoAD? = null

    // Metodo que retorna una representacion en String del Nodo.
    override fun toString(): String {
        var str = "NodoAD($valor"
        if (izq != null) {
            str += ", $izq"
        }
        if (der != null) {
            str += ", $der"
        }
        return str + ")"
    }
}