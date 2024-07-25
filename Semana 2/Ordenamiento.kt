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
fun insertionSort(A: Array<Number>){
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
}

/*
    Funcion que mezcla dos subarreglos U y V en el arreglo T
    Llamada: merge(U,V,T)
    Precondicion: U.size > 0. V.size > 0
    Postcondicion: T.size == U.size + V.size. Los elementos de T estan ordenados ascendentemente
 */
fun merge(U: Array<Number>, V: Array<Number>, T: Array<Number>){
    var i = 0
    var j = 0
    for (k in 0 until T.size) {
        if (i == U.size) {
            T[k] = V[j]
            j += 1
        } else if (j == V.size) {
            T[k] = U[i]
            i += 1
        } else if (U[i] < V[j]) {
            T[k] = U[i]
            i += 1
        } else {
            T[k] = V[j]
            j += 1
        }
    }
}

/*
    Funcion que ordena los elementos de un arreglo mediante Merge Sort
    Llamada: mergeSort(T)
    Precondicion: T.size > 0
    Postcondicion: Los elementos de T estan ordenados ascendentemente
 */
fun mergeSort(T: Array<Number>){
    // Inicio
    // Si n es pequeña, usar insertionSort
    if (T.size < 10) {
        insertionSort(T)
    } else {
        // Crear dos arreglos U y V para almacenar las mitades del arreglo T
        val U: Array<Number> = Array(T.size/2, { 0 })
        val V: Array<Number> = Array(T.size - U.size, { 0 })
        // Copiar los elementos de T a U y V
        for (i in 0 until U.size) {
            U[i] = T[i]
        }
        for (i in 0 until V.size) {
            V[i] = T[i+U.size]
        }
        // Ordenar recursivamente las mitades U y V
        mergeSort(U)
        mergeSort(V)
        // Mezclar las mitades ordenadas U y V en el arreglo T
        merge(U, V, T)
    }
}

/*
    Funcion que mezcla dos subarreglos U y V en el arreglo T
    Llamada: mergeIt(U,V,T)
    Precondicion: T.size > 0. aux.size > 0. izq > 0. der > 0. fin > 0
    Postcondicion: Los elementos de T estan ordenados ascendentemente
 */
// Función que mezcla dos subarreglos ordenados usando un arreglo auxiliar
fun mergeIt(T: Array<Number>, aux: Array<Number>, izq: Int, der: Int, fin: Int) {
    // Inicializar los índices para recorrer los subarreglos
    var i = izq // Índice para el subarreglo izquierdo
    var j = der // Índice para el subarreglo derecho
    var k = izq // Índice para el arreglo auxiliar
    // Mezclar los elementos de los subarreglos en el arreglo auxiliar
    while (i < der && j < fin) {
        // Se comparan los elementos de los subarreglos y se elege el menor
        if (T[i] <= T[j]) {
            // Se copia el elemento del subarreglo izquierdo al arreglo auxiliar
            aux[k] = T[i]
            i++ // Se incrementa el indice del subarreglo izquierdo
        } else {
            // Se copiar el elemento del subarreglo derecho al arreglo auxiliar
            aux[k] = T[j]
            j++ // Se incrementa el indice del subarreglo derecho
        }
        k++ // Se incrementa el indice del arreglo auxiliar
    }
    // Se copian los elementos restantes del subarreglo izquierdo, si es que hay
    while (i < der) {
        aux[k] = T[i]
        i++
        k++
    }
    // Se copian los elementos restantes del subarreglo derecho, si es que hay
    while (j < fin) {
        aux[k] = T[j]
        j++
        k++
    }
}

/*
    Funcion que ordena los elementos de un arreglo de numeros mediante Merge Sort Iterativo 
    Llamada: mergeSortIt(T)
    Precondicion: T.size > 0
    Postcondicion: Los elementos de T estan ordenados ascendentemente
 */
fun mergeSortIt(T: Array<Number>) {
    val aux: Array<Number> = Array(T.size, { 0 })
    // Se inicializa el tamaño de los subarreglos a 1
    var tam = 1
     
    while (tam < T.size) {
        // Se inicializan los indices de los subarreglos
        var izq = 0
        var der = tam
        var fin = der + tam

        while (der < T.size) {
            // Se ajusta el indice final si es mayor que el tamaño de T
            if (fin > T.size) {
                fin = T.size
            }
            // Se mezclan los subarreglos T[izq..der-1] y T[der..fin-1] en el arreglo auxiliar
            mergeIt(T, aux, izq, der, fin)
            // Se actualizan los indices de los subarreglos
            izq = fin
            der = izq + tam
            fin = der + tam
        }
        // Se copian los elementos restantes de T a aux
        for (i in izq until T.size) {
            aux[i] = T[i]
        }
        // Se copian los elementos de aux a T
        for (i in 0 until T.size) {
            T[i] = aux[i]
        }
        // Se duplica el tamaño de los subarreglos
        tam = tam * 2
    }
}


