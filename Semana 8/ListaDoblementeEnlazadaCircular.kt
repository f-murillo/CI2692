import kotlin.random.Random // Esto es unicamente para el metodo NodoRandom, que se usa para la prueba
/*
* Clase de lista doblemente enlazada circular, para implementar una secuencia matematica con sentinela, la cual se
* usara para la implementacion de los TAD Pila y Cola
*/
class ListaDoblementeEnlazadaCircular(){
    
    // sentinel: El nodo sentinela de la lista
    var sentinel = Nodo(null,null)

    // Metodo init
    init {
        // Se cambia el nodo siguiente y anterior del nodo sentinela para que apunte a si mismo
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    // size: El tamaño de la lista
    private var size: Int = 0

    // Metodo que devuelve el tamaño de la lista
    fun getSize(): Int {
        return size
    }

    // Metodo verifica si la lista esta vacia
    fun estaVacia(): Boolean {
        return size == 0
    }

    // Metodo que agrega un nodo con un dato al principio de la lista
    fun agregarAlFrente(dato: Int){
        // Se crea el nuevo nodo
        val nuevoNodo = Nodo(dato, sentinel.next)
        // Se cambia el nodo anterior del nuevo nodo para que apunte al nodo sentinela
        nuevoNodo.cambiarPrev(sentinel) 
         // Se cambia el nodo anterior del nodo siguiente al nuevo nodo para que apunte al nuevo nodo
        sentinel.next!!.cambiarPrev(nuevoNodo)
        // Se cambia el nodo siguiente del nodo sentinela para que apunte al nuevo nodo
        sentinel.cambiarNext(nuevoNodo) 
        // Se aumenta en uno el tamaño de la lista
        size++ 
    }

    // Funcion que agrega un nodo con el dato dado al final de la lista
    fun agregarAlFinal(dato: Int){
        // Se crea el nuevo nodo
        val nuevoNodo = Nodo(dato, sentinel)
        // Se cambia el nodo anterior del nuevo nodo para que apunte al nodo anterior al nodo sentinela
        nuevoNodo.cambiarPrev(sentinel.prev!!)
        // Se cambia el nodo siguiente del nodo anterior al nodo sentinela para que apunte al nuevo nodo 
        sentinel.prev!!.cambiarNext(nuevoNodo)
        // Se cambia el nodo anterior del nodo sentinela para que apunte al nuevo nodo 
        sentinel.cambiarPrev(nuevoNodo)
        // Se aumenta en uno el tamaño de la lista 
        size++ 
    }

    // Metodo que elimina el primer nodo con el dato dado
    fun eliminar(nodo: Nodo?){
        // Si la lista esta vacia, se lanza una excepcion
        if (estaVacia()) {
            throw IllegalStateException("La lista esta vacia")
        }
        
        if(nodo == null || nodo == sentinel)
            throw IllegalStateException("El nodo no existe o es el sentinela")
        // Se elimina el nodo, haciendo que el nodo previo al nodo a eliminar apunte al siguiente, y viceversa
        nodo.prev?.cambiarNext(nodo.next)
        nodo.next?.cambiarPrev(nodo.prev)
        // Se disminuye en uno el tamaño de la lista
        size--    
    }

    // Metodo que elimina el primer nodo de la lista
    fun eliminarPrimero(){
        // Si la lista esta vacia, se lanza una excepcion
        if (estaVacia())
            throw IllegalStateException("La lista esta vacia")
        
        // Se cambia el nodo siguiente y anterior del nodo siguiente al nodo sentinela
        sentinel.next!!.next!!.cambiarPrev(sentinel)
        sentinel.cambiarNext(sentinel.next!!.next!!)
        // Se disminuye en uno el tamaño de la lista
        size--
    }

    // Metodo que elimina el ultimo nodo de la lista
    fun eliminarUltimo(){
        // Si la lista esta vacia, se lanza una excepcion
        if (estaVacia())
            throw IllegalStateException("La lista esta vacia")
        
        // Se cambia el nodo siguiente y anterior del nodo anterior al nodo sentinela
        sentinel.prev!!.prev!!.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel.prev!!.prev!!)
        // Se disminuye en uno el tamaño de la lista
        size--
    }

    // Metodo que devuelve el nodo con el dato, o si el dato no se encuentra en la lista, devuelve null
    fun buscar(dato: Int): Nodo?{
        // Si la lista esta vacia, lanza una excepcion
        if (estaVacia())
            throw IllegalStateException("La lista esta vacia")
        
        // Se declara la variable nodoActual como el inmediato siguiente al sentinela
        var nodoActual = sentinel.next
        // Se itera mientras que el nodo actual sea distinto al sentinela
        while (nodoActual != sentinel){
            // Si el valor del nodo actual es igual al que se esta buscando, se retorna el nodo con ese valor
            if (nodoActual!!.dato == dato)
                return nodoActual
            
            // Sino, se pasa al nodo siguiente
            nodoActual = nodoActual.next
        }
        
        // Si salio del ciclo significa que el nodo no esta en la lista, asi que se retorna null
        return null
    }

    // Metodo que devuelve el primer elemento de la lista
    fun primerElemento(): Nodo{
        return sentinel.next!!
    }

    // Metodo que devuelve el ultimo elemento de la lista
    fun ultimoElemento(): Nodo{
        return sentinel.prev!!
    }

    // Metodo que devuelve una representacion en String de la lista
    override fun toString(): String {
        if (estaVacia()) {
            return "La lista esta vacia"
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
}
