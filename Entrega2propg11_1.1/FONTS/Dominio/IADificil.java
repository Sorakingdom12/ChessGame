package Dominio;

import java.util.ArrayList;

public class IADificil {

    private Arbol a;

    private int quien_soy; /* Me devuelve si soy Blanca(0) o negra(1) */

    public void set_quien_soy(int aux) {
        this.quien_soy = aux;
    }

    public Arbol getArbol() {return a;}

    public void setArbol(Arbol a) {this.a = a;}

    public void llenar_arbol(Nodo n, int quien_juega, int prof, Taulell t, int wins) {
        /* Ya hemos rellenado hasta 2*n de profundidad*/
        if (a.get_profundidad() == prof) {
            n.set_hijos(new ArrayList<Nodo>());
            return;
        }
        ArrayList<Nodo> nodos = t.todos_movs(quien_juega, n.get_puntuacion());
        n.set_hijos(nodos);

        /*Recorremos el vector de nodos (= todos los posibles movimientos)*/
        for (int i = 0; i < nodos.size(); i++) {
            int x_act = nodos.get(i).get_ficha().get_pos_x();
            int y_act = nodos.get(i).get_ficha().get_pos_y();

            if (quien_juega == wins) t.puntua(nodos.get(i), true, false);
            else t.puntua(nodos.get(i), false, false);

            Ficha comida = t.mover(nodos.get(i).get_ficha(), nodos.get(i).get_posx(), nodos.get(i).get_posy());
            boolean promocion = false;
            boolean ilegal = false;

            //MOVS ILEGALES
            if (quien_juega == 0) {
                /* Si movemos y dejamos en jaque a nuestro rey, deshacemos movimiento y ponemos a null ese nodo */
                /* SI AL MOVER HEMOS MATADO UNA PIEZA DEL EQUIPO CONTRARIO, AL DESHACER EL MOVIMIENTO, HEMOS DE REVIVIRLA*/
                if (t.jaque("blanca")) {
                    t.mover(nodos.get(i).get_ficha(), x_act, y_act);
                    t.resucitar(comida);

                    nodos.remove(i);
                    n.get_Hijos().remove(i);
                    --i;
                    ilegal = true;
                }
            } else {
                /* Si movemos y dejamos en jaque a nuestro rey, deshacemos movimiento y ponemos a null ese nodo */
                /* SI AL MOVER HEMOS MATADO UNA PIEZA DEL EQUIPO CONTRARIO, AL DESHACER EL MOVIMIENTO, HEMOS DE REVIVIRLA*/
                if (t.jaque("negra")) {
                    t.mover(nodos.get(i).get_ficha(), x_act, y_act);
                    t.resucitar(comida);

                    nodos.remove(i);
                    n.get_Hijos().remove(i);
                    --i;
                    ilegal = true;
                }
            }
            if (!ilegal) {
                Ficha peon = null;
                if (nodos.get(i).get_ficha().get_nom().equals("peo") && t.to_promote(nodos.get(i).get_ficha())) {
                    promocion = true;
                    double puntitos = n.get_puntuacion();

                    t.mover(nodos.get(i).get_ficha(), x_act, y_act);
                    t.resucitar(comida);

                    if (nodos.get(i).getPromotion().equals("false")) {
                        nodos.get(i).setPromotion("reina");
                        n.get_Hijos().get(i).setPromotion("reina");
                        nodos.add(i + 1, new Nodo(nodos.get(i).get_ficha(), nodos.get(i).get_posx(), nodos.get(i).get_posy(), puntitos, nodos.get(i).get_Hijos(), "caball"));
                        n.get_Hijos().add(i + 1, nodos.get(i + 1));//add(i+1, nodos.get(i+1));
                    }
                    //Puntuamos
                    nodos.get(i).setPuntos(puntitos);
                    if (quien_juega == wins) t.puntua(nodos.get(i), true, true);
                    else t.puntua(nodos.get(i), false, true);

                    comida = t.mover(nodos.get(i).get_ficha(), nodos.get(i).get_posx(), nodos.get(i).get_posy());
                    peon = t.promover_peon(nodos.get(i).get_ficha(), nodos.get(i).getPromotion());
                    nodos.get(i).set_ficha(t.get_ficha(nodos.get(i).get_ficha().get_id()));
                    n.get_Hijos().get(i).set_ficha(t.get_ficha(nodos.get(i).get_ficha().get_id()));
                }

                /* Cambiamos el turno de quien juega para llamar a la recursividad*/
                quien_juega = (quien_juega + 1) % 2;

                llenar_arbol(nodos.get(i), quien_juega, prof + 1, t, wins);
                /* Deshacemos el cambio de turno al salir de la recursividad*/
                quien_juega = (quien_juega + 1) % 2;
                if (promocion) {
                    t.despromover(nodos.get(i).get_ficha(), peon);
                    nodos.get(i).set_ficha(t.get_ficha(nodos.get(i).get_ficha().get_id()));
                    n.get_Hijos().get(i).set_ficha(t.get_ficha(nodos.get(i).get_ficha().get_id()));
                }
                t.mover(nodos.get(i).get_ficha(), x_act, y_act);
                t.resucitar(comida);
            }
        }
        n.setPuntos(0.0); // Posibles mods
    }

