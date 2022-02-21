package Dominio;

import Persistencia.ControladorDatos;
import javafx.util.Pair;

import java.util.ArrayList;

public class Joc {

    private ArrayList<Player> Players = new ArrayList<Player>();
    private ArrayList<ArrayList<Problema>> Problemes = new ArrayList<ArrayList<Problema>>();
    private ArrayList<Ranking> Rankings = new ArrayList<Ranking>();
    private Player P_act;
    private ControladorDatos datos;

    // Creadora
    public Joc(){

        datos = new ControladorDatos();
        //MainStartup();
        Players = Jug_DatosToObj();
        Problemes = Prob_DatosToObj();
        Rankings = Rank_DatosToObj();
    }

    void traduccion() {
        ArrayList<ArrayList<String>> infoProb = new ArrayList<>();
        for (int i = 0; i < Problemes.size(); i++) {
            for (Problema p: Problemes.get(i)) {
                ArrayList<String> problema = new ArrayList<>();
                problema.add(String.valueOf(i));
                problema.add(String.valueOf(p.getCodi()));
                problema.add(String.valueOf(p.getTema()));
                problema.add(String.valueOf(p.getDepth()));
                problema.add(String.valueOf(p.getWidth()));
                problema.add(p.getStarter());
                problema.add(p.getDificulty());
                problema.add(p.getFen());
                infoProb.add(problema);
            }
        }
        datos.guardaProblemas(infoProb);
    }

    ArrayList<Ranking> Rank_DatosToObj () {
        ArrayList<Ranking> rankings = new ArrayList<>();
        for(int i = 0; i < getPublicsSize(); i++) {
            rankings.add(new Ranking(i));
        }
        ArrayList<ArrayList<String>> dat = datos.getRankings();
        for (ArrayList<String> d: dat) {
            int codi = Integer.parseInt(d.get(0));
            int player = Integer.parseInt(d.get(1));
            double score = Double.parseDouble(d.get(2));
            rankings.get(codi).Update_Scoreboard(player, score);
        }
        return rankings;
    }

    ArrayList<Player> Jug_DatosToObj () {
        ArrayList<ArrayList<String>> jugadores = datos.getJugadores();
        ArrayList<Player> pl = new ArrayList<Player>();
        for (ArrayList<String> jugador: jugadores) {
            Player p;
            int codi = Integer.parseInt(jugador.get(0));
            String user = jugador.get(1);
            String pass = jugador.get(2);
            p = new Player(user, pass, codi);
            pl.add(p);
        }
        return pl;
    }

    // Recopilar información del estado actual de la partida, para guardarlo en la base de datos
    //El codi_user es el codi_user de p_act
    //El resto de parámetros que necesitamos se pasan por ArrayList<String> partida
    public void guardar_salir(ArrayList<String> partida) {
        ArrayList<String> infoPart = new ArrayList<>();
        infoPart.add(String.valueOf(P_act.getCode_User()));
        for(String s: partida) {
            infoPart.add(s);
        }
        datos.guardarPartida(infoPart);
    }

    //Devuelve todos los parámetros de la partida identificada por los parámetros que hay en infoPart
    public ArrayList<String> getPartida(ArrayList<String> infoPart) {
        return datos.getPartida(infoPart);
    }

    private ArrayList<ArrayList<Problema>> Prob_DatosToObj () {
        ArrayList<ArrayList<String>> dat = datos.getProblemas();
        ArrayList<ArrayList<Problema>> problemas = new ArrayList<>();
        for (int i = 0; i < Players.size(); i++) {
            problemas.add(new ArrayList<>());
        }
        for (ArrayList<String> prob: dat) {
            Problema p;
            int codi_p = Integer.parseInt(prob.get(1));
            int codi_u = Integer.parseInt(prob.get(0));
            int tema = Integer.parseInt(prob.get(2));
            int depth = Integer.parseInt(prob.get(3));
            int width = Integer.parseInt(prob.get(4));
            String starter = prob.get(5);
            String dif = prob.get(6);
            String fen = prob.get(7);
            fen += " ";
            fen += prob.get(8);
            fen += " ";
            fen += prob.get(9);
            fen += " ";
            fen += prob.get(10);
            fen += " ";
            fen += prob.get(11);
            fen += " ";
            fen += prob.get(12);

            p = new Problema(codi_p, codi_u, tema, depth, width, starter, dif, fen);
            /*if (codi_u == problemas.size()) {
                problemas.add(new ArrayList<>());
            }*/
            problemas.get(codi_u).add(p);
        }

        return problemas;
    }

