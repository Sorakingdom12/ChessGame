package Dominio;

import java.util.ArrayList;

public class Arbol {

    /* Algoritmo minimax: no muy eficiente, para poca profundidad (mate en 1, 2 o 3)*/
    /* Poda alfa-beta:  algoritmo minimax eficiente. */
    /* Algoritmo maximin?*/



    private Nodo raiz;
    private int profundidad;

    public Arbol(Ficha f, int x, int y, int profundidad, ArrayList<Nodo> hijos) {
        raiz = new Nodo(f, x, y, 0.0, hijos);
        this.profundidad = profundidad;
    }

    public int get_profundidad() {
        return this.profundidad;
    }

    public Nodo get_raiz() {
        return raiz;
    }


    /* A partir de una ficha y una posición final, se crea un nodo y se inserta en el árbol. */

}
