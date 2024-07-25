

### CodificacionTelefonos.kt

El programa define varias funciones:

- La función `obtenerSubconjuntos` genera todos los subconjuntos posibles de una cadena de caracteres. Esta función utiliza una técnica de programación recursiva para generar los subconjuntos. Si se ha alcanzado el final de la cadena, se agrega el subconjunto actual a la lista de resultados. Si no, se agrega el carácter actual al subconjunto y se llama a la función de nuevo con el siguiente índice.

- La función `transformarAPalabra` toma un número y lo transforma en una palabra utilizando una tabla de codificación. Primero, obtiene todos los subconjuntos posibles del número. Luego, para cada subconjunto, busca las palabras correspondientes en la tabla de codificación y genera todas las combinaciones posibles de estas palabras. Finalmente, imprime todas las combinaciones válidas.

- La función `main` es el punto de entrada del programa. Lee un archivo de diccionario y un archivo de números de teléfono desde la línea de comandos. Para cada línea en el archivo de diccionario, transforma la palabra en un número y la guarda en una tabla de hash. Luego, para cada línea en el archivo de números de teléfono, limpia la línea para que solo contenga números y llama a la función `transformarAPalabra` para transformar el número en palabras.

### ListaDoblementeEnlazadaCircular.kt

En este archivo, se define una clase `ListaDoblementeEnlazadaCircular` que representa una lista doblemente enlazada circular. Esta lista implementa la interfaz `Iterable` para permitir la iteración sobre sus elementos.

- La lista utiliza un nodo sentinela, `sentinel`, que sirve como referencia para el inicio y el final de la lista. En el constructor de la clase, se inicializa este nodo sentinela y se configuran sus enlaces para que apunten a sí mismo, lo que indica que la lista está vacía.

- La lista mantiene un contador `size` para llevar un registro de su tamaño actual. Se proporcionan métodos `getSize` y `estaVacia` para obtener el tamaño de la lista y verificar si está vacía, respectivamente.

- La lista proporciona métodos `agregarAlFrente` y `agregarAlFinal` para agregar nuevos nodos al principio y al final de la lista, respectivamente. Estos métodos crean un nuevo nodo con el dato proporcionado, actualizan los enlaces de los nodos adyacentes y aumentan el tamaño de la lista.

- El método `eliminarUltimo` elimina el último nodo de la lista. Si la lista está vacía, lanza una excepción.

- El método `addAllAlFrente` agrega todos los elementos de otra lista al frente de esta lista. Utiliza el método `forEach` para iterar sobre los elementos de la otra lista y agregarlos al frente de esta lista.

- El método `filtrar` filtra los elementos de la lista según un predicado dado y devuelve una nueva lista con los elementos filtrados. Crea una nueva lista, itera sobre los elementos de esta lista y agrega a la nueva lista aquellos elementos que cumplen con el predicado.

- El método `toString` devuelve una representación en forma de cadena de la lista. Si la lista está vacía, devuelve una cadena que indica que la lista está vacía. De lo contrario, itera sobre los nodos de la lista y construye una cadena que representa la lista.

- Finalmente, el método `iterator` devuelve un iterador sobre los elementos de la lista. Este iterador implementa los métodos `hasNext` y `next` para verificar si hay más elementos en la lista y obtener el siguiente elemento, respectivamente.


### TablaDeHash.kt
El archivo `TablaDeHash.kt` define una clase `HashTable` que representa una estructura de datos de tabla hash.

La tabla hash se inicializa con un tamaño `n` y se crea un array de listas mutables para almacenar los valores.

La clase define varias funciones:

- La función `hash` toma una cadena de texto y la transforma en un índice para la tabla hash. Utiliza el método de multiplicación como función de hash, con el número áureo como constante. Calcula la parte fraccionaria del producto de la cadena de entrada y el número áureo, y luego multiplica este valor por el tamaño de la tabla para obtener el índice.

- La función `guardar` toma una palabra y su representación numérica, calcula el índice de la palabra utilizando la función `hash` y agrega la palabra y su representación numérica a la lista en el índice correspondiente de la tabla.

- La función `buscar` toma una representación numérica, calcula el índice correspondiente utilizando la función `hash` y busca la representación numérica en la lista en el índice correspondiente de la tabla. Devuelve una lista de todas las palabras que corresponden a la representación numérica.

### Nodo.kt
El archivo `Nodo.kt` define una clase `Nodo` que representa un nodo en una estructura de datos enlazada.

La clase `Nodo` tiene un tipo genérico `T` que representa el tipo de dato que se almacenará en el nodo.

La clase tiene tres propiedades:

- `dato`: el dato almacenado en el nodo.
- `next`: el siguiente nodo en la estructura.
- `prev`: el nodo anterior al nodo actual.

La clase define varios métodos:

- `cambiarNext`: cambia el nodo siguiente al nodo actual.
- `cambiarPrev`: cambia el nodo anterior al nodo actual.
- `obtenerDato`: devuelve el dato que contiene el nodo.
- `obtenerNodo`: busca un nodo con un dato específico en la estructura de datos enlazada. Si el dato del nodo actual es igual al dato buscado, devuelve el nodo actual. De lo contrario, llama al método `obtenerNodo` en el siguiente nodo.
- `toString`: devuelve una representación en forma de cadena del dato que contiene el nodo.

## Funcionamiento

Primero, el programa lee un archivo de diccionario que se pasa como argumento en la línea de comandos (`args[1]`). Como no se conoce la cantidad de palabras en el diccionario, se leen todas las líneas del archivo y se almacenan en la variable lines.

A continuación, se crea una tabla de hash (llamada `opciones`) con un tamaño igual al número de líneas (es decir, palabras) en el archivo del diccionario. Luego, para cada línea (o palabra) en el archivo del diccionario, se transforma la palabra en su representación numérica correspondiente utilizando la función `transformarANumero`. Esta representación numérica se guarda en la tabla de hash junto con la palabra original.

Después de procesar el archivo del diccionario, el programa lee un archivo de números de teléfono que también se pasa como argumento en la línea de comandos (`args[0]`). Para cada línea en el archivo de números de teléfono, se limpia la línea para que solo contenga números. Esto se hace utilizando la función `filter` de Kotlin y pasando una función lambda que verifica si un carácter es un dígito.

Finalmente, para cada número de teléfono limpio, se llama a la función `transformarAPalabra` para transformar el número en palabras. Esta función busca el número en la tabla de hash y genera todas las posibles combinaciones de palabras que corresponden a ese número.

Se decidió usar una tabla de hash para almacenar los datos del diccionario pues, de esta manera, podríamos acceder a los datos de manera eficaz, usando una función de hash que evite la mayor cantidad de colisiones posibles. De la misma manera, la lista doblemente enlazada circular se usó a lo largo de todo el programa para almacenar variables clave en el mismo, debido a su mutabilidad.


## Detalles

Estamos conscientes de que, en su forma actual, el programa no ejecuta al 100% los requerimientos solicitados en cuanto a la transformación de palabras, lo cual se debe a la magnitud que representa cada posible caso en cada posible subconjunto de combinaciones de los números telefónicos de la agenda, problema que fue bastante difícil de abordar en su totalidad. Sin embargo, consideramos que, en general, funciona como esperado, sólo que para casos muy particulares (por ejemplo, transformaciones con varias posibilidades para un mismo subconjunto, que adentro tienen comodines) la solución no es la esperada. 