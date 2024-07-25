                                                OBSERVACIONES

    OBSERVACION sobre mergesort y heapsort
    Si bien el algoritmo de mergesort es mas eficiente a largo plazo que heapsort, ésto solo será visible en las 
    gráficas para arreglos de tamaños grandes. En particular, se noto que a partir de tamaños de 100000 elementos, 
    el algoritmo mergesort ordena de manera más rápida los elementos que heapsort                                            
    
    OBSERVACION sobre quicksort Clasico
    El peor caso del algoritmo quickSort ocurre cuando al crear la partición se escoge como pivote el elemento más
    pequeño del arreglo o el más grande (dependiendo de como se establezca en el algoritmo partition), lo cual ocurre
    si el arreglo está ordenado descendentemente, o si el arreglo consta de solo dos tipos de números (en nuestro caso,
    0 y 1).

    Como en partition establecimos que se escogiera siempre como pivote el último elemento, en el caso de que el 
    arreglo esté ordenado descendentemente, se esté escogiendo el elemento más pequeño como pivote; y en el caso 
    de que el arreglo solo tenga 0 y 1, partition siempre escogerá el menor o mayor elemento como pivote. En ambos
    casos se generan unas particiones muy desbalanceadas, en concreto uno de 0 elementos, y otro de n-1 elementos.

    Luego, en ambos casos, se hacen n-1 llamadas recursivas, cada una con una complejidad O(n), lo que hace que
    la complejidad de quicksort sea O(n^2) en el peor caso.

    Si al ejecutar el programa (dandose el peor caso) el arreglo es de por lo menos 15000 elementos para el caso de
    arreglos ordenados descendentemente (-s inv), o 35000 elementos para el caso de arreglos con ceros y unos (-s zu),
    retorna el error 'Exception in thread "main" java.lang.StackOverflowError', lo que indica que la memoria de pila 
    de la maquina virtual de java se sobrepasó, esto por una profundidad de llamadas recursivas muy grande, producto 
    del peor caso. Por lo que se recomienda que si se va a ejecutar el algoritmo con secuencias de tipo inv o zu,
    se utilicen tamaños de las secuencias menores a 15000 para inv, y menores a 35000 para zu