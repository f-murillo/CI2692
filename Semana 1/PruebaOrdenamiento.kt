import kotlin.random.Random

// Funciones para generar las clases de secuencias (representadas con arreglos)

/*
    Funcion para generar una secuencia de n enteros de forma aleatoria en el intervalo [0..n]
    Llamada: genera_random(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n elementos de tipo entero generados al azar en el intervalo [0..n]
 */
fun genera_random(n: Int): Array<Number>{
    // Se crea un arreglo de n elementos de tipo int llamado secuencia, cuyos valores iniciales son todos 0
    val secuencia: Array<Number> = Array(n, {0})
    for (i in 0 until n) {
        // Se itera sobre los elementos del arreglo y les asigna un valor aleatorio
        secuencia[i] = (0..n).random()
    }
    return secuencia
}

/*
    Funcion para generar una secuencia de n elementos de tipo double de forma aleatoria en el intervalo [0..1]
    Llamada: genera_randomd(n)
    Se va a usar la funcion Random.nextDouble() del paquete kotlin.random.Random
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n elementos de tipo double generados al azar en el intervalo [0..1]
 */
fun genera_randomd(n: Int): Array<Number>{
    // Se crea un arreglo primitivo de tipo double de n elementos llamado secuencia, cuyos valores iniciales son todos 0
    val secuencia: Array<Number> = Array(n, {0.0})
    for (i in secuencia.indices) {
        // Se itera sobre los elementos del arreglo y les asigna un valor aleatorio
        secuencia[i] = Random.nextDouble(0.0,1.0)
    }
    return secuencia
}

/*
    Funcion que genera un arreglo ordenado de n enteros, tal que el arreglo es de la forma [1,2,..n]
    Llamada: genera_sorted(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n enteros ordenado ascendentemente
*/
fun genera_sorted(n: Int): Array<Number>{
    // Se crea un arreglo primitivo de tipo int de n elementos llamado secuencia, cuyos valores iniciales son todos 0
    val secuencia: Array<Number> = Array(n, {0})
    for(i in 0 until n){
        // Se itera sobre los elementos del arreglo y les asigna el valor de i+1
        secuencia[i] = i+1
    }
    return secuencia
}

/*
    Funcion que genera un arreglo ordenado de n elementos de tipo double entre 0 y 1
    Llamada: genera_sortedd(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n elementos de tipo double entre 0 y 1 ordenado ascendentemente
*/
fun genera_sortedd(n: Int): Array<Number>{
    val secuencia: Array<Number> = Array(n, {0.0})
    for(i in 0 until n){
        secuencia[i] = i.toDouble()/n
    }
    return secuencia
}

/*
    Funcion que genera un arreglo de n elementos ordenados de forma inversa, tal que tiene la forma [N,..,2,1]
    Llamada: genera_inv(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n enteros ordenados descendentemente
*/
fun genera_inv(n: Int): Array<Number>{
    val secuencia: Array<Number> = Array(n, {0})
    for (i in 0 until n) {
        secuencia[i] = n-i
    }
    return secuencia
}

/*
    Funcion que genera un arreglo de n elementos con 0 y 1
    Llamada: genera_zu(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n enteros con valores 0 o 1
*/
fun genera_zu(n: Int): Array<Number>{
    val secuencia: Array<Number> = Array(n, {0})
    for (i in 0 until n) {
        secuencia[i] = (0..1).random()
    }
    return secuencia
}

/*
    Funcion que genera un arreglo de n elementos ordenados ascendentemente la primera mitad, y descendentemente
    la segunda mitad
    Llamada: genera_media(n)
    Precondicion: n>0
    Postcondicion: retorna un arreglo de n enteros ordenados ascendentemente desde el primer elemento hasta n/2,
    y luego descendentemente desde n/2 hasta el ultimo elemento
*/
fun genera_media(n: Int): Array<Number>{
    val secuencia: Array<Number> = Array(n, {0})
    for (i in 0 until n / 2) {
        // Itera sobre los elementos de la secuencia entre el primero y el elemento n/2 - 1
        secuencia[i] = i + 1
    }
    for (i in n / 2 until n) {
        // Itera sobre los elementos de la secuencia entre el elemento n/2 y el último
        secuencia[i] = n - i
    }
    return secuencia
}

