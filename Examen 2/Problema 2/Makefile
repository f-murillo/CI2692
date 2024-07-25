KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	Problema2.jar

SRC=	Problema2.kt

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
