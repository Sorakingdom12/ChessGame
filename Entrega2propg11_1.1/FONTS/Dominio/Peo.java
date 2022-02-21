package Dominio;

import javafx.util.Pair;
import java.util.ArrayList;

public class Peo extends Ficha {
    private boolean pos_init = false;

    private int[] posX = {1, 1, 1};
    private int[] posY = {0, -1, 1};


    Peo(int id, String nom_F, String color, char clau, int pos_x, int pos_y, double puntuacionBase) {

        super(id, nom_F, color, clau, pos_x, pos_y, puntuacionBase);
    }

    public boolean pos_ok(int x, int y, Taulell t) {
        if (x == 7 || x == 0) return false;
        if (x == 6 && this.get_color() == "blanca") pos_init = true;
        else if (x == 1 && this.get_color() == "negra") pos_init = true;
        return true;
    }

    public ArrayList<Pair<Integer, Integer>> todos_movimientos(Taulell t) {
        /*movimiento de las blancas: dir == 1 */
        /*movimiento de las negras: dir == -1*/
        int dir =  1;
        if (this.get_color() == "blanca") dir = -1;
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < posX.length; i++) {
            if (movimiento_ok(posX[i]*dir, posY[i], 1, t)) {
                result.add(new Pair(this.get_pos_x() + posX[i]*dir, this.get_pos_y() + posY[i]));
                if (pos_init == true && i == 0) {
                    if (movimiento_ok(posX[i]*dir, posY[i], 2, t)) {
                        result.add(new Pair(this.get_pos_x() + posX[i]* 2 *dir, this.get_pos_y() + posY[i]));
                    }
                }
            }
        }
        return result;
    }
}