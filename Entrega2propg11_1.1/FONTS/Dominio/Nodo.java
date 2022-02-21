package Dominio;

import java.util.ArrayList;

public class Nodo {

    /* Dominio.Ficha que se mueve */
    private Ficha f;
    /* Posición a la que va*/
    private int x;
    private int y;
    /* Punteros que apunten a lo hijos */
    private ArrayList<Nodo> hijos;
    private String promotion;
    /* Puntuación del nodo */
    private double puntuacion;
    /*la matriz auxiliar de tablero*/

    Nodo (Ficha f, int x, int y, double puntuacion, ArrayList<Nodo> hijos) {
        this.f = f;
        this.x = x;
        this.y = y;
        this.hijos = hijos;
        this.puntuacion = puntuacion;
        this.promotion = "false";
    }

    Nodo (Ficha f, int x, int y, double puntuacion, ArrayList<Nodo> hijos, String promocion) {
        this.f = f;
        this.x = x;
        this.y = y;
        this.hijos = hijos;
        this.puntuacion = puntuacion;
        this.promotion = promocion;
    }

    public Ficha get_ficha() {
        return f;
    }

    public int get_posx() {
        return x;
    }

    public int get_posy() {
        return y;
    }

    public String getPromotion() { return promotion; }

    public ArrayList<Nodo> get_Hijos() {
        return hijos;
    }

    public double get_puntuacion() {
        return puntuacion;
    }

    public void setPromotion(String promocion) {
        this.promotion = promocion;
    }

    public void set_hijos(ArrayList<Nodo> hijos) {
        for(int i = 0; i < hijos.size(); i++) {
            this.hijos.add(hijos.get(i));
        }
    }

    public void setPuntos(double x){
        puntuacion = x;
    }

    public void setPos(int posx, int posy){ x = posx;  y = posy; }

    public void set_ficha(Ficha f) {
        this.f = f;
    }

}
