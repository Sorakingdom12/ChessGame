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

    public void setPromotion(String promocion) {
        this.promotion = promocion;
    }

    public ArrayList<Nodo> get_Hijos() {
        return hijos;
    }

    public double get_puntuacion() {
        return puntuacion;
    }

    public void set_hijos(ArrayList<Nodo> hijos) {
        for(int i = 0; i < hijos.size(); i++) {
            this.hijos.add(hijos.get(i));
        }
    }

    public void setPuntos(double x){
        puntuacion = x;
    }

    public double Valmin(){
        double min;
        if (hijos.size() == 0) {
            //if (puntuacion == Double.POSITIVE_INFINITY || puntuacion == Double.NEGATIVE_INFINITY) System.out.println("PENE");
            return this.get_puntuacion();
        }
        else {
            boolean hijo_no_decepcion = false;
            min = Double.POSITIVE_INFINITY;
            for (Nodo hijo : hijos) {
                if (hijo.get_ficha() != null) {
                    //if (hijo.Valmax() == Double.POSITIVE_INFINITY) System.out.println("MAAAAL!");
                    hijo_no_decepcion = true;
                    double aux = hijo.Valmax();
                    //boolean a = false;
                    //if (min == 300000 || aux == 300000) System.out.println("pene " + aux + " " + min); a = true;
                    min = Math.min(min, aux);
                    //if (a) System.out.println("min: " + min);
                }
                //System.out.println("min = "+ min);
            }

            if (hijo_no_decepcion) return min;
            else return puntuacion;
        }
    }

    public double Valmax(){
        double max;

        if (hijos.size() == 0) {
            //if (puntuacion == Double.POSITIVE_INFINITY || puntuacion == Double.NEGATIVE_INFINITY) System.out.println("PENE");
            return this.get_puntuacion();
        }

        else {
            boolean hijo_no_decepcion = false;
            max = Double.NEGATIVE_INFINITY;
            for (Nodo hijo : hijos) {
                if (hijo.get_ficha() != null) {
                    //if (hijo.Valmax() == Double.NEGATIVE_INFINITY) System.out.println("malito!");
                    hijo_no_decepcion = true;
                    double aux = hijo.Valmin();
                    //boolean a;
                    //if (max == 300000 || aux == 300000) System.out.println("pene " + aux + " " + max); a = true;
                    max = Math.max(max,aux);
                    //if (a) System.out.println("max: " + max);
                    //System.out.println("max : " + max);
                }
            }
            if (hijo_no_decepcion) return max;
            else return puntuacion;
        }
    }

    public void setPos(int posx, int posy){
        x = posx;
        y = posy;
    }

    public void set_ficha(Ficha f) {
        this.f = f;
    }

    private String toStringy(){
        // Programa una función que devuelva el contenido del nodo en una sola linea.
        if (f == null) return ("x --> root");
        return (f.get_nom() + "[" + f.get_id() + "]; x: " + x + ", y: " + y + ". Promotion: " + promotion + ". Puntuación: " + puntuacion);
    }
    public void draw_tree(){
        draw_tree_aux("");
    }

    protected void draw_tree_aux(String prefijo){
        String str = toStringy();
        System.out.println(prefijo + str);
        if(prefijo.equals("")) prefijo = " \\_";
        else prefijo = "  " + prefijo;
        for(Nodo n : hijos){
            n.draw_tree_aux(prefijo);
        }
    }
}
