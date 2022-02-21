package Dominio;

import javafx.util.Pair;

import java.util.ArrayList;

public class Caball extends Ficha {

    private int[] posX = {1, -1, -2, -2, -1,  1,  2,  2};
    private int[] posY = {2,  2,  1, -1, -2, -2, -1,  1};

    Caball(int id, String nom_F, String color,char clau, int pos_x, int pos_y, double puntuacionBase){

        super(id,nom_F,color,clau, pos_x, pos_y, puntuacionBase);
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