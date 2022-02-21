package Presentacion;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import javafx.util.Pair;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.management.StringValueExp;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.xml.bind.annotation.XmlType;
import java.net.URL;


public class Partida implements MouseMotionListener, MouseListener {
        private int secs = 0;
        private int totalsecs =0;
        private int hora = 0;
        private int minutos = 0;
        private String timeText="";
        private Timer time;
        private JLabel Temps;
        private JLabel turnos;
        private JPanel MainPanel;
        private JPanel PanelInfo;
        private String p1;
        private String p2;
        private String PlayWho = "";
        private int endgame;
        private ControladorPresentacion m;
        private JLayeredPane layeredPane;
        private JPanel chessBoard;
        private JPanel Lletres;
        private JPanel Nombres;
        private JLabel chessPiece;
        private int xAdjustment;
        private int yAdjustment;
        private String fen_util;
        private String Fen_init;
        private int codiP;
        private int tema;
        private String col;
        private String winner;
        private int jugada;
        private int plays_to_end;
        private boolean Over = false;
        private boolean WIN = false;

    private void Updatetime(ActionEvent evt){
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Temps.setText(timeText);
                totalsecs++;
                secs++;
                if (secs == 60){
                    secs = 0;
                    ++minutos;
                }
                if (minutos == 60){
                    minutos = 0;
                    hora++;
                }

                timeText = "";
                if(hora < 10){
                    timeText+="0";
                    timeText+= String.valueOf(hora);
                    timeText+=":";
                }
                else{
                    timeText+= String.valueOf(hora);
                    timeText+=":";
                }
                if(minutos < 10){
                    timeText+="0";
                    timeText+= String.valueOf(minutos);
                    timeText+=":";
                }
                else{
                    timeText+= String.valueOf(minutos);
                    timeText+=":";
                }
                if(secs < 10){
                    timeText+="0";
                    timeText+= String.valueOf(secs);
                }
                else{
                    timeText+= String.valueOf(secs);
                }
            }
        });
        time.start();
    }

    public Partida(ControladorPresentacion m, Pair<Integer,String> PC,String colo, int tem, String win){

        this.m = m;
        jugada = m.getJugada();
        totalsecs = m.getTotals();
        p1 = m.getPlayerOne();
        p2 = m.getPlayerTwo();
        PlayWho = m.getPlayWho();
        Fen_init = PC.getValue();
        codiP = PC.getKey();
        tema = tem;
        endgame = m.getEndgame();
        plays_to_end = tema;
        col = colo;
        if (win.equals("blanco")) winner = "B";
        else winner = "N";
        //jugada = 1; //A no ser que sea una partida guardada que vuelve a iniciarse, supongo, xD
        fen_util = Fen_init.substring(0,Fen_init.length()-10);

        Dimension Ventana = new Dimension(900,1000);
        Dimension boardSize = new Dimension(700,700);
        Dimension Letras = new Dimension(700,50);
        Dimension Numeros = new Dimension(50,700);
        Dimension blank = new Dimension(200,100);

        // Creamos Timer y lo empezamos
        Temps = new JLabel();
        Updatetime(null);

        // Creamos Panel Principal
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(Ventana);
        MainPanel.setSize(Ventana);
        MainPanel.setLayout(null);
        MainPanel.setBackground(new Color(238,229,218));
        // TABLERO PANEL
        Nombres = new JPanel();
        Nombres.setLayout(new GridLayout(8,1));
        Nombres.setSize(Numeros);
        Nombres.setBounds(50,100,Numeros.width,Numeros.height); // 50, 700
        Nombres.setBackground(new Color(238,229,218));
        MainPanel.add(Nombres);

        JLabel auxN = null;
        for (int j = 0; j < 9; ++j){
            if (j == 0) auxN = new JLabel("8");
            if (j == 1) auxN = new JLabel("7");
            if (j == 2) auxN = new JLabel("6");
            if (j == 3) auxN = new JLabel("5");
            if (j == 4) auxN = new JLabel("4");
            if (j == 5) auxN = new JLabel("3");
            if (j == 6) auxN = new JLabel("2");
            if (j == 7) auxN = new JLabel("1");
            auxN.setFont(new Font("Arial Black",1,25));
            auxN.setBackground(new Color(238,229,218));
            auxN.setHorizontalAlignment(SwingConstants.CENTER);
            Nombres.add(auxN);
        }

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();

        MainPanel.add(layeredPane);
        layeredPane.setBounds(100,100,700,700);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        layeredPane.setMaximumSize(boardSize);
        layeredPane.setMinimumSize(boardSize);
        layeredPane.setSize(boardSize);

        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);


        for (int i = 0; i < 64; i++) {


            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
            Color blanco = new Color(255,255,200);
            Color marron = new Color(149,106,47);
            int row = (i / 8) % 2;
            if (row == 0) square.setBackground( i % 2 == 0 ? marron : blanco );
            else square.setBackground( i % 2 == 0 ? blanco : marron);
        }

        Lletres = new JPanel();
        MainPanel.add(Lletres);
        Lletres.setLayout(new GridLayout(1,8));
        Lletres.setSize(Letras);
        Lletres.setBounds(100,800,Letras.width,Letras.height);
        Lletres.setBackground(new Color(238,229,218));
        JLabel aux = null;
        for (int j = 0; j < 8; ++j){
            if (j == 0) aux = new JLabel("  A");
            if (j == 1) aux = new JLabel("  B");
            if (j == 2) aux = new JLabel("  C");
            if (j == 3) aux = new JLabel("  D");
            if (j == 4) aux = new JLabel("  E");
            if (j == 5) aux = new JLabel("  F");
            if (j == 6) aux = new JLabel("  G");
            if (j == 7) aux = new JLabel("  H");
            aux.setFont(new Font("Arial Black",1,25));
            aux.setHorizontalAlignment(JLabel.CENTER);
            Lletres.add(aux);
        }


        // INFO PANEL

        PanelInfo = new JPanel();
        Dimension Info = new Dimension(400, 400);
        PanelInfo.setSize(Info);
        PanelInfo.setBorder(BorderFactory.createTitledBorder("INFORMAME"));

        Temps.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK),"Tiempo",
                TitledBorder.CENTER,TitledBorder.ABOVE_TOP,new Font("Arial Black",1,25)));
        Temps.setBounds(1000,100,300,100);
        Temps.setFont(new Font("Arial Black",1,40));
        Temps.setHorizontalAlignment(0);
        MainPanel.add(Temps);


        JLabel Colorete =  new JLabel("Turno del Color:",SwingConstants.CENTER);
        Colorete.setBounds(1000,250,300,50);
        Colorete.setFont(new Font("Arial Black",1,25));
        MainPanel.add(Colorete);


        JPanel p = new JPanel();
        JLabel Color = new JLabel();
        URL urli = this.getClass().getResource("/Presentacion/Piezas/gif4.gif");
        p.setBorder(BorderFactory.createMatteBorder(-1,-1,-1,-1,new ImageIcon(urli)));
        p.setBounds(1000,300,300,150);
        p.setLayout(new GridLayout(1,1));
        if (col.equals("blanco")) Color.setBackground(java.awt.Color.WHITE);
        else Color.setBackground(java.awt.Color.BLACK);
        Color.setOpaque(true);
        p.add(Color); //indice 0.
        MainPanel.add(p); //indice 5.


        JLabel Histo = new JLabel("Historial de Jugadas:",SwingConstants.CENTER);
        Histo.setFont(new Font("Arial Black",1,20));
        Histo.setBounds(1000,600,300,50);
        MainPanel.add(Histo);
        JTextArea texto = new JTextArea();
        texto.setFont(new Font("Arial Black",1,14));
        texto.setEditable(false);
        texto.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK)));
        JScrollPane barra = new JScrollPane(texto);
        barra.setBounds(1000,650,300,300);
        barra.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        MainPanel.add(barra);

        JButton b1 = new JButton("Pausar");
        JButton b2 = new JButton("Guardar y Salir");
        b1.setBounds(100, 900,300,50);
        b2.setBounds(500, 900,300,50);
        b1.setFont(new Font("Arial Black",1,20));
        b2.setFont(new Font("Arial Black",1,20));
        MainPanel.add(b1);
        MainPanel.add(b2);

        turnos = new JLabel();
        turnos.setText(String.valueOf(endgame));
        turnos.setHorizontalAlignment(0);
        turnos.setBounds(1000,500,300,100);
        turnos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK),"Turnos Restantes:",
                TitledBorder.CENTER,TitledBorder.ABOVE_TOP,new Font("Arial Black",1,25)));
        turnos.setFont(new Font("Arial Black",1,40));
        MainPanel.add(turnos);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.stop();
                m.guardaTime(time);
                m.changeState(9);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> info = new ArrayList<>();
                info.add(String.valueOf(codiP));
                if (m.getPub()) info.add("publico");
                else info.add("privado");
                info.add(p1);
                info.add(p2);
                info.add(col);
                info.add(fen_util);
                info.add(String.valueOf(totalsecs));
                info.add(String.valueOf(tema-plays_to_end));
                info.add(String.valueOf(jugada-1));
                //info.add(winner);
                if (winner.equals("B")) {
                    info.add("blanco");
                    info.add("negro");
                }
                else {
                    info.add("negro");
                    info.add("blanco");
                }
                info.add(String.valueOf(tema));
                JScrollPane Historial = (JScrollPane)(MainPanel.getComponents()[7]);
                JTextArea Histo = (JTextArea)(Historial.getViewport().getView());
                String h = "";
                for (int i = 0; i < Histo.getText().length(); i++) {
                    if (Histo.getText().charAt(i) == '\n') h += " n ";
                    else h += String.valueOf(Histo.getText().charAt(i));
                }
                info.add(h);
                m.guardarSalir(info);
                time.stop();
                m.guardaTime(time);
                m.setPlayWho(PlayWho);
                m.changeState(3);
            }
        });

        pintaPieza(fen_util);

        if (!PlayWho.equals("Humano")) play();
    }

    private void play() {
        chessPiece = null;
        despintaBorders();
        Pair<Pair<Integer,String>, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>> res;
        res = m.mandaIA(fen_util,col,endgame,PlayWho);
        //Posición inicial
        int xc,yc;
        xc = res.getValue().getKey().getKey();
        yc = res.getValue().getKey().getValue();
        int locC = xc*8+yc;
        Component d = chessBoard.getComponent(locC);
        chessPiece = (JLabel) d.getAccessibleContext().getAccessibleChild(0);
        chessPiece.setVisible(false);

        //Posición final
        int x,y;
        x = res.getValue().getValue().getKey();
        y = res.getValue().getValue().getValue();
        int casilla = x*8+y;
        Component c = chessBoard.getComponent(casilla);
        //chessPiece.setLocation(p);
        //chessBoard.add(chessPiece,casilla);

        boolean eat = (c.getAccessibleContext().getAccessibleChild(0) instanceof JLabel);
        if (eat) {
            Container parent = (Container) c;
            parent.remove(0);
            parent.add(chessPiece);
        }
        else {
            Container parent = (Container) c;
            parent.add(chessPiece);
        }
        int jaque_mate = res.getKey().getKey();

        chessPiece.setVisible(true);
        endgame--;
        turnos.setText(String.valueOf(endgame));
        //pintaPieza(res.getKey().getValue().substring(0,res.getKey().getValue().length()-10));
        UpdateFen();
        String promo = "";
        if (col.equals("blanco")){ promo = checkPromoMaqB(fen_util, res.getKey().getValue(), c);}
        else {promo =checkPromoMaqN(fen_util, res.getKey().getValue(), c);}
        changeCol();
        updateHisto(checkPlay(casilla, eat, promo, jaque_mate));// cosas devueltas en el parentesis
        chessPiece = null;
        if (PlayWho.equals(p1)) PlayWho = p2;
        else PlayWho = p1;
    }

    public void mouseClicked(MouseEvent e) {
        if (PlayWho.equals("Humano")) {
            Component c = chessBoard.findComponentAt(e.getX(), e.getY());
            int casilla;
            if (c instanceof JLabel) {
                casilla = c.getParent().getAccessibleContext().getAccessibleIndexInParent();
            }
            else casilla = c.getAccessibleContext().getAccessibleIndexInParent();

            if (chessPiece != null) { // 2n click
                Component[] comp = chessBoard.getComponents();
                JPanel cuadrado = (JPanel) comp[casilla];
                Border bordeline = cuadrado.getBorder();
                if (bordeline != null && ((LineBorder) bordeline).getLineColor().equals(Color.CYAN)) {//.equals(BorderFactory.createLineBorder(Color.CYAN, 5))) {
                    chessPiece.setVisible(false);
                    boolean eat = (c instanceof JLabel);
                    if (eat) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        parent.add(chessPiece);
                    }
                    else {
                        Container parent = (Container) c;
                        parent.add(chessPiece);
                    }
                    int jaque_mate = 0;
                    String promo = "";
                    if (checkPromo(casilla)) promo = promover(casilla);

                    chessPiece.setVisible(true);

                    UpdateFen();
                    changeCol();
                    jaque_mate = m.checkJaque(fen_util, col);
                    updateHisto(checkPlay(casilla, eat, promo, jaque_mate));
                    if (PlayWho.equals(p1)) PlayWho = p2;
                    else PlayWho = p1;
                    --endgame;
                    turnos.setText(String.valueOf(endgame));
                    if (endgame!=0 && !PlayWho.equals("Humano")) play();
                    if (Over) {
                        despintaBorders();
                        chessPiece = null;
                        endgame();
                    }
                }
                despintaBorders();
                chessPiece = null;
            }
            else {            // 1r click
                if (c instanceof JPanel) return;
                Point parentLocation = c.getParent().getLocation();
                xAdjustment = parentLocation.x - e.getX();
                yAdjustment = parentLocation.y - e.getY();
                chessPiece = (JLabel) c;

                if (checkColor()) {
                    Pair<Integer, Integer> p = m.Tradueix(casilla);
                    JPanel cuadrado = (JPanel) chessBoard.getComponents()[casilla];
                    cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
                    pintaBorders(m.dameMoves(p, fen_util));
                }
                else chessPiece = null;
            }
        }
    }

    private boolean checkPromo(int casilla) {
        Component c = chessBoard.getComponent(casilla);
        JLabel pieza = (JLabel)(c.getAccessibleContext().getAccessibleChild(0));
        String path = pieza.getIcon().toString();
        path = path.substring(path.length()-9,path.length()-5);
        if (path.equals("Peon")) {
            return ((casilla < 8) || (casilla >= 56));
        }
        return false;
    }

    private String checkPromoMaqB (String fen_util, String fen_d, Component c) {
        int i = 0;
        fen_d = fen_d.substring(0,fen_d.length()-10);
        while (fen_util.charAt(i) != '/') {
            if (fen_util.charAt(i) == 'P') {
                ((JLabel) c.getAccessibleContext().getAccessibleChild(0)).setVisible(false);
                pintaPieza(fen_d);
                UpdateFen();
                return String.valueOf(fen_d.charAt(i));
            }
            i++;
        }
        return "";
    }

    private String checkPromoMaqN (String fen_util, String fen_d, Component c) {
        fen_d = fen_d.substring(0,fen_d.length()-10);
        int i = fen_util.length()-1;
        while (fen_util.charAt(i) != '/') {
            if (fen_util.charAt(i) == 'p') {
                ((JLabel) c.getAccessibleContext().getAccessibleChild(0)).setVisible(false);
                pintaPieza(fen_d);
                UpdateFen();
                return String.valueOf(fen_d.charAt(i));
            }
            i--;
        }
        return "";
    }

    private String promover(int casilla) {
        return "";
    }

    private void UpdateFen() {
        int count = 0;
        int blanks = 0;
        String Fen = "";
        for (Component c : chessBoard.getComponents()) {
            if (c.getAccessibleContext().getAccessibleChildrenCount() != 0 && c.getAccessibleContext().getAccessibleChild(0) instanceof JLabel) {
                if (blanks != 0) Fen += String.valueOf(blanks);
                blanks = 0;
                JLabel pieza = (JLabel)(c.getAccessibleContext().getAccessibleChild(0));
                String path = pieza.getIcon().toString();
                path = path.substring(path.length()-15);
                if (path.equals("ezas/TorreN.png")) {
                    Fen += "r";
                }
                else if (path.equals("zas/CavallN.png")) {
                    Fen += "n";
                }
                else if (path.equals("ezas/AlfilN.png")) {
                    Fen += "b";
                }
                else if (path.equals("ezas/ReinaN.png")) {
                    Fen += "q";
                }
                else if (path.equals("Piezas/ReiN.png")) {
                    Fen += "k";
                }
                else if (path.equals("iezas/PeonN.png")) {
                    Fen += "p";
                }
                else if (path.equals("ezas/TorreB.png")) {
                    Fen += "R";
                }
                else if (path.equals("zas/CavallB.png")) {
                    Fen += "N";
                }
                else if (path.equals("ezas/AlfilB.png")) {
                    Fen += "B";
                }
                else if (path.equals("ezas/ReinaB.png")) {
                    Fen += "Q";
                }
                else if (path.equals("Piezas/ReiB.png")) {
                    Fen += "K";
                }
                else if (path.equals("iezas/PeonB.png")) {
                    Fen += "P";
                }
            }
            else blanks++;
            count++;
            if (count%8 == 0) {
                if (blanks != 0) Fen += String.valueOf(blanks);
                blanks = 0;
                if (count != 64) Fen += "/";
            }
        }

        fen_util = Fen;

    }

    private boolean checkColor() {
        String path = chessPiece.getIcon().toString();
        int len = path.length();
        return ((path.substring(len-5,len-4).equals("B") && col.equals("blanco")) ||
                (path.substring(len-5,len-4).equals("N") && col.equals("negro")));
    }

    private void changeCol() {
        JPanel p = (JPanel)MainPanel.getAccessibleContext().getAccessibleChild(5);
        JLabel Color = (JLabel)p.getAccessibleContext().getAccessibleChild(0);
        if (col.equals("blanco")) {
            col = "negro";
            Color.setBackground(java.awt.Color.BLACK);
        }
        else {
            col = "blanco";
            Color.setBackground(java.awt.Color.WHITE);
        }
    }

    private String traduceFicha(int casilla) {
        Component c = chessBoard.getComponent(casilla);
        JLabel pieza = (JLabel)(c.getAccessibleContext().getAccessibleChild(0));
        String path = pieza.getIcon().toString();
        String color = path.substring(path.length()-5,path.length()-4);
        String Result = "";
        if (color.equals(winner)) {
            Result += String.valueOf(jugada);
            Result += ".";
            plays_to_end -= 1;
            if (plays_to_end == 0)Over = true;
        }
        else jugada += 1;
        path = path.substring(path.length()-15, path.length()-5);
        if (path.equals("ezas/Torre"))
            Result += " R";
        else if (path.equals("zas/Cavall"))
            Result += " N";
        else if (path.equals("ezas/Alfil"))
            Result += " B";
        else if (path.equals("ezas/Reina"))
            Result += " Q";
        else if (path.equals("Piezas/Rei"))
            Result += " K";
        else if (path.equals("iezas/Peon"))
            Result += " ";

        return Result;
    }

    private String traducePos(Pair<Integer,Integer> pos) {
        String mov = "";
        switch (pos.getValue()) {
            case 0:
                mov += "a";
                break;
            case 1:
                mov += "b";
                break;
            case 2:
                mov += "c";
                break;
            case 3:
                mov += "d";
                break;
            case 4:
                mov += "e";
                break;
            case 5:
                mov += "f";
                break;
            case 6:
                mov += "g";
                break;
            case 7:
                mov += "h";
                break;
            default:
                mov += "";
        }
        mov += String.valueOf(8-pos.getKey());
        return mov;
    }

    private String checkPlay(int casilla, boolean eat, String promo, int jaque_mate) {
        Pair<Integer,Integer> p = m.Tradueix(casilla);
        String Play = "";
        Play += traduceFicha(casilla);
        if (eat) Play += "x";
        Play += traducePos(p);
        if (!promo.equals("")) Play += "=" + promo;
        if (jaque_mate == 1) Play += "+";
        else if (jaque_mate == 2){
            Play += "++";
            Over = true;
            if (!winner.equals(col))WIN = true;
        }
        if (Play.substring(0,1).equals(" ")) Play += "\n";
        return Play;
    }

    public void updateHisto(String Play) {
        JScrollPane Historial = (JScrollPane)(MainPanel.getComponents()[7]);
        JTextArea Histo = (JTextArea)(Historial.getViewport().getView());
        Histo.append(Play);
    }

    JPanel getpanel() {
        return MainPanel;
    }

    private void pintaPieza(String fen){

        URL fondo;
        /*
        URL fondo = this.getClass().getResource("/Presentacion/Piezas/ReiB.png");
        JLabel piece = new JLabel( new ImageIcon(fondo)); // new ImageIcon("/Presentacion/peon.png").getImage()
        JPanel panel = (JPanel)chessBoard.getComponent(0);
        panel.add(piece);
        */
        int pos = 0;
        for (int j = 0; j < fen.length();++j) {
            if (fen.charAt(j) == 'r') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/TorreN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'n') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/CavallN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'b') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/AlfilN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'q') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/ReinaN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'k') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/ReiN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'p') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/PeonN.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'R') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/TorreB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'N') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/CavallB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'B') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/AlfilB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'Q') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/ReinaB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'K') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/ReiB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) == 'P') {
                fondo = this.getClass().getResource("/Presentacion/Piezas/PeonB.png");
                JLabel piece = new JLabel(new ImageIcon(fondo));
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                ++pos;
                panel.add(piece);
            } else if (fen.charAt(j) != '/') {
                int aux = Integer.parseInt(String.valueOf(fen.charAt(j)));
                pos += aux;

            }
        }
    }

    private void endgame(){
        if (WIN) {
            time.stop();
            JOptionPane.showMessageDialog(null, "Felicidades, Has Ganado!");
        }
        else {
            time.stop();
            JOptionPane.showMessageDialog(null, "Fin del Juego! Has usado el maximo de jugadas.");
        }

        if (m.getPub() && WIN) {
            double puntos = calcularPuntos();
            m.setPuntitos(puntos);
            m.updateRanking(puntos);
            m.pochaPartida();
            m.changeState(21);
        }
        else {
            m.pochaPartida();
            m.changeState(3);
        }
    }

    private void pintaBorders(ArrayList<Pair<Integer,Integer>> A){
        Component[] C = chessBoard.getComponents();
        for (Pair<Integer,Integer> p : A ){
            int casilla = p.getKey()*8 + p.getValue();
            JPanel cuadrado = (JPanel)C[casilla];
            cuadrado.setBorder(BorderFactory.createLineBorder(Color.CYAN,5));
        }
    }

    private void despintaBorders() {
        for (Component c : chessBoard.getComponents()) {
            JPanel cuadrado = (JPanel)c;
            cuadrado.setBorder(null);
        }
    }

    public void setsecs(int secs) {
        this.secs = secs;
    }

    public Timer getTime() {
        return time;
    }

    private double calcularPuntos() {
        return tema*10000.0 - (totalsecs*200.0)/tema;
    }

    public void mouseDragged(MouseEvent me) {

    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e){ }
}
