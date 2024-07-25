import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


/*
    Como cada ciudad tiene asociado un indice, una coordenada X, una coordenada Y, y un beneficio,
    se crea una clase de datos de tipo ciudad, con 4 valores
*/
data class Ciudad<a, b, c, d>(val indice: a, val x: b, val y: c, val beneficio: d)

// Función para calcular la distancia entre dos ciudades
fun distancia(ciudad1: Ciudad<Int, Int, Int, Int>, ciudad2: Ciudad<Int, Int, Int, Int>): Double {

    val dx = ciudad2.x - ciudad1.x
    val dy = ciudad2.y - ciudad1.y
    return Math.sqrt(((dx * dx) + (dy * dy)).toDouble())
}

// Función para calcular la ganancia de una ruta
fun ganancia(ruta: List<Ciudad<Int, Int, Int, Int>>): Double {

    var premioTotal = 0.0
    var costoTotal = 0.0

    for (i in 0 until ruta.size) {
        premioTotal += ruta[i].beneficio
        if (i > 0) costoTotal += distancia(ruta[i - 1], ruta[i])
    }

    return premioTotal - costoTotal
}

/*
    Funcion que crea las particiones para aplicar Quicksort sobre las ciudades de la lista en funcion
    de la coordenada X
*/
fun partition(ciudades: MutableList<Ciudad<Int,Int,Int,Int>>, low: Int, high: Int): Int {
    val pivot = ciudades[high].x
    var i = low - 1

    for (j in low until high) {
        if (ciudades[j].x < pivot) {
            i++

            val temp = ciudades[i]
            ciudades[i] = ciudades[j]
            ciudades[j] = temp
        }
    }

    val temp = ciudades[i + 1]
    ciudades[i + 1] = ciudades[high]
    ciudades[high] = temp

    return i + 1
}

/*
    Funcion que ordena las ciudades de la lista en funcion de la coordenada x usando Quicksort
*/
fun quicksort(ciudades: MutableList<Ciudad<Int,Int,Int,Int>>, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(ciudades, low, high)

        quicksort(ciudades, low, pi - 1)
        quicksort(ciudades, pi + 1, high)
    }
}

fun resolverMiniPRMB(
        ciudades: List<Ciudad<Int, Int, Int, Int>>
): Pair<List<Ciudad<Int, Int, Int, Int>>, Double> {
    var solucion = Pair<List<Ciudad<Int, Int, Int, Int>>, Double>(emptyList(), 0.0)

    // Si solo hay una ciudad, la ruta óptima es simplemente esa ciudad
    if (ciudades.size == 1) {
        solucion =
                solucion.copy(
                        first = listOf(ciudades[0]),
                        second = ciudades[0].beneficio.toDouble()
                )
        return solucion
    }

    // Si hay dos ciudades
    // Comparamos la ganancia entre las ciudades con la recompensa de cada ciudad
    if (ciudades.size == 2) {

        val gananciaEntreCiudades =
                (ciudades[0].beneficio + ciudades[1].beneficio) -
                        distancia(ciudades[0], ciudades[1])

        if (ciudades[0].beneficio >= ciudades[1].beneficio) {
            // Si la ganancia entre las ciudades es menor o igual a la recompensa de la primera
            // ciudad,
            // la ruta óptima es simplemente la primera ciudad
            if (ciudades[0].beneficio >= gananciaEntreCiudades) {
                solucion =
                        solucion.copy(
                                first = listOf(ciudades[0]),
                                second = ciudades[0].beneficio.toDouble()
                        )
                return solucion
            }
            // Si no, la ruta óptima es la ruta entre las dos ciudades
            solucion =
                    solucion.copy(
                            first = listOf(ciudades[0], ciudades[1]),
                            second = gananciaEntreCiudades
                    )
            return solucion
        } else {
            // Análogo al caso anterior
            if (ciudades[1].beneficio >= gananciaEntreCiudades) {
                solucion =
                        solucion.copy(
                                first = listOf(ciudades[1]),
                                second = ciudades[1].beneficio.toDouble()
                        )
                return solucion
            }

            solucion =
                    solucion.copy(
                            first = listOf(ciudades[0], ciudades[1]),
                            second = gananciaEntreCiudades
                    )
            return solucion
        }
    }

    // Si hay tres ciudades, se prueban todas las posibles rutas, incluyendo las que tienen menos
    // ciudades
    // y se elige la de mayor ganancia

    // Comenzamos evaluando cada ciudad por separado
    var maxIndividual = Pair(listOf(ciudades[0]), ciudades[0].beneficio.toDouble())
    ciudades.forEach({ ciudad ->
        if (ciudad.beneficio > maxIndividual.second) {
            maxIndividual = Pair(listOf(ciudad), ciudad.beneficio.toDouble())
        }
    })

    // Luego evaluamos las rutas entre dos ciudades
    var maxEntreDos =
            Pair(
                    listOf(ciudades[0], ciudades[1]),
                    (ciudades[0].beneficio + ciudades[1].beneficio) -
                            distancia(ciudades[0], ciudades[1])
            )
    if (ciudades[0].beneficio >= ciudades[1].beneficio) {
        if (ciudades[0].beneficio >= maxEntreDos.second) {
            maxEntreDos = Pair(listOf(ciudades[0]), ciudades[0].beneficio.toDouble())
        }
    } else {
        if (ciudades[1].beneficio >= maxEntreDos.second) {
            maxEntreDos = Pair(listOf(ciudades[1]), ciudades[1].beneficio.toDouble())
        }
    }

    // Finalmente, evaluamos las rutas entre las tres ciudades
    val rutas =
            listOf(
                    listOf(ciudades[0], ciudades[1], ciudades[2]),
                    listOf(ciudades[0], ciudades[2], ciudades[1]),
                    listOf(ciudades[1], ciudades[0], ciudades[2]),
                    listOf(ciudades[1], ciudades[2], ciudades[0]),
                    listOf(ciudades[2], ciudades[0], ciudades[1]),
                    listOf(ciudades[2], ciudades[1], ciudades[0])
            )

    var rutasEntreTres = rutas[0]
    var gananciaEntreTres = ganancia(rutasEntreTres)

    for (ruta in rutas) {
        val ganancia = ganancia(ruta)
        if (ganancia > gananciaEntreTres) {
            rutasEntreTres = ruta
            gananciaEntreTres = ganancia
        }
    }
    val maxEntreTres = Pair(rutasEntreTres, gananciaEntreTres)

    // Se elige la ruta con mayor ganancia
    solucion = listOf(maxIndividual, maxEntreDos, maxEntreTres).maxBy { it.second }
    return solucion
}

fun combinarRutas(ruta1: Pair<List<Ciudad<Int,Int,Int,Int>>,Double>, ruta2: Pair<List<Ciudad<Int,Int,Int,Int>>,Double>): Pair<List<Ciudad<Int,Int,Int,Int>>,Double> {

    // Se inicializa la ruta conectada con la primera mitad de la ruta 1
    val rutaConectada = ArrayList(ruta1.first.subList(0, ruta1.first.size / 2))

    // Se inicializa la ganancia máxima con la ganancia de la ruta conectada
    var gananciaMaxima: Double

    // Se añade la primera ciudad de la segunda ruta a la ruta conectada
    if(ruta1.first.size/2 - 1 >= 0){
        rutaConectada.add(ruta2.first[0])
    }
    // Se añaden el resto de las ciudades de la segunda ruta a la ruta conectada
    for (i in 1 until ruta2.first.size) {
        rutaConectada.add(ruta2.first[i])
    }
    // Se añade una conexión desde la última ciudad de la segunda ruta a la primera ciudad de la segunda mitad de la ruta 1
    rutaConectada.add(ruta1.first[ruta1.first.size / 2])

    // Se añaden el resto de las ciudades de la segunda mitad de la ruta 1 a la ruta conectada
    for (i in ruta1.first.size / 2 + 1 until ruta1.first.size) {
        rutaConectada.add(ruta1.first[i])
    }
    
    gananciaMaxima = ganancia(rutaConectada)

    val solucion = Pair(rutaConectada, gananciaMaxima)
    return solucion
}


fun divideAndConquerPRMB(
        ciudades: List<Ciudad<Int, Int, Int, Int>>
): Pair<List<Ciudad<Int, Int, Int, Int>>, Double> {

    // Si el problema es suficientemente pequeño, se resuelve de manera ad-hoc
    if (ciudades.size <= 3) {
        return resolverMiniPRMB(ciudades)
    }

    // Se divide el problema en dos subproblemas
    val mitad = ciudades.size / 2
    val ciudadesIzquierda = ciudades.subList(0, mitad)
    val ciudadesDerecha = ciudades.subList(mitad, ciudades.size)

    // Se resuelven los subproblemas de manera recursiva
    val rutaIzquierda = divideAndConquerPRMB(ciudadesIzquierda)
    val rutaDerecha = divideAndConquerPRMB(ciudadesDerecha)

    // Se combinan las soluciones de los subproblemas
    return combinarRutas(rutaIzquierda, rutaDerecha)
}

/*
    Metodo Main
*/
fun main(args: Array<String>) {
    //  Se recibirán como parametro el archivo de entrada (args[0])
    val archivoInstancia = File(args[0])
    // Se crea el objeto lector
    val lector = BufferedReader(FileReader(archivoInstancia))

    // Se lee la primera linea, que contiene el numero de ciudades
    val n = lector.readLine().toInt()
    // Creamos un arreglo de pares de enteros para almacenar las coordenadas de las ciudades
    val ciudades = MutableList(n) { Ciudad<Int, Int, Int, Int>(0, 0, 0, 0) }
    for (i in 0 until n) {
        val ciudad = lector.readLine().trim().split(" ")

        val indice = i
        val x = ciudad[0].trim().toInt()
        val y = ciudad[1].trim().toInt()
        val beneficio = ciudad[2].trim().toInt()
        ciudades[i] = Ciudad<Int, Int, Int, Int>(indice, x, y, beneficio)
    }
    lector.close()

    // Ordenamos la lista de ciudades en función de la coordenada X
    val ciudadesCopia = ArrayList(ciudades)
    quicksort(ciudadesCopia,0,ciudadesCopia.size-1)

    val simboloD = DecimalFormatSymbols()
    simboloD.decimalSeparator = '.'
    val formatoD = DecimalFormat("#.####",simboloD)

    val solucionPRMB = divideAndConquerPRMB(ciudadesCopia)
    val rutaSolucion = solucionPRMB.first.map{ciudad -> ciudad.indice}
    val gananciaSolucion = formatoD.format(solucionPRMB.second)

    println("${rutaSolucion.joinToString(" ")}")

    println("$gananciaSolucion")
}
