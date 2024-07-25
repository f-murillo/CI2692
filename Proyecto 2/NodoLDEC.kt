class NodoLDEC<T>(val dato: T?, var next: NodoLDEC<T>?) {
    // Variables que se usan en la clase

    // prev: El nodo anterior al nodo actual
    var prev: NodoLDEC<T>? = null

    // Metodos de la clase

    // Metodo que cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: NodoLDEC<T>?) {
        this.next = nodo
    }

    // Metodo que cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: NodoLDEC<T>?) {
        this.prev = nodo
    }

    // Metodo que devuelve el dato que contiene el nodo
    fun obtenerDato(): T? {
        return this.dato
    }

    fun obtenerNodo(datoBuscado: T?): NodoLDEC<T>? {
        if (this.dato == datoBuscado) return this

        return this.next?.obtenerNodo(datoBuscado)
    }

    // Metodo que representa como el dato que contiene el nodo
    override fun toString(): String {
        return "${this.dato}"
    }
}
