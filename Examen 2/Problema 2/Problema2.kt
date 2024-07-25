// Programa que encuentra, si lo hay, un elemento mayoritario en una secuencia de n numeros enteros

// Para la solucion del problema, se hace uso de arreglos

/*
* Funcion que devuelve el elemento mayoritario de un arreglo, o -1 si este no existe 
* La funcion utiliza dos ciclos:
* 
* El primer ciclo busca un candidato a elemento mayoritario. Se recorre el arreglo manteniendo un
* contador para las ocurrencias del elemento actual. Si el contador llega a cero, se selecciona un nuevo
* candidato y se reinicia el contador. Al final de este ciclo, se tiene un candidato para ser el elemento
* mayoritario. Sin embargo, no esta garantizado que sea el mayoritario, ya que podria no aparecer mas de
* n/2 veces.
* 
* El segundo ciclo verifica si el candidato seleccionado es realmente el elemento mayoritario. Se cuenta
* cuantas veces aparece el candidato en el arreglo.
* 
* Si el candidato aparece mas de n/2 veces, entonces es el elemento mayoritario y se retorna su valor.
* En caso contrario, se devuelve -1.
* 
* Cada ciclo tiene una complejidad temporal de O(n), ya que solo se realizan operaciones de asignacion y
* comparacion, que tienen complejidad O(1), pero se recorren los n elementos del arreglo.
* 
* Por lo tanto, la complejidad temporal de la funcion encuentraMayoritario es O(n).
*/
fun encuentraMayoritario(arreglo: IntArray): Int {
    var contador = 0
    var candidato = -1

    // Primer ciclo que encuentra el candidato a elemento mayoritario
    for (num in arreglo) {
        if (contador == 0) {
            candidato = num
        }

        // Si el valor de num es igual a nuestro candidato, aumentamos en uno el contador
        // En caso contrario, disminuimos en un el contador
        if(num == candidato)
            contador += 1
        else 
            contador-=1    
        
    }

    // Segundo ciclo que verifica si el candidato efectivamente es un elemento mayoritario
    contador = 0
    for (num in arreglo) {
        if (num == candidato) {
            contador++
        }
    }

    /* Si el valor de contador es mayor a la mitad del numero de elementos del arreglo, entonces el candidato
    *   es el elemento mayoritario, asi que se retorna el candidato
    *
    *   En caso contrario, se retorna -1
    */
    if(contador > arreglo.size / 2)
        return candidato
    else
        return -1    

}

fun main(args: Array<String>) {
    val arreglo = IntArray(args.size)
    for (i in args.indices) {
        arreglo[i] = args[i].toInt()
    }
    val mayoritario = encuentraMayoritario(arreglo)
    if (mayoritario != -1)
        println(mayoritario)
     else 
        println("No hay elemento mayoritario")
    
}