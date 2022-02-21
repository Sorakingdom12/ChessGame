package Dominio;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverFicha {

    private static Ficha f;
    private static Scanner lector;


    public static void Ficha() {

        if (f != null) System.out.println("Ya existe la ficha");
        else {
            System.out.println("Introduce el tipo de ficha [alfil/caball/peo/reina/rey/torre]: ");
            String nombre = lector.nextLine();
            System.out.println("Introduce un color: ");
            String color = lector.nextLine();
            System.out.println("Introduce un id: ");
            int id = lector.nextInt();
            System.out.println("Introduce una posición x: ");
            int posx = lector.nextInt();
            System.out.println("Introduce una posición y");
            int posy = lector.nextInt();
            System.out.println("Introduce una puntuación base: ");
            double punt = lector.nextInt();
            if (nombre.equals("alfil") && color.equals("negra")) f = new Alfil(id, nombre, color,'b', posx, posy, punt);
            else if (nombre.equals("alfil") && color.equals("blanca")) f = new Alfil(id, nombre, color,'B', posx, posy, punt);
            else if (nombre.equals("caball") && color.equals("negra")) f = new Caball(id, nombre, color,'n', posx, posy, punt);
            else if (nombre.equals("caball")  && color.equals("blanca")) f = new Caball(id, nombre, color,'N' , posx, posy, punt);
            else if (nombre.equals("peo") && color.equals("negra")) f = new Peo(id, nombre, color,'p', posx, posy, punt);
            else if (nombre.equals("peo")  && color.equals("blanca")) f = new Peo(id, nombre, color,'P', posx, posy, punt);
            else if (nombre.equals("reina") && color.equals("negra")) f = new Reina(id, nombre, color,'q', posx, posy, punt);
            else if (nombre.equals("reina") && color.equals("blanca")) f = new Reina(id, nombre, color,'Q', posx, posy, punt);
            else if (nombre.equals("rey") && color.equals("negra")) f = new Rey(id, nombre, color,'k', posx, posy, punt);
            else if (nombre.equals("rey") && color.equals("blanca")) f = new Rey(id, nombre, color,'K', posx, posy, punt);
            else if (nombre.equals("torre") && color.equals("negra")) f = new Torre(id, nombre, color,'r', posx, posy, punt);
            else if (nombre.equals("torre") && color.equals("blanca")) f = new Torre(id, nombre, color,'R', posx, posy, punt);
            else {
                System.out.println("Opciones incorrectas");
                return;
            }
            System.out.println("Ficha creada correctamente");
        }
    }

    public static void pos_ok() {
        if (f != null) {
            Taulell t = new Taulell("5rkr/pp3p2/2p3p1/6R1/1b2N1Q1/3P4/qPP4P/2K5 w - - 0 1");
            t.fen_ok();
            System.out.println("Un tablero ha sido generado para su comodidad.");

            System.out.println("Introduzca la posicion x que quiere comprobar: ");
            int x = lector.nextInt();
            System.out.println("Introduzca la posicion y que quiere comprobar: ");
            int y = lector.nextInt();
            if (!t.dentro_del_tablero(x, y)) {
                System.out.println("Las posiciones estan fuera del tablero, pos_ok() nunca es llamada en esas condiciones");
                return;
            }
            if (f.get_nom().equals("peo") && !f.pos_ok(x, y, t)) {
                System.out.println("La posicion de la ficha no seria correcta");
            }
            else if (f.get_nom().equals("rey") && !f.pos_ok(x, y, t)) {
                System.out.println("La posicion de la ficha no seria correcta");
            }
            else System.out.println("La posicion de la ficha es correcta");
        }
        else System.out.println("La ficha no se ha creado");
    }

    public static void actualizar_pos() {
        if (f == null) System.out.println("No se ha creado la ficha");
        else {
            System.out.println("Introduce una nueva posición x");
            int x = lector.nextInt();
            System.out.println("Introduce una nueva posición y");
            int y = lector.nextInt();
            f.actualizar_pos(x,y);
            System.out.println("La nueva posicion de la ficha es x = " + f.get_pos_x() + ", y = " + f.get_pos_y() + ".");
        }
    }

    public static void movimiento_ok() {
        if (f == null) {
            System.out.println("La ficha no se ha creado");
            return;
        }
        System.out.println("Introduce el vector de direccion en el que quieres mover la ficha");
        System.out.println("El movX: ");
        int movx = lector.nextInt();
        System.out.println("El movY: ");
        int movy = lector.nextInt();
        System.out.println("Introduce la cantidad de pasos en la direccion escogida que quieres intentar dar");
        int count = lector.nextInt();

        Taulell t = new Taulell("5rkr/pp3p2/2p3p1/6R1/1b2N1Q1/3P4/qPP4P/2K5 w - - 0 1");
        t.fen_ok();
        System.out.println("Un tablero ha sido generado para su comodidad.");

        boolean movo = f.movimiento_ok(movx, movy, count, t);
        if (movo) System.out.println("El movimiento propuesto es correcto (No se ha comprobado que sea un movimiento legal)");
        else System.out.println("El movimiento propuesto no es correcto (No se ha comprobado que sea un movimiento legal)");
    }

    public static void setMuerte_temp() {
        if (f == null) {
            System.out.println("La ficha no se ha creado");
            return;
        }
        System.out.println("Muerte temporal de la ficha era " + f.get_muerte_temp());
        f.setMuerte_temp();
        System.out.println("Ahora muerte temporal de la ficha es " + f.get_muerte_temp());
    }

    public static void todos_movimientos() {
        if (f != null) {
            Taulell t = new Taulell("5rkr/pp3p2/2p3p1/6R1/1b2N1Q1/3P4/qPP4P/2K5 w - - 0 1");
            t.fen_ok();
            System.out.println("Un tablero ha sido generado para su comodidad.");

            ArrayList<Pair<Integer,Integer>> movs = f.todos_movimientos(t);
            for (Pair p : movs) {
                System.out.println("La ficha tiene un movimiento valido que la lleva a x: " + p.getKey() + ", y: " + p.getValue());
            }
        }
        else System.out.println("La ficha no ha sido creada");
    }

    public static void usage() {
        System.out.println("Menú de opciones: ");
        System.out.println("    0. Salir");
        System.out.println("    1. Crear una ficha");
        System.out.println("    2. Comprobar la correccion de una posicion para la ficha");
        System.out.println("    3. Actualizar posicion de la ficha");
        System.out.println("    4. Comprobar que un movimiento es correcto para la ficha");
        System.out.println("    5. Intercambiar el estado muerte temporal por su opuesto");
        System.out.println("    6. Te devuelve una lista con todos los movimientos correctos de la ficha");
    }

    public static void main(String args[]) {
        lector = new Scanner(System.in);
        usage();
        String s = "";
        try {
            while ((s = lector.nextLine()) != "0") {

                switch (s) {
                    case "0":
                        System.exit(0);
                    case "1":
                        Ficha();
                        break;
                    case "2":
                        pos_ok();
                        break;
                    case "3":
                        actualizar_pos();
                        break;
                    case "4":
                        movimiento_ok();
                        break;
                    case "5":
                        setMuerte_temp();
                        break;
                    case "6":
                        todos_movimientos();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
