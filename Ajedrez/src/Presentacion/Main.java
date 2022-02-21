package Presentacion;


import Dominio.Joc;
import javafx.util.Pair;

import java.util.Scanner;
import java.util.ArrayList;

public class Main{

    public void MainHub(Joc J){
        while(true) {
            boolean start = false;
            do {
                System.out.println("[login/register/salir]");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();

                if (s.contentEquals("login")) {
                    boolean usrPassw = false;
                    int cont = 0;
                    String nom;
                    do{
                        if (cont != 0) System.out.println("Username o Password Incorrecta, Intenta de nuevo o Exit");
                        Scanner a = new Scanner(System.in);

                        System.out.println("Username :");
                        nom = a.nextLine();

                        if (nom.contentEquals("Exit")) break;

                        System.out.println("Password :");
                        String p = a.nextLine();

                        usrPassw = J.checkID(nom, p);

                        ++cont;
                    }while(!usrPassw);

                    if (!nom.equals("Exit") && usrPassw) {
                        J.LogIn(nom);
                        start = true;
                    }
                }

                else if (s.contentEquals("register")) {

                    boolean okiname = false;
                    boolean okipass = false;
                    int cont = 0;
                    String name = "";
                    String pass = "";
                    while (!okiname) {
                        if (cont != 0) System.out.println("Username en Uso");
                        name = nuevoUsn();
                        okiname = J.valid_name(name);
                        ++cont;
                    }

                    while(!okipass) {
                        pass = nuevoPwd();
                        int us = J.valid_pass(pass);
                        if (us == 0) okipass = true;
                        else Usage(us);
                    }

                    J.Registra(name,pass);
                    System.out.println("Usuario Registrado!");
                }
                else if(s.contentEquals("salir")){
                    System.exit(0);
                }
            } while (!start);

            int Opt = 0;
            while (Opt != -1) {
                Opt = showOptions();

                if (Opt == 1) {
                    boolean Color = false;
                    String p1 = ChoosePlayer();
                    String p2 = ChoosePlayer();
                    if (p1.equals("IA") && p2.equals("IA")){
                        int k;
                        String s = "";
                        while (!J.temafine(s)) {
                            System.out.println("Introduce el número de problemas a resolver:");
                            Scanner in = new Scanner(System.in);
                            s = in.nextLine();
                        }
                        k = Integer.parseInt(s);
                        int primera = 0, segunda = 0;
                        for (int i = 0; i < k; i++) {
                            int ganador = ChooseP(J, p1, p2);
                            if (ganador == 1) primera++;
                            else if (ganador == 2) segunda++;
                        }
                        System.out.println("IA 1: " + primera + " IA 2: "+ segunda);
                        if (primera > segunda) System.out.println("\nIA 1 es mejor que IA 2\n");
                        else System.out.println("\nIA 2 es mejor que IA 1\n");
                    }
                    else {
                        ChooseP(J, p1, p2);
                    }
                }

                //if (Opt == 2);

                if (Opt == 3) {
                    boolean repe = false;
                    Scanner in = new Scanner(System.in);
                    System.out.println("Introduce un Fen válido: ");
                    String Fen = in.nextLine();
                    int FenOk = -1;
                    System.out.println("Fen Validando");
                    while (FenOk != 0 && !Fen.equals("Exit")) {
                        FenOk = J.FenCorrecto(Fen);
                        UsageFen(FenOk);
                        if(FenOk != 0) {
                            System.out.println(" Prueba un nuevo Fen o escribe 'Exit' para salir. ");
                            Fen = in.nextLine();
                        }
                        else if(J.comprovaDuplo(Fen)) {
                            System.out.println(" El Fen introducido ya existe.");
                            FenOk = -1;
                        }
                    }
                    if (!Fen.equals("Exit")) {
                        boolean temita = false;
                        int cont = 0;
                        String tema_prob = "";
                        while (!temita) {
                            if (cont != 0) System.out.println("Entrada Erronea");
                            System.out.println(" Introduce el tema - Mate en: ");
                            tema_prob = in.nextLine();
                            temita = valid(tema_prob);
                            ++cont;
                        }

                        if (J.Crear_Problema(Fen, tema_prob)) System.out.println("Problema Añadido\n");
                        else System.out.println(" Problema no resoluble en el mate elegido\n");
                    }
                }

                if (Opt == 4){ // Modificar Problema
                    int choice = -1;
                    Scanner in = new Scanner(System.in);
                    ArrayList<Integer> llista = J.getProblemes();
                    for (Integer codi : llista) {
                        System.out.println("  +-------------------------------+");
                        System.out.println("  |               "+ codi + "               |");
                        System.out.println("  +-------------------------------+\n");

                        char[][] matchar = J.getMatrix("privados",codi);
                        pintarMatchar(matchar);
                    }
                    boolean decided = false;
                    int count = 0;
                    String decision = "";
                    while(!decided){
                        if (count != 0) System.out.println(" Entrada Invalida ");
                        System.out.println("Elige: ");
                        decision= in.nextLine();
                        if(decision.equals("exit")) decided = true;
                        else if (valid(decision)){
                            choice = Integer.parseInt(decision);
                            for(int aux: llista) {
                                if (aux == choice) decided = true;
                            }
                        }
                        ++count;
                    }
                    if (!decision.equals("exit")){
                        System.out.println("Introduce un Fen válido: ");
                        String Fen = in.nextLine();
                        int FenOk = -1;
                        System.out.println("Fen Validando");
                        while (FenOk != 0 && !Fen.equals("Exit")) {
                            FenOk = J.FenCorrecto(Fen);
                            UsageFen(FenOk);
                            if(FenOk != 0) {
                                System.out.println(" Prueba un nuevo Fen o escribe 'Exit' para salir. ");
                                Fen = in.nextLine();
                            }
                        }

                        boolean temita = false;
                        int cont = 0;
                        String tema_prob = "";
                        while(!temita) {
                            if (cont != 0) System.out.println("Entrada Erronea");
                            System.out.println(" Introduce el tema - Mate en: ");
                            tema_prob = in.nextLine();
                            temita = valid(tema_prob);
                            ++cont;
                        }
                        J.Modificar(choice,Fen,tema_prob);
                    }

                }

                if (Opt == 5){// Eliminar Problema
                    int choice = -1;
                    Scanner in = new Scanner(System.in);
                    ArrayList<Integer> llista = J.getProblemes();
                    for (Integer codi : llista) {
                        System.out.println("  +-------------------------------+");
                        System.out.println("  |               "+ codi + "               |");
                        System.out.println("  +-------------------------------+\n");

                        char[][] matchar = J.getMatrix("privados",codi);
                        pintarMatchar(matchar);
                    }
                    boolean decided = false;
                    int count = 0;
                    String decision = "";
                    while(!decided){
                        if (count != 0) System.out.println(" Entrada Invalida ");
                        System.out.println("Elige: ");
                        decision= in.nextLine();
                        if(decision.equals("exit")) decided = true;
                        else if (valid(decision)){
                            choice = Integer.parseInt(decision);
                            for(int aux: llista) {
                                if (aux == choice) decided = true;
                            }
                        }
                        ++count;
                    }
                    if(!decision.equals("exit"))J.Borra(choice);
                }
            }
        }
    }

