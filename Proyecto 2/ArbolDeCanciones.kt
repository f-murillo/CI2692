/**
 * Clase que representa un nodo en un árbol de canciones.
 *
 * @property cancion La canción almacenada en el nodo.
 * @property padre El nodo padre del nodo actual.
 * @property izquierdo El nodo hijo izquierdo del nodo actual.
 * @property derecho El nodo hijo derecho del nodo actual.
 */
class Nodo(var cancion: Cancion) {
    var padre: Nodo? = null
    var izquierdo: Nodo? = null
    var derecho: Nodo? = null
}

/** Clase que representa un árbol de canciones. */
class ArbolDeCanciones {
    var raiz: Nodo? = null

    /**
     * Inserta una canción en el árbol.
     * @param cancion La canción a insertar.
     */
    fun insertar(cancion: Cancion) {
        val nuevoNodo = Nodo(cancion)
        if (raiz == null) {
            raiz = nuevoNodo
        } else {
            var actual = raiz
            var padre: Nodo? = null
            while (actual != null) {
                padre = actual
                if (cancion.interprete < actual.cancion.interprete ||
                                (cancion.interprete == actual.cancion.interprete &&
                                        cancion.titulo < actual.cancion.titulo)
                ) {
                    actual = actual.izquierdo
                } else if (cancion.interprete > actual.cancion.interprete ||
                                (cancion.interprete == actual.cancion.interprete &&
                                        cancion.titulo > actual.cancion.titulo)
                ) {
                    actual = actual.derecho
                } else {
                    // La canción ya existe en el árbol, no se permite duplicados.
                    return
                }
            }
            if (padre != null) {
                if (cancion.interprete < padre.cancion.interprete ||
                                (cancion.interprete == padre.cancion.interprete &&
                                        cancion.titulo < padre.cancion.titulo)
                ) {
                    padre.izquierdo = nuevoNodo
                } else {
                    padre.derecho = nuevoNodo
                }
                nuevoNodo.padre = padre
            }
        }
    }

    /**
     * Elimina una canción del árbol.
     * @param cancion La canción a eliminar.
     */
    fun eliminar(cancion: Cancion) {
        var actual = raiz
        while (actual != null &&
                (actual.cancion.interprete != cancion.interprete ||
                        actual.cancion.titulo != cancion.titulo)) {
            actual =
                    if (cancion.interprete < actual.cancion.interprete ||
                                    (cancion.interprete == actual.cancion.interprete &&
                                            cancion.titulo < actual.cancion.titulo)
                    ) {
                        actual.izquierdo
                    } else {
                        actual.derecho
                    }
        }
        if (actual == null) {
            // La canción no se encuentra en el árbol.
            return
        }
        // Caso 1: Nodo sin hijos (nodo hoja).
        if (actual.izquierdo == null && actual.derecho == null) {
            if (actual == raiz) {
                raiz = null
            } else if (actual == actual.padre?.izquierdo) {
                actual.padre?.izquierdo = null
            } else {
                actual.padre?.derecho = null
            }
        }
        // Caso 2: Nodo con un solo hijo.
        else if (actual.izquierdo == null || actual.derecho == null) {
            val hijo = actual.izquierdo ?: actual.derecho
            if (actual == raiz) {
                raiz = hijo
            } else if (actual == actual.padre?.izquierdo) {
                actual.padre?.izquierdo = hijo
            } else {
                actual.padre?.derecho = hijo
            }
            hijo?.padre = actual.padre
        }
        // Caso 3: Nodo con dos hijos.
        else {
            val sucesor = minNodo(actual.derecho)
            if (sucesor != null) {
                actual.cancion = sucesor.cancion
                eliminar(sucesor.cancion)
            }
        }
    }

    /**
     * Encuentra el nodo con el valor mínimo en un subárbol.
     * @param nodo El nodo raíz del subárbol.
     * @return El nodo con el valor mínimo.
     */
    private fun minNodo(nodo: Nodo?): Nodo? {
        var actual = nodo
        while (actual?.izquierdo != null) {
            actual = actual.izquierdo
        }
        return actual
    }

