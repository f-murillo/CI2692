/*
* Clase del TAD Pila
* La pila es implementada usando una secuencia matematica, la cual esta implementada con 
* una lista doblemente enlazada circular. Esta lista hace uso de objetos de tipo Nodo
*/
class Pila(){
    private var contenido = ListaDoblementeEnlazadaCircular()
    // Metodo init
    init {
        println("Se creó una pila exitosamente")
    }

    // Métodos usados en la clase
    
    // Metodo que devuelve el numero de elementos que tiene la pila
    fun getSize(): Int {
        return contenido.getSize()
    }

    // Metodo que verifica si la pila esta vacia
    fun estaVacia(): Boolean {
        return contenido.estaVacia()
    }

    // Metodo que agrega un elemento a la pila
    fun empilar(dato: Int){ 
        contenido.agregarAlFinal(dato)
    }

    // Metodo que elimina un elemento de la pila, siguiendo la politica LIFO (Last In - First Out)
    fun desempilar(){
        // Verificamos que la pila no este vacia
        if (estaVacia())
            throw IllegalStateException("La cola esta vacia")
        
        contenido.eliminarUltimo()
    }

    // Metodo que devuelve el último elemento insertado
    fun tope(): Nodo{
        // Verificamos que la pila no este vacia
        if (contenido.estaVacia())
            throw IllegalStateException("La pila esta vacia")
        
        // Se devuelve el dato del nodo que esta en la cabeza
        return contenido.ultimoElemento()
    }

    // Metodo que devuelve una representacion en String de la pila
    override fun toString(): String {
        return contenido.toString()
    }

    // clase interna para el Iterador
    inner class PilaIterator(PilaActual: Pila): Iterator<Int>{
        private var actual: Nodo? = PilaActual.tope()

        // Método que verifica si hay un siguiente elemento en la pila
        override fun hasNext(): Boolean {
            // Utilizamos esta comparación ya que al transformar a string, obtenemos el valor correcto (Actual), y no el almacenado
            return actual.toString() != "null"
        }

        // Método que devuelve el siguiente elemento en la pila
        override fun next(): Int {
            if (actual == null)
                throw NoSuchElementException("No hay más elementos en la pila")

            val dato = actual!!.dato
            actual = actual?.prev
            return dato!!
        }
    }

    // Sobreescribimos el iterador
    fun iterator(): Iterator<Int> {
        return PilaIterator(this)
    } 
}