    // Borra Problema
    public void Borra(int choice){
        Problemes.get(P_act.getCode_User()).remove(choice);
        for (int i = choice; i<Problemes.get(P_act.getCode_User()).size(); ++i){
            Problemes.get(P_act.getCode_User()).get(i).minusCodi();
        }
        traduccion();
    }

    //getters
    ArrayList<Problema> GetProblemes(int aux){
        if (aux == 1) return Problemes.get(P_act.getCode_User());
        return Problemes.get(0);
    }

    //Comparte problema
    public boolean compartirProb(int choice) {
        Problema elegido = Problemes.get(P_act.getCode_User()).get(choice);
        String fen = elegido.getFen();
        boolean duplicado = comprovaDuploPublicos(fen);
        if (!duplicado) {
            new Problema(this, fen, elegido.getTema());
        }
        return duplicado;
    }

    public Ranking getRankings( int indice) {
        return Rankings.get(indice);
    }

    int getCodeProbNew(int p){
         return Problemes.get(p).size();
    }

    public int getPublicsSize(){return Problemes.get(0).size();}

    Player getPlayer(int i){ return Players.get(i);}

    int getPlayerSize(){ return Players.size();}

    int getCodi(Player p){
       return Players.indexOf(p);
    }

    public ArrayList<Integer> getProblemes(){
        ArrayList<Integer> problems = new ArrayList<Integer>();
        for (Problema p : Problemes.get(P_act.getCode_User())){
            problems.add(p.getCodi());
        }
        return problems;
    }

    public ArrayList<Pair<Integer,String>> EligeProblema(int tema, String dif, String Color, String tipo){
        ArrayList<Pair<Integer,String>> Codis = new ArrayList<Pair<Integer,String>>();
        int pos = Players.indexOf(P_act);
        if (tipo.contentEquals("publicos")) {
            for (int i = 0; i < Problemes.get(0).size(); ++i) {
                if (Problemes.get(0).get(i).getTema() == tema && Problemes.get(0).get(i).getDificulty().contentEquals(dif) ) {
                    Problema prob = Problemes.get(0).get(i);
                    String Fen = prob.getFen();
                    int mida = Fen.length();
                    String col = Fen.substring(mida-9,mida-8);
                    if (col.equals("w") && Color.equals("blanco") || col.equals("b") && Color.equals("negro")) {
                        int code_p = Problemes.get(0).get(i).getCodi();
                        String fen = Problemes.get(0).get(i).getFen();
                        Codis.add(new Pair<>(code_p,fen));
                    }
                }
            }
        }
        else if (tipo.contentEquals("privados")) {

            for (int i = 0; i < Problemes.get(pos).size(); ++i) {

                if (Problemes.get(pos).get(i).getTema() == tema && Problemes.get(pos).get(i).getDificulty().contentEquals(dif)) {

                    Problema prob = Problemes.get(pos).get(i);
                    String Fen = prob.getFen();
                    int mida = Fen.length();
                    String col = Fen.substring(mida-9,mida-8);
                    if (col.equals("w") && Color.equals("blanco") || col.equals("b") && Color.equals("negro")) {

                        int code_p = Problemes.get(pos).get(i).getCodi();
                        String fen = Problemes.get(pos).get(i).getFen();
                        Codis.add(new Pair<>(code_p,fen));
                    }
                }
            }
        }
        return Codis;
    }

