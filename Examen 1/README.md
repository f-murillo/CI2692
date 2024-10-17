Examen 1 - Franco Murillo - 1610782
Programa que resuelve el problema de encontrar un elemento no repetido en una matriz usando la tecnica Divide-And-Conquer

La entrada del programa es un archivo de texto con la siguiente estructura:
- En la primera linea, dos numeros naturales separados por comas, que representan las dimensiones n y m de una matriz, con m impar
- Luego, n lineas con m numeros naturales ordenados, que representan los elementos de la matriz, tal que en todas las lineas hay numeros repetidos, a excepcion de uno.
- Finalmente, la ultima linea es un numero natural x, que indica que en la fila donde se encuentra dicho numero, esta el numero que no se repite que se quiere obtener

El programa recibe el archivo con la estructura mencionada, y retorna el elemento no repetido que se encuentra en la fila donde esta x. Se recorre la matriz columna por columna, aplicando busqueda binaria para encontrar la fila, para posteriormente encontrar el elemento no repetido, usando una version modificada de la busqueda binaria.

Para la busqueda de la fila a partir del elemento x, y la busqueda del elemento que no se repite en dicha fila, se aplica la tecnica Divide-And-Conquer

OBSERVACION: el programa asume que la entrada es correcta, es decir:
- Las dimensiones n y m corresponden a las dimensiones de la matriz del archivo
- La dimension m de la matriz es un numero impar
- Los elementos de la matriz se encuentran ordenados
- El elemento x existe y se encuentra en la matriz
- La fila donde se encuentra x tiene un elemento no repetido     
