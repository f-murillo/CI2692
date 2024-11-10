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
    when(tipoSecuencia){
	    "random" -> return genera_random(n)
	    "randomd" -> return genera_randomd(n)
	    "sorted" -> return genera_sorted(n)
	    "sortedd" -> return genera_sorted(n)
	    "inv" -> return genera_inv(n)
	    "zu" -> return genera_zu(n)
	    "media" -> return genera_media(n)
	    else ->{
        	// Si el tipo de secuencia no es ninguno de los anteriores, devuelve una secuencia ordenada por defecto
        	println("Error: Tipo de secuencia incorrecto. Se utilizará una secuencia ordenada")
        	return genera_sorted(n)		    
	    }
    }	
}

/**
* estaEnOrdenAscendente: Array<Int> -> Boolean
* Uso: estaEnOrdenAscendente(secuencia)
* Precondición: secuencia.size > 0
* Poscondición: Devuelve true si la secuencia está ordenado en forma ascendente
* y false en caso contrario.
*/
fun estaEnOrdenAscendente(secuencia: Array<Number>): Boolean {
    for (i in 0 until secuencia.size - 1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia[i] > secuencia[i + 1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    }
    }
    // Si no encontró ningún par de elementos consecutivos que no estén ordenados en forma ascendente
    return true
}

/*
    Funcion para calcular la desviacion estandar de los tiempos
    Llamada: calcularDesviacionEstandar(tiempo, media)
    Precondición: tiempo.size > 1
    Poscondición: Devuelve la desviación estándar de los elementos del arreglo tiempo
*/
fun calcularDesviacionEstandar(tiempo: Array<Double>, media: Double): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += Math.pow((tiempo[i] - media), 2.0)
    }
    return Math.sqrt(suma / (tiempo.size))
}


/*
    Funcion que imprime el mensaje de exito del algoritmo de ordenamiento, con su tiempo de ejecución
    Llamada: imprimeMensajeE(tipoOrdenamiento, tiempos)
    Precondicion: tipoOrdenamiento != "" && tiempos.size > 0 
    Postcondicion: imprime correctamente el mensaje
 */
fun imprimeMensajeE(tipoOrdenamiento: String, tiempo: Array<Double>){
    if (tiempo.size > 1) {
        val media = tiempo.average()
        val desviacionEstandar: Double = calcularDesviacionEstandar(tiempo, media)
        // Se divide entre 1000000000.0 para convertir el tiempo de nanosegundos a segundos
        println("Ordenamiento exitoso con $tipoOrdenamiento en ${tiempo.size} intentos")
        println("Tiempo promedio de ejecución de $tipoOrdenamiento: ${(media/1000000000.0).toDouble()} segundos")
        println("Desviación estándar de $tipoOrdenamiento: ${(desviacionEstandar/1000000000.0).toDouble()} segundos")
    } else {
        println("Ordenamiento exitoso con $tipoOrdenamiento")
        println("Tiempo de ejecución de $tipoOrdenamiento: ${(tiempo[0]/1000000000.0).toDouble()} segundos ")
    }
    println("----------------------------------------------------------------------------------------")
}


/*
    Funcion que escoge el algoritmo de ordenamiento a ejecutar, y obtiene sus tiempos de ejecución
    Llamada: escogeAlgoritmo(secuencia, algoritmo, intentos, algoritmosMap)
    Precondicion: secuencia.size > 0 && algoritmo != "" && intentos > 0 
    Postcondicion: retorna un arreglo con los tiempos de ejecución del algoritmo escogido
 */
fun escogeAlgoritmo(secuencia: Array<Number>, algoritmo: String, intentos: Int, algoritmosMap: Map<String, String>,): Array<Double> {
    val tiempoEjecucion = Array<Double>(intentos,{0.0})
    for (i in 0 until intentos) {
        val secuenciaCopia = secuencia.copyOf()
        val tiempoInicial = System.nanoTime()

        when(algoritmo){
            "is" -> insertionSort(secuenciaCopia)
            "bs" -> bubbleSort(secuenciaCopia)
            "ms" -> mergeSort(secuenciaCopia)
            "mi" -> mergeSortIt(secuenciaCopia)
            "hs" -> heapSort(secuenciaCopia)
            "qc" -> quicksort(secuenciaCopia)
            "qd" -> dualPivotQuicksort(secuenciaCopia)
            "cs" -> countingSort(secuenciaCopia)
            "rs" -> radixSort(secuenciaCopia)
        }
	
        val tiempoFinal = System.nanoTime()
	
        // Se verifica si la secuencia copia está ordenada
        if(!estaEnOrdenAscendente(secuenciaCopia)){
            // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
            println("Error: La secuencia no está ordenada con el algoritmo ${algoritmosMap["$algoritmo"]}")
            println("----------------------------------------------------------------------------------------")
            throw Exception("")
        }
        //Si la secuencia esta ordenada ascendentemente, se guarda el tiempo de ejecucion
        tiempoEjecucion[i] = (tiempoFinal - tiempoInicial).toDouble()
    }

    // Retorna el arreglo que contiene los tiempos de ejecucion 
    return tiempoEjecucion

}

