operator fun Number.compareTo(other: Number) = this.toDouble().compareTo(other.toDouble())

/*
                                    NOTA
    Los algoritmos implementados en esta libreria estan basados en los algoritmos presentes en la bibliografia
    del curso teorico (del Cormen), adaptados para su correcto funcionamiento en Kotlin
*/

/*
    Funcion que intercambia dos elementos de un arreglo
    Llamada: swap(A,i,j)
    Precondicion: A.size > 0. i > 0. j > 0
    Postcondicion: Se intercambian los elementos de las posiciones i y j del arreglo
*/
fun swap(A: Array<Number>, i: Int, j:Int){
    val auxiliar = A[i]
    A[i] = A[j]
    A[j] = auxiliar
}

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
                swap(A,j,j-1)
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

/*
    Funcion que transforma un arbol (representado como un arreglo) en un Max Heap
    Llamada: maxHeapify(A,i,n), donde A es el arreglo (arbol), i la posicion desde la cual 
    se viola la condicion de Heap, y n el tamaño del arreglo
    Preconcicion: A.size>0. i>0. 
    Postcondicion: Al final de la ejecucion, A es un Max Heap
*/
fun maxHeapify(A: Array<Number>, i: Int, n: Int){
    val l = 2*i
    val r = 2*i+1
    var largest: Int

    if(l<=n && A[l]>A[i])
        largest = l
    else
        largest = i
    
    if(r<=n && A[r]>A[largest])
        largest = r

    if(largest!=i){
        swap(A, i, largest)
        maxHeapify(A,largest,n)
    }
}

/*
    Funcion que construye un Max Heap a partir de un arreglo dado
    Llamada: buildMaxHeap(A)
    Precondicion: A.size>0
    Postcondicion: Al final de la ejecucion, A es un Max Heap
*/
fun buildMaxHeap(A: Array<Number>){
    for(i in (A.size-1)/2 downTo 0){
        maxHeapify(A,i,A.size-1)
    }
}

/*
    Funcion que ordena un arreglo de numeros usando HeapSort
    Llamada: heapSort(A)
    Precondicion: A.size>0
    Postcondicion: Al final de la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun heapSort(A: Array<Number>){
    buildMaxHeap(A)
    for (i in (A.size-1) downTo 0) {
        swap(A,0,i)
        maxHeapify(A,0,i-1)
    }
}

/*
    Funcion que crea la particion de un arreglo dadas una posicion inicial p, y una final r,
    alrededor de un elemento pivote
    Observacion: esta funcion esta basada en la vista en la clase de teoria, la cual escoge el ultimo
    elemento como pivote
    Llamada: partition(A,p,r)
    Precondicion: A.size > 0. p > 0. r > 0
    Postcondicion: Crea la particion del arreglo y retorna la posicion del pivote, tal que todos los elementos
    que estan antes del elemento pivote son menores o iguales a este, y todos los elementos que estan despues 
    son mayores o iguales
*/
fun partition(A: Array<Number>, p: Int, r: Int): Int{
    // Se inicializa la variable x (el pivote) como el ultimo elemento 
    val x = A[r]
    // Se inicializa la variable q como el numero de elementos que van a la izquierda del pivote
    var q = p-1

    // Se recorren todos los elementos a excepcion del pivote  
    for (i in p..r-1) {
        /*
            Si el elemento actual es menor o igual al pivote, entonces se incrementa el numero de elementos a 
            la izquierda del pivote (q), e intercambia los elementos de las posiciones q e i del arreglo,
            quedando entonces el elemento A[i] a la izquierda del pivote
        */
        if(A[i] <= x){
            q++
            swap(A,q,i)
        }
    }
    // Se intercambia el pivote con el primer elemento mayor que el, quedando en la mitad de la particion
    swap(A,q+1,r)
    // Se retorna la posicion en la cual se encuentra el pivote
    return q+1
}

/*
    Funcion que ordena los elementos de un arreglo de numeros usando QuickSort dadas una posicion inicial p y una 
    posicion final r
    (Leer observacion sobre QuickSort clasico en el archivo Leeme.txt)
    Llamada: quickSort(A,p,r)
    Precondicion: A.size > 0. p > 0. r > 0
    Postcondicion: Al final de la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun quicksortClasico(A: Array<Number>, p: Int, r: Int){
    // Si la posicion inicial es menor a la final
    if(p < r){
        // Se crea la particion y se obtiene la posicion del elemento pivote
        val q = partition(A,p,r)
        // Se ordenan ambos lados de la particion haciendo llamadas recursivas
        quicksortClasico(A,p,q-1)
        quicksortClasico(A,q+1,r)
    }
}

/*
    Funcion que hace la llamada al algoritmo clasico de quickSort
*/
fun quicksort(A: Array<Number>){
    quicksortClasico(A,0,A.size-1)
}



