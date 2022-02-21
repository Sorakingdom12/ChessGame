package Presentacion;

import Dominio.Joc;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ControladorPresentacion {
    private int partidas_skynet = 0;
    private static int estado;
    private static int estadoAct;
    private static Account login;
    private static Joc J;
    private static JFrame UI;
    private boolean Pub = false;
    private String playerOne = "";
    private String playerTwo = "";
    private String Dif = "";
    private String Col = "";
    private int tema = 0;
    private Pair<Integer,String> PC;
    private Partida part;
    private Timer time = null;
    private String User = "";
    private String Pass = "";
    private boolean Mod = false;
    private Pair<Integer, String> PMod;
    private String win = "";
    private int secs = 0;
    private int jugada = 0;
    private String historial;
    String PlayWho = "";
    private int endgame = 0;
    private double puntitos = 0.0;

    public ControladorPresentacion(){

        estado = -1;
        estadoAct = -1;
        J = new Joc();

        UI = new JFrame("Ajedrez");
        UI.setVisible(true);
        UI.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension d = new Dimension(720,900);
        UI.setMinimumSize(d);
        changeState(0);
    }

    public void changeState(int i){
        estado = i;
        cambiaestado(i);
    }

    private void cambiaestado(int i) {
        if (estado == 0 && estadoAct != estado) {
            estadoAct = 0;
            JPanel j = new InterfaceLogin(this).getpanel1() ;
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new InterfaceLogin(this).getpanel1());
            //UI.setSize(1080, 720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 1 && estadoAct != estado){
            estadoAct = 1;
            JPanel j = new Register(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Register(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 2 && estadoAct != estado){
            estadoAct = 2;
            JPanel j = new Login(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 3 && estadoAct != estado){
            estadoAct = 3;
            if (login == null)login = new Account(this);
            JPanel j = login.getpanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(login.getpanel1());

            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }


        // JUGAR PARTIDA

        else if (estado == 4 && estadoAct != estado){
            estadoAct = 4;
            JPanel j = new PoP(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new PoP(this).getMainPanel());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 5 && estadoAct != estado){
            estadoAct = 5;
            JPanel j = new EligePlayer(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new EligePlayer(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 6 && estadoAct != estado){
            estadoAct = 6;
            JPanel j = new TemaDif(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new TemaDif(this).getMainPanel());
            //UI.setContentPane(new ChooseP(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 7 && estadoAct != estado){
            estadoAct = 7;
            JPanel j = new ChooseP(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new ChooseP(this).getMainPanel());
            //UI.setSize(1080,1080);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }

        else if (estado == 8 && estadoAct != estado){
            boolean aux = estadoAct == 9;
            estadoAct = 8;
            if (!aux){

                part = new Partida(this,PC,Col,tema,win);
                part.setsecs(secs);
                part.getTime().start();
                part.updateHisto(historial);
            }
            JPanel j = part.getpanel();
            JScrollPane sp = new JScrollPane(j);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }

        else if (estado == 9 && estadoAct != estado){
            estadoAct = 9;
            JPanel j = new Pause(this,time).getpausa();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Pause(this).getpausa());
            //UI.setSize(1080,1080);
            //UI.setResizable(true);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 10 && estadoAct != estado){
            estadoAct = 10;
            JPanel j = new CreaMod(this,Mod).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Pause(this).getpausa());
            //UI.setSize(1080,1080);
            //UI.setResizable(true);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }

        else if (estado == 11 && estadoAct != estado){
            estadoAct = 11;
            JPanel j = new edituser(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 12 && estadoAct != estado){
            estadoAct = 12;
            JPanel j = new CambiarUsn(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 13 && estadoAct != estado){
            estadoAct = 13;
            JPanel j = new CambiarPsw(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 14 && estadoAct != estado){
            estadoAct = 14;
            setPub(false);
            JPanel j = new TemaDifComp(this).getMainPanel();
            //JPanel j = new ChooseProbPriv(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 15 && estadoAct != estado){
            estadoAct = 15;
            setPub(false);
            JPanel j = new TemaDifElim(this).getMainPanel();
            //JPanel j = new ChooseProbPriv(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 16 && estadoAct != estado){
            estadoAct = 16;
            setPub(false);
            JPanel j = new ChoosePComp(this).getMainPanel();
            //JPanel j = new ChooseProbPriv(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 17 && estadoAct != estado){
            estadoAct = 17;
            setPub(false);
            JPanel j = new ChoosePElim(this).getMainPanel();
            //JPanel j = new ChooseProbPriv(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 18 && estadoAct != estado){
            estadoAct = 18;
            setPub(false);
            JPanel j = new ChoosePGuard(this).getMainPanel();
            //JPanel j = new ChooseProbPriv(this).getMainPanel();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 19 && estadoAct != estado){
            estadoAct = 19;
            JPanel j = new ChoosePCreaMod(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 20 && estadoAct != estado){
            estadoAct = 20;
            JPanel j = new TemaDifCreaMod(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 21 && estadoAct != estado){
            estadoAct = 21;
            JPanel j = new Ranking(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            //UI.setContentPane(new Login(this).getPanel1());
            //UI.setSize(1080,720);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 22 && estadoAct != estado) {
            estadoAct = 22;
            JPanel j = new ChooseProblemas(this).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
        else if (estado == 23 && estadoAct != estado) {
            estadoAct = 23;
            JPanel j = new Skynet(this,PC,Col,tema).getPanel1();
            JScrollPane sp = new JScrollPane(j);
            sp.setPreferredSize(j.getPreferredSize());
            UI.setContentPane(sp);
            UI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            UI.setVisible(true);
        }
    }

    boolean Check(String nom , String pass){
        return J.checkID(nom, pass);
    }

    boolean CheckReg (String nom, String pass){
        boolean okiN = J.valid_name(nom);
        int passa = J.valid_pass(pass);
        boolean okiP = false;
        if (passa == 0) okiP = true;
        else usage(passa+1);
        return okiN && okiP;
    }

    void Reg (String a, String b){
        J.Registra(a,b);
    }

    void setPub(boolean pub) {
        Pub = pub;
    }

    void setPlayerOne(String p){
        playerOne = p;
    }

    String getPlayerOne() {
        return playerOne;
    }

    void setPlayerTwo(String p){
        playerTwo = p;
    }

    String getPlayerTwo(){
        return playerTwo;
    }

    void setDif(String d){
        Dif = d;
    }

    String getDif(){
        return Dif;
    }

    void setTema(int t){
        tema = t;
    }

    int getTema() {
        return tema;
    }

    void setCol(String s){
        Col = s;
    }

    String getCol(){
        return Col;
    }

    void setTotals(int s) {
        secs = s;
    }

    int getTotals() {
        return secs;
    }

    void usage(int num){
        if (num == 1) JOptionPane.showMessageDialog(null,"Usuario o Contraseña Incorrecto");
        if (num == 2)JOptionPane.showMessageDialog(null,"Password Incorrecta : Falten minuscule/s ");
        if (num == 3)JOptionPane.showMessageDialog(null,"Password Incorrecta : Falten Majuscule/s");
        if (num == 4)JOptionPane.showMessageDialog(null,"Password Incorrecta : Falten Nombre/s");
        if (num == 5)JOptionPane.showMessageDialog(null,"Password Incorrecta : Minim: 8 caràcters, Maxim: 12");
    }

    boolean getPub() {
        return Pub;
    }

    void setPC(Pair<Integer,String> p){
        PC =p;
    }

    Pair<Integer,String> getPC(){
        return PC;
    }

    ArrayList<Pair<Integer,String>> getInfoProb(boolean pub, int tema, String col, String dif) {
        //int tema, String dif, String Color, String tipo
        String tipo;
        if (pub)tipo = "publicos";
        else tipo = "privados";

        return J.EligeProblema(tema,dif,col,tipo);
    }

    Pair<Integer,Integer> Tradueix (int Casilla){
        int x,y;
        x = Casilla/8;
        y = Casilla%8;

        return new Pair<Integer, Integer>(x,y);
    }

    ArrayList<Pair<Integer,Integer>> dameMoves (Pair<Integer,Integer> p,String fen){
        fen += " w - - 0 1";
        return J.dameMoves(p,fen);
    }

    int checkJaque(String Fen, String color) {
        if (color.equals("blanco")) color = "blanca";
        else color = "negra";
        return J.checkJaque(Fen, color);
    }

    void log(String name){
        J.LogIn(name);
    }

    void guardaTime(Timer t){time = t;}

    Pair<Pair<Integer, String>, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> mandaIA(String fen_util, String col, int endgame,String PlayWho) {
        int color;
        if (col.equals("blanco")) color = 0;
        else color = 1;
        fen_util += " w - - 0 1";
        return J.minimax(fen_util,endgame,color,PlayWho);
    }

    void pochaPartida(){
        part = null;
        String aux;
        if (Pub)aux = "publico";
        else aux = "privado";
        J.pochaPartida(String.valueOf(PC.getKey()),aux);
    }

    void setUser(String nom){
        User = nom;
    }

    String getUser(){
        return User;
    }

    void actUser(String nom){
        J.actualizaUsn(nom);
    }

    boolean validUser(String nom){
        return J.valid_name(nom);
    }

    int NouProb(String fen, String t){
        return J.Crear_Problema(fen,t);
    }

    void setPass(String pass) {
        Pass = pass;
    }

    String getPass(){
        return Pass;
    }

    void actPass(String pass){
        J.actualizaPass(pass);
    }

    int validPass(String pass){
        return J.valid_pass(pass);
    }

    void setPMod(Pair<Integer, String> integerStringPair) {
        PMod = integerStringPair;
    }

    void setMod(boolean b){
        Mod = b;
    }

    boolean compartirProb(int choice) {
        return J.compartirProb(choice);
    }

    void eliminarProb(int choice) {
        J.Borra(choice);
    }

    void guardarSalir(ArrayList<String> info) {
        J.guardar_salir(info);
    }

    void setWin(String col) {
        win = col;
    }

    ArrayList<ArrayList<String>> getInfoPart() {
        return J.getPartidas();
    }

    void setJugada(int j) {
        jugada = j;
    }

    int getJugada() {
        return jugada;
    }

    void setHistorial(String h) {
        historial = h;
    }

    String getHistorial(){
        return historial;
    }

    String getPlayWho() {
        return PlayWho;
    }

    void setPlayWho(String pw) {
        PlayWho = pw;
    }

    int getEndgame(){ return endgame; }

    void setEndgame (int eg) {endgame = eg;}

    public double getPuntitos() { return puntitos; }

    public void setPuntitos(double puntos) { puntitos = puntos; }

    Pair<Integer, ArrayList<ArrayList<String>>> getRanking() {
        int codiR = getPC().getKey();

        return J.getRanking(codiR);
    }

    void updateRanking(double puntos) {
        J.hacer_ranking(PC.getKey(), puntos);
    }

    public Pair<Integer, String> getPMod() {
        return PMod;
    }

    public int validaf(String f, String t,int p){
        return J.Modificar(p,f,t);
    }

    boolean computaIA(String fen, String col, int endgame) {
        int color;
        if (col.equals("blanco")) {
            color = 0;
            fen += " w - - 0 1";
        }
        else {
            color = 1;
            fen += " b - - 0 1";
        }
        return J.computaIA(fen, color, endgame, playerOne, playerTwo);
    }

    public int getPartidas_skynet() { return partidas_skynet; }

    public void setPartidas_skynet(int battles) { partidas_skynet = battles; }

    public Pair<Integer,Integer> getSkynet_wins() {
        return skynet_wins;
    }

    public void setSkynet_wins(Pair<Integer,Integer> win) {
        skynet_wins = new Pair<>(win.getKey(),win.getValue());
    }

    private Pair<Integer,Integer> skynet_wins = new Pair<> (0,0);

    public void limpia(){
        Mod = false;
    }

}
