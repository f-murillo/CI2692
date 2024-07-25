Libreria de algoritmos de ordenamiento y cliente de prueba de los algoritmos

Autores: Franco Murillo     - 1610782
                 Fredthery Castro  - 1810783

El archivo LabSem5_1610782_1810783.tar.xz contiene:
                             - La libreria de los algoritmos de ordenamiento (Ordenamiento.kt)
                             - El cliente de prueba (PruebaOrdenamiento.kt)
                             - El archivo que se usará para compilar los archivos (Makefile)
                             - El archivo de tipo bash que ejecuta el programa (probarAlgOrd.sh)
                             - Carpeta con la libreria PlotRunTime (libPlotRuntime)
                             - Declaracións de autenticidad firmada por los autores

El programa ejecuta los algoritmos de ordenamiento, sobre un tipo especifico de secuencia, con uno o más tamaños,
en un número de intentos, e imprime por la salida estándar el tiempo promedio de ejecución y la desviación estándar 
de cada algoritmo. Los algoritmos a utilizar, el tipo de secuencia, los tamaños y el número de intentos son ingresados
por el usuario por la terminal. Finalmente, si se ingresaron más de un tamaño de secuencia, se puede hacer uso de la
librería PlotRunTime para graficar los tiempos de ejecución (en segundos) de cada algoritmo que se usó, en función del 
tamaño del arreglo de entrada. 

Compilacion del programa: 
>make

Ejecucion del programa:
>./probarAlgOrd.sh -a <Algoritmo/s a usar> -n <Tamaño/s de la/s secuencia/s> -s <Tipo de secuencia> -t <Numero de intentos> -g <Nombre del archivo .png que tendra la grafica> 

Observaciones generales:

- El orden de los parametros indicados anteriormente se uso a modo de ejemplo, los parametros pueden ingresarse en 
cualquier orden

- El parametro -g solo debe ingresarse en caso de que se usen por lo menos dos tamaños de secuencia

- Para evitar el desbordamiento de la memoria de pila de la maquina virtual de java (que ocurre al probar el peor caso
del algoritmo Quicksort, el cual se da al utilizar secuencias ordenadas, invertidas, y con ceros y unos) que retorna
el error "java.lang.StackOverflowError", se incremento el tamaño de la pila de memoria a 515MB usando el comando 
"-J-Xss4m" en el archivo probarAlgOrd.sh



