package Dominio;

import java.util.Scanner;

public class DriverProblema {
    private static Scanner lector;
    private static Problema p;

    public static void ProblemaPublic() {
        Joc J = new Joc();
        J.Registra("Admin", "Dd121212");
        System.out.println("Un Joc amb usuari administrador ha estat creat per a la creadora de publics");

        System.out.println("Introdueix un Fen");
        String Fen = lector.nextLine();

        System.out.println("Introdueix un tema (mate en ?)");
        int tema = lector.nextInt();
        int prev = J.getCodeProbNew(0);
        p = new Problema(J, Fen, tema);
        int post = J.getCodeProbNew(0);
        if (prev < post) System.out.println("El problema s'ha afegit correctament");
        else System.out.println("El problema ha estat determinat com a incorrecte");
    }

    public static void ProblemaPrivat() {
        Joc J = new Joc();
        J.Registra("Admin", "Dd121212");
        J.Registra("A", "Dd121212");
        J.Registra("B", "Dd121212");
        J.Registra("C", "Dd121212");
        J.Registra("D", "Dd121212");
        J.Registra("E", "Dd121212");

        System.out.println("Un Joc amb 5 usuaris i un administrador ha estat creat per a la creadora de privats");

        System.out.println("Introdueix el usuari a qui vols afegir el problema [1/2/3/4/5]");
        Player player = J.getPlayer(lector.nextInt());

        System.out.println("Introdueix un Fen");
        Scanner sc = new Scanner(System.in);
        String Fen = sc.nextLine();

        System.out.println("Introdueix un tema (mate en ?)");
        int tema = lector.nextInt();
        p = new Problema(J, Fen, tema);
        if (p.getCodi() != -1) System.out.println("El problema s'ha afegit correctament");
        else System.out.println("El problema ha estat determinat com a incorrecte");
    }

    public static void minusCodi() {
        if (p != null) {
            System.out.println("El Codi del problema es "+ p.getCodi());
            p.minusCodi();
            System.out.println("\nDespres de la trucada a minusCodi el codi es " + p.getCodi());
        }
        else System.out.println("El problema no ha estat inicialitzat");
    }

    public static void checkValid() {
        if (p != null) {
            String Fen = p.getFen();
            System.out.println("Ara comprovem la validesa del problema (Si es compleix en mate en " + p.getTema());
            Fen = p.checkValid(Fen);
            if (!Fen.equals("Wrong")) System.out.println("El problema es solucionable en mate en "+ p.getTema()+" i potser en menys");
            else System.out.println("El problema no es solucionable en mate en " + p.getTema());
        }
        else System.out.println("El problema no ha estat inicialitzat");
    }

    public static void exploreFen() {
        if (p != null) {
            Taulell Board = new Taulell(p.getFen());
            Board.fen_to_mat();
            System.out.println("Introduce la profundidad con la que quieres empezar a comprobar");
            System.out.println("(Ten en cuenta que compruebas las ganadoras, asi que la profundidad es impar)");
            int p_depth = lector.nextInt();
            char w = p.getFen().charAt(p.getFen().length()-9);
            String color1,color2;
            if (w == 'w') {
                color1 = "blanca";
                color2 = "negra";
            }
            else {
                color1 = "negra";
                color2 = "blanca";
            }
            System.out.println(w);
            if (p.exploreFen(Board, p_depth, color2, color1)) System.out.println("Hay al menos una jugada de las " + color1 + "s con mate asegurado");
            else System.out.println("No hay ninguna jugada de las " + color1 + "s con mate asegurado.");
        }
        else System.out.println("El problema no ha estat inicialitzat");
    }

    public static void checkLoser() {
        if (p != null) {
            Taulell Board = new Taulell(p.getFen());
            Board.fen_to_mat();
            System.out.println("Introduce la profundidad con la que quieres empezar a comprobar");
            System.out.println("(Ten en cuenta que compruebas las perdedoras, asi que la profundidad es par)");
            int p_depth = lector.nextInt();
            char w = p.getFen().charAt(p.getFen().length()-9);
            String color1,color2;
            if (w == 'w') {
                color1 = "blanca";
                color2 = "negra";
            }
            else {
                color1 = "negra";
                color2 = "blanca";
            }
            if (p.exploreFen(Board, p_depth, color2, color1)) System.out.println("Todas las jugadas de las " + color2 + "s acaban en mate a manos de las " + color1 + "s.");
            else System.out.println("Alguna jugada de las " + color2 + "s evita el mate por parte de las "+ color1 +"s.");
        }
        else System.out.println("El problema no ha estat inicialitzat");
    }

    public static void usage() {
        System.out.println("Menú de opciones: ");
        System.out.println("    0. Salir");
        System.out.println("    1. Crear un problema publico");
        System.out.println("    2. Crear un problema privado");
        System.out.println("    3. Restarle uno al codigo de un problema (Se usa despues de remover un problema)");
        System.out.println("    4. Comprobar la validez del tema del problema");
        System.out.println("    5. Comprobar si alguna jugada del color que empieza acaba en mate seguro en los movimientos que quedan");
        System.out.println("    6. Comprobar que todas las jugadas del color que no empieza acaban en mate seguro en los movimientos que quedan");
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
                        ProblemaPublic();
                        break;
                    case "2":
                        ProblemaPrivat();
                        break;
                    case "3":
                        minusCodi();
                        break;
                    case "4":
                        checkValid();
                        break;
                    case "5":
                        exploreFen();
                        break;
                    case "6":
                        checkLoser();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
