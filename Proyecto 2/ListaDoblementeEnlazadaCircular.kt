class ListaDoblementeEnlazadaCircular<T> : Iterable<T> {

    // sentinel: El nodo sentinela de la lista
    var sentinel = NodoLDEC<T>(null, null)

    /**
     * Método init de la clase ListaDoblementeEnlazadaCircular.
     * 
     * Este método se encarga de inicializar la lista doblemente enlazada circular.
     * Cambia el nodo siguiente y anterior del nodo sentinela para que apunte a sí mismo.
     */
    init {
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    // size: El tamaño de la lista
    private var size: Int = 0

    /**
     * Devuelve el tamaño de la lista.
     *
     * @return El tamaño de la lista.
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

    var cursor: NodoLDEC<T>? = null

    /**
     * Retorna el primer elemento de la lista.
     *
     * @return El primer elemento de la lista o null si la lista está vacía.
     */
    fun primera(): T? {
        cursor = sentinel.next
        return cursor?.dato
    }

    /**
     * Devuelve el elemento actual de la lista.
     *
     * @return El elemento actual de la lista o null si la lista está vacía.
     */
    fun actual(): T? {
        return cursor?.dato ?: sentinel.next?.dato
    }

    /**
     * Devuelve el siguiente elemento en la lista.
     * Si el cursor está en el último elemento, se mueve al primer elemento de la lista.
     * @return El siguiente elemento en la lista o null si la lista está vacía.
     */
    fun siguiente(): T? {
        cursor = cursor?.next ?: sentinel.next
        if (cursor == sentinel) {
            cursor = sentinel.next
        }
        return cursor?.dato
    }

    /**
     * Agrega un nuevo elemento al frente de la lista doblemente enlazada circular.
     *
     * @param dato El dato a agregar al frente de la lista.
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

    /**
     * Agrega un nuevo elemento al final de la lista doblemente enlazada circular.
     *
     * @param dato El elemento a agregar.
     */
    fun agregarAlFinal(dato: T) {
        val nuevoNodo = NodoLDEC<T>(dato, sentinel)
        nuevoNodo.cambiarPrev(sentinel.prev!!)
        sentinel.prev!!.cambiarNext(nuevoNodo)
        sentinel.cambiarPrev(nuevoNodo)

        if (size == 0) {
            // Si la lista está vacía, inicializa el cursor en el nuevo nodo.
            cursor = nuevoNodo
        }

        size++
    }

    /**
     * Elimina el último elemento de la lista doblemente enlazada circular.
     * Si la lista está vacía, se lanza una excepción.
     * Después de eliminar el último elemento, se actualizan los enlaces de los nodos
     * anterior y siguiente al nodo sentinela.
     * También se disminuye en uno el tamaño de la lista.
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

    /**
     * Agrega todos los elementos de otraLista al frente de la lista actual.
     *
     * @param otraLista la lista de elementos a agregar al frente.
     */
    fun addAllAlFrente(otraLista: ListaDoblementeEnlazadaCircular<T>) {
        otraLista.forEach { this.agregarAlFrente(it) }
    }

    /**
     * Filtra los elementos de la lista utilizando el predicado proporcionado y devuelve una nueva lista con los elementos filtrados.
     *
     * @param predicado el predicado que se utilizará para filtrar los elementos de la lista.
     * @return una nueva instancia de ListaDoblementeEnlazadaCircular<T> que contiene los elementos filtrados.
     */
    fun filtrar(predicado: (T) -> Boolean): ListaDoblementeEnlazadaCircular<T> {
        val listaFiltrada = ListaDoblementeEnlazadaCircular<T>()
        this.forEach { if (predicado(it)) listaFiltrada.agregarAlFrente(it) }
        return listaFiltrada
    }

    /**
     * Devuelve una representación en forma de cadena de la lista doblemente enlazada circular.
     * Si la lista está vacía, se devuelve el mensaje "La lista está vacía".
     * Si la lista contiene elementos, se devuelve una cadena que representa los elementos de la lista
     * separados por comas y encerrados entre paréntesis.
     *
     * @return una representación en forma de cadena de la lista doblemente enlazada circular.
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
            } else {
                lista += "${nodoActual.dato}"
            }
            nodoActual = nodoActual.next
        }
        lista += ")"
        return lista
    }

    /**
     * Devuelve un iterador para recorrer los elementos de la lista doblemente enlazada circular.
     *
     * @return un iterador que permite recorrer los elementos de la lista.
     */
    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var actual = sentinel.next

            /**
             * Verifica si hay más elementos en la lista.
             *
             * @return true si hay más elementos, false de lo contrario.
             */
            override fun hasNext(): Boolean {
                return actual != sentinel
            }

            /**
             * Devuelve el siguiente elemento de la lista.
             *
             * @return el siguiente elemento de la lista.
             * @throws NoSuchElementException si no hay más elementos en la lista.
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
