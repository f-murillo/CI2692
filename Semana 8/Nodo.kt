/* 
* Clase Nodo que se usara para la implementacion de la lista doblemente enlazada circular
  Parametro: - dato: el dato que va a contener el nodo 
             - next: el nodo siguiente 
  Se usa como segundo parametro el nodo siguiente para las operaciones de la lista doblemente enlazada circular
*/
class Nodo(val dato: Int?, var next: Nodo?) {
    
    // Variables que se usan en la clase 

    // prev: El nodo anterior al nodo actual
    var prev: Nodo? = null

    // Metodos de la clase 

    // Metodo que cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: Nodo?){
        this.next = nodo
    }

    // Metodo que cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: Nodo?){
        this.prev = nodo
    }

    // Metodo que devuelve el dato que contiene el nodo
    fun obtenerDato(): Int?{
        return this.dato
    }

    fun obtenerNodo(datoBuscado: Int): Nodo?{
        if(this.dato == datoBuscado)
            return this

         return this.next?.obtenerNodo(datoBuscado)   
    }

    // Metodo que representa como el dato que contiene el nodo
    override fun toString(): String {
        return "${this.dato}"
    }
}