package Dominio;

import javafx.util.Pair;

import java.util.ArrayList;

public class Reina extends Ficha {
    private int[] posX = {-1, 0, 1, 1, 1, 0, -1, -1 };
    private int[] posY = {1, 1, 1, 0, -1, -1, -1, 0 };

    Reina(int id, String nom_F, String color, char clau,  int pos_x, int pos_y, double puntuacionBase){

        super(id,nom_F,color,clau, pos_x, pos_y, puntuacionBase);
    }

    public ArrayList<Pair<Integer,Integer> > todos_movimientos (Taulell t) {
        ArrayList<Pair<Integer,Integer> > result = new ArrayList<Pair<Integer,Integer> > ();
        for (int i = 0; i < posX.length; i++) {
            for (int count = 1; count < 8; ++count) {
                if (movimiento_ok(posX[i], posY[i], count,t)) {
                    result.add(new Pair(this.get_pos_x() + posX[i]*count,this.get_pos_y() + posY[i]*count));
                }
                else count = 8;
            }
        }
        return result;
    }

}