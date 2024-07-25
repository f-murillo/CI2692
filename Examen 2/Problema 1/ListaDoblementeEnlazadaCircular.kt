class ListaDoblementeEnlazadaCircular<T> : Iterable<T> {

    // sentinel: El nodo sentinela de la lista
    var sentinel = NodoLDEC<T>(null, null)

    /*
     * Metodo init de la clase 
     */
    init {
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    // size: El tamaño de la lista
    private var size: Int = 0

    /*
     * Devuelve el tamaño de la lista.
     */
    fun getSize(): Int {
        return size
    }

    /*
     * Verifica si la lista está vacía.
     */
    fun estaVacia(): Boolean {
        return size == 0
    }

    var cursor: NodoLDEC<T>? = null

    /*
     * Retorna el primer elemento de la lista.
     */
    fun primera(): T? {
        cursor = sentinel.next
        return cursor?.dato
    }

    /*
     * Devuelve el elemento actual de la lista.
     */
    fun actual(): T? {
        return cursor?.dato ?: sentinel.next?.dato
    }

    /*
     * Devuelve el siguiente elemento en la lista.
     */
    fun siguiente(): T? {
        cursor = cursor?.next ?: sentinel.next
        if (cursor == sentinel) {
            cursor = sentinel.next
        }
        return cursor?.dato
    }

    /*
     * Agrega un nuevo elemento al frente de la lista doblemente enlazada circular.
     */
    fun agregarAlFrente(dato: T) {
        // Se crea el nuevo nodo
        val nuevoNodo = NodoLDEC<T>(dato, sentinel.next)
        // Se cambia el nodo anterior del nuevo nodo para que apunte al nodo sentinela
        nuevoNodo.cambiarPrev(sentinel)
        // Se cambia el nodo anterior del nodo siguiente al nuevo nodo para que apunte al nuevo nodo
        sentinel.next!!.cambiarPrev(nuevoNodo)
        // Se cambia el nodo siguiente del nodo sentinela para que apunte al nuevo nodo
        sentinel.cambiarNext(nuevoNodo)
        // Se aumenta en uno el tamaño de la lista
        size++
    }

    /*
     * Agrega un nuevo elemento al final de la lista doblemente enlazada circular.
     */
    fun agregarAlFinal(dato: T): NodoLDEC<T> {
        val nuevoNodo = NodoLDEC<T>(dato, sentinel)
        nuevoNodo.cambiarPrev(sentinel.prev!!)
        sentinel.prev!!.cambiarNext(nuevoNodo)
        sentinel.cambiarPrev(nuevoNodo)

        if (size == 0) {
            // Si la lista está vacía, inicializa el cursor en el nuevo nodo.
            cursor = nuevoNodo
        }

        size++
        return nuevoNodo
    }

    /*
     * Elimina el último elemento de la lista doblemente enlazada circular.
     */
    fun eliminarUltimo() {
        // Si la lista esta vacia, se lanza una excepcion
        if (estaVacia()) throw IllegalStateException("La lista esta vacia")

        // Se cambia el nodo siguiente y anterior del nodo anterior al nodo sentinela
        sentinel.prev!!.prev!!.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel.prev!!.prev!!)
        // Se disminuye en uno el tamaño de la lista
        size--
    }

    /*
     * Agrega todos los elementos de otraLista al frente de la lista actual.
     */
    fun addAllAlFrente(otraLista: ListaDoblementeEnlazadaCircular<T>) {
        otraLista.forEach { this.agregarAlFrente(it) }
    }

    fun agregarDespuesDe(nodo: NodoLDEC<T>, dato: T): NodoLDEC<T> {
        val nuevoNodo = NodoLDEC(dato, nodo.next)
        nuevoNodo.cambiarPrev(nodo)
        nodo.next!!.cambiarPrev(nuevoNodo)
        nodo.cambiarNext(nuevoNodo)
        return nuevoNodo
    }

    /*
     * Filtra los elementos de la lista utilizando el predicado proporcionado y devuelve una nueva lista con los elementos filtrados.
     */
    fun filtrar(predicado: (T) -> Boolean): ListaDoblementeEnlazadaCircular<T> {
        val listaFiltrada = ListaDoblementeEnlazadaCircular<T>()
        this.forEach { if (predicado(it)) listaFiltrada.agregarAlFrente(it) }
        return listaFiltrada
    }

    /*
     * Devuelve una representación en forma de cadena de la lista doblemente enlazada circular.
     */
    override fun toString(): String {
        if (estaVacia()) {
            return "La lista está vacía"
        }
        var nodoActual = sentinel.next
        var lista = ""
        while (nodoActual != sentinel) {
            lista += "${nodoActual!!.dato} "
            nodoActual = nodoActual.next
        }
        return lista.trimEnd()
    }

    /*
     * Devuelve un iterador para recorrer los elementos de la clase
     */
    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var actual = sentinel.next

            /*
             * Verifica si hay más elementos en la lista.
             */
            override fun hasNext(): Boolean {
                return actual != sentinel
            }

            /*
             * Devuelve el siguiente elemento de la lista.
             */
            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException("No hay más elementos")
                val datoActual =
                        actual?.dato
                                ?: throw NoSuchElementException("No hay más elementos en la lista.")
                actual = actual?.next
                return datoActual
            }
        }
    }
}