/*
    Funcion que ordena los elementos de un arreglo usando Quicksort con dos pivotes
    Llamada: quicksortPivot
    Precondicion: A.size > 0. izq > 0. der > 0
    Postcondicion: Al final de la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun quicksortPivot(A: Array<Number>, izq: Int, der: Int){
    if ( (der - izq) >= 1 ){
        if (A[izq] > A[der]) {
            swap(A, izq, der)
        }
        val p = A[izq]
        val q = A[der]
        var k = izq +1
        var l = k
        var g = der - 1
        var h = g
        while (k <= g) {
            if (A[k] < p) {
                swap(A, k, l)
                l = l + 1
            }
            else if (A[k] >= q) {
                while (A[h] > q && k < h) {
                    h = h-1
                }
                swap(A, k, h)
                h = h-1
                g = g-1
                if (A[k] < p) {
                    swap(A, k, l)
                    l = l+1
                }
            }
            k = k + 1
        }

        l = l - 1
        g = g + 1

        
        swap(A, izq, l)
        swap(A, der, g)
        quicksortPivot(A, izq, l-1)
        quicksortPivot(A, l+1, g - 1)
        quicksortPivot(A, g+1, der)

    }
}
/*
    Funcion que hace la llamada al algoritmo de quicksort con dos pivotes
*/
fun dualPivotQuicksort(A: Array<Number>){
    quicksortPivot(A,0, A.size-1)
}

/*
    Funcion que hace la llamada al algoritmo de quicksort con dos pivotes
*/
/*fun dualPivotQuicksort(A: Array<Number>){
    quicksortPivot(A,0, A.size-1)
}*/

/*
    Funcion que obtiene el maximo valor de un arreglo de numeros (para usarlo en Counting Sort y Radix Sort)
    Llamada: maximo(A)
    Precondicion: A.size > 0
    Postcondicion: retorna el valor maximo del arreglo

*/
fun maximo(A: Array<Number>): Number{
    var max: Number = A[0]
    for (i in 0 until A.size) {
        if(A[i] > max)
            max = A[i]
    }
    return max
}

/*
    Funcion que ordena los elementos de un arreglo usando Counting Sort
    Llamada: countingSort(A)
    Precondicion: A.size > 0
    Postcondicion: Al finalizar la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun countingSort(A: Array<Number>){
    // Se halla el maximo valor del arreglo A
    val k = maximo(A)
    // Se cambian los elementos de A a tipo entero, y se almacenan en un arreglo auxiliar
    val aux = A.map{it.toInt()}.toTypedArray()
    // Se crea el arreglo que contendra la frecuencia de cada elemento de A, desde 0 hasta k
    var C = Array<Int>(k.toInt()+1,{0})
    // Se crea el arreglo donde quedaran ordenados los elementos
    val B = Array<Int>(A.size,{0})

    // Se almacenan las frecuencias de los elementos de A 
    for (i in 0 until A.size){
        C[aux[i]]+=1
    }

    // Se realiza la suma acumulada de los elementos 
    for (i in 1 until C.size){
        // C[i] tendra la posicion del arreglo B donde ira el elemento i+1
        C[i]+=C[i-1] 
    }

    // Se almacenan los elementos de A en B
    for (i in A.size-1 downTo 0){
        B[C[aux[i]]-1] = aux[i]
        C[aux[i]]-=1
    }

    // Se copia el arreglo B en el A
    for (i in 0 until A.size){
        A[i] = B[i]
    }
}

/*
    Funcion que ordena los elementos de un arreglo usando Counting Sort, pero esta vez recibiendo un parámetro del dígito a tomar en cuenta
    Llamada: countingSort(A)
    Precondicion: A.size > 0
    Postcondicion: Al finalizar la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun countingSortRadix(A: Array<Number>, exp: Int, B: Array<Number>): Array<Number> {
    val output = Array(A.size) { 0 }
    val count = Array(maximo(A).toInt() + 1) { 0 }

    for (i in A.indices) {
        count[(A[i].toInt() / exp) % 10]++
    }

    for (i in 1 until count.size) {
        count[i] += count[i - 1]
    }

    for (i in A.indices.reversed()) {
        output[count[((A[i].toInt() / exp) % 10).toInt()] - 1] = A[i].toInt()
        count[(A[i].toInt() / exp) % 10]--
    }

    for (i in A.indices) {
        B[i] = output[i]
    }

    return B
}

/*
    Funcion que ordena los elementos de un arreglo usando radix Sort
    Llamada: radixSort(A)
    Precondicion: A.size > 0
    Postcondicion: Al finalizar la ejecucion, los elementos del arreglo se encuentran ordenados ascendentemente
*/
fun radixSort(A: Array<Number>){
    val max = maximo(A).toInt()

    var exp = 1
    while (max / exp > 0) {
        countingSortRadix(A, exp, A)
        exp *= 10
    }
    
}


