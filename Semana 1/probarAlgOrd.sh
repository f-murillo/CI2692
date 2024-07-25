#!/usr/bin/bash

# Función que verifica si la clase ingresada es correcta
verifySec(){
    # Creamos una lista con las clases correctas
    clases=("random", "randomd", "sorted", "sortedd", "inv", "zu", "media")

    # Se comprueba si la clase ingresada está en la lista
    if ! [[ "${clases[@]}" =~ "${secclass}" ]]; then
        # Si no está, mostramos mensaje y terminamos el programa
        echo "La clase de secuencia ingresada no existe, por favor, ingrese una clase válida."
        exit 1
    fi
}

# Funcion que verifica si los parámetros númericos tienen datos válidos (es decir, son numeros)
verifyNums(){
    # Si el parámetro -t no contiene un número, se muestra error y terminamos el programa
    if ! [[ $trynum =~ ^[0-9]+$ ]]; then
        echo "Los parámetros -t y -n deben ser números."
        exit 1
    # Si el parámetro -n no contiene un número, se muestra error y terminamos el programa
    elif ! [[ $elemnum =~ ^[0-9]+$ ]]; then 
        echo "Los parámetros -t y -n deben ser números."
        exit 1
    fi
}

# Obtenemos los parámetros de entrada
while getopts ":t:a:s:n:" OPTION; do
    case "$OPTION" in

        # Numero de intentos de ejecución de algoritmos
        t) trynum=$OPTARG;;

        # Numero de elementos de las secuencias
        n) elemnum=$OPTARG;;
        
        # Clase de secuencia
        s) secclass=$OPTARG ;;

        # Algoritmos de ordenamiento
        a) algos=$OPTARG ;;
    esac
done

verifyInput(){
    # Si algún parámetro está vacío, detenemos la ejecución del script
    if [ -z "$trynum" ]; then
        echo "Alguno de los parámetros está vacío, el uso es >./probarAlOrd.sh [-t #num] [-a <algoritmos>] [-s <secuencia>] [-n #num]"
        exit 1 
    elif [ -z "$elemnum" ]; then
        echo "Alguno de los parámetros está vacío, el uso es >./probarAlOrd.sh [-t #num] [-a <algoritmos>] [-s <secuencia>] [-n #num]"
        exit 1
    elif [ -z "$algos" ]; then
        echo "Alguno de los parámetros está vacío, el uso es >./probarAlOrd.sh [-t #num] [-a <algoritmos>] [-s <secuencia>] [-n #num]"
        exit 1
    elif [ -z "$secclass" ]; then
        echo "Alguno de los parámetros está vacío, el uso es >./probarAlOrd.sh [-t #num] [-a <algoritmos>] [-s <secuencia>] [-n #num]"
        exit 1
    fi
}

# Verificamos que los parámetros ingresados sean los correctos
verifyInput

# Luego, verificamos que el tipo de secuencia ingresada sea correcta
verifySec

# Verificamos si los parámetros numéricos (t y n) son números.
verifyNums

# Finalmente, ejecutamos el cliente
java -jar PruebaAlgOrd.jar $trynum $elemnum $secclass $algos