    /**
     * Verifica si el árbol es un árbol de búsqueda de canciones.
     * @param nodo El nodo raíz del árbol.
     * @return true si el árbol es un árbol de búsqueda de canciones, false de lo contrario.
     */
    fun esArbolDeBusqCancion(nodo: Nodo?): Boolean {
        if (nodo == null) {
            return true
        }

        if (nodo.izquierdo != null) {
            val maxIzq = maxInterprete(nodo.izquierdo)
            val maxTituloIzq = maxTitulo(nodo.izquierdo)
            if (nodo.cancion.interprete < maxIzq ||
                            (nodo.cancion.interprete == maxIzq &&
                                    nodo.cancion.titulo <= maxTituloIzq)
            ) {
                return false
            }
        }

        if (nodo.derecho != null) {
            val minDer = minInterprete(nodo.derecho)
            val minTituloDer = minTitulo(nodo.derecho)
            if (nodo.cancion.interprete > minDer ||
                            (nodo.cancion.interprete == minDer &&
                                    nodo.cancion.titulo >= minTituloDer)
            ) {
                return false
            }
        }

        return esArbolDeBusqCancion(nodo.izquierdo) && esArbolDeBusqCancion(nodo.derecho)
    }

    /**
     * Encuentra el valor mínimo del campo "interprete" en un subárbol.
     * @param nodo El nodo raíz del subárbol.
     * @return El valor mínimo del campo "interprete".
     */
    fun minInterprete(nodo: Nodo?): String {
        var actual = nodo
        while (actual?.izquierdo != null) {
            actual = actual.izquierdo
        }
        return actual?.cancion?.interprete ?: ""
    }

    /**
     * Encuentra el valor máximo del campo "interprete" en un subárbol.
     * @param nodo El nodo raíz del subárbol.
     * @return El valor máximo del campo "interprete".
     */
    fun maxInterprete(nodo: Nodo?): String {
        var actual = nodo
        while (actual?.derecho != null) {
            actual = actual.derecho
        }
        return actual?.cancion?.interprete ?: ""
    }

    /**
     * Encuentra el valor mínimo del campo "titulo" en un subárbol.
     * @param nodo El nodo raíz del subárbol.
     * @return El valor mínimo del campo "titulo".
     */
    fun minTitulo(nodo: Nodo?): String {
        var actual = nodo
        while (actual?.izquierdo != null) {
            actual = actual.izquierdo
        }
        return actual?.cancion?.titulo ?: ""
    }

    /**
     * Encuentra el valor máximo del campo "titulo" en un subárbol.
     * @param nodo El nodo raíz del subárbol.
     * @return El valor máximo del campo "titulo".
     */
    fun maxTitulo(nodo: Nodo?): String {
        var actual = nodo
        while (actual?.derecho != null) {
            actual = actual.derecho
        }
        return actual?.cancion?.titulo ?: ""
    }

    /**
     * Busca una canción en el árbol.
     * @param titulo El título de la canción a buscar.
     * @param interprete El intérprete de la canción a buscar.
     * @return La canción encontrada, o null si no se encuentra.
     */
    fun buscarCancion(titulo: String, interprete: String): Cancion? {
        var actual = raiz
        while (actual != null) {
            if (actual.cancion.titulo == titulo && actual.cancion.interprete == interprete) {
                return actual.cancion
            } else if (interprete < actual.cancion.interprete ||
                            (interprete == actual.cancion.interprete &&
                                    titulo < actual.cancion.titulo)
            ) {
                actual = actual.izquierdo
            } else {
                actual = actual.derecho
            }
        }
        return null
    }
    /**
     * Convierte el árbol en una secuencia ordenada de canciones.
     * @param nodo El nodo raíz del árbol.
     * @param listaAcumulativa La lista acumulativa donde se almacenarán las canciones.
     * @return La lista acumulativa con las canciones ordenadas.
     */
    fun deArbolASecuencia(
            nodo: Nodo?,
            listaAcumulativa: ListaDoblementeEnlazadaCircular<Cancion> =
                    ListaDoblementeEnlazadaCircular()
    ): ListaDoblementeEnlazadaCircular<Cancion> {
        if (nodo == null) {
            if (listaAcumulativa.estaVacia()) {
                listaAcumulativa.cursor =
                        listaAcumulativa.sentinel.next // Inicializa el cursor en la primera canción
            }
            return listaAcumulativa
        }

        deArbolASecuencia(nodo.izquierdo, listaAcumulativa)
        listaAcumulativa.agregarAlFinal(nodo.cancion)
        deArbolASecuencia(nodo.derecho, listaAcumulativa)
        return listaAcumulativa
    }
}
