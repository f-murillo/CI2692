/*
    Se importan las clases necesarias para leer el archivo de entrada
*/
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/*
    Funcion que usa la busqueda binaria recursiva en una columna C de una matriz para hallar un elemento x 
    Complejidad: O(logn)
*/
fun busquedaBinariaRec(C: IntArray, i: Int, j: Int, x: Int): Int {
    
    // Si i>j, ya recorrimos toda la columna y no se encontro el elemento x, asi que se retorna -1
    if (i > j) 
	    return -1    
   
    // Se inicializa el indice k como el punto medio entre i y j
    val k = (i+j).div(2)

    /*
        Si el indice k se encuentra dentro del rango de valores de indices de C, y x es igual al elemento C[k],
        se retorna el indice k
    */
    return if(k < C.size && x == C[k])
        k
    // Sino, si x es menor a C[k], se busca en la mitad inferior de C    
    else if(x<C[k])
        busquedaBinariaRec(C, i, k-1, x)
    /*
        Si x no es menor a C[k], nos aseguramos que k se encuentre en el rango de valores de los indices de C, 
        y se busca en la mitad superior de C  
    */    
    else if(k+1 < C.size)
        busquedaBinariaRec(C, k+1, j, x)
    // Si no se cumple nunguna de estas condiciones, x no esta en C, por lo que se retorna -1    
    else
        -1           
}

/*
    Funcion que busca y retorna el elemento que no se repite, dada la fila donde se encuentra,
    el inicio y el final de dicha fila
    Complejidad: O(logn)
*/
fun buscaNoRepetido(F: IntArray, inicio: Int, fin: Int): Int {
    // Caso base: si el inicio es igual al fin, hemos encontrado el elemento no repetido
    if (inicio == fin) {
        return F[inicio]
    }

    // Se divide el arreglo en dos
    val mitad = (inicio + fin) / 2

    /* Si el indice medio es par y el elemento en la posicion mitad es igual al elemento en la posicion mitad + 1,
       entonces el elemento no repetido esta en la segunda mitad del arreglo.
       De lo contrario, el elemento no repetido esta en la primera mitad del arreglo.
       Importante: ademas de las condiciones ya expuestas, tambien se verifica que el valor mitad se 
       encuentre en el rango de valores de la dimension de la fila 
    */
    return if (mitad % 2 == 0 && mitad+1 < F.size && F[mitad] == F[mitad + 1]) 
        buscaNoRepetido(F, mitad + 2, fin)
    else if (mitad % 2 != 0 && mitad-1 >= 0 && F[mitad] == F[mitad - 1]) 
        buscaNoRepetido(F, mitad + 1, fin)
     else 
        buscaNoRepetido(F, inicio, mitad)
    
}

/*
    Funcion que encuentra y retorna el elemento no repetido, a partir de un elemento x que se encuentra 
    en la fila donde esta el no repetido
    Complejidad: O(mlogn), donde m es el numero de columnas de la matriz
*/

fun encuentraNoRepetido(M: Array<IntArray>, x: Int): Int{
    // Primero se obtiene la fila donde esta x
    
    // Se inicializa el indice de la fila con -1
    var fila = -1
    // Se inicializa el indice de la columna
    var i = 0
    /*
        Mientras que fila sea igual a -1, se buscara en cada columna de la matriz
        la fila donde se encuentra el elemento x (recordar que por definicion del problema, el elemento x existe)
    */
    while(fila==-1){
        // Obtenemos la columna i
        val columna = IntArray(M.size){j -> M[j][i]}
        // Aplicamos la busqueda binaria sobre la columna i
        fila = busquedaBinariaRec(columna, 0, columna.size, x)
        // Incrementamos el indice de la columna
        i++
    }

    // Luego se retorna la funcion que encuentra el elemento no repetido a partir de la fila obtenida
    return buscaNoRepetido(M[fila], 0, M[fila].size)
}

fun main(args: Array<String>){
    // Se obtiene el archivo de entrada
    val archivoInstancia = File(args[0])
    // Se crea el objeto lector
    val lector = BufferedReader(FileReader(archivoInstancia))
    // Se lee la primera linea, que contiene las dimensiones n y m de la matriz, y se almacenan los valores
    val dimension = lector.readLine().trim().split(" ")
    val n = dimension[0].trim().toInt()
    val m = dimension[1].trim().toInt()
    // Se inicializa la matriz con dimension nxm
    val matriz = Array(n){IntArray(m)}
    // Se rellena la matriz con los valores del archivo correspodientes a la matriz
    for(i in 0 until n){
        val fila = lector.readLine().trim().split(" ")
        for (j in 0 until m) {
            matriz[i][j] = fila[j].trim().toInt()
        }
    }
    // Se lee la ultima linea del archivo, que corresponde al elemento que esta en la fila donde esta el no repetido
    val x = lector.readLine().toInt()
    // Se cierra el objeto lector
    lector.close()
    // Se halla el elemento no repetido
    val elementoNoRepetido = encuentraNoRepetido(matriz,x)
    // Se imprime el resultado
    println("El elemento que no se repite es $elementoNoRepetido")
}
