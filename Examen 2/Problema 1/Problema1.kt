// Programa que agrupa los elementos repetidos de una lista en el mismo orden en el que aparecen

/* 
*    Para la solucion del problema, se hace uso de listas doblemente enlazadas circulares para almacenar 
*    la lista original y la lista con los elementos repetidos agrupados, y para poder realizar inserciones
*    en tiempo constante.
*    Ademas, se hace uso de una tabla de hash para realizar busquedas e inserciones en tiempo constante
*/

/* 
*   Funcion que agrupa los elementos repetidos
*   Se tiene que las funciones obtener e insertar de la clase TablaHash tienen complejidad O(1)
*   Por otro lado, las funciones agregarAlFinal y agregarDespuesDe de la clase ListaDoblementeEnlazadaCircular
*   tienen complejidad O(1)
*   Como se recorren todos los n elementos de la lista original, y para cada uno se realizan operaciones de
*   complejidad O(1), la complejidad total de la funcion agrupar elementos es O(n)
*/
fun agruparElementos(lista: ListaDoblementeEnlazadaCircular<Int>): ListaDoblementeEnlazadaCircular<Int> {
    val resultado = ListaDoblementeEnlazadaCircular<Int>()
    val elementosUnicos = TablaHash(lista.getSize() * 2)

    for (elemento in lista) {
        val nodo = elementosUnicos.obtener(elemento)
        if (nodo == null) {
            val nuevoNodo = resultado.agregarAlFinal(elemento)
            elementosUnicos.insertar(elemento, nuevoNodo)
        } else {
            resultado.agregarDespuesDe(nodo, elemento)
        }
    }

    return resultado
}

fun main(args: Array<String>) {
    val lista = ListaDoblementeEnlazadaCircular<Int>()
    for (arg in args) {
        lista.agregarAlFinal(arg.toInt())
    }
    val agrupados = agruparElementos(lista)
    println(agrupados.toString())
}