    char[][] getMatrix(String tipo, int codi){
        Problema p;
        if (tipo.equals("publicos")) p = Problemes.get(0).get(codi);
        else p = Problemes.get(P_act.getCode_User()).get(codi);
        String Fen = p.getFen();
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        return t.mat_char();
    }

    char[][] getMatrix(String Fen) {
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        return t.mat_char();
    }

    public String getFen(String tipo, int codi){
        Problema p;
        if (tipo.equals("publicos")) p = Problemes.get(0).get(codi);
        else p = Problemes.get(P_act.getCode_User()).get(codi);
        return p.getFen();
    }

    public int getTema(String tipo, int codi){
        Problema p;
        if (tipo.equals("publicos")) p = Problemes.get(0).get(codi);
        else p = Problemes.get(P_act.getCode_User()).get(codi);
        return p.getTema();
    }

    public int getFicha(String Fen, int xAct, int yAct, String colorJug) {
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        int id = t.get_id_ficha(xAct,yAct);
        if (id != -1) {
            if (t.get_ficha(id).get_color().equals(colorJug)) return id;
            return -2;
        }
        return id;//F = t.get_ficha(id);
    }

    // setters

    void MainStartup(){
        setPlayer(new Player("","Dd121212",0));
        // MATE EN 1
        new Problema(this,"5rkr/pp3p2/2p3p1/6R1/1b2N1Q1/3P4/qPP4P/2K5 b - - 0 1",1);
        new Problema(this,"4rk2/8/6b1/8/1p6/8/1PPK1p2/q7 b - - 0 1",1);
        new Problema(this,"R7/2pk4/1pN3p1/1Pn3P1/2Q3bP/5q2/1P2r3/2K5 w - - 0 1",1);
        // MATE EN 2
        new Problema(this,"8/8/r7/kbK5/7R/1P6/8/8 w - - 0 1",2);
        new Problema(this,"5rkr/pp3p2/2p3p1/6R1/1b2N1Q1/3P4/qPP4P/2K5 w - - 0 1",2);
        new Problema(this,"r4br1/3b1kpp/1q1P4/1pp1RP1N/p7/6Q1/PPB3PP/2KR4 w - - 0 1",2);
        new Problema(this,"6k1/ppp3pp/2n1p3/8/3Pq3/PN4Q1/1P3rRP/5rRK b - - 0 1",2);
        // MATE EN 3
        new Problema(this, "r1b3kr/ppp1Bp1p/1b6/n2P4/2p3q1/2Q2N2/P4PPP/RN2R1K1 w - - 0 1", 3);
        new Problema(this, "r2n1rk1/1ppb2pp/1p1p4/3Ppq1n/2B3P1/2P4P/PP1N1P1K/R2Q1RN1 b - - 0 1", 3);
        new Problema(this, "3q1r1k/2p4p/1p1pBrp1/p2Pp3/2PnP3/5PP1/PP1Q2K1/5R1R w - - 0 1", 3);
        new Problema(this, "6k1/ppp2ppp/8/2n2K1P/2P2P1P/2Bpr3/PP4r1/4RR2 b - - 0 1", 3);
        new Problema(this, "rn3rk1/p5pp/2p5/3Ppb2/2q5/1Q6/PPPB2PP/R3K1NR b - - 0 1", 3);
        new Problema(this, "N1bk4/pp1p1Qpp/8/2b5/3n3q/8/PPP2RPP/RNB1rBK1 b - - 0 1", 3);
        new Problema(this, "8/2p3N1/6p1/5PB1/pp2Rn2/7k/P1p2K1P/3r4 w - - 0 1", 3);
        new Problema(this, "r1b1k1nr/p2p1ppp/n2B4/1p1NPN1P/6P1/3P1Q2/P1P1K3/q5b1 w - - 0 1", 3);
        new Problema(this, "1q2r3/k4p2/prQ2b1p/R7/1PP1B1p1/6P1/P5K1/8 w - - 0 1", 3);
        new Problema(this, "r1bqr1k1/ppp2pp1/3p4/4n1NQ/2B1PN2/8/P4PPP/b4RK1 w - - 0 1", 3);
        new Problema(this, "3r4/pp5Q/B7/k7/3q4/2b5/P4PPP/1R4K1 w - - 0 1", 3);
        new Problema(this, "rnbk1b1r/ppqpnQ1p/4p1p1/2p1N1B1/4N3/8/PPP2PPP/R3KB1R w - - 0 1", 3);
        new Problema(this, "3rnr1k/p1q1b1pB/1pb1p2p/2p1P3/2P2N2/PP4P1/1BQ4P/4RRK1 w - - 0 1", 3);
        new Problema(this, "8/Qp4pk/2p3b1/5p1p/3B3P/1P4P1/P1P1rnBK/3r4 b - - 0 1", 3);
        new Problema(this, "k7/1p1rr1pp/pR1p1p2/Q1pq4/P7/8/2P3PP/1R4K1 w - - 0 1", 3);
        new Problema(this, "3r1rk1/p1p4p/8/1PP1p1bq/2P5/3N1Pp1/PB2Q3/1R3RK1 b - - 0 1", 3);
        new Problema(this, "Q4R2/3kr3/1q3n1p/2p1p1p1/1p1bP1P1/1B1P3P/2PBK3/8 w - - 0 1", 3);
    }

