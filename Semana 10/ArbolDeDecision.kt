/*
 * Clase ArbolDeDecision que sera usada en la clase CodigoMorseAD
 * Representa un arbol de decision binario para la decodificacion de codigo morse
 */
public class ArbolDeDecision() {
    
    // Nodo que representa la raiz del arbol de decision binario
    var raiz = NodoAD(' ', "")

    // Metodos de la clase ArbolDeDecision

    //Metodo que agrega un nodo al arbol de decision binario
    fun agregar(nuevo: NodoAD) {
        // Se recorre el arbol de decision hasta encontrar el lugar donde se desea agregar el nodo
        var x: NodoAD? = raiz
        var y: NodoAD? = null
        var i = 0
        val codigoNuevo = nuevo.codigo
        while (x != null && i < codigoNuevo.length){
            // Se guarda el nodo padre del nodo que se desea agregar
            y = x
            // Si el codigo del nodo que se desea agregar es un punto, se recorre el arbol de decision por la rama izquierda
            if (codigoNuevo[i] == '.') 
                x = x.izq
            
            // En caso contrario, se recorre el arbol de decision por la rama derecha
            else 
                x = x.der
            
            i++
        }
        // Si el nodo padre del nodo que se desea agregar es la raiz o el nodo que se desea agregar es nulo, entonces el nodo que se desea agregar es la raiz
        if (y == null) 
            raiz = nuevo
        
        // En caso contrario, se agrega el nodo al arbol de decision
        else {
            // Se guarda el nodo padre del nodo que se desea agregar
            nuevo.padre = y
            // Si el codigo del nodo que se desea agregar es un punto, entonces el nodo que se desea agregar es el hijo izquierdo del nodo padre
            if (codigoNuevo[i - 1] == '.')
                y.izq = nuevo
            
            // En caso contrario, el nodo que se desea agregar es el hijo derecho del nodo padre
            else
                y.der = nuevo
            
        }
    }

    // Metodo que busca un nodo en el arbol de decision binario 
    fun buscar(codigo: String): Char? {
        var x: NodoAD? = this.raiz
        var y: NodoAD?
        var i = 0

        // Se recorre el arbol de decision hasta encontrar el nodo que se desea buscar
        while (x != null && i < codigo.length){
            // Si el siguiente caracter del codigo es un punto, se recorre el arbol de decision por la rama izquierda
            // En caso contrario, se recorre el arbol de decision por la rama derecha
            if (codigo[i] == '.') x = x.izq else x = x.der
            i++
        }

        // Se guarda el resultado de la busqueda
        y = x

        // Se retorna el valor del nodo que se desea buscar en el arbol de decision
        if (y == this.raiz || y == null || codigo.length != y.codigo.length)
            // Se retorna null si el nodo que se desea buscar no existe en el arbol de decision
            return null
        else
            // Se retorna el valor del nodo que se desea buscar en el arbol de decision
            return y.valor
    }

    // Metodo que retorna una representacion en String del arbol de decision binario
    override fun toString(): String {
        var str = "ArbolDeDecision(raiz"
        if (raiz.izq != null)
            str += ", ${raiz.izq}"
        
        if (raiz.der != null)
            str += ", ${raiz.der}"
        
        return str + ")"
    }
}


