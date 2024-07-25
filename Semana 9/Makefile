KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	CodificacionTelefonos.jar

SRC=	CodificacionTelefonos.kt TablaDeHash.kt ListaDoblementeEnlazadaCircular.kt Nodo.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
