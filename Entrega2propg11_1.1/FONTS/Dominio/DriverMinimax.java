package Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverMinimax {

    private static IAFacil Mini;

    public static void llenar_arbol() {
        if (Mini == null || Mini.getArbol() == null) {
            Taulell t = new Taulell("R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1");
            t.fen_to_mat();
            System.out.println("Se le proporciona un Fen para su comodidad");
            int quien_juega = 0;
            int profundidad = 1;

            Mini = new IAFacil();
            Mini.setArbol(new Arbol(null, -1, -1, profundidad, new ArrayList<>()));
            Mini.llenar_arbol(Mini.getArbol().get_raiz(), quien_juega, 0, t, quien_juega);

            draw_tree(Mini.getArbol().get_raiz());
        }
        else System.out.println("IAFacil ya esta inicializada y/o el arbol ya esta lleno");
    }

    public static void Best_Move() {
        if (Mini != null && Mini.getArbol().get_raiz() != null) {
            Nodo aux = Mini.Best_Move(Mini.getArbol().get_raiz());
            System.out.println("El mejor movimiento es: ");
            draw_tree(aux);
        }
        else System.out.println("IAFacil esta vacio, inicialo primero");
    }



    /* Funciones usadas exclusivamente para imprimir */
    private static String toStringy(Nodo n){
        // Programa una función que devuelva el contenido del nodo en una sola linea.
        if (n.get_ficha() == null) return ("x --> root");
        return (n.get_ficha().get_nom() + "[" + n.get_ficha().get_id() + "]; x: " + n.get_posx() + ", y: " + n.get_posy() + ". Promotion: " + n.getPromotion() + ". Puntuación: " + n.get_puntuacion());
    }
    public static void draw_tree(Nodo n){
        draw_tree_aux(n, "");
    }
    protected static void draw_tree_aux(Nodo n, String prefijo){
        String str = toStringy(n);
        System.out.println(prefijo + str);
        if(prefijo.equals("")) prefijo = " \\_";
        else prefijo = "  " + prefijo;
        for(Nodo nodo : n.get_Hijos()){
            draw_tree_aux(nodo, prefijo);
        }
    }



    public static void usage() {
        System.out.println("Menú de opciones: ");
        System.out.println("    0. Salir");
        System.out.println("    1. Llenar árbol");
        System.out.println("    2. Retorna el mejor movimiento");
    }

    public static void main(String args[]) {
        Scanner lector = new Scanner(System.in);
        usage();
        String s = "";
        try {
            while ((s = lector.nextLine()) != "0") {

                switch (s) {
                    case "0":
                        System.exit(0);
                    case "1":
                        llenar_arbol();
                        break;
                    case "2":
                        Best_Move();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
