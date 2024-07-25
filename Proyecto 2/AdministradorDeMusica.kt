import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.Scanner

class AdministradorDeMusica {
    private val listaReproduccion = ListaReproduccion()
    private var listaCanciones = listaReproduccion.obtenerLR()
    var reproductor: Reproductor? = null
    var cancionActual: Cancion? = null

    /**
     * Carga una lista de reproducción desde un archivo especificado por el usuario.
     * 
     * El método solicita al usuario que ingrese el nombre del archivo de la lista de canciones.
     * Si el archivo existe, se agrega la lista de reproducción y se actualiza la lista de canciones.
     * Si el archivo no existe, se muestra un mensaje de error.
     * 
     * @throws FileNotFoundException si el archivo especificado no se encuentra.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    fun cargarListaDeReproduccion() {
        println("Introduce el nombre del archivo de la lista de canciones:")
        val nombreArchivo = readLine() ?: ""
        try {
            if (File(nombreArchivo).exists()) {
                listaReproduccion.agregarLista(nombreArchivo)
                println("Lista de reproducción cargada con éxito.\n")
                listaCanciones = listaReproduccion.obtenerLR()
            } else {
                println("El archivo $nombreArchivo no existe.")
            }
        } catch (e: FileNotFoundException) {
            println("El archivo especificado no fue encontrado.")
        } catch (e: IOException) {
            println("Ocurrió un error al leer el archivo.")
        }
    }

    
    /**
     * Muestra la lista de reproducción.
     * Si la lista de canciones está vacía, imprime "La lista de reproducción está vacía."
     * De lo contrario, muestra la lista de reproducción y agrega un salto de línea al final.
     */
    fun mostrarListaDeReproduccion() {
        if (listaCanciones.estaVacia()) {
            println("La lista de reproducción está vacía.")
        } else {
            listaReproduccion.mostrarLR()
            println("")
        }
    }

    /**
     * Elimina una canción de la lista de reproducción.
     * Si la lista de reproducción está vacía, muestra un mensaje indicando que está vacía.
     * De lo contrario, muestra la lista de reproducción actual y solicita al usuario que ingrese el intérprete y el título de la canción a eliminar.
     * Luego, elimina la canción de la lista de reproducción y actualiza la lista de canciones.
     */
    fun eliminarCancion() {
        if (listaCanciones.estaVacia()) {
            println("La lista de reproducción está vacía.\n")
        } else {
            mostrarListaDeReproduccion()
            println("Introduce el intérprete de la canción a eliminar:")
            val interprete = readLine() ?: ""
            println("Introduce el título de la canción a eliminar:")
            val titulo = readLine() ?: ""
            println("")
            listaReproduccion.eliminarCancion(interprete, titulo)
            listaCanciones = listaReproduccion.obtenerLR()
        }
    }


    /**
     * Reproduce la canción actual de la lista de reproducción.
     * Si la lista de canciones está vacía, muestra un mensaje indicando que está vacía.
     * Si el reproductor no está reproduciendo ninguna canción, reproduce la canción actual y muestra un mensaje indicando la canción que se está reproduciendo.
     * Si el reproductor está reproduciendo una canción, crea un nuevo reproductor con la canción actual y lo reproduce, mostrando un mensaje indicando la canción que se está reproduciendo.
     */
    fun reproducir() {
        if (listaCanciones.estaVacia()) {
            println("La lista de reproducción está vacía.")
        } else if (reproductor?.estaTocandoCancion() == false) {
            cancionActual = listaCanciones.actual()
            println("Reproduciendo: ${cancionActual.toString()}")
            reproductor?.reanudar()
        } else {
            cancionActual = listaCanciones.actual()
            if (cancionActual != null) {
                reproductor = Reproductor(cancionActual!!)
                reproductor?.reproducir()
                println("Reproduciendo: ${cancionActual.toString()}")
            }
        }
    }

    /**
     * Pausa la reproducción de la canción actual si hay una canción reproduciéndose.
     * Si no hay ninguna canción reproduciéndose, se muestra un mensaje indicando esto.
     */
    fun pausar() {
        if (reproductor?.estaTocandoCancion() == true) {
            reproductor?.pausa()
        } else {
            println("No hay ninguna canción reproduciéndose en este momento.")
        }
    }

    /**
     * Reanuda la reproducción de la canción actual si no está reproduciendo ninguna canción.
     * Si ya hay una canción reproduciéndose, se muestra un mensaje indicando esto.
     */
    fun reanudar() {
        if (reproductor?.estaTocandoCancion() == false) {
            reproductor?.reanudar()
        } else {
            println("Ya hay una canción reproduciéndose.")
        }
    }

    /**
     * Detiene la reproducción de la canción actual.
     * Si hay una canción reproduciéndose, se detiene.
     * Si no hay ninguna canción reproduciéndose, se muestra un mensaje indicando que no hay ninguna canción reproduciéndose en este momento.
     */
    fun parar() {
        if (reproductor?.estaTocandoCancion() == true) {
            reproductor?.parar()
        } else {
            println("No hay ninguna canción reproduciéndose en este momento.")
        }
    }

    /**
     * Reproduce la próxima canción en la lista de reproducción.
     * Si la lista de canciones está vacía, se muestra un mensaje de error.
     * Si hay canciones en la lista, se detiene la reproducción actual, se selecciona la siguiente canción
     * y se reproduce utilizando el reproductor de música.
     * Si no hay más canciones en la lista, se reinicia la lista de reproducción.
     */
    fun proximaCancion() {
        if (listaCanciones.estaVacia()) {
            println("La lista de reproducción está vacía.")
        } else {
            reproductor?.parar()
            val cancion = listaCanciones.siguiente()
            if (cancion != null) {
                cancionActual = cancion
                reproductor = Reproductor(cancion)
                reproductor?.reproducir()
                println("Reproduciendo: ${cancionActual.toString()}")
            } else {
                println("No hay más canciones en la lista de reproducción.\n")
                listaCanciones.primera() // Reinicia la lista de reproducción si es necesario
            }
        }
    }
}

/**
 * Función principal del programa.
 * Permite interactuar con el administrador de música a través de un menú de opciones.
 */
fun main() {
    val scanner = Scanner(System.`in`)
    val adm = AdministradorDeMusica()

    do {
        println(
                """
        |1. Cargar lista de reproducción.
        |2. Mostrar lista de reproducción.
        |3. Eliminar canción.
        |4. Reproducir.
        |5. Pausar.
        |6. Parar.
        |7. Próxima canción.
        |8. Salir del administrador de música.
        |
        |Selecciona una opción: """.trimMargin()
        )
        var opcion: Int = 0
        try {
            opcion = scanner.nextInt()
            println("")
        } catch (e: Exception) {
            println("Por favor, introduce un número válido.")
            scanner.nextLine() // Limpiar el buffer del scanner
            continue
        }

        when (opcion) {
            1 -> adm.cargarListaDeReproduccion()
            2 -> adm.mostrarListaDeReproduccion()
            3 -> adm.eliminarCancion()
            4 -> adm.reproducir()
            5 -> adm.pausar()
            6 -> adm.parar()
            7 -> adm.proximaCancion()
            8 -> println("Saliendo del administrador de música...")
            else -> println("Opción no válida, intenta de nuevo.")
        }
    } while (opcion != 8)
    scanner.close()
}