fun main(args: Array<String>) {

    // Primeramente, debemos verificar que los parámetros ingresados sean correctos.

    // creamos una lista que contenga los tipos de secuencia válidos
    val secuenciasValidas = arrayOf("random", "randomd", "sorted", "sortedd", "inv", "zu", "media")

    // creamos una variable de tipo Map para los algoritmos
    val algoritmosMap = mapOf(
        "is" to "Insertion Sort", 
        "bs" to "Bubble Sort",
        "ms" to "Merge Sort",
        "mi" to "Merge Sort Iterativo",
        "hs" to "Heap Sort",
        "qc" to "Quick Sort Clasico",
        "qd" to "Quick Sort con Pivotes Duales",
        "cs" to "Counting Sort",
        "rs" to "Radix Sort"
    )

    var secuencia: String? = null
    var algoritmos: Array<String>? = null
    var intentos: Int? = null
    var tamanos: Array<Number>? = null
    var grafico: String? = null

    // Ahora, verificamos las entradas ingresadas como parámetros
    var i = 0
    while (i < args.size) {
        when (args[i]) {
            "-s" -> {
                if (i + 1 < args.size) {
                    secuencia = args[i + 1]
                    i += 2
                } else {
                    println("Error: El parámetro ${args[i]} requiere un valor.")
                    return
                }
            }
            "-a" -> {
                if (i + 1 < args.size) {
                    algoritmos = args[i + 1].split(",").toTypedArray()
                    i += 2
                } else {
                    println("Error: El parámetro ${args[i]} requiere un valor.")
                    return
                }
            }
            "-t" -> {
                if (i + 1 < args.size) {
                    intentos = args[i + 1].toIntOrNull()
                    i += 2
                } else {
                    println("Error: El parámetro ${args[i]} requiere un valor.")
                    return
                }
            }
            "-n" -> {
                if (i + 1 < args.size) {
                    tamanos = args[i + 1].split(",").mapNotNull { it.toIntOrNull() }.toTypedArray()
                    i += 2
                } else {
                    println("Error: El parámetro ${args[i]} requiere un valor.")
                    return
                }
            }
            "-g" -> {
                if (i + 1 < args.size) {
                    grafico = args[i + 1]
                    i += 2
                } else {
                    println("Error: El parámetro ${args[i]} requiere un valor.")
                    return
                }
            }
            else -> {
                println("Error: Parámetro desconocido ${args[i]}.")
                return
            }
        }
    }

    if (secuencia == null || algoritmos == null || intentos == null || tamanos == null) {
        println("Error: Faltan parámetros obligatorios.")
        return
    }

    if (intentos <= 0) {
        println("Error: El valor de -t debe ser un entero positivo.")
        return
    }

    if (!secuencia.split(",").all { it in secuenciasValidas }) {
        println("Error: Algunas de las secuencias proporcionadas no son válidas.")
        return
    }

    if (!algoritmos.all { it in algoritmosMap.keys }) {
        println("Error: Uno o varios de los algoritmos proporcionados no son válidos.")
        return
    }

    if (tamanos.size < 2 && grafico != null) {
        println("Error: Para generar un gráfico se necesitan al menos dos tamaños de secuencia.")
        return
    }

    if (grafico != null && grafico.contains(".png")){
        println("Error: El parámetro del archivo no debe contener la extensión, sólo el nombre")
        return
    }
    
    if (!estaEnOrdenAscendente(tamanos)) {
        println("Error: Los tamaños de las secuencias deben estar en orden ascendente.")
        return
    }

    // Luego de verificar los parámetros, comenzamos a ejecutar los algoritmos
    
    // Primeramente, creamos cada variable que contendrá los parámetros del gráfico
    val algorithmLabels = ArrayList<String>()
    val numElements = ArrayList<Int>()
    val minTimes = ArrayList<Double>()
    val averageTimes = ArrayList<Double>()
    val maxTimes = ArrayList<Double>()

    for (algoritmo in algoritmos) {
        // Creamos la lista que contiene los labels de los algoritmos, ordenados
        repeat(tamanos.size){
            algorithmLabels.add(algoritmosMap[algoritmo]!!)
        }
        
        // Ahora, creamos la lista que contiene los tamaños de las entradas
        for (n in tamanos) {
            numElements.add(n.toInt())
        }

        // por cada algoritmo, presentamos la secuencia, su tipo y su tamaño, y ejecutamos
        println("\nEjecución de algoritmo: ${algoritmosMap[algoritmo]}")
        println("----------------------------------------------------------------------------------------")

        for (n in tamanos) {
            var sec = crearSecuencia(secuencia, n.toInt())
            println("Secuencia de tipo $secuencia de $n elementos")
            println("----------------------------------------------------------------------------------------")

            // Calculamos el tiempo de ejecución del algoritmo actual con la secuencia actual
            val tiempos = escogeAlgoritmo(sec, algoritmo, intentos, algoritmosMap)
            imprimeMensajeE(algoritmosMap["$algoritmo"]!!,tiempos)

            // Ahora, debemos añadir a los arreglos el tiempo mínimo, máximo y promedio de todos los intentos
            // convirtiendo el tiempo a segundos.
            minTimes.add(tiempos.min()/1000000000.0)
            maxTimes.add(tiempos.max()/1000000000.0)
            averageTimes.add(tiempos.average()/1000000000.0)
        }
    }

    // Ahora, graficamos con los datos obtenidos
    if (grafico != null){

        plotRuntime(
            "Graficas de tiempo de algoritmos de ordenamiento",
            ".",
            "$grafico.png",
            "Resultados de la secuencia de tipo $secuencia",
            "Número de elementos",
            "Tiempo (seg)",
            algorithmLabels.toTypedArray(),
            numElements.toTypedArray(),
            minTimes.toTypedArray(),
            averageTimes.toTypedArray(),
            maxTimes.toTypedArray()
        )
    }
  
}
