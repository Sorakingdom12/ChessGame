package Dominio;

import javafx.util.Pair;
import java.util.ArrayList;

public class Rey extends Ficha {
    private int[] posX = {-1, 0, 1, 1, 1, 0, -1, -1 };
    private int[] posY = {1, 1, 1, 0, -1, -1, -1, 0 };

    Rey(int id, String nom_F, String color,char clau,  int pos_x, int pos_y, double puntuacionBase){

        super(id,nom_F,color, clau, pos_x, pos_y, puntuacionBase);
    }

    public boolean pos_ok(int x, int y, Taulell t) {
        int id_vecino;
        for (int i = 0; i  < posX.length; i++ ){
            /* mira si la posición que queremos mirar está dentro del tablero. Si lo está sacamos el id, si está fuera ponemos el id -1 para que no lo mire  */

            if ( t.dentro_del_tablero(x + posX[i], y + posY[i])) {
                id_vecino =  t.get_id_ficha(x + posX[i],y + posY[i]);
            }
            else{ id_vecino = -1; }
            if( id_vecino != -1 && t.get_nom_ficha(id_vecino).equals("rey") && !t.get_color_ficha(id_vecino).equals(this.get_color())) { return false; }
        }
        return true;
    }

    public ArrayList<Pair<Integer,Integer> > todos_movimientos (Taulell t) {
        ArrayList<Pair<Integer,Integer> > result = new ArrayList<Pair<Integer,Integer> > ();
        for (int i = 0; i < posX.length; i++) {
            if (movimiento_ok(posX[i], posY[i], 1, t)) {
                result.add(new Pair(this.get_pos_x()+posX[i], this.get_pos_y()+posY[i]));
            }
        }
        return result;
    }

}