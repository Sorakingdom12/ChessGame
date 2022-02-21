package Dominio;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

public class Joc {

    private ArrayList<Player> Players;
    private ArrayList<ArrayList<Problema>> Problemes;


    private ArrayList<Ranking> Rankings = new ArrayList<Ranking>();
    private Player P_act;
    // Creadora
    public Joc(){
        Players = new ArrayList<Player>();
        Problemes = new ArrayList<ArrayList<Problema>>();
        Rankings = new ArrayList<Ranking>();
    }

    public Ranking getRankings( int indice) {
        return Rankings.get(indice);
    }

    public void MainStartup(){
        Players.add(new Player("","Dd121212",0));
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
        //new Problema(this, "k6r/1bpNqpp1/1p1b3p/8/2P1n1B1/8/1PQ2PPP/rN2R1K1 w - - 0 1", 3);
    }

    public int getCodeProbNew(int p){
         return Problemes.get(p).size();
    }

    public int getPublicsSize(){return Problemes.get(0).size();}

    public Player getPlayer(int i){

        return Players.get(i);
    }

    public int getPlayerSize(){

        return Players.size();
    }

    public ArrayList<Problema> GetProblemes(int i){

        if (i == 1) return Problemes.get(P_act.getCode_User());
        else return Problemes.get(0);
    }

    public int getCodi(Player p){
       return Players.indexOf(p);
    }

    public ArrayList<Integer> getProblemes(){
        ArrayList<Integer> problems = new ArrayList<Integer>();
        for (Problema p : Problemes.get(P_act.getCode_User())){
            problems.add(p.getCodi());
        }
        return problems;
    }

    public void Borra(int choice){
        Problemes.get(P_act.getCode_User()).remove(choice);
        for (int i = choice; i<Problemes.get(P_act.getCode_User()).size(); ++i){
            Problemes.get(P_act.getCode_User()).get(i).minusCodi();
        }
    }

    public int FenCorrecto(String Fen) {
        Taulell t = new Taulell(Fen);
        return t.fen_ok();
    }

    public boolean comprovaDuplo(String Fen){
        for(int i = 0; i < Problemes.get(P_act.getCode_User()).size(); ++i) {
            if(Problemes.get(P_act.getCode_User()).get(i).getFen().equals(Fen))return true;
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

    public void afegirProb(Player p, Problema Prob){
        int i = Players.indexOf(p);
        if (Problemes.size() == i){
            ArrayList<Problema> aux = new ArrayList<Problema>();
            aux.add(Prob);
            Problemes.add(aux);
        }
        else {
            Problemes.get(i).add(Prob);
        }
    }

    public boolean Crear_Problema(String Fen, String tema) {
        Problema prob = new Problema(this, P_act, Fen, tema);
        if (prob.getCodi() != -1){
            afegirProb(P_act, prob);
            return true;
        }
        return false;
    }
/*
    public void initprivats(){
         Problema ini = new Problema(-1);
         ArrayList<Problema> aux = new ArrayList<Problema>();
         aux.add(ini);
         Problemes.add(aux);
    }
*/
    public void Modificar(int pos,String Fen,String tema){
        Problema prim = Problemes.get(P_act.getCode_User()).get(pos);
        Problema mod = new Problema(this,P_act,Fen,tema);
        mod.setCodi(prim.getCodi());
        Problemes.get(P_act.getCode_User()).set(pos, mod);
    }

    public void afegeixPub(Problema p){
        if (Problemes.size() == 0){
            Problemes.add(new ArrayList<Problema>());
            Problemes.get(0).add(p);
        }
        else{
            Problemes.get(0).add(p);
        }
        Rankings.add(p.getCodi(),new Ranking(p.getCodi()));
    }

    public void setPlayer(Player nuevo){
         Players.add(nuevo);
         Problemes.add(new ArrayList<>());
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

    public void Registra(String name, String pass){
        Player P = new Player(name, pass, getPlayerSize());
        setPlayer(P);
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
            if (!mayus && pass.charAt(i)>='A' && pass.charAt(i)<='Z') mayus = true;
            else if (!minus && pass.charAt(i)>='a' && pass.charAt(i)<='z') minus = true;
            else if (!number && pass.charAt(i)>='0' && pass.charAt(i)<='9') number = true;
        }

        //System.out.println("mayus : "+ mayus + "\nminus : "+ minus + "\nnumber : "+ number);
        if (mayus && minus && number && size) return 0; // 0 si está bien
        else {
            if (!minus)return 1;
            if (!mayus)return 2;
            if (!number)return 3;
            if (!size)return 4;
            return -1;
        }
    }

    public void setP_act(Player p){
        P_act = p;
    }

    public void LogIn(String nom){
        // set P_act con el jugador loggeado con nombre nom
        for(Player P: Players){
            if (P.getUsername().equals(nom)) setP_act(P);
        }
    }

    public char[][] getMatrix(String tipo, int codi){
        Problema p = null;
        if (tipo.equals("publicos")) p = Problemes.get(0).get(codi);
        else p = Problemes.get(P_act.getCode_User()).get(codi);
        String Fen = p.getFen();
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        return t.mat_char();
    }

    public ArrayList<Integer> EligeProblema(int tema, String dif, String Color, String tipo){
        ArrayList<Integer> Codis = new ArrayList<Integer>();
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
                        Codis.add(code_p);
                    }
                }
            }
        }
        else if (tipo.contentEquals("privados")) {
            for (int i = 0; i < Problemes.get(pos).size(); ++i) {

                if (Problemes.get(pos).get(i).getTema() == tema && Problemes.get(pos).get(i).getDificulty().contentEquals(dif)) {

                    Problema prob = Problemes.get(0).get(i);
                    String Fen = prob.getFen();
                    int mida = Fen.length();
                    String col = Fen.substring(mida-9,mida-8);
                    if (col.equals("w") && Color.equals("blanco") || col.equals("b") && Color.equals("negro")) {

                        int code_p = Problemes.get(pos).get(i).getCodi();
                        Codis.add(code_p);
                    }
                }
            }
        }
        return Codis;
    }

    public Pair<ArrayList<Pair<Integer, Double>>, Integer> hacer_ranking(int codi, double Puntos) {
        Rankings.get(codi).Update_Scoreboard(P_act.getCode_User(),Puntos);
        return Rankings.get(codi).check_Score(P_act.getCode_User());
    }

    public char[][] getMatrix(String Fen) {
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        return t.mat_char();
    }

    public String getFen(String tipo, int codi){
        Problema p = null;
        if (tipo.equals("publicos")) p = Problemes.get(0).get(codi);
        else p = Problemes.get(P_act.getCode_User()).get(codi);
        return p.getFen();
    }

    public int getTema(String tipo, int codi){
        Problema p = null;
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

    public Pair<Pair<Boolean,String>, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>> minimax(String Fen, int prof, int aux) {
        Minimax Mini = new Minimax();
        Mini.set_quien_soy(aux);
        Taulell t = new Taulell(Fen);
        t.fen_to_mat();
        Nodo Play = Mini.algoritmo_minimax(t, prof, aux);
        Pair<Pair<Integer, Integer>, Pair<Integer,Integer>> Jugada = new Pair (new Pair (Play.get_ficha().get_pos_x(), Play.get_ficha().get_pos_y()), new Pair (Play.get_posx(), Play.get_posy()));

        t.mover(Play.get_ficha(),Play.get_posx(),Play.get_posy());
        if (!Play.getPromotion().equals("false")) {
            t.promover_peon(Play.get_ficha(),Play.getPromotion());
        }
        boolean mate;
        if (aux == 0) mate = t.mate("negro");
        else mate = t.mate("blanco");

        return new Pair (new Pair(mate,t.mat_to_fen()), Jugada);
    }

}
