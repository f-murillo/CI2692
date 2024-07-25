import java.io.File

class ListaReproduccion {
    var contenido: ArbolDeCanciones = ArbolDeCanciones()

    /**
     * Agrega una lista de reproducción desde un archivo al contenido de la lista actual.
     *
     * @param na La ruta del archivo que contiene la lista de reproducción.
     */
    fun agregarLista(na: String) {
        try {
            val lineas = File(na).readLines()
            for (linea in lineas) {
                val campos = linea.split(";")
                if (campos.size == 3) {
                    val cancion = Cancion(campos[1], campos[0], campos[2])
                    contenido.insertar(cancion)
                } else {
                    throw IllegalArgumentException("Error: Formato de línea incorrecto en el archivo $na: $linea")
                }
            }
        } catch (e: Exception) {
            println("Error al leer el archivo: ${e.message}")
        }
    }

    /**
     * Elimina una canción de la lista de reproducción.
     *
     * @param i El identificador de la canción.
     * @param t El título de la canción.
     */
    fun eliminarCancion(i: String, t: String) {
        val cancion = contenido.buscarCancion(t, i)
        if (cancion != null){
            contenido.eliminar(cancion)
        } else {
            println("La canción no está en la lista de reproducción actual.\n")
        }
    }

    /**
     * Obtiene la lista de reproducción.
     *
     * @return La lista de reproducción como una instancia de [ListaDoblementeEnlazadaCircular] de [Cancion].
     */
    fun obtenerLR(): ListaDoblementeEnlazadaCircular<Cancion> {
        return contenido.deArbolASecuencia(contenido.raiz)
    }

    /**
     * Muestra todas las canciones de la lista de reproducción.
     */
    fun mostrarLR() {
        val canciones = obtenerLR()
        for (cancion in canciones) {
            println(cancion.toString())
        }
    }
}