/* 
    Funcion que crea la clase de secuencia
    Llamada: creaSecuencia(tipoSecuencia, n)
    Precondición: tipoSecuencia es uno de los siguientes valores: "random", "randomd",
    "sorted", "sortedd", "inv", "zu", "media"
    Poscondición: Devuelve una secuencia de n elementos del tipo especificado
*/
fun crearSecuencia(tipoSecuencia: String, n: Int ): Array<Number> {
    if(tipoSecuencia == "random") {
        return genera_random(n)
    } else if(tipoSecuencia == "randomd"){
        return genera_randomd(n)
    } else if(tipoSecuencia == "sorted") {
        return genera_sorted(n)
    } else if(tipoSecuencia == "sortedd"){
        return genera_sortedd(n)
    }else if(tipoSecuencia == "inv") {
        return genera_inv(n)
    } else if(tipoSecuencia == "zu") {
        return genera_zu(n)
    } else if(tipoSecuencia == "media") {
        return genera_media(n)
    } else{
        // Si el tipo de secuencia no es ninguno de los anteriores, devuelve una secuencia ordenada por defecto
        println("Tpo de secuencia incorrecto. Se utilizará una secuencia ordenada")
        return genera_sorted(n)
    }
}

/*
    Funcion que verifica si una secuencia esta ordenada ascendentemente, para verificar el funcionamiento
    de los algoritmos de ordenamiento
    Llamada: estaEnOrdenAscendente(secuencia)
    Precondición: secuencia.size > 0
    Poscondición: Devuelve true si la secuencia está ordenado en forma ascendente, y false en caso contrario
*/
fun estaEnOrdenAscendente(secuencia: Array<Number>): Boolean {
    for (i in 0 until secuencia.size - 1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia[i] > secuencia[i+1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    }
    }
    // Si termina el ciclo, la secuencia esta ordenada de forma ascendente
    return true
}

/*
    Funcion para calcular la media de tiempo de los algoritmos de ordenamiento
    Llamada: calcularMedia(tiempo)
    Precondición: tiempo.size > 0
    Poscondición: retorna el promedio de los elementos del arreglo tiempo
*/
fun calcularMedia(tiempo: Array<Long>): Double{
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += tiempo[i]
    }
    return suma / tiempo.size
}

/*
    Funcion para calcular la desviacion estandar de los tiempos
    Llamada: calcularDesviacionEstandar(tiempo, media)
    Precondición: tiempo.size > 1
    Poscondición: Devuelve la desviación estándar de los elementos del arreglo tiempo
*/
fun calcularDesviacionEstandar(tiempo: Array<Long>, media: Double): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += Math.pow((tiempo[i] - media), 2.0)
    }
    return Math.sqrt(suma / (tiempo.size))
}


/*
    Metodo principal
*/
fun main(args: Array<String>) {
    // Almacenamos los valores ingresados por consola	
    val t: Int = args[0].toInt() // Numero de intentos
    val n: Int = args[1].toInt() // Numero de elementos a ordenar
    val s = args[2]              // Tipo de secuencia 
    val a = args[3]              // Algoritmos a usar 


    // Creamos la secuencia de n números a ordenar
    val secuencia = crearSecuencia(s, n)

    // Obtenemos una lista con los algoritmos a implementar
    val algoritmos = a.split(",")

    // Por cada algoritmo de la lista de algoritmos
    for (algoritmo in algoritmos) {
        println("Comienzo de ejecución de algoritmo: $algoritmo")
        // Se realizan intentos de ordenamiento con el algoritmo actual y se mide el tiempo de ejecución
        val tiempoInsertionSort = Array<Long>(t, {0})
        for (i in 0 until t) {
            // Se copia la secuencia original para no modificarla
            var secuenciaCopia = secuencia.copyOf()
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            val tiempoInicial = System.nanoTime()
            insertionSort(secuenciaCopia)
            val tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                println("Error: la secuencia no está ordenada con el algoritmo $a")
                println("----------------------------------------")
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoInsertionSort[i] = (tiempoFinal - tiempoInicial)
        }

        /*
            Si la secuencia esta ordenada ascendentemente, se imprime un mensaje de ordenamiento exitoso, y se
            calcula la media y la desviacion estandar de los tiempos obtenidos
        */
        if (tiempoInsertionSort.size > 1) {
            val media: Double = calcularMedia(tiempoInsertionSort)
            val desviacionEstandar: Double = calcularDesviacionEstandar(tiempoInsertionSort, media)
            // Se divide entre 1000000000.0 para convertir el tiempo de nanosegundos a segundos
            println("Ordenamiento exitoso con algoritmo: $algoritmo en ${tiempoInsertionSort.size} intentos")
            println("Tiempo promedio de ejecución de $algoritmo: ${(media/1000000000.0).toDouble()} segundos")
            println("Desviación estándar de $algoritmo: ${(desviacionEstandar/1000000000.0).toDouble()} segundos")
        } 
        println("----------------------------------------")
        
    }
}
