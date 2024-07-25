KC=	kotlinc

KFLAGS=	-include-runtime

PROG=	DivideAndConquerPRMBSolver.jar

SRC=	DivideAndConquerPRMBSolver.kt 

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)

.PHONY : clean

clean :
	rm -rf $(PROG)
