package Dominio;

import java.util.Scanner;

public class DriverPlayer {
    private static Scanner sc = new Scanner(System.in);
    private static Player p = null;

    public static void Player() {
        if (p != null) System.out.println("El jugador ya está creado");
        else {
            System.out.println("Introduce un username: ");
            String username = sc.nextLine();
            System.out.println("Introduce una contraseña: ");
            String password = sc.nextLine();
            p = new Player(username, password, 1);
            System.out.println("Se ha creado el usuario " + p.getUsername() + " con password " + p.getPassword());
        }
    }

    public static void usage() {
        System.out.println("Menú de opciones: ");
        System.out.println("    0. Salir");
        System.out.println("    1. Crear Jugador");
    }

    public static void main(String args[]) {
        sc = new Scanner(System.in);
        usage();
        String s = "";
        try {
            while ((s = sc.nextLine()) != "0") {

                switch (s) {
                    case "0":
                        System.exit(0);
                    case "1":
                        Player();
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
