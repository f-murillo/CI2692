import java.io.FileInputStream
import java.io.FileNotFoundException

class Reproductor(c: Cancion) {
    var actual: Cancion = c
    private var player: PausablePlayer? = null
    private var estaReproduciendo: Boolean =
            false // Para verificar si se esta reproduciendo o no una cancion

    init {
        cargarCancion(c)
    }

    /**
     * Carga una canción en el reproductor.
     *
     * @param c La canción a cargar.
     */
    fun cargarCancion(c: Cancion) {
        try {
            parar() // Detiene la reproducción actual si existe
            actual = c
            player = PausablePlayer(FileInputStream(c.obtenerUbicacion()))
            estaReproduciendo = false
        } catch (e: FileNotFoundException) {
            println("Error al cargar la canción: ${e.message}")
        }
    }

    /**
     * Reproduce el archivo de audio.
     * Si ocurre un error durante la reproducción, se imprime un mensaje de error y se establece el estado de reproducción a falso.
     */
    fun reproducir() {
        try {
            player?.play()
            estaReproduciendo = true
        } catch (e: Exception) {
            println("Error al reproducir: ${e.message}")
            estaReproduciendo = false
        }
    }

    /**
     * Detiene la reproducción actual.
     * 
     * Esta función detiene el reproductor de audio actual, cierra el recurso y establece el reproductor a nulo.
     * Además, establece la variable `estaReproduciendo` a `true`.
     */
    fun parar() {
        player?.stop()
        player?.close()
        player = null
        estaReproduciendo = true
    }

    /**
     * Pausa la reproducción actual.
     * 
     * Esta función pausa la reproducción actual del reproductor de música.
     * Si no hay ninguna canción reproduciéndose, no tiene ningún efecto.
     * 
     * @return Unit
     */
    fun pausa() {
        player?.pause()
        estaReproduciendo = false
    }

    /**
     * Reanuda la reproducción del reproductor.
     */
    fun reanudar() {
        player?.resume()
        estaReproduciendo = true
    }

    /**
     * Devuelve un valor booleano que indica si se está reproduciendo una canción.
     *
     * @return true si se está reproduciendo una canción, false de lo contrario.
     */
    fun estaTocandoCancion(): Boolean {
        return estaReproduciendo
    }
}