    public void setPlayer(Player nuevo) {
        Players.add(nuevo);
        Problemes.add(new ArrayList<>());
        ArrayList<String> infoJug = new ArrayList<>();
        infoJug.add (String.valueOf(nuevo.getCode_User()));
        infoJug.add (nuevo.getUsername());
        infoJug.add (nuevo.getPassword());
        datos.guardaJugador(infoJug);
    }

    void setP_act(Player p){
        P_act = p;
    }

    void afegirProb(Player p, Problema Prob){
        int i = Players.indexOf(p);
        if (Problemes.size() == i){
            ArrayList<Problema> aux = new ArrayList<Problema>();
            aux.add(Prob);
            Problemes.add(aux);
        }
        else {
            Problemes.get(i).add(Prob);
        }
        traduccion();
    }

    void afegeixPub(Problema p){
        if (Problemes.size() == 0){
            Problemes.add(new ArrayList<Problema>());
            Problemes.get(0).add(p);
        }
        else{
            Problemes.get(0).add(p);
        }
        p.setCodi(getPublicsSize()-1);
        traduccion();
        Rankings.add(p.getCodi(),new Ranking(p.getCodi()));
    }

    public void Registra(String name, String pass){
        Player P = new Player(name, pass, getPlayerSize());
        setPlayer(P);
    }

    public int Crear_Problema(String Fen, String tema) {
        Problema prob = new Problema(this, P_act, Fen, tema);
        for (Problema p:Problemes.get(P_act.getCode_User())) {
            if (p.getFen().equals(Fen)) return 16;
        }
        if (prob.getCodi() != -1){
            afegirProb(P_act, prob);
            return 0;
        }
        return prob.getError();

    }

    public Pair<ArrayList<Pair<Integer, Double>>, Integer> hacer_ranking(int codi, double Puntos) {

        Rankings.get(codi).Update_Scoreboard(P_act.getCode_User(),Puntos);
        ArrayList<ArrayList<String>> infoRank = new ArrayList<>();
        ArrayList<Pair<Integer, Double>> scoreboard = Rankings.get(codi).getScoreboard();
        for (Pair<Integer,Double> pos: scoreboard) {
            ArrayList<String> rank = new ArrayList<>();
            rank.add(String.valueOf(codi));
            rank.add(String.valueOf(pos.getKey()));
            rank.add(String.valueOf(pos.getValue()));
            infoRank.add(rank);
        }
        datos.guardarRankings(infoRank);
        return Rankings.get(codi).check_Score(P_act.getCode_User());
    }

