import java.io.File

/**
 * Clase que representa una canción.
 *
 * @param t El título de la canción.
 * @param i El intérprete de la canción.
 * @param u La ubicación de la canción.
 */
class Cancion(t: String, i: String, u: String) {
    val titulo: String
    val interprete: String
    val ubicacion: String

    /**
     * Inicializa una instancia de la clase Cancion.
     *
     * @param t El título de la canción.
     * @param i El intérprete de la canción.
     * @param u La ubicación de la canción.
     * @throws IllegalArgumentException Si alguno de los argumentos es inválido.
     */
    init {
        if (t.isEmpty() || i.isEmpty() || u.isEmpty() || !esUbicacionValida(u)) {
            throw IllegalArgumentException("Los argumentos no son válidos")
        }
        titulo = t
        interprete = i
        ubicacion = u
    }

    /**
     * Verifica si la ubicación de un archivo es válida.
     *
     * @param ubicacion La ubicación del archivo.
     * @return `true` si la ubicación es válida, `false` de lo contrario.
     */
    fun esUbicacionValida(ubicacion: String): Boolean {
        val archivo = File(ubicacion)
        return archivo.exists() && archivo.isFile && archivo.canRead() && ubicacion.endsWith(".mp3")
    }

    /**
     * Obtiene el título de la canción.
     *
     * @return El título de la canción.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Obtiene el nombre del intérprete de la canción.
     *
     * @return El nombre del intérprete de la canción.
     */
    fun obtenerInterprete(): String {
        return interprete
    }

    /**
     * Obtiene la ubicación de la canción.
     *
     * @return La ubicación de la canción.
     */
    fun obtenerUbicacion(): String {
        return ubicacion
    }

    /**
     * Devuelve una representación en forma de cadena de la canción.
     *
     * @return La representación en forma de cadena de la canción.
     */
    override fun toString(): String {
        return "Título: $titulo, Intérprete: $interprete"
    }
}
