### Cancion.kt

La clase `Cancion` en el archivo `Cancion.kt`es una clase que representa una canción. Tiene tres propiedades: `titulo`, `interprete` y `ubicacion`, que representan el título de la canción, el intérprete y la ubicación del archivo de la canción, respectivamente.

- En el bloque `init`, se verifica si alguna de las entradas está vacía o si la ubicación no es válida (no es un archivo .mp3 existente y legible), en cuyo caso se lanza una excepción.

- La función `esUbicacionValida` verifica si la ubicación proporcionada es válida. Para ser válida, la ubicación debe ser un archivo existente, legible y con extensión .mp3.

- Las funciones `obtenerTitulo`, `obtenerInterprete` y `obtenerUbicacion` son getters para las propiedades `titulo`, `interprete` y `ubicacion`, respectivamente.

- Finalmente, la función `toString` sobrescribe la función `toString` predeterminada para representar la canción como una cadena de texto en el formato `"Título: $titulo, Intérprete: $interprete"`.

### Reproductor.kt

La clase `Reproductor` en el archivo `Reproductor.kt` es una clase que representa un reproductor de música. Tiene tres propiedades: `actual`, `player` y `estaReproduciendo`, que representan la canción actual, el reproductor de música y un indicador de si se está reproduciendo una canción, respectivamente.

- En el bloque init, se carga la canción proporcionada al crear el objeto `Reproductor`.

- La función `cargarCancion` carga una nueva canción en el reproductor. Primero detiene la reproducción actual si existe, luego establece la canción actual y crea un nuevo `PausablePlayer` con la ubicación de la canción. Si no se puede cargar la canción (por ejemplo, si el archivo no se encuentra), se imprime un mensaje de error.

- La función `reproducir` intenta reproducir la canción actual. Si la canción se reproduce con éxito, se establece `estaReproduciendo` en `true`. Si ocurre un error al reproducir la canción, se imprime un mensaje de error y se establece `estaReproduciendo` en `false`.

### ListaReproduccion.kt

La clase `ListaReproduccion` en el archivo `ListaReproduccion.kt` es una claseque representa una lista de reproducción de canciones. Esta lista se utiliza para almacenar y gestionar una colección de canciones.

- La propiedad `canciones` es una lista que almacena las canciones en la lista de reproducción.

- La función `agregarCancion` se utiliza para agregar una nueva canción a la lista de reproducción. La canción se añade al final de la lista.

- La función `eliminarCancion` se utiliza para eliminar una canción de la lista de reproducción. Si la canción se encuentra en la lista, se elimina y la función devuelve `true`. Si la canción no se encuentra en la lista, la función devuelve `false`.

- La función `obtenerCancion` se utiliza para obtener una canción de la lista de reproducción por su índice. Si el índice es válido, la función devuelve la canción. Si el índice no es válido, la función devuelve `null`.

- La función `obtenerNumeroCanciones` devuelve el número de canciones en la lista de reproducción.

- La función `estaVacia` verifica si la lista de reproducción está vacía. Devuelve `true` si la lista está vacía y `false` en caso contrario.

### ArbolDeCanciones.kt

La clase `ArbolDeCanciones` en el archivo `ArbolDeCanciones.kt` es una clase que representa un árbol de canciones. Este árbol se utiliza para almacenar y buscar canciones por título.

- La propiedad `minTitulo` representa el nodo con el título más pequeño en el árbol. Se utiliza para buscar rápidamente la canción con el título más pequeño.

- La función `insertar` se utiliza para insertar una nueva canción en el árbol. Si el árbol está vacío, la nueva canción se convierte en la raíz del árbol. Si el árbol no está vacío, la canción se inserta en la posición correcta según su título.

- La función `buscar` se utiliza para buscar una canción por título en el árbol. Si la canción se encuentra en el árbol, la función devuelve la canción. Si la canción no se encuentra en el árbol, la función devuelve `null`.

- La función `eliminar` se utiliza para eliminar una canción del árbol. Si la canción se encuentra en el árbol, se elimina y la función devuelve `true`. Si la canción no se encuentra en el árbol, la función devuelve `false`.

- La función `minimo` se utiliza para encontrar la canción con el título más pequeño en el árbol. Esta función se utiliza internamente por la función `eliminar` para reorganizar el árbol después de una eliminación.

