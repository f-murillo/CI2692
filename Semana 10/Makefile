KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	CodigoMorseAD.jar

SRC=	NodoAD.kt ArbolDeDecision.kt CodigoMorseAD.kt PruebaCodigoMorseAD.kt

TEMP=	*.class META-INF

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG) $(TEMP)
