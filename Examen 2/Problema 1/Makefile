KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	Problema1.jar

SRC=	Problema1.kt ListaDoblementeEnlazadaCircular.kt NodoLDEC.kt TablaHash.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