    public int showOptions(){
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println(" Elige: [1/2/3/4/5/6/7]\n");
            System.out.println(" 1 - Jugar Partida");
            System.out.println(" 2 - Cargar Partida");
            System.out.println(" 3 - Crear Problema");
            System.out.println(" 4 - Modificar Problema");
            System.out.println(" 5 - Eliminar Problema");
            System.out.println(" 6 - Editar Perfil ");
            System.out.println(" 7 - Salir ");
            String Option = in.nextLine();
            switch (Option) {
                case "1":
                    return 1;

                case "2":
                    System.out.println("Work In Progress");
                    break;
                //return 2;
                case "3":
                    return 3;

                case "4":
                    return 4;

                case "5":
                    return 5;

                case "6":
                    System.out.println("Work In Progress");
                    //return 6;
                    break;

                case "7":
                    return -1;

                default:
                    System.out.println("Opción no Válida!");

            }
        }
    }

    public void Usage(int i){
        if (i == 1)System.out.println("Password Incorrecta : Falten minúscule/s ");
        if (i == 2)System.out.println("Password Incorrecta : Falten Majúscule/s");
        if (i == 3)System.out.println("Password Incorrecta : Falten Nombre/s");
        if (i == 4)System.out.println("Password Incorrecta : Mínim: 8 caràcters, Màxim: 12");
    }

    public void pintarMatchar(char[][] Tablero){

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

    public void UsageFen(int tipo) {

        if (tipo == 0 ) {
            System.out.println("Fen Correcto");
        }
        else if ( tipo == 1 ){
            System.out.println("Error: tablero mal definido, muchas columnas ");

        }
        else if ( tipo == 2 ) {
            System.out.println("Error: tablero mal definido, pocas columnas ");

        }
        else if ( tipo == 3 ){
            System.out.println("Error: tablero mal definido, muchas filas ");

        }
        else if ( tipo == 4 ){
            System.out.println("Error: tablero mal definido, pocas filas ");
        }
        else if ( tipo == 5 ){
            System.out.println("Error: muchos peones blancos ");
        }
        else if ( tipo == 6 ){
            System.out.println("Error: muchos peones negros ");
        }
        else if ( tipo == 7 ){
            System.out.println("Error: muchas fichas blancas ");
        }
        else if ( tipo == 8 ){
            System.out.println("Error: muchas fichas negras ");

        }
        else if ( tipo == 9 ) {
            System.out.println("Error: el final es diferente de \" - - 0 1\" ");

        }
        else if (tipo == 10){
            System.out.println("Error: peón mal colocado ");

        }
        else if (tipo == 11){
            System.out.println("Error: el rey negro y el blanco están en casillas adyacentes");
        }
        else if (tipo == 12){
            System.out.println("Error: hay mas de un rey negro");
        }
        else if (tipo == 13){
            System.out.println("Error: hay mas de un rey blanco");
        }
        else if (tipo == 14){
            System.out.println("Error: no hay ningún rey negro");
        }
        else if (tipo == 15){
            System.out.println("Error: no hay ningún rey blanco");
        }
        else if (tipo == 16){
            System.out.println("Error: carácter inválido");
        }
    }

    public String nuevoUsn(){
        Scanner in = new Scanner(System.in);
        System.out.print(" Introduce Username: \n");
        return in.nextLine();
    }

    public String nuevoPwd(){
        Scanner in = new Scanner(System.in);
        System.out.print(" Introduce Password: \n");
        return in.nextLine();
    }

    public String ChoosePlayer(){
        String s = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige Jugador: [Humano/IA]"); // Cambiará a más IA se añadan
        s = sc.nextLine();
        while (!s.equals("Humano") && !s.equals("IA")) {
            s = sc.nextLine();
        }
        return s;
    }

    public int ChooseP(Joc J, String p1, String p2){
        //si devolvemos 1 ha ganado la IA 1, si devolvemos 2 ha ganado la IA 2
        int choice = 0;
        Scanner in = new Scanner(System.in);
        String s = "";
        String tema;
        while (!J.temafine(s)) {
            System.out.println("Introduce el Numero de Jugadas para mate [1/2/3/...]");
            s = in.nextLine();
        }
        String dif;
        int cont = 0;
        do {
            if (cont != 0) System.out.println("Entrada Erronea");
            System.out.println("Dificultad? [facil/media/dificil]");
            dif = in.nextLine();
            ++cont;
        }while (!dif.contentEquals("facil") && !dif.contentEquals("dificil") && !dif.contentEquals("media"));

        int mate = Integer.parseInt(String.valueOf(s));
        cont = 0;
        String Color;
        while (!s.equals("blanco") && !s.equals("negro")){
            if (cont != 0 ) System.out.println("Color Incorrecto");
            System.out.println("Elige un Color de Inicio: [blanco/negro]");
            s = in.nextLine();
            ++cont;
        }
        Color = s;
        cont = 0;
        System.out.println("[publicos / privados]?");
        while(!s.equals("publicos") && !s.equals("privados")){
            if (cont != 0 ) System.out.println("Entrada Incorrecta [publicos/privados]?");
            s = in.nextLine();
            ++cont;
        }


        ArrayList<Integer> Codis = J.EligeProblema(mate,dif,Color,s);

        for (Integer codi : Codis) {
            System.out.println("  +-------------------------------+");
            System.out.println("  |               "+ codi + "               |");
            System.out.println("  +-------------------------------+\n");

            char[][] matchar = J.getMatrix(s,codi);
            pintarMatchar(matchar);
        }
        boolean decided = false;
        int count = 0;
        String decision = "";
        while(!decided){
            if (count != 0) System.out.println(" Entrada Invalida ");
            System.out.println("Elige: ");
            decision= in.nextLine();
            if (valid(decision)){
                choice = Integer.parseInt(decision);
                for(int aux: Codis) {
                    if (aux == choice) decided = true;
                }
            }
            ++count;
        }

        Pair<Integer, Double> result;

        if(Color.equals("negro")) result = Simula_Game(choice, s, p1, p2, true,J);
        else result = Simula_Game(choice, s, p1, p2, false,J);

        if(result.getKey() == -1) System.out.println("GAME OVER, Out Of Moves :(");
        else if (result.getKey() == 1 ||result.getKey() == 2) System.out.println("Felicidades Jugador\nHas Espachurrado a tu Enemigo");

        //Ranking
        if (s.equals("publicos") && p1.equals("Humano")) {
            Pair<ArrayList<Pair<Integer, Double>>, Integer> res = J.hacer_ranking(choice, result.getValue());
            if (res != null) {
                ArrayList<Pair<Integer, Double>> Scoreboard = res.getKey();
                int pos = res.getValue();
                System.out.println("Ranking: ");
                for (int i = 0; i < 10 && i < Scoreboard.size(); ++i) {
                    if (i == pos) System.out.print(" -->");
                    System.out.println(" User ID: " + Scoreboard.get(i).getKey() + ", Username: " + J.getPlayer(Scoreboard.get(i).getKey()).getUsername() + ", Score: " + Scoreboard.get(i).getValue());
                }
                if (pos - 3 >= 10) System.out.println();
                for (int i = pos - 3; i < pos + 3 && pos < Scoreboard.size(); ++i) {
                    if (i >= 10) {
                        if (i == pos) System.out.print(" -->");
                        System.out.println(" - User ID: " + Scoreboard.get(i).getKey() + ", Score: " + Scoreboard.get(i).getValue());
                    }

                }
            } else {
                System.out.println("Player is not on this ranking. Complete the problem and try again.");
            }
        }

        if (p1.equals("IA") && p2.equals("IA")) {
            if (result.getKey() == 0) return 1;
            else if (result.getKey() == 1) return 2;
        }
        return 0;


        //Color Player == Color??

    }

    public boolean valid(String a){
        if (a.equals("")) return false;
        for(int i =0; i< a.length(); ++i){
            if (a.charAt(i) >= '0' && a.charAt(i)<= '9') return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Main m = new Main();
        Joc J = new Joc();
        J.MainStartup();
        m.MainHub(J);
    }

    public Pair<Integer,Integer> Tradueix (String Posicion){
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

    public void Tradueix2 (int posinix, int posiniy, int posx, int posy){
        switch(posiniy){
            case 0: System.out.print("a"); break;
            case 1: System.out.print("b"); break;
            case 2: System.out.print("c"); break;
            case 3: System.out.print("d"); break;
            case 4: System.out.print("e"); break;
            case 5: System.out.print("f"); break;
            case 6: System.out.print("g"); break;
            case 7: System.out.print("h"); break;
        }
        System.out.print((8-posinix));
        System.out.print(" movida a ");
        switch(posy){
            case 0: System.out.print("a"); break;
            case 1: System.out.print("b"); break;
            case 2: System.out.print("c"); break;
            case 3: System.out.print("d"); break;
            case 4: System.out.print("e"); break;
            case 5: System.out.print("f"); break;
            case 6: System.out.print("g"); break;
            case 7: System.out.print("h"); break;
        }
        System.out.println((8-posx));
    }

    public Pair<Integer, Double> Simula_Game(int codi, String tipo, String player1, String player2,boolean ColorPlayer, Joc J){
        /*Ahora le pasamos el codi y tenemos que buscar el problema con ese codigo*/


        char[][] matchar = J.getMatrix(tipo, codi);
        pintarMatchar(matchar);
        String Fen = J.getFen(tipo, codi);

    /*
    Problema Prob = null;
    if (tipo.equals("publicos")) Prob = Problemes.get(0).get(codi);
    else Prob = Problemes.get(P_act.getCode_User()).get(codi);
    */
        //ColorPlayer true= negras false = blancas
        String Color1;
        String Color2;
        if (!ColorPlayer) {
            Color1 = "blanca";
            Color2 = "negra";
        }
        else{
            Color1 = "negra";
            Color2 = "blanca";
        }
        String colorJug;

        //Taulell t = new Taulell(Prob.getFen());
        //Alternativa a Taulell en Main

        //t.fen_to_mat();
        int endgame = 2*J.getTema(tipo,codi)-1;
        double Puntos = 0.0;
        //Nodo Apunta = new Nodo(null,-1,-1,0.0,null);
        boolean mate;

        if (player1.equals("Humano") || player2.equals("Humano")) { // Alguien es Human
            /* Sequencia turno*/
            boolean human = false;
            boolean human2 = false;
            boolean bothHuman;
            mate =  false;
            if (player1.equals("Humano")) human = true;
            if (player2.equals("Humano")) human2 = true;
            bothHuman = human && human2;

            if (bothHuman) { // Esperamos cada turno, Preguntamos Jugadas Cada Vez

                boolean Turno = false; // False 1r , True 2n

                while (!mate && endgame > 0){
                    if (!Turno) {
                        // False --> 1r
                        System.out.println("\n------- Turno: Jugador 1 -------\n");
                        colorJug = Color1;
                    }
                    else{
                        System.out.println("\n------- Turno: Jugador 2 -------\n");
                        colorJug = Color2;
                    }

                    Scanner uno = new Scanner(System.in);
                    String Act;
                    String Final;
                    Pair<Integer, Integer> a;
                    int xAct,yAct,xFi,yFi;
                    xAct = yAct = xFi = yFi = -1;
                    boolean moved = false;

                    while (!moved) {
                        while (xAct == -1) {
                            System.out.println("Introduce [Posicion_Actual]\n");
                            Act = uno.nextLine();
                            a = Tradueix(Act);
                            if (a != null) {
                                xAct = a.getKey();
                                yAct = a.getValue();
                            }
                        }
                        while (xFi == -1) {
                            System.out.println("\nIntroduce [PosiciónFinal]\n");
                            Final = uno.nextLine();
                            a = Tradueix(Final);
                            if(a != null) {
                                xFi = a.getKey();
                                yFi = a.getValue();
                            }
                        }

                        int ficha;
                        // -1 = sin ficha, -2 = ficha enemiga, else = ficha id;
                        ficha = J.getFicha(Fen,xAct,yAct,colorJug);

                        if (ficha != -1) {
                            if (ficha == -2) {
                                System.out.println("I smell some \"Black\" magics in here, No hagas trampas!");
                                break;
                            }

                            // -1 = movimiento ilegal, 0 = movimiento normal, 1 = movimiento peon con promo
                            int mov = J.tryToMoveFicha(Fen, ficha, xFi, yFi, colorJug);

                            Pair<String, Double> Jugada;
                            if (mov == -1) {
                                System.out.println("Movimiento Ilegal");
                                break;
                            }
                            else if (mov == 0) {
                                Jugada = J.moverFicha(Fen, ficha, xFi, yFi, "false");
                            }
                            else {
                                boolean promo = false;
                                String choice = null;
                                while(!promo){
                                    Scanner sc = new Scanner(System.in);
                                    System.out.println("Introduce Pieza [torre/alfil/cavall/reina]");
                                    choice = sc.nextLine();
                                    if (choice.equals("torre") ||choice.equals("cavall") ||choice.equals("alfil") ||choice.equals("reina")){
                                        promo = true;
                                        if (choice.equals("cavall"))choice = "caball";
                                    }
                                }
                                Jugada = J.moverFicha(Fen, ficha, xFi, yFi, choice);
                            }

                            if (!Turno) Puntos += Jugada.getValue();

                            Fen = Jugada.getKey();
                            matchar = J.getMatrix(Fen);
                            pintarMatchar(matchar);
                            System.out.println("");

                            if(Jugada.getValue() == 300000) mate = true;
                            moved = true;
                            Turno = !Turno;
                            --endgame;
                        }
                    }
                }
            }

            else if (human){ // Human vs IA
                boolean Turno = false; // False Humano, True IA
                int aux;
                if (Color2.equals("blanca")) aux = 0;
                else aux = 1;

                while (!mate && endgame > 0){


                    if (!Turno) { // False --> Juega Humano (1r Jugador)

                        System.out.println("\n------- Turno: Jugador 1 -------\n");
                        colorJug = Color1;

                        Scanner uno = new Scanner(System.in);
                        String Act;
                        String Final;
                        Pair<Integer, Integer> a;
                        int xAct, yAct, xFi, yFi;
                        xAct = yAct = xFi = yFi = -1;
                        boolean moved = false;

                        while (!moved) {
                            while (xAct == -1) {
                                System.out.println("Introduce [Posicion_Actual]\n");
                                Act = uno.nextLine();
                                a = Tradueix(Act);
                                if (a != null) {
                                    xAct = a.getKey();
                                    yAct = a.getValue();
                                }
                            }
                            while (xFi == -1) {
                                System.out.println("\nIntroduce [PosiciónFinal]\n");
                                Final = uno.nextLine();
                                a = Tradueix(Final);
                                if (a != null) {
                                    xFi = a.getKey();
                                    yFi = a.getValue();
                                }
                            }

                            int ficha;
                            // -1 = sin ficha, -2 = ficha enemiga, else = ficha id;
                            ficha = J.getFicha(Fen, xAct, yAct, colorJug);

                            if (ficha != -1) {
                                if (ficha == -2) {
                                    System.out.println("I smell some \"Black\" magics in here, No hagas trampas!");
                                    break;
                                }

                                // -1 = movimiento ilegal, 0 = movimiento normal, 1 = movimiento peon con promo
                                int mov = J.tryToMoveFicha(Fen, ficha, xFi, yFi, colorJug);

                                Pair<String, Double> Jugada;
                                if (mov == -1) {
                                    System.out.println("Movimiento Ilegal");
                                    break;
                                } else if (mov == 0) {
                                    Jugada = J.moverFicha(Fen, ficha, xFi, yFi, "false");
                                } else {
                                    boolean promo = false;
                                    String choice = null;
                                    while (!promo) {
                                        Scanner sc = new Scanner(System.in);
                                        System.out.println("Introduce Pieza [torre/alfil/cavall/reina]");
                                        choice = sc.nextLine();
                                        if (choice.equals("torre") || choice.equals("cavall") || choice.equals("alfil") || choice.equals("reina")) {
                                            promo = true;
                                            if (choice.equals("cavall")) choice = "caball";
                                        }
                                    }
                                    Jugada = J.moverFicha(Fen, ficha, xFi, yFi, choice);
                                }

                                Puntos += Jugada.getValue();

                                Fen = Jugada.getKey();
                                matchar = J.getMatrix(Fen);
                                pintarMatchar(matchar);
                                System.out.println("");

                                if (Jugada.getValue() == 300000) mate = true;
                                moved = true;
                                Turno = !Turno;
                                --endgame;
                            }
                        }
                    }

                    else { // Juga IA
                        System.out.println("\n------- Turno: Jugador 2 -------\n");
                        int prof = Math.min(endgame,4);
                        Pair<Pair<Boolean,String>, Pair<Pair<Integer, Integer>, Pair<Integer,Integer>>> Jugada;
                        // ((mate, FEN), ((xIni,yIni),(xFi,yFi))
                        Jugada = J.minimax(Fen,prof,aux);
                        //Nodo Play = Mini.algoritmo_minimax(t,prof,Mini.getQuien_soy());


                        Tradueix2(Jugada.getValue().getKey().getKey(), Jugada.getValue().getKey().getValue(), Jugada.getValue().getValue().getKey(), Jugada.getValue().getValue().getValue());
                        System.out.println("");

                        //t.mover(Play.get_ficha(),Play.get_posx(),Play.get_posy());
                        Fen = Jugada.getKey().getValue();
                        matchar = J.getMatrix(Fen);
                        pintarMatchar(matchar);
                        System.out.println("");
                        System.out.println("");
                        mate = Jugada.getKey().getKey();
                        Turno = !Turno;
                        --endgame;
                    }
                }
            }

            else{// IA vs Human
                boolean Turno = false; // False IA, True Humano
                int aux;
                if (Color1.equals("blanca")) aux = 0;
                else aux = 1;

                while (!mate && endgame > 0){


                    if (Turno) { // False --> Juega Humano (1r Jugador)

                        System.out.println("\n------- Turno: Jugador 2 -------\n");
                        colorJug = Color1;

                        Scanner uno = new Scanner(System.in);
                        String Act;
                        String Final;
                        Pair<Integer, Integer> a;
                        int xAct, yAct, xFi, yFi;
                        xAct = yAct = xFi = yFi = -1;
                        boolean moved = false;

                        while (!moved) {
                            while (xAct == -1) {
                                System.out.println("Introduce [Posicion_Actual]\n");
                                Act = uno.nextLine();
                                a = Tradueix(Act);
                                if (a != null) {
                                    xAct = a.getKey();
                                    yAct = a.getValue();
                                }
                            }
                            while (xFi == -1) {
                                System.out.println("\nIntroduce [PosiciónFinal]\n");
                                Final = uno.nextLine();
                                a = Tradueix(Final);
                                if (a != null) {
                                    xFi = a.getKey();
                                    yFi = a.getValue();
                                }
                            }

                            int ficha;
                            // -1 = sin ficha, -2 = ficha enemiga, else = ficha id;
                            ficha = J.getFicha(Fen, xAct, yAct, colorJug);

                            if (ficha != -1) {
                                if (ficha == -2) {
                                    System.out.println("I smell some \"Black\" magics in here, No hagas trampas!");
                                    break;
                                }

                                // -1 = movimiento ilegal, 0 = movimiento normal, 1 = movimiento peon con promo
                                int mov = J.tryToMoveFicha(Fen, ficha, xFi, yFi, colorJug);

                                Pair<String, Double> Jugada;
                                if (mov == -1) {
                                    System.out.println("Movimiento Ilegal");
                                    break;
                                } else if (mov == 0) {
                                    Jugada = J.moverFicha(Fen, ficha, xFi, yFi, "false");
                                } else {
                                    boolean promo = false;
                                    String choice = null;
                                    while (!promo) {
                                        Scanner sc = new Scanner(System.in);
                                        System.out.println("Introduce Pieza [torre/alfil/cavall/reina]");
                                        choice = sc.nextLine();
                                        if (choice.equals("torre") || choice.equals("cavall") || choice.equals("alfil") || choice.equals("reina")) {
                                            promo = true;
                                            if (choice.equals("cavall")) choice = "caball";
                                        }
                                    }
                                    Jugada = J.moverFicha(Fen, ficha, xFi, yFi, choice);
                                }

                                Puntos += Jugada.getValue();

                                Fen = Jugada.getKey();
                                matchar = J.getMatrix(Fen);
                                pintarMatchar(matchar);
                                System.out.println("");

                                if (Jugada.getValue() == 300000) mate = true;
                                moved = true;
                                Turno = !Turno;
                                --endgame;
                            }
                        }
                    }

                    else { // Juga IA
                        System.out.println("\n------- Turno: Jugador 1 -------\n");
                        int prof = Math.min(endgame,4);
                        Pair<Pair<Boolean,String>, Pair<Pair<Integer, Integer>, Pair<Integer,Integer>>> Jugada;
                        // ((mate, FEN), ((xIni,yIni),(xFi,yFi))
                        Jugada = J.minimax(Fen,prof,aux);
                        //Nodo Play = Mini.algoritmo_minimax(t,prof,Mini.getQuien_soy());


                        Tradueix2(Jugada.getValue().getKey().getKey(), Jugada.getValue().getKey().getValue(), Jugada.getValue().getValue().getKey(), Jugada.getValue().getValue().getValue());
                        System.out.println("");

                        //t.mover(Play.get_ficha(),Play.get_posx(),Play.get_posy());
                        Fen = Jugada.getKey().getValue();
                        matchar = J.getMatrix(Fen);
                        pintarMatchar(matchar);
                        System.out.println("");
                        System.out.println("");
                        mate = Jugada.getKey().getKey();
                        Turno = !Turno;
                        --endgame;
                    }
                }
            }

        }

        else { //SOLO IAs
            int res;
            mate = false;
            boolean Turno = true; // False IA_2, True IA_1
            //Minimax Mini = new Minimax();
            int aux, aux2;
            if (Color1.equals("blanca")) aux = 0;
            else aux = 1;
            aux2 = (aux+1)%2;
            //Mini.set_quien_soy(aux);
            while (!mate && endgame > 0) {

                int prof = Math.min(endgame, 4);
                Pair<Pair<Boolean,String>, Pair<Pair<Integer, Integer>, Pair<Integer,Integer>>> Jugada;

                if (Turno) {
                    System.out.println("\n------- Turno: Jugador 1 -------\n");
                    Jugada = J.minimax(Fen,prof,aux);
                }
                else {
                    System.out.println("\n------- Turno: Jugador 2 -------\n");
                    Jugada = J.minimax(Fen,prof,aux2);
                }

                Tradueix2(Jugada.getValue().getKey().getKey(), Jugada.getValue().getKey().getValue(), Jugada.getValue().getValue().getKey(), Jugada.getValue().getValue().getValue());
                System.out.println("");

                Fen = Jugada.getKey().getValue();
                matchar = J.getMatrix(Fen);
                pintarMatchar(matchar);
                System.out.println("");
                System.out.println("");
                mate = Jugada.getKey().getKey();
                Turno = !Turno;
                --endgame;
            }
        }

        if (endgame == 0 && !mate) {
            return new Pair<Integer, Double> (0, Puntos);
        }
        else if (mate) {
            return new Pair<Integer, Double>(1, Puntos);
        }
        return null;
    }
}
