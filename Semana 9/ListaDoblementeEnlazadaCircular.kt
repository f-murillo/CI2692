/**
 * Clase que representa una Lista Doblemente Enlazada Circular.
 * Esta clase implementa la interfaz Iterable para permitir la iteración sobre los elementos de la lista.
 *
 * @param T el tipo de dato de los elementos de la lista.
 */
class ListaDoblementeEnlazadaCircular<T>: Iterable<T>{

    // sentinel: El nodo sentinela de la lista
    var sentinel = Nodo<T>(null,null)

    /**
     * Constructor de la clase ListaDoblementeEnlazadaCircular.
     * Inicializa la lista estableciendo el nodo sentinela y configurando sus enlaces para que apunten a sí mismo.
     */
    init {
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    // size: El tamaño de la lista
    private var size: Int = 0

    /**
     * Devuelve el tamaño actual de la lista.
     *
     * @return el tamaño de la lista.
     */
    fun getSize(): Int {
        return size
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false de lo contrario.
     */
    fun estaVacia(): Boolean {
        return size == 0
    }

    /**
     * Agrega un nuevo nodo con el dato dado al principio de la lista.
     *
     * @param dato el dato a agregar.
     */
    fun agregarAlFrente(dato: T){
        val nuevoNodo = Nodo<T>(dato, sentinel.next)
        nuevoNodo.cambiarPrev(sentinel)
        sentinel.next!!.cambiarPrev(nuevoNodo)
        sentinel.cambiarNext(nuevoNodo)
        size++
    }

    /**
     * Agrega un nuevo nodo con el dato dado al final de la lista.
     *
     * @param dato el dato a agregar.
     */
    fun agregarAlFinal(dato: T){
        val nuevoNodo = Nodo<T>(dato, sentinel)
        nuevoNodo.cambiarPrev(sentinel.prev!!)
        sentinel.prev!!.cambiarNext(nuevoNodo)
        sentinel.cambiarPrev(nuevoNodo)
        size++
    }

    /**
     * Elimina el último nodo de la lista.
     * Si la lista está vacía, se lanza una excepción.
     *
     * @throws IllegalStateException si la lista está vacía.
     */
    fun eliminarUltimo(){
        if (estaVacia())
            throw IllegalStateException("La lista está vacía")
        
        sentinel.prev!!.prev!!.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel.prev!!.prev!!)
        size--
    }

    /**
     * Agrega todos los elementos de otra lista al frente de esta lista.
     *
     * @param otraLista la lista de la cual se agregarán los elementos.
     */
    fun addAllAlFrente(otraLista: ListaDoblementeEnlazadaCircular<T>){
        otraLista.forEach{
            this.agregarAlFrente(it)
        }
    }

    /**
     * Filtra los elementos de la lista según el predicado dado y devuelve una nueva lista con los elementos filtrados.
     *
     * @param predicado el predicado que se utilizará para filtrar los elementos.
     * @return una nueva lista con los elementos filtrados.
     */
    fun filtrar(predicado: (T) -> Boolean): ListaDoblementeEnlazadaCircular<T> {
        val listaFiltrada = ListaDoblementeEnlazadaCircular<T>()
        this.forEach { if (predicado(it)) listaFiltrada.agregarAlFrente(it) }
        return listaFiltrada
    }

    /**
     * Devuelve una representación en forma de cadena de la lista.
     *
     * @return una cadena que representa la lista.
     */
    override fun toString(): String {
        if (estaVacia()) {
            return "La lista está vacía"
        }
        var nodoActual = sentinel.next
        var lista = "("
        while (nodoActual != sentinel) {
            if (nodoActual!!.next != sentinel) {
                lista += "${nodoActual.dato}, "
            }
            else {
                lista += "${nodoActual.dato}"
            }
            nodoActual = nodoActual.next
        }
        lista += ")"
        return lista
    }

    /**
     * Devuelve un iterador sobre los elementos de la lista.
     *
     * @return un iterador de la lista.
     */
    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var actual = sentinel.next

            override fun hasNext(): Boolean {
                return actual != sentinel
            }

            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException("No hay más elementos")
                val datoActual = actual?.dato ?: throw NoSuchElementException("No hay más elementos en la lista.")
                actual = actual?.next
                return datoActual
            }
        }
    }

}
