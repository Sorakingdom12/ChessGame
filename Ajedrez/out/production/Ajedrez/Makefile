JCC = javac 
JFLAGS = -g
default: Dominio.class Presentacion.class 

Dominio.class: Dominio/*.java
	$(JCC) $(JFLAGS) Dominio/*.java

Presentacion.class: Presentacion/*.java
	$(JCC) $(JFLAGS) Presentacion/*.java


clean:
	$(RM) Dominio/*.class
	$(RM) Presentacion/*.class

