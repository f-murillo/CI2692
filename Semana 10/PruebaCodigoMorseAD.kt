fun main(args: Array<String>) {
    val codigo = CodigoMorseAD()
    val texto = args.joinToString(" ")
    val mensajeDecodificado = codigo.decodificarMensaje(texto)
    if (mensajeDecodificado == "") {
        println("Error: El codigo morse ingresado no es valido.")
        return
    }
    println(mensajeDecodificado)
}
