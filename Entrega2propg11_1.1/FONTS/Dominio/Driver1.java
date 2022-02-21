package Dominio;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver1 {

    /* Driver de la clase taulell y de la clase ficha */

    private static Scanner lector;

    private static Ficha f = null;
    private static Taulell t = null;
    private static Problema p = null;


    public static void Taulell() {
        if (t != null) System.out.println("Ya existe el taulell");
        else {
            System.out.println("Introduce un fen");
            String fen = lector.nextLine();
            t = new Taulell(fen);
            System.out.println("Taulell creado (fen sin comprobar si es correcto)");
        }
    }

    public static void fen_to_mat() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            int sit = t.fen_to_mat();
            if (sit == 0) {
                System.out.println("Se ha transformado el fen a mat correctamente: ");
            }
            else {
                System.out.println("Fen no valido. Taulell eliminado");
                t = null;
            }
        }
    }

    public static void get_id_ficha() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce la posicion x");
            int x = lector.nextInt();
            System.out.println("Introduce la posicion y");
            int y = lector.nextInt();
            int id = t.get_id_ficha(x, y);
            if (id == -1) System.out.println("No hay una ficha en esa posicion");
            else System.out.println("El id de la ficha que esta en la posicion " + x + " " + y + " es " + id);
        }
    }

    public static void get_nom_ficha() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha: ");
            int id = lector.nextInt();
            String nom = t.get_nom_ficha(id);
            System.out.println("El nombre de la ficha con id " + id + " es " + nom);
        }
    }

    public static void get_clau_ficha() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha: ");
            int id = lector.nextInt();
            char c = t.get_clau_ficha(id);
            System.out.println("La clau de la ficha con id " + id + " es " + c);
        }
    }

    public static void get_color_ficha() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha");
            int id = lector.nextInt();
            String color = t.get_color_ficha(id);
            System.out.println("El color de la ficha con id " + id + " es " + color);
        }
    }

    public static void dentro_del_tablero() {
        if(t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce una posicion x:");
            int posx = lector.nextInt();
            System.out.println("Introduce una posicion y");
            int posy = lector.nextInt();
            boolean dentro = t.dentro_del_tablero(posx, posy);
            if (dentro) System.out.println("La posición del tablero si es una posicion del tablero");
            else System.out.println("La posicion del tablero no es una posicion del tablero");

        }
    }

    public static void mat_to_fen() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            String fen = t.mat_to_fen();
            System.out.println("Se ha traducido correctamente. El fen es: " + fen);
        }
    }

    public static void mate() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el color que quieres comprobar: [blanca/negra]");
            String color = lector.nextLine();
            if(color.equals("blanca") || color.equals("negra")) {
                boolean mate = t.mate(color);
                if (mate) System.out.println("El rey de color " + color + " si esta en mate");
                else System.out.println("El rey de color " + color + " no está en mate");
            }
            else System.out.println("El color introducido no es correcto");
        }
    }

    public static void jaque() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el color que quieres comprobar: [blanca/negra]");
            String color = lector.nextLine();
            if (color.equals("negra") || color.equals("blanca")) {
                boolean mate = t.jaque(color);
                if (mate) System.out.println("El rey de color " + color + " si está en jaque");
                else System.out.println("El rey de color " + color + " no esta en jaque");
            }
            else System.out.println("El color introducido no es correcto");
        }
    }

    public static void todos_movs() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el color que quieres comprobar: [blanca/negra]");
            String color = lector.nextLine();
            ArrayList<Nodo> result = null;
            if (color.equals("blanca")) {
                result = t.todos_movs(0, 1);
            }
            else if (color.equals("negra")){
                result = t.todos_movs(1, 1);
            }
            else System.out.println("Color introducido no correcto");
            if (result != null) {
                System.out.println("Todos los posibles movimientos de las fichas " + color + "s: ");
                for (int i = 0; i < result.size(); i++) {
                    Nodo aux = result.get(i);
                    System.out.println("Ficha: " + aux.get_ficha().get_nom() + ", posx final: " + aux.get_posx() + ", posy final: " + aux.get_posy());
                }
            }
        }
    }

    public static void puntua() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el id valido de la ficha que quieres puntuar: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            String color = t.get_color_ficha(id);
            System.out.println("Introduce la posx final: ");
            int x = lector.nextInt();
            System.out.println("Introduce la posy final: ");
            int y = lector.nextInt();
            System.out.println("Introduce la ficha a la que quieres promover o false si no quieres promover: [caball,alfil,reina,torre,false]");
            String forma = sc.nextLine();
            if (forma.equals("false") || forma.equals("caball") || forma.equals("alfil") || forma.equals("torre") || forma.equals("reina")) {
                System.out.println("¿Quieres la puntuación positiva [true] o negativa [false]?");
                boolean op = lector.nextBoolean();
                Nodo n = new Nodo(aux, x, y, 0.0, null, forma);
                if (forma.equals("false")) t.puntua(n, op, false);
                else t.puntua(n, op, true);
                System.out.println("La puntuacion del movimiento de la ficha es: " + n.get_puntuacion());
            }
            else {
                System.out.println("Forma no introducida correctamente");
            }
        }
    }

    public static void amenazo() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id valido de la ficha: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            String color = t.get_color_ficha(id);
            Double puntuacion = t.ataca(aux, color);
            System.out.println("La ficha con id " + id + " esta amenazando a diferentes fichas con un total colectivo de " + puntuacion + "puntos" );
        }
    }

    public static void estoy_expuesto() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            String color = t.get_color_ficha(id);
            boolean expuesto = t.amenaza(aux.get_pos_x(), aux.get_pos_y(), color);
            if (expuesto) System.out.println("La ficha con id " + id + " si está expuesta");
            else System.out.println("La ficha con id " + id  + " no esta expuesta");
        }
    }

    public static void estoy_protegido() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            String color = t.get_color_ficha(id);
            if (color.equals("negra") ){
                boolean expuesto = t.amenaza(aux.get_pos_x(), aux.get_pos_y(), "blanca");
                if (expuesto) System.out.println("La ficha con id " + id + " si está expuesta");
                else System.out.println("La ficha con id " + id  + " no está expuesta");
            }
            else if (color.equals("blanca")) {
                boolean expuesto = t.amenaza(aux.get_pos_x(), aux.get_pos_y(), "negra");
                if (expuesto) System.out.println("La ficha con id " + id + " si esta expuesta");
                else System.out.println("La ficha con id " + id  + " no esta expuesta");
            }
        }
    }

    public static void aseguro() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            String color = t.get_color_ficha(id);
            Double puntuacion = null;
            if (color.equals("blanca")) {
                puntuacion = t.Asegura(aux, "negra");
            }
            else if (color.equals("negra")) {
                puntuacion = t.Asegura(aux, "blanca");
            }
            System.out.println("La ficha con id " + id + " esta protegiendo a diferentes fichas con un total colectivo de " + puntuacion + "puntos");
        }
    }

    public static void mover() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres mover");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            System.out.println("Introduce posicion x final: ");
            int x = lector.nextInt();
            System.out.println("Introduce posicion y final: ");
            int y = lector.nextInt();
            Ficha muerta = t.mover(aux, x, y);
            System.out.println("El movimiento se ha realizado (No se ha comprobado que el movimiento sea legal)");
            if (muerta != null) {
                System.out.println("Has matado a la ficha: " + muerta.get_nom());
            }
        }
    }

    public static void resucitar() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres \"resucitar\"");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            t.resucitar(aux);
            System.out.println("La ficha con id " + id +" ha cambiado su estado de muerte temporal. (No se ha comprobado si la ficha estaba en el tablero)");
        }
    }

    public static void to_promote() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres promover");
            int id = lector.nextInt();
            String nom = t.get_nom_ficha(id);
            if (nom != "peo") System.out.println("Esta ficha no se puede promover (no es un peon)");
            else {
                Ficha aux = t.get_ficha(id);
                boolean sepuede = t.to_promote(aux);
                if (sepuede) System.out.println("La ficha se puede promover");
                else System.out.println("La ficha no se puede promover");
            }
        }
    }

    public static void promover_peon() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres promover: ");
            int id = lector.nextInt();
            String nom = t.get_nom_ficha(id);
            if (!nom.equals("peo")) System.out.println("Esta ficha no se puede promover (no es un peon)");
            else {
                Scanner sc = new Scanner(System.in);
                Ficha aux = t.get_ficha(id);
                System.out.println("Indica la forma a la que quieres promoverla [alfil/caball/torre/reina] ");
                String forma = sc.nextLine();
                if (forma.equals("alfil") || forma.equals("caball") || forma.equals("torre") || forma.equals("reina")) {
                    f= t.promover_peon(aux, forma);
                    System.out.println("Ficha promovida a " + forma + " correctamente");
                }
                else System.out.println("La forma introducida no es correcta");
            }
        }
    }

    public static void despromover() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres despromover: ");
            int id2 = lector.nextInt();
            Ficha aux2 = t.get_ficha(id2);
            t.despromover(aux2, f);
            System.out.println("Ficha despromovida correctamente");
        }
    }

    public static void camino_ok() {
        if(t==null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("Introduce el id de la ficha que quieres comprobar: ");
            int id = lector.nextInt();
            Ficha aux = t.get_ficha(id);
            int x = aux.get_pos_x();
            int y = aux.get_pos_y();
            String color = aux.get_color();
            System.out.println("Indica el vector de dirección en que te quieres mover: [direccion x, direccion y]");
            int dirx = lector.nextInt();
            int diry = lector.nextInt();
            System.out.println("Indica cuantas veces te quieres mover en esa direccion");
            int count = lector.nextInt();
            boolean sepuede = t.camino_ok(x, y, dirx, diry, count, color);
            if (sepuede) System.out.println("Se puede hacer este camino con la pieza (No se ha comprobado que sea un movimiento legal)");
            else System.out.println("No se puede hacer este camino (No se ha comprobado que sea un movimiento legal)");
        }
    }

    public static void matchar() {
        if (t == null) System.out.println("No se ha creado el taulell");
        else {
            System.out.println("La matriz del tablero con chars es: ");
            char[][] matchar = t.mat_char();
            System.out.println("  +---+---+---+---+---+---+---+---+ ");
            for (int a = 0; a < 8; ++a) {
                System.out.print(8 - a);
                for (int b = 0; b < 8; ++b) {
                    System.out.print(" | ");
                    System.out.print(matchar[a][b]);
                }
                System.out.print(" | ");
                System.out.print("\n");
                System.out.println("  +---+---+---+---+---+---+---+---+ ");
            }
            System.out.println("    a   b   c   d   e   f   g   h   \n\n");
        }
    }

    public static void usage() {
        System.out.println("Menu de opciones: ");
        System.out.println("    0. Salir");
        System.out.println("    1. Crear un taulell");
        System.out.println("    2. Transformar fen a matriz");
        System.out.println("    3. Get id de una ficha");
        System.out.println("    4. Get nom de una ficha");
        System.out.println("    5. Get clau de una ficha");
        System.out.println("    6. Get color de una ficha");
        System.out.println("    7. Comprobar si una posición esta dentro del tablero");
        System.out.println("    8. Transformar de matriz a fen");
        System.out.println("    9. ¿Hay mate?");
        System.out.println("   10. ¿Hay jaque?");
        System.out.println("   11. Get todos los movimientos de un color");
        System.out.println("   12. Get puntuacion de un movimiento");
        System.out.println("   13. ¿Amenazo?");
        System.out.println("   14. ¿Estoy expuesto?");
        System.out.println("   15. ¿Estoy protegido?");
        System.out.println("   16. ¿Aseguro?");
        System.out.println("   17. Mover una ficha");
        System.out.println("   18. Resucitar una ficha");
        System.out.println("   19. ¿Se puede promover esta ficha?");
        System.out.println("   20. Promover un peon");
        System.out.println("   21. Despromover una ficha");
        System.out.println("   22. ¿Es posible hacer este camino?");
        System.out.println("   23. Imprimir la matriz del tablero con chars (claus)");
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
                        Taulell();
                        break;
                    case "2":
                        fen_to_mat();
                        break;
                    case "3":
                        get_id_ficha();
                        break;
                    case "4":
                        get_nom_ficha();
                        break;
                    case "5":
                        get_clau_ficha();
                        break;
                    case "6":
                        get_color_ficha();
                        break;
                    case "7":
                        dentro_del_tablero();
                        break;
                    case "8":
                        mat_to_fen();
                        break;
                    case "9":
                        mate();
                        break;
                    case "10":
                        jaque();
                        break;
                    case "11":
                        todos_movs();
                        break;
                    case "12":
                        puntua();
                        break;
                    case "13":
                        amenazo();
                        break;
                    case "14":
                        estoy_expuesto();
                        break;
                    case "15":
                        estoy_protegido();
                        break;
                    case "16":
                        aseguro();
                        break;
                    case "17":
                        mover();
                        break;
                    case "18":
                        resucitar();
                        break;
                    case "19":
                        to_promote();
                        break;
                    case "20":
                        promover_peon();
                        break;
                    case "21":
                        despromover();
                        break;
                    case "22":
                        camino_ok();
                        break;
                    case "23":
                        matchar();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