    public Nodo Best_Move(Nodo n) {

        Nodo max = new Nodo (null, -1, -1, Double.NEGATIVE_INFINITY, new ArrayList<Nodo>());
        //Dominio.Nodo maxAct;
        double maxAux;
        double alfa, beta;
        alfa = Double.NEGATIVE_INFINITY;
        beta = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n.get_Hijos().size(); i++) {
            if (n.get_Hijos().get(i).get_ficha() != null) {
                maxAux = Valmin(n.get_Hijos().get(i), alfa, beta);

                if (maxAux > max.get_puntuacion()) {
                    max.setPromotion(n.get_Hijos().get(i).getPromotion());
                    max.setPuntos(maxAux);
                    max.setPos(n.get_Hijos().get(i).get_posx(), n.get_Hijos().get(i).get_posy());
                    max.set_hijos(n.get_Hijos().get(i).get_Hijos());
                    max.set_ficha(n.get_Hijos().get(i).get_ficha());
                }
            }
        }
        return max;
    }

    public double Valmax (Nodo n, double alfa, double beta) {
        if (n.get_Hijos().size() == 0) {
            return n.get_puntuacion();
        }
        else {
            boolean hijo_no_decepcion = false;
            for (Nodo hijo : n.get_Hijos()) {
                if (hijo.get_ficha() != null) {
                    hijo_no_decepcion = true;
                    alfa = Math.max(alfa, Valmin(hijo, alfa, beta));
                    if (alfa >= beta) return beta;
                }
            }
            if (hijo_no_decepcion) return alfa;
            else return n.get_puntuacion();
        }
    }

    public double Valmin (Nodo n, double alfa, double beta) {
        if (n.get_Hijos().size() == 0) {
            return n.get_puntuacion();
        }
        else {
            boolean hijo_no_decepcion = false;
            for (Nodo hijo : n.get_Hijos()) {
                if (hijo.get_ficha() != null) {
                    hijo_no_decepcion = true;
                    beta = Math.min(beta, Valmax(hijo, alfa, beta));
                    if (alfa >= beta) return alfa;

                }
            }
            if (hijo_no_decepcion) return beta;
            else return n.get_puntuacion();
        }
    }

    public Nodo algoritmo_poda_alfa_beta(Taulell t, int profundidad, int player) {

        a = new Arbol (null, -1, -1, Integer.min(4,profundidad), new ArrayList<Nodo>());

        llenar_arbol(a.get_raiz(), player, 0, t, player);

        return Best_Move(a.get_raiz());

    }

}
