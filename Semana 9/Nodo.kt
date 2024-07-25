/**
 * Clase que representa un nodo en una estructura de datos enlazada.
 *
 * @param T el tipo de dato que se almacenará en el nodo.
 * @property dato el dato almacenado en el nodo.
 * @property next el siguiente nodo en la estructura.
 * @property prev el nodo anterior al nodo actual.
 */
class Nodo<T>(val dato: T?, var next: Nodo<T>?) {
    // Variables que se usan en la clase 

    // prev: El nodo anterior al nodo actual
    var prev: Nodo<T>? = null

    // Metodos de la clase 

    // Metodo que cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: Nodo<T>?){
        this.next = nodo
    }

    // Metodo que cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: Nodo<T>?){
        this.prev = nodo
    }

    // Metodo que devuelve el dato que contiene el nodo
    fun obtenerDato(): T?{
        return this.dato
    }

    fun obtenerNodo(datoBuscado: T?): Nodo<T>?{
        if(this.dato == datoBuscado)
            return this

         return this.next?.obtenerNodo(datoBuscado)   
    }

    // Metodo que representa como el dato que contiene el nodo
    override fun toString(): String {
        return "${this.dato}"
    }
}