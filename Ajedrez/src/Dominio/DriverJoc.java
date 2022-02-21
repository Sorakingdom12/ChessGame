package Dominio;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.util.Pair;

import Dominio.Joc;

public class DriverJoc {

    private static Joc J = null;
    private static Player Jugador;

    public static void crearJoc(){
        if(J != null) System.out.println("Ya existe un Joc");
        else {
            J = new Joc();
            J.MainStartup();
            Jugador = new Player("Testeador","Testeador1",1);
            J.setPlayer(Jugador);
            J.setP_act(Jugador);
            //problemas privados
            Problema P1 = new Problema(J,Jugador,"R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1","1");
            J.afegirProb(Jugador,P1);
            Problema P2 = new Problema(J,Jugador,"6k1/ppp3pp/2n1p3/8/3Pq3/PN4Q1/1P3rRP/5rRK b - - 0 1","2");
            J.afegirProb(Jugador,P2);
            Problema P3 = new Problema(J,Jugador, "Q4R2/3kr3/1q3n1p/2p1p1p1/1p1bP1P1/1B1P3P/2PBK3/8 w - - 0 1", "3");
            J.afegirProb(Jugador,P3);
            System.out.println("Joc creado con exito");
        }
    }

    public static void DrivBorrar(){

        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);

        ArrayList<Integer> Vec_Prob = J.getProblemes();
        System.out.println(" Escribe el numero de problema que quieres eliminar //(Ejemplo : 1 ) ");
        for (int i:Vec_Prob) System.out.println("Problema " + i );

        int entrada = lector.nextInt();