    public int Modificar(int pos,String Fen,String tema){
        Problema prim = Problemes.get(P_act.getCode_User()).get(pos);
        Problema mod = new Problema(this,P_act,Fen,tema);
        int res = mod.getError();
        mod.setCodi(prim.getCodi());
        Problemes.get(P_act.getCode_User()).set(pos, mod);
        traduccion();
        return res;
    }



    public void LogIn(String nom){
        // set P_act con el jugador loggeado con nombre nom
        for(Player P: Players){
            if (P.getUsername().equals(nom)) setP_act(P);
        }
    }

    public int tryToMoveFicha(String Fen, int id, int xFi, int yFi, String colorJug) {
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        Ficha F = t.get_ficha(id);
        int xAct = F.get_pos_x();
        int yAct = F.get_pos_y();
        ArrayList<Pair<Integer, Integer>> movs = F.todos_movimientos(t);
        boolean found = false;
        boolean right = false;

        for (Pair<Integer, Integer> move : movs) {
            if (move.getKey() == xFi && move.getValue() == yFi) {
                found = true;
            }
            if (found) {
                Ficha Dead = t.mover(F, xFi, yFi);
                right = !t.jaque(colorJug);
                t.mover(F, xAct, yAct);
                t.resucitar(Dead);
                if (!right) {
                    return -1;
                }
                break;
            }
        }
        if (right) {
            if (F.get_nom().equals("peo") && ((F.get_color().equals("blanca") && xFi== 0) || (F.get_color().equals("negra") && xFi == 7) )) return 1;
            return 0;
        }
        return -1;
    }

    public ArrayList<Pair<Integer,Integer>> dameMoves(Pair<Integer,Integer> p, String Fen){

        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        int id = t.get_id_ficha(p.getKey(),p.getValue());
        Ficha F = t.get_ficha(id);
        String color = t.get_color_ficha(id);
        ArrayList<Pair<Integer,Integer>> movs = F.todos_movimientos(t);
        boolean right;
        ArrayList<Pair<Integer,Integer>> Result = new ArrayList<>();
        for (Pair<Integer, Integer> mov : movs) {
            Ficha Dead = t.mover(F, mov.getKey(), mov.getValue());
            right = !t.jaque(color);
            t.mover(F, p.getKey(), p.getValue());
            t.resucitar(Dead);
            if (right) {
                Result.add(mov);
            }
        }
        return Result;
    }

    public int checkJaque(String Fen, String color) {
        Fen += " w - - 0 1";
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();

        if (t.jaque(color)) {
            if (t.mate(color)) return 2;
            return 1;
        }
        return 0;
    }

    public Pair<String,Double> moverFicha(String Fen, int id, int xFi, int yFi, String promocion) {
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        Ficha F = t.get_ficha(id);
        Nodo Apunta = new Nodo(F,xFi,yFi,0.0,null, promocion);
        if (promocion.equals("false")) t.puntua(Apunta,true,false);
        else t.puntua(Apunta,true,true);

        t.mover(F, xFi, yFi);
        if (!Apunta.getPromotion().equals("false")) t.promover_peon(F,Apunta.getPromotion());
        String newFen = t.mat_to_fen();

        return new Pair (newFen, Apunta.get_puntuacion());
    }

    public Pair<Pair<Integer,String>, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>> minimax(String Fen, int prof, int aux,String player) {
        Nodo Play;
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        if (player.equals("IA2")) {
            IAMedia Mini = new IAMedia();
            Mini.set_quien_soy(aux);
            Play = Mini.algoritmo_minimax(t, prof, aux);
        }
        else if (player.equals("IA3")) {
            IADificil Mini = new IADificil();
            Mini.set_quien_soy(aux);
            Play = Mini.algoritmo_poda_alfa_beta(t, prof, aux);
        }
        else {
            IAFacil Mini = new IAFacil();
            Mini.set_quien_soy(aux);
            Play = Mini.algoritmo_minimax_tonto(t, prof, aux);
        }
        Pair<Pair<Integer, Integer>, Pair<Integer,Integer>> Jugada = new Pair (new Pair (Play.get_ficha().get_pos_x(), Play.get_ficha().get_pos_y()), new Pair (Play.get_posx(), Play.get_posy()));

        t.mover(Play.get_ficha(),Play.get_posx(),Play.get_posy());
        if (!Play.getPromotion().equals("false")) {
            t.promover_peon(Play.get_ficha(),Play.getPromotion());
        }
        String feni = t.mat_to_fen();
        int mate;
        if (aux == 0) mate = checkJaque(feni,"negro");
        else mate = checkJaque(feni,"blanco");

        return new Pair (new Pair(mate,feni), Jugada);
    }

    public int FenCorrecto(String Fen) {
        Taulell t = new Taulell(Fen);
        return t.fen_ok();
    }

    public boolean comprovaDuplo(String Fen){
        if (!Problemes.get(P_act.getCode_User()).isEmpty()){
            for(int i = 0; i < Problemes.get(P_act.getCode_User()).size(); ++i) {
                if(Problemes.get(P_act.getCode_User()).get(i).getFen().equals(Fen))return true;
            }
        }
        return false;
    }

    private boolean comprovaDuploPublicos(String Fen) {
        for(int i = 0; i < Problemes.get(0).size(); i++) {
            if (Problemes.get(0).get(i).getFen().equals(Fen)) return true;
        }
        return false;
    }

    public boolean temafine(String s){
        if (s.length() == 0) return false;
        for(int k = 0; k < s.length(); ++k){
            if (s.charAt(k)!= '0' &&
                    s.charAt(k)!= '1' &&
                    s.charAt(k)!= '2' &&
                    s.charAt(k)!= '3' &&
                    s.charAt(k)!= '4' &&
                    s.charAt(k)!= '5' &&
                    s.charAt(k)!= '6' &&
                    s.charAt(k)!= '7' &&
                    s.charAt(k)!= '8' &&
                    s.charAt(k)!= '9') return false;
        }
        return true;
    }

    public boolean checkID(String user, String Pass){

        for (int i = 0; i < Players.size(); ++i) {
            if (user.contentEquals(Players.get(i).getUsername())) {
                if (Pass.contentEquals(Players.get(i).getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean valid_name(String Usn){
        if (Usn.equals(""))return false;
        for(int i = 0; i < Usn.length(); ++i) if (Usn.charAt(i) == ' ') return false;
        boolean found = false;
        int Players_size = getPlayerSize();
        for (int i = 0;!found && i < Players_size; ++i){

            if (getPlayer(i).getUsername().contentEquals(Usn)) found = true;
        }
        return !found;
    }

    public int valid_pass(String pass){

        boolean mayus = false;
        boolean minus = false;
        boolean number = false;
        boolean size = false;
        if (pass.length() <= 11 && pass.length() >= 7) size = true;
        for (int i = 0; i < pass.length(); ++i){
            if (pass.charAt(i) == ' ') return 4;
            if (!mayus && pass.charAt(i)>='A' && pass.charAt(i)<='Z') mayus = true;
            else if (!minus && pass.charAt(i)>='a' && pass.charAt(i)<='z') minus = true;
            else if (!number && pass.charAt(i)>='0' && pass.charAt(i)<='9') number = true;
        }
        if (mayus && minus && number && size) return 0; // 0 si está bien
        else {
            if (!minus)return 1;
            else if (!mayus)return 2;
            else if (!number)return 3;
            else return 4;
        }
    }

    public void actualizaUsn(String name) {
        P_act.setUsername(name);
        ArrayList<String> infoJug = new ArrayList<>();
        infoJug.add(String.valueOf(P_act.getCode_User()));
        infoJug.add(String.valueOf(P_act.getUsername()));
        infoJug.add(String.valueOf(P_act.getPassword()));
        datos.actualizarJugador(infoJug);
    }

    public void actualizaPass(String pass) {
        P_act.setPassword(pass);
        ArrayList<String> infoJug = new ArrayList<>();
        infoJug.add(String.valueOf(P_act.getCode_User()));
        infoJug.add(String.valueOf(P_act.getUsername()));
        infoJug.add(String.valueOf(P_act.getPassword()));
        datos.actualizarJugador(infoJug);
    }
    public ArrayList<ArrayList<String>> getPartidas() {
        return datos.getPartidas(String.valueOf(P_act.getCode_User()));
    }

    public Pair<Integer, ArrayList<ArrayList<String>>> getRanking(int codiR) {
        int codiU = P_act.getCode_User();
        Ranking R = Rankings.get(codiR);
        ArrayList<Pair<Integer,Double>> score = R.check_Score(codiU).getKey();
        ArrayList<ArrayList<String>> scoreBoard = new ArrayList<>();

        for (Pair<Integer,Double> p : score) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(codiR));
            line.add(String.valueOf(p.getKey()));
            line.add(String.valueOf(p.getValue()));
            scoreBoard.add(line);
        }
        return new Pair<> (codiU, scoreBoard);
    }

    public boolean computaIA(String fen, int color, int endgame, String p1, String p2) {
        Taulell t = new Taulell(fen);
        t.fen_to_mat();

        int color2 = (color+1)%2;
        int p_1;
        IAFacil Mini_F1 = new IAFacil();
        IAMedia Mini_M1 = new IAMedia();
        IADificil Mini_D1 = new IADificil();

        int p_2;
        IAFacil Mini_F2 = new IAFacil();
        IAMedia Mini_M2 = new IAMedia();
        IADificil Mini_D2 = new IADificil();

        if (p1.equals("IA2")) {
            Mini_M1.set_quien_soy(color);
            p_1 = 2;
        }
        else if (p1.equals("IA3")) {
            Mini_D1.set_quien_soy(color);
            p_1 = 3;
        }
        else {
            Mini_F1.set_quien_soy(color);
            p_1 = 1;
        }

        if (p2.equals("IA2")) {
            Mini_M2.set_quien_soy(color);
            p_2 = 2;
        }
        else if (p2.equals("IA3")) {
            Mini_D2.set_quien_soy(color);
            p_2 = 3;
        }
        else {
            Mini_F2.set_quien_soy(color);
            p_2 = 1;
        }


        Nodo Play;
        boolean turnop1 = true;
        while (endgame > 0) {
            if (turnop1) {
                switch (p_1) {
                    case 1:
                        Play = Mini_F1.algoritmo_minimax_tonto(t, endgame, color);
                        break;
                    case 2:
                        Play = Mini_M1.algoritmo_minimax(t, endgame, color);
                        break;
                    case 3:
                        Play = Mini_D1.algoritmo_poda_alfa_beta(t, endgame, color);
                        break;
                    default:
                        Play = Mini_F1.algoritmo_minimax_tonto(t, endgame, color);
                }
            }
            else {
                switch (p_2) {
                    case 1:
                        Play = Mini_F2.algoritmo_minimax_tonto(t, endgame, color2);
                        break;
                    case 2:
                        Play = Mini_M2.algoritmo_minimax(t, endgame, color2);
                        break;
                    case 3:
                        Play = Mini_D2.algoritmo_poda_alfa_beta(t, endgame, color2);
                        break;
                    default:
                        Play = Mini_F2.algoritmo_minimax_tonto(t, endgame, color2);
                }
            }
            t.mover(Play.get_ficha(),Play.get_posx(),Play.get_posy());
            if (!Play.getPromotion().equals("false")) {
                t.promover_peon(Play.get_ficha(),Play.getPromotion());
            }
            turnop1 = !turnop1;
            --endgame;
        }
        String col;
        if (color == 0) col = "negra";
        else col = "blanca";
        boolean m = t.mate(col);

        return m;
    }

    public void pochaPartida(String codi, String pub) {
        ArrayList<String> a = new ArrayList<>();
        a.add(String.valueOf(P_act.getCode_User()));
        a.add(codi);
        a.add(pub);
        datos.eliminarPartida(a);
    }
}
