package Dominio;

import javafx.util.Pair;
import java.util.ArrayList;

public abstract class Ficha {
    private String nom_F;
    private int id;
    private String color;
    private char clau;
    private int pos_x;
    private int pos_y;
    private double puntuacionBase;
    private boolean muerte_temp;

    Ficha (int id, String nom_F, String color, char clau, int pos_x, int pos_y, double puntuacionBase){
        this.id = id;
        this.nom_F = nom_F;
        this.color = color;
        this.clau = clau;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.muerte_temp = false;
        this.puntuacionBase = puntuacionBase;
    }

    public String get_nom (){
        return nom_F;
    }

    public char get_clau(){
        return clau;
    }

    public int get_pos_x() {
        return pos_x;
    }

    public int get_pos_y() {
        return pos_y;
    }

    public int get_id() {
        return id;
    }


    public boolean get_muerte_temp() {
        return muerte_temp;
    }

    public String get_color() {
        return color;
    }

    //comprobar si la pos (x,y) es correcta
    public boolean pos_ok (int x, int  y, Taulell t) {
        return true;
    }

    //actualizar la posición de la ficha
    public void actualizar_pos(int x, int y) {
        pos_x = x;
        pos_y = y;
    }

    //comprueba si es posible hacer el movimiento hasta (x,y)
    public boolean movimiento_ok(int x, int y, int count,Taulell t){
        return (t.camino_ok(pos_x, pos_y, x,y,count, color));
    }

    //devuelve un array de todas las posibles posiciones a las que puede ir la ficha
    abstract ArrayList<Pair<Integer,Integer> > todos_movimientos ( Taulell t);

    public void setMuerte_temp(){
        muerte_temp = !muerte_temp;
    }

    public boolean isMuerte_temp(){
        return muerte_temp;
    }

    public double getPuntuacionBase() {
        return puntuacionBase;
    }
}