- La función `maximo` se utiliza para encontrar la canción con el título más grande en el árbol. Esta función se utiliza internamente por la función `eliminar` para reorganizar el árbol después de una eliminación.

### ListaDoblementeEnlazadaCircular.kt

La clase `ListaDoblementeEnlazadaCircular` en el archivo `ListaDoblementeEnlazadaCircular.kt` es una clase que representa una lista doblemente enlazada circular. Esta lista se utiliza para almacenar y gestionar una colección de nodos, que en este caso serán las canciones de la lista de reproducción.

La función `primera` devuelve el primer nodo en la lista.

La función `actual` devuelve el nodo actual en la lista.

La función `estaVacia` verifica si la lista está vacía. Devuelve `true` si la lista está vacía y `false` en caso contrario.

La función `agregarAlFinal` se utiliza para insertar un nuevo nodo en al final de la lista. Si la lista está vacía, el nuevo nodo se convierte en la cabeza de la lista. Si la lista no está vacía, el nuevo nodo se inserta al final.

## NodoLDEC.kt

La clase `NodoLDEC` en el archivo `NodoLDEC.kt` es una clase que representa un nodo en una lista doblemente enlazada circular. Cada nodo tiene un dato y dos referencias a otros nodos: `anterior` y `siguiente`.

- La propiedad `dato` es el dato almacenado en el nodo. Puede ser de cualquier tipo, ya que es un tipo genérico.

- Las propiedades `anterior` y `siguiente` son referencias a los nodos anterior y siguiente en la lista, respectivamente. Si el nodo es el primero de la lista, `anterior` apunta al último nodo de la lista. Si el nodo es el último de la lista, `siguiente` apunta al primer nodo de la lista.

- El constructor de la clase `NodoLDEC` inicializa el dato y las referencias a los nodos anterior y siguiente. Si no se proporcionan nodos anterior y siguiente, se establecen en null.

- La función `toString` sobrescribe la función `toString` predeterminada para representar el nodo como una cadena de texto. Por defecto, solo muestra el dato del nodo.

### AdministradorDeMusica.kt
La clase `AdministradorDeMusica` en el archivo `AdministradorDeMusica.kt` es una clase que representa un administrador de música. Esta clase se utiliza para gestionar la reproducción de música y las listas de reproducción.

- La variable `listaReproduccion` es una instancia de la clase `ListaReproduccion` que se utiliza para almacenar las canciones que se van a reproducir.

- La variable `listaCanciones` es una instancia de la clase `ListaDoblementeEnlazadaCircular` que se obtiene al aplicar la función obtenerLR() sobre listareproduccion. Se utiliza para representar mediante una lista la lista de reproducción.

- La variable `reproductor` es una instancia de la clase `Reproductor` que se utiliza para reproducir música.

- La variable `cancionActual` es una instancia de la clase `cancionActual` que se utiliza para llevar un control de cuál es la canción actual

- La función `cargarListaDeReproduccion` se utiliza para agregar una lista de reproduccion. El usuario debe pasar por terminal el nombre del archivo que contiene las direcciones absolutas de las canciones. Si el archivo no existe, no puede ser localizado, u ocurre algún error, se imprime el error por la salida estándar.

- La función `mostrarListaDeReproduccion` se utiliza para imprimir por la salida estándar las canciones de la  lista de reproducción. Si la lista está vacía, se devuelve un mensaje de error por la salida etándar.

- La función `eliminarCancion` se utiliza para eliminar una canción de la lista de reproducción.

- La función `reproducir` se utiliza para reproducir la canción actual en la lista de reproducción.

- La función `pausar` se utiliza para pausar la reproducción de la canción actual.

- La función `siguiente` se utiliza para pasar a la siguiente canción en la lista de reproducción.

- La función `anterior` se utiliza para volver a la canción anterior en la lista de reproducción.

- La función `mostrarListaReproduccion` se utiliza para mostrar todas las canciones en la lista de reproducción.

### Ejemplo de ejecución del programa
- `Compilar` los archivos
            > make
- `Ejecutar el programa`
            > kotlin -cp ".:$(printf %s: lib/*.jar)" AdministradorDeMusicaKt

