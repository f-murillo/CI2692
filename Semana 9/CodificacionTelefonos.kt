import java.io.File

/**
 * Transforma una cadena de texto en un número según la codificación de teléfonos.
 *
 * @param it La cadena de texto a transformar.
 * @return El número resultante de la transformación.
 */
fun transformarANumero(it: String): String{
    // Creamos la variable a retornar al final
    var resultado = ""
    it.forEach { char ->
        // transformamos cada caracter en su respectivo número
        when (char) {
            'E', 'e' -> resultado += "0"
            'J', 'N', 'Q', 'j', 'n', 'q' -> resultado += "1"
            'R', 'W', 'X', 'r', 'w', 'x' -> resultado += "2"
            'D', 'S', 'Y', 'd', 's', 'y' -> resultado += "3"
            'F', 'T', 'f', 't' -> resultado += "4"
            'A', 'M', 'a', 'm' -> resultado += "5"
            'C', 'I', 'V', 'c', 'i', 'v' -> resultado += "6"
            'B', 'K', 'U', 'b', 'k', 'u' -> resultado += "7"
            'L', 'O', 'P', 'l', 'o', 'p' -> resultado += "8"
            'G', 'H', 'Z', 'g', 'h', 'z' -> resultado += "9"
        }  
    }
    return resultado
}

/**
 * Esta función recibe una cadena de caracteres 's' y genera todos los subconjuntos posibles de 's'.
 * 
 * @param s La cadena de caracteres de la cual se generarán los subconjuntos.
 * @param i El índice actual para recorrer la cadena.
 * @param subset El subconjunto actual que se está construyendo.
 * @param result La lista de subconjuntos generados hasta el momento.
 * 
 * @return Una lista de listas de subconjuntos generados a partir de 's'.
 */
fun obtenerSubconjuntos(s: String, i: Int = 0, subset: ListaDoblementeEnlazadaCircular<String> = ListaDoblementeEnlazadaCircular(), result: ListaDoblementeEnlazadaCircular<ListaDoblementeEnlazadaCircular<String>> = ListaDoblementeEnlazadaCircular()): ListaDoblementeEnlazadaCircular<ListaDoblementeEnlazadaCircular<String>> {
    if (i == s.length) {
        // Si hemos alcanzado el final de la cadena 's', creamos una nueva lista vacía.
        val nuevaLista = ListaDoblementeEnlazadaCircular<String>()
        // Iteramos sobre cada elemento del subconjunto y lo agregamos a la nueva lista.
        subset.forEach { elemento ->
            nuevaLista.agregarAlFinal(elemento)
        }
        // Agregamos la nueva lista al resultado final.
        result.agregarAlFinal(nuevaLista)
    } else {
        // Si no hemos alcanzado el final de la cadena 's', continuamos generando subconjuntos.
        for (j in i until s.length) {
            // Agregamos el subconjunto actual a 'subset' utilizando la función 'substring'.
            subset.agregarAlFinal(s.substring(i, j + 1))
            // Llamamos recursivamente a la función 'obtenerSubconjuntos' para generar los subconjuntos restantes.
            obtenerSubconjuntos(s, j + 1, subset, result)
            // Eliminamos el último elemento agregado al subconjunto para generar el siguiente subconjunto.
            subset.eliminarUltimo()
        }
    }
    // Devolvemos el resultado final que contiene todos los subconjuntos generados.
    return result
}

/**
 * Transforma un resultado de números en una palabra utilizando una tabla de codificación.
 *
 * @param resultado El resultado de números a transformar en palabra.
 * @param tabla La tabla de codificación que contiene las correspondencias entre números y palabras.
 * @param numeroOriginal El número original antes de la transformación.
 */
fun transformarAPalabra(resultado: String, tabla: HashTable, numeroOriginal: String) {
    // Obteniendo todos los subconjuntos posibles de la cadena 'resultado'
    val subconjuntos = obtenerSubconjuntos(resultado)
    // Creando una lista para almacenar combinaciones válidas
    val combinacionesValidas = ListaDoblementeEnlazadaCircular<String>()

    subconjuntos.forEach { subconjunto ->
        var combinacionesParciales = ListaDoblementeEnlazadaCircular<String>().apply { agregarAlFrente("") }
        var ultimoIndice = 0

        subconjunto.forEach { numero ->
            // Buscando las palabras correspondientes al número actual en la 'tabla'
            val palabras = tabla.buscar(numero)
            if (palabras.isNotEmpty()) {
                val nuevasCombinaciones = ListaDoblementeEnlazadaCircular<String>()
                combinacionesParciales.forEach { combinacion ->
                    palabras.forEach { palabra ->
                        nuevasCombinaciones.agregarAlFrente(if (combinacion.isEmpty()) palabra else "$combinacion $palabra")
                    }
                }
                combinacionesParciales = nuevasCombinaciones
                ultimoIndice += numero.length
            } else if (numero.length == 1 && ultimoIndice < resultado.length - 1) {
                val nuevasCombinaciones = ListaDoblementeEnlazadaCircular<String>()
                combinacionesParciales.forEach { combinacion ->
                    nuevasCombinaciones.agregarAlFrente("$combinacion $numero")
                }
                combinacionesParciales = nuevasCombinaciones
                ultimoIndice += numero.length
            }
        }

        if (ultimoIndice == resultado.length) {
            // Filtrando las combinaciones que contienen números consecutivos y cadenas vacías
            val combinacionesFiltradas = combinacionesParciales.filtrar { it.trim().isNotEmpty() && !it.contains(Regex("\\d\\s\\d")) }
            combinacionesValidas.addAllAlFrente(combinacionesFiltradas)
        }
    }

    // Imprimiendo las combinaciones válidas
    combinacionesValidas.forEach { combinacion ->
        println("$numeroOriginal: $combinacion")
    }
}

fun main(args: Array<String>){
    // Como no conocemos la cantidad de palabras en el diccionario, leemos todas las lineas
    val diccionario = File(args[1])
    val lines = diccionario.readLines()
    // Creamos la tabla de Hash para almacenar los valores
    val opciones = HashTable(lines.size)
    // A cada linea la transformamos en su respectivo número, y la mandamos a la hashtable
    lines.forEach {
        // Transformamos
        val resultado = transformarANumero(it)
        // Almacenamos
        opciones.guardar(it, resultado)
    }
    // Ahora leemos el archivo de teléfonos
    val telefonos = File(args[0])
    val lineas = telefonos.readLines()

    // Buscamos cada número en la tabla
    lineas.forEach {

        // Limpiamos el número telefónico para que sólo contenga números
        val numericString = it.filter { it.isDigit() }
        transformarAPalabra(numericString, opciones, it)
    }
}