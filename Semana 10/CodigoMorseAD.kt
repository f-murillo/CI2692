/*
 * Clase CodigoMorse. Representa el TAD Codigo Morse 
 */
public class CodigoMorseAD(){

    // Arbol de decision binario que representa el alfabeto en morse
    private val alfabeto = ArbolDeDecision()

    // Metodos de la clase

    /*
     * init
     * Metodo constructor que crea un arbol de decision binario con todas las letras del alfabeto en morse
     */
    init {
        alfabeto.agregar(NodoAD('E', "."))
        alfabeto.agregar(NodoAD('T', "-"))
        alfabeto.agregar(NodoAD('I', ".."))
        alfabeto.agregar(NodoAD('A', ".-"))
        alfabeto.agregar(NodoAD('N', "-."))
        alfabeto.agregar(NodoAD('M', "--"))
        alfabeto.agregar(NodoAD('S', "..."))
        alfabeto.agregar(NodoAD('U', "..-"))
        alfabeto.agregar(NodoAD('R', ".-."))
        alfabeto.agregar(NodoAD('W', ".--"))
        alfabeto.agregar(NodoAD('D', "-.."))
        alfabeto.agregar(NodoAD('K', "-.-"))
        alfabeto.agregar(NodoAD('G', "--."))
        alfabeto.agregar(NodoAD('O', "---"))
        alfabeto.agregar(NodoAD('H', "...."))
        alfabeto.agregar(NodoAD('V', "...-"))
        alfabeto.agregar(NodoAD('F', "..-."))
        alfabeto.agregar(NodoAD('L', ".-.."))
        alfabeto.agregar(NodoAD('P', ".--."))
        alfabeto.agregar(NodoAD('J', ".---"))
        alfabeto.agregar(NodoAD('B', "-..."))
        alfabeto.agregar(NodoAD('X', "-..-"))
        alfabeto.agregar(NodoAD('C', "-.-."))
        alfabeto.agregar(NodoAD('Y', "-.--"))
        alfabeto.agregar(NodoAD('Z', "--.."))
        alfabeto.agregar(NodoAD('Q', "--.-"))
    }

    /* 
     * Metodo que recibe un codigo morse y devuelve la letra correspondiente,
     * o null si el codigo morse no es valido.
     * El metodo hace uso de la funcion buscar de la clase ArbolDeDecision
     */
    fun decodificarLetra(codigo: String): Char?{
        return alfabeto.buscar(codigo)
    }

    /*
     * Metodo que recibe un mensaje en codigo morse y devuelve el mensaje en texto correspondiente,
     * o un mensaje vacio si el mensaje en codigo morse no es valido
     */
    fun decodificarMensaje(mensaje: String): String{
        // Se separan las palabras del mensaje, el mensaje se separa por espacios y las palabras por "/"
        val palabras = mensaje.uppercase().split("/")
        // Se inicializa el mensaje decodificado.
        var mensajeDecodificado = ""
        // Si hay un caracter que no sea '.', '-' o espacio, se devuelve un mensaje vacio
        for (palabra in palabras) {
            for (letra in palabra) {
                if (letra != '.' && letra != '-' && letra != ' ')
                    return ""
                
            }
        }
        // Se recorren las palabras del mensaje
        for (palabra in palabras){
            // Se separan las letras de la palabra, la palabra se separa por espacios
            val letras = palabra.trim().split(" ")
            // Se recorren las letras de la palabra.
            for (letra in letras){
                // Se decodifica la letra
                val letraDecodificada: Char? = decodificarLetra(letra.trim())
                // Si la letra no se encuentra en el alfabeto, se devuelve un mensaje vacio
                if (letraDecodificada == null) 
                    return ""
                
                // En caso contrario, se agrega la letra decodificada al mensaje decodificado
                mensajeDecodificado += letraDecodificada
            }
            // Se agrega un espacio al mensaje decodificado para separar las palabras
            mensajeDecodificado += " "
        }
        return mensajeDecodificado.trim().lowercase()
    }

    // Metodo que devuelve la representacion en String del alfabeto en morse
    override fun toString(): String {
        return alfabeto.toString()
    }
}