        boolean sinnombre = false;
        for (int i:Vec_Prob) if(i == entrada) sinnombre = true;
        if ( !sinnombre ) System.out.println("Este problema no consta en nuestra base de referencias");
        else {
            System.out.println("Problema detectado, tienes " + J.getProblemes().size() + " problemas guardados, se procedera a la eliminacion del problema " + entrada);
            J.Borra(entrada);
            System.out.println("Problemas restantes : " + J.getProblemes().size());
        }

    }

    public static void DrivEligeProblema(){

        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);

        System.out.println(" Escribe de donde quieres escoger el problema, publicos o privados (Ejemplo : publicos ) ");
        String tipo = lector.nextLine();
        System.out.println(" Escribe que mate quieres, 1, 2, 3, ... (Ejemplo : 1 ) ");
        int tema = lector.nextInt();
        System.out.println(" Escribe que dificultad quieres, facil, media o dificil (Ejemplo : facil ) ");
        String dificultad = lector.next();
        System.out.println(" Escribe que color quieres, blanco o negro (Ejemplo : blanco ) ");
        String color = lector.next();
        System.out.println(tipo+" "+tema+" "+dificultad+" "+color);

        ArrayList<Integer> Res;
        Res = J.EligeProblema(tema, dificultad, color, tipo);

        boolean correcto = true;
        for (int i:Res){
            ArrayList<Problema> Vec_Prob;
            if (tipo.equals("privados")){
                Vec_Prob = J.GetProblemes(1);
            }
            else {
                Vec_Prob = J.GetProblemes(0);
            }

            if (Vec_Prob.get(i).getTema() != tema) {
                correcto = false;
                System.out.println("tema incorrecto");
            }
            if ( ! Vec_Prob.get(i).getDificulty().equals(dificultad)) {
                correcto = false;
                System.out.println("dificultad incorrecta");
            }
            if (color.equals("blanco") && Vec_Prob.get(i).getStarter() != "blanca") {
                correcto = false;
                System.out.println("color incorrecto");
            }
            if (color.equals("negro") && Vec_Prob.get(i).getStarter() != "negra") {
                correcto = false;
                System.out.println("color incorrecto");
            }
        }

        if(correcto) System.out.println("Problemas encontrados y elegidos correctamente");
        else System.out.println("No se han escogido correctamente ");
    }

    public static void  DrivgetMatrix_prob(){
        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);
        System.out.println(" Escribe de donde quieres escoger el problema, publicos o privados (Ejemplo : publicos ) ");
        String tipo = lector.nextLine();

        ArrayList<Problema> Vec_Prob;
        if (tipo.equals("privados")){
            Vec_Prob = J.GetProblemes(1);
        }
        else {
            Vec_Prob = J.GetProblemes(0);
        }

        System.out.println(" Escribe el numero de problema que quieres seleccionar //(Ejemplo : 1 ) ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() );

        int id = lector.nextInt();

        char[][] matchar = J.getMatrix(tipo,id);
        pintarMatchar(matchar);

    }

    public static void  DrivgetMatrix_fen(){
        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);
        System.out.println(" La funcion asume que el Fen es correcto");
        System.out.println(" Escribe el Fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1 ) ");
        String fen = lector.nextLine();

        char[][] matchar = J.getMatrix(fen);
        pintarMatchar(matchar);
    }

    public static void pintarMatchar(char[][] Tablero){

        System.out.println("  +---+---+---+---+---+---+---+---+ ");
        for (int a = 0; a < Tablero.length;++a){
            System.out.print(8-a);
            for (int b = 0; b < Tablero[a].length; ++b) {
                System.out.print(" | ");
                System.out.print(Tablero[a][b]);
            }
            System.out.print(" | ");
            System.out.print("\n");
            System.out.println("  +---+---+---+---+---+---+---+---+ ");

        }
        System.out.println("    a   b   c   d   e   f   g   h   \n\n");
    }

    public static void DrivafegirProb(){
        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);
        ArrayList<Problema> Vec_Prob;
        Vec_Prob = J.GetProblemes(1);
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());
        System.out.println("Procedemos a crear un problema:");
        System.out.println("Asumimos que el fen no esta en la base de datos porque eso se comprueba en otra fucnion");
        System.out.println(" - introduce un fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1 )");
        String Fen = lector.nextLine();
        System.out.println(" - introduce un tema (Ejemplo : 1 )");
        String tema = lector.nextLine();
        System.out.println("Procedemos a crear el problema con fen " + Fen + " y con tema " + tema);

        Problema P = new Problema(J,Jugador, Fen, tema);
        J.afegirProb(Jugador,P);

        Vec_Prob = J.GetProblemes(1);
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());

        boolean correcto = true;
        if (Vec_Prob.get(Vec_Prob.size()-1).getCodi() != P.getCodi()) correcto = false;
        if (Vec_Prob.get(Vec_Prob.size()-1).getFen() != Fen) correcto = false;
        if (Vec_Prob.get(Vec_Prob.size()-1).getTema() != Integer.parseInt(String.valueOf(tema))) correcto = false;

        if(correcto) System.out.println("Problemas se ha añadido correctamente");
        else System.out.println("No se ha añadido correctamente ");


    }

    public static void DrivafegeixPub(){
        if(J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        System.out.println("Ahora añadiremos un problema publico y nosotros somos un usuario registrado, esto en nuestro programa no pasara, solo puede \n " +
                "añadir problemaas publicos el mismo programa o el administrador pero lo hacemos asi para comprobar la funcionalidad");
        Scanner lector = new Scanner(System.in);
        ArrayList<Problema> Vec_Prob;
        Vec_Prob = J.GetProblemes(0);
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());
        System.out.println("Procedemos a crear un problema:");
        System.out.println("Asumimos que el fen no esta en la base de datos porque eso se comprueba en otra fucnion");
        System.out.println(" - introduce un fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1 )");
        String Fen = lector.nextLine();
        System.out.println(" - introduce un tema (Ejemplo : 1 )");
        String tema = lector.nextLine();
        System.out.println("Procedemos a crear el problema con fen " + Fen + " y con tema " + tema);

        Problema P = new Problema(J,Jugador, Fen, tema);
        J.afegirProb(Jugador,P);

        Vec_Prob = J.GetProblemes(1);
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());

        boolean correcto = true;
        if (Vec_Prob.get(Vec_Prob.size()-1).getCodi() != P.getCodi()) correcto = false;
        if (Vec_Prob.get(Vec_Prob.size()-1).getFen() != Fen) correcto = false;
        if (Vec_Prob.get(Vec_Prob.size()-1).getTema() != Integer.parseInt(String.valueOf(tema))) correcto = false;

        if(correcto) System.out.println("Problemas se ha añadido correctamente");
        else System.out.println("No se ha añadido correctamente ");


    }

    public static void DrivRegistra() {
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);
        ArrayList<Player> Vec_Jug = new ArrayList<>();
        Vec_Jug.add(J.getPlayer(0));
        System.out.println("En esta funcion no se comprueba que el nombre este repetido, se hace en otra funcion \n" +
                "por lo tanto aqui asumimos que ese caso no se da en la entrada");
        System.out.println(" Estos son los jugadores que tienes en tu base de datos: ");
        for (int i = 1; i < J.getPlayerSize(); i++) {
            Vec_Jug.add(J.getPlayer(i));
            System.out.println("Codigo del jugador " + Vec_Jug.get(i).getCode_User() + " , nombre del jugador " + Vec_Jug.get(i).getUsername());
        }
        System.out.println("Procedemos a crear un jugador:");
        System.out.println(" - introduce un nombre de usuario no repetido que no contenga espacios y que no este vacio ");
        String User = lector.nextLine();
        System.out.println(" - introduce una contraseña de mas de 7 caracteres y menos de 11, que contenga mayuscula, minuscula y numero");
        String pass = lector.nextLine();
        System.out.println("Procedemos a crear el jugador con nombre de usuario " + User + " y con contraseña " + pass);
        Player Jug = new Player(User, pass, J.getPlayerSize());
        J.setPlayer(Jug);
        System.out.println(" Estos son los jugadores que tienes en tu base de datos: ");
        ArrayList<Player> Vec_Jug_Nuev = new ArrayList<>();
        Vec_Jug_Nuev.add(J.getPlayer(0));
        for (int i = 1; i < J.getPlayerSize(); i++) {
            Vec_Jug_Nuev.add(J.getPlayer(i));
            System.out.println("Codigo del jugador " + Vec_Jug_Nuev.get(i).getCode_User() + " , nombre del jugador " + Vec_Jug_Nuev.get(i).getUsername());
        }

        boolean correcto = true;
        if (Vec_Jug_Nuev.get(J.getPlayerSize() - 1).getCode_User() != Jug.getCode_User()) correcto = false;

        if (correcto) System.out.println("Jugador se ha añadido correctamente");
        else System.out.println("No se ha añadido correctamente ");


    }

    public static void DrivModificar(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }

        Scanner lector = new Scanner(System.in);
        Scanner lector2 = new Scanner(System.in);
        ArrayList<Problema> Vec_Prob;
        Vec_Prob = J.GetProblemes(1);
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());
        System.out.println("Procedemos a modificar un problema:");
        System.out.println(" - introduce el codigo del problema (Ejemplo : 1 )");
        int codi = lector.nextInt();
        System.out.println(" - introduce el nuevo fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1 )");
        String Fen = lector2.nextLine();
        System.out.println(" - introduce el nuevo tema (Ejemplo : 1 )");
        String tema = lector2.nextLine();
        System.out.println("Procedemos a modificar el problema con fen " + Fen + " y con tema " + tema);

        J.Modificar(codi,Fen,tema);

        Vec_Prob = J.GetProblemes(1);
        boolean correcto = true;
        System.out.println(" Estos son los problemas que tienes en tu base de datos: ");
        for (Problema p:Vec_Prob) System.out.println("Problema " + p.getCodi() + " , Fen "+  p.getFen() +" , Tema "+ p.getTema());

        if( !Vec_Prob.get(codi).getFen().equals(Fen ) ) correcto = false;

        if(correcto) System.out.println("Problemas se ha modificado correctamente");
        else System.out.println("No se ha modificado correctamente ");
    }

    public static void DrivcomprovaDuplo(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce un fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1 )");
        String fen = lector.nextLine();

        if(J.comprovaDuplo(fen)) System.out.println("Este fen esta duplicado");
        else System.out.println("Este fen no esta duplicado");


    }

    public static void Drivtemafine(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce un tema (Ejemplo : 1 )");
        String tema = lector.nextLine();

        if(J.temafine(tema)) System.out.println("Tema es correcto");
        else System.out.println("Tema es incorrecto");

    }

    public static void Drivvalid_name(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce un nombre de usuario que no contenga espacios y que no este vacio (Ejemplo : Diana )");
        String nom = lector.nextLine();

        boolean correcto = true;
        if (nom.equals("")) correcto = false;
        for (int i = 0; i < nom.length(); ++i) if (nom.charAt(i) == (' ')) correcto = false;
        boolean repe = false;
        for(int i = 0; !repe && i < J.getPlayerSize(); i++){
            if (J.getPlayer(i).getUsername().contentEquals(nom)) repe = true;
        }
        correcto = correcto && !repe;

        if (J.valid_name(nom) == correcto){
            System.out.println("La funcion devuelve valores correctos");
            if(correcto) System.out.println("En este caso el nombre introducido es correcto ");
            else System.out.println("En este caso el nombre introducido es incorrecto ");
        }else System.out.println("La funcion devuelve valores incorrectos");
    }

    public static void Drivvalid_pass(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce una contraseña (Ejemplo : Diana1998 )");
        String pass = lector.nextLine();

        boolean correcto = true;
        boolean mayus = false ,minus = false, number = false, size = false;
        if(pass.length() <= 11 && pass.length() >= 7) size = true;
        for(int i = 0; i < pass.length(); i++) {
            if (!mayus && pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z') mayus = true;
            else if (!minus && pass.charAt(i)>='a' && pass.charAt(i)<='z') minus = true;
            else if (!number && pass.charAt(i)>='0' && pass.charAt(i)<='9') number = true;
        }
        correcto = mayus && minus && number && size;

        if (J.valid_pass(pass) == 0 && correcto ){
            System.out.println("La funcion devuelve valores correctos");
            System.out.println("La contraseña se acepta");
        }
        else if(J.valid_pass(pass) == 1 && !correcto && !minus) {
            System.out.println("La funcion devuelve valores correctos");
            System.out.println("La contraseña está mal, falta una minuscula");
        }
        else if(J.valid_pass(pass) == 2 && !correcto && !mayus) {
            System.out.println("La funcion devuelve valores correctos");
            System.out.println("La contraseña está mal, falta una mayuscula");
        }
        else if(J.valid_pass(pass) == 3 && !correcto && !number) {
            System.out.println("La funcion devuelve valores correctos");
            System.out.println("La contraseña está mal, falta un numero");
        }
        else if(J.valid_pass(pass) == 4 && !correcto && !size) {
            System.out.println("La funcion devuelve valores correctos");
            System.out.println("La contraseña está mal, la medida no es la que toca");
        }
        else System.out.println("La funcion devuelve valores incorrectos");

    }

    public static Pair<Integer,Integer> Tradueix (String Posicion){
        // Letra = Y ; Numero =  X
        if (Posicion.isEmpty()){
            System.out.println("Entrada Vacia");
            return null;
        }
        char Letra = Posicion.charAt(0);
        char Numero = Posicion.charAt(1);
        int x,y;
        if (Letra == 'a'){
            y = 0;
        }
        else if (Letra == 'b'){
            y = 1;
        }
        else if (Letra == 'c'){
            y = 2;
        }
        else if (Letra == 'd'){
            y = 3;
        }
        else if (Letra == 'e'){
            y = 4;
        }
        else if (Letra == 'f'){
            y = 5;
        }
        else if (Letra == 'g'){
            y = 6;
        }
        else if(Letra == 'h'){
            y = 7;
        }
        else {
            System.out.println("Posición Invalida :"+ Letra);
            return new Pair<Integer, Integer>(-1,-1);
        }
        // Numeros
        if (Numero == '1'){
            x =7;
        }
        else if (Numero == '2'){
            x =6;
        }
        else if (Numero == '3'){
            x =5;
        }
        else if (Numero == '4'){
            x =4;
        }
        else if (Numero == '5'){
            x =3;
        }
        else if (Numero == '6'){
            x =2;
        }
        else if (Numero == '7'){
            x =1;
        }
        else if (Numero == '8'){
            x =0;
        }
        else {
            System.out.println("Posición Invalida :"+ Numero);
            return new Pair<Integer, Integer>(-1,-1);
        }
        return new Pair<Integer, Integer>(x,y);
    }

    public static void DrivtryToMoveFicha(){
        if (J == null) {
            System.out.println("No existe un Joc");
            return;
        }
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce un fen (Ejemplo : R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1  )");
        String fen = lector.nextLine();

        pintarMatchar( J.getMatrix(fen) );

        Taulell t = new Taulell(fen);
        t.fen_to_mat();

        System.out.println("Introduce las coordenadas de la ficha que quieres mover (Ejemplo : a1 )");
        String coord_ini = lector.nextLine();
        int X_ini, Y_ini;
        Pair<Integer,Integer> ini = Tradueix(coord_ini);
        X_ini = ini.getKey();
        Y_ini = ini.getValue();

        int id = t.get_id_ficha(X_ini,Y_ini);
        char char_color = fen.charAt(fen.length()-9);
        String color;
        if (char_color == 'w') color = "blanca";
        else color = "negra";

        System.out.println("Introduce las coordenadas finales de la ficha que quieres mover (Ejemplo : a1 )");
        String coord_fin = lector.nextLine();
        int X_fin, Y_fin;
        Pair<Integer,Integer> fin = Tradueix(coord_fin);
        X_fin = fin.getKey();
        Y_fin = fin.getValue();

        if ( t.get_ficha(id).get_color().equals(color)) {
            int Res = J.tryToMoveFicha(fen,id,X_fin, Y_fin, color);
            if (Res == 0) System.out.println("El movimiento es realizable");
            else if(Res == 1) System.out.println("El movimiento es realizable, la ficha es un peon y en opcion de promocion");
            else System.out.println("El movimiento no es realizable");
        }
        else {
            System.out.println("Estas intentendo mover una ficha del otro equipo");
        }



    }

    public static void main(String[] args) {

        System.out.println("Esto es el Driver del Joc, elige una de las opciones siguientes:");
        System.out.println("0. Salir");
        System.out.println("1. Creadora de Joc");
        System.out.println("2. Comprobar Borra()");
        System.out.println("3. Comprobar EligeProblema()");
        System.out.println("4. Comprobar getMatrix() de un problema");
        System.out.println("5. Comprobar getMatrix() de un fen");
        System.out.println("6. Comprobar getFen");
        System.out.println("7. Comprobar getTema");
        System.out.println("8. Comprobar getFicha");
        System.out.println("9. Comprobar MainStartup()");
        System.out.println("10. Comprobar afegirProb()");
        System.out.println("11. Comprobar afegeixPub()");
        System.out.println("12. Comprobar minimax");
        System.out.println("13. Comprobar FenCorrecto");
        System.out.println("14. Comprobar Registra");
        System.out.println("15. Comprobar Crear_Problema");
        System.out.println("16. Comprobar hacer_ranking");
        System.out.println("17. Comprobar Modificar");
        System.out.println("18. Comprobar LogIn");
        System.out.println("19. Comprobar tryToMoveFicha");
        System.out.println("20. Comprobar moverFicha");
        System.out.println("21. Comprobar comprovaDuplo");
        System.out.println("22. Comprobar temafine");
        System.out.println("23. Comprobar checkID");
        System.out.println("24. Comprobar valid_name");
        System.out.println("25. Comprobar valid_pass");

        Scanner lector = new Scanner(System.in);
        String s = "";
        try{

            while(( s = lector.nextLine()) != "0"){

                switch (s) {
                    case "0":
                        System.exit(0);
                        break;

                    case "1":
                        crearJoc();
                        break;

                    case "2":
                        DrivBorrar();
                        break;

                    case "3":
                       DrivEligeProblema();
                        break;

                    case "4":
                        DrivgetMatrix_prob();
                        break;

                    case "5":
                        DrivgetMatrix_fen();
                        break;

                    case "6":
                        System.out.println("Es un getter de otra clase");
                        break;

                    case "7":
                        System.out.println("Es un getter de otra clase");
                        break;

                    case "8":
                        System.out.println("Es un getter de otra clase");
                        break;

                    case "9":
                        System.out.println("Son llamadas a las creadoras de otras clases");
                        break;

                    case "10":
                        DrivafegirProb();
                        break;

                    case "11":
                        DrivafegeixPub();
                        break;

                    case "12":
                        System.out.println("Esta funcion se comprueba en el Driver de minimax, asumimos que funciona");
                        break;

                    case "13":
                        System.out.println("Esta funcion se comprueba en el Driver de Taulell, asumimos que funciona");
                        break;

                    case "14":
                        DrivRegistra();
                        break;

                    case "15":
                        System.out.println("Es la creadora de problema, se comprueba en el Driver de Problema y luego añade el problema creado que ya ha sido comprobada (caso 10)");
                        break;

                    case "16":
                        System.out.println("Esta funcion actualiza tus puntos de un problema y te devuelve los 10 primeros, se comprueba en RankingTest, suponemos que es correcta");
                        break;

                    case "17":
                        DrivModificar();
                        break;

                    case "18":
                        System.out.println("Es un setter, suponemos que hace su funcion correctamnete ");
                        break;

                    case "19":
                        DrivtryToMoveFicha();
                        break;

                    case "20":
                        System.out.println("Interaccion etre las capas de presentacion y dominio, Joc hace de controlador");
                        break;

                    case "21":
                        DrivcomprovaDuplo();
                        break;

                    case "22":
                        Drivtemafine();
                        break;

                    case "23":
                        System.out.println("Esta comprobacion se hace en el Driver de Player");
                        break;

                    case "24":
                        Drivvalid_name();
                        break;

                    case "25":
                        Drivvalid_pass();
                        break;

                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
