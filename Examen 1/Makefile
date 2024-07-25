KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	BusquedaMatrizNoRepetido.jar

SRC=	BusquedaMatrizNoRepetido.kt 

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
