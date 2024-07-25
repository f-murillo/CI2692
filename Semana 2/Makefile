KC=	kotlinc
KFLAG=	-cp
LIB=	libPlotRuntime

all:	PruebaOrdenamientoKt.class

PruebaOrdenamientoKt.class: PruebaOrdenamiento.kt Ordenamiento.kt
	$(KC) $(KFLAG) $(LIB)/libPlotRuntime.jar PruebaOrdenamiento.kt Ordenamiento.kt

clean:
	rm -rf *.png *.class META-INF