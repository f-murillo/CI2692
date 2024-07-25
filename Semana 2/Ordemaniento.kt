operator fun Number.compareTo(other: Number) = this.toDouble().compareTo(other.toDouble())

/**
    Funcion que ordena un arreglo de numero usando Insertion Sort
    Llamada de la funcion: bubbleSort(A)
    Precondicion: A es un arreglo de numeros. A.size>1.
    Postcondicion: Ordena los elementos de A en forma ascendente
 */
fun bubbleSort(A: Array<Number>) {
    for (i in 0 until A.size) {
        for (j in (A.size - 1) downTo (i+1)) {
            if (A[j] < A[j - 1]){
                var tmp = A[j]
                A[j] = A[j-1]
                A[j-1] = tmp
            }
        }
    }
}



/**
    Funcion que ordena un arreglo de numero usando Insertion Sort
    Llamada de la funcion: insertionSort(A)
    Precondicion: A es un arreglo de numeros. A.size>1.
    Postcondicion: Ordena los elementos de A en forma ascendente
 */
fun insertionSort(A: Array<Number>): Array<Number> {
    for (i in 1 until A.size) {
        val pivote = A[i]
        var j = i - 1
        // Mueve los elementos de A[0..i-1],, que son mas grandes que el pivote, a una posición adelante
        // de su posición actual
        while (j >= 0 && A[j] > pivote) {
            A[j + 1] = A[j]
            j--
        }
        A[j + 1] = pivote
    }
    return A
}
