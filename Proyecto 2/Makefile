KC=	kotlinc
KFLAG=	-cp
LIB=	lib

all:	AdministradorDeMusicaKt.class

AdministradorDeMusicaKt.class: AdministradorDeMusica.kt  NodoLDEC.kt ListaDoblementeEnlazadaCircular.kt Cancion.kt Reproductor.kt ArbolDeCanciones.kt ListaReproduccion.kt 
	$(KC) $(KFLAG) $(LIB)/jlayer-1.0.1.jar:$(LIB)/pausablePlayer.jar AdministradorDeMusica.kt  NodoLDEC.kt ListaDoblementeEnlazadaCircular.kt Cancion.kt Reproductor.kt ArbolDeCanciones.kt ListaReproduccion.kt 

clean:
	rm -rf *.class META-INF
