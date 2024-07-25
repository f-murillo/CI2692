/*
* Clase del TAD Cola
* La cola es implementada usando una secuencia matematica, la cual esta implementada con 
* una lista doblemente enlazada circular. Esta lista hace uso de objetos de tipo Nodo
*/
class Cola(){
    var contenido = ListaDoblementeEnlazadaCircular()
    // Metodo init
    init {
        println("Se creó una cola exitosamente")
    }

    // Métodos usados en la clase
    
    // Metodo que devuelve el numero de elementos que tiene la cola
    fun getSize(): Int {
        return contenido.getSize()
    }

    // Metodo que verifica si la cola esta vacia
    fun estaVacia(): Boolean {
        return contenido.estaVacia()
    }

    // Metodo que agrega un elemento a la cola
    fun encolar(dato: Int){ 
        contenido.agregarAlFinal(dato)
    }

    // Metodo que elimina un elemento de la cola, siguiendo la politica FIFO (First In - First Out)
    fun desencolar(){
        // Verificamos que la cola no este vacia
        if (estaVacia())
            throw IllegalStateException("La cola esta vacia")
        
        contenido.eliminarPrimero()
    }

    // Metodo que devuelve el primer elemento de la cola
    fun primero(): Nodo{
        // Verificamos que la cola no este vacia
        if (contenido.estaVacia())
            throw IllegalStateException("La cola esta vacia")
        
        // Se devuelve el dato del nodo que esta en la cabeza
        return contenido.primerElemento()
    }

    // Metodo que devuelve una representacion en String de la cola
    override fun toString(): String {
        return contenido.toString()
    }

    // clase interna para el Iterador
    inner class ColaIterator(ColaActual: Cola): Iterator<Int>{
        private var actual: Nodo? = ColaActual.primero()

        // Método que verifica si hay un siguiente elemento en la cola
        override fun hasNext(): Boolean {
            // Utilizamos esta comparación ya que al transformar a string, obtenemos el valor correcto (Actual), y no el almacenado
            return actual.toString() != "null"
        }

        // Método que devuelve el siguiente elemento en la cola
        override fun next(): Int {
            
            if (actual == null)
                throw NoSuchElementException("No hay más elementos en la cola")

            val dato = actual!!.dato
            actual = actual?.next
            return dato!!
        }
    }

    // Sobreescribimos el iterador
    fun iterator(): Iterator<Int> {
        return ColaIterator(this)
    } 

}

