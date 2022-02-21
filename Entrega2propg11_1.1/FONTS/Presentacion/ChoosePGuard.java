package Presentacion;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ChoosePGuard {
    private JPanel MainPanel;
    private JButton lefty;
    private JButton righty;
    private JButton Accepty = new JButton("Confirmar");
    private JButton Backy = new JButton("Atrás");
    private JLabel Tagy = new JLabel("Problema : ");
    private JTextArea Infy = new JTextArea();
    private ArrayList<ArrayList<String>> P = new ArrayList<>();
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private int i = 0;

    ChoosePGuard(ControladorPresentacion m) {

        MainPanel = new JPanel();
        Dimension Ventana = new Dimension(1000,1000);
        generataulell();
        MainPanel.add(layeredPane);
        MainPanel.setPreferredSize(Ventana);
        MainPanel.setLayout(null);

        P = m.getInfoPart();

        URL flechaL = this.getClass().getResource("/Presentacion/Piezas/arrowL.png");
        URL flechaR = this.getClass().getResource("/Presentacion/Piezas/arrowR.png");
        lefty = new JButton(new ImageIcon(flechaL));
        righty = new JButton(new ImageIcon(flechaR));

        lefty.setBounds(25,410,100,100);
        righty.setBounds(875,410,100,100);
        Accepty.setBounds(150,870,150,50);
        Backy.setBounds(700,870,150,50);
        Tagy.setBounds(425,50,150,50);

        Font fonty = new Font("Arial Black",1,20);
        Tagy.setFont(fonty);

        String Info = setInformacion(i);

        Infy.setText(Info);
        Infy.setFont(fonty);
        Infy.setBounds(150,800,700,70);

        MainPanel.add(lefty);
        MainPanel.add(righty);
        MainPanel.add(Accepty);
        MainPanel.add(Backy);
        MainPanel.add(Tagy);
        MainPanel.add(Infy);

        m.setWin(m.getCol());
        CambiaTablero();
        Backy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPub(false);
                m.setPlayerOne("");
                m.setPlayerTwo("");
                m.setDif("");
                m.setCol("");
                m.setTema(0);
                m.changeState(3);
            }
        });
        Accepty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codi = Integer.parseInt(P.get(i).get(1));
                String fen = P.get(i).get(6) + " w - - 0 1";
                Pair<Integer, String> pc = new Pair<Integer, String>(codi, fen);
                m.setPC(pc);
                if (P.get(i).get(2).equals("publico")) m.setPub(true);
                else m.setPub(false);
                m.setPlayerOne(P.get(i).get(3));
                m.setPlayerTwo(P.get(i).get(4));
                //m.setPlayWho(m.getPlayerOne());
                m.setTema(Integer.valueOf(P.get(i).get(12)));
                if (Integer.parseInt(P.get(i).get(8)) > (Integer.parseInt(P.get(i).get(9)))) m.setCol(P.get(i).get(11));
                else m.setCol(P.get(i).get(10));
                m.setWin(P.get(i).get(10));
                m.setTotals(Integer.valueOf(P.get(i).get(7)));
                m.setJugada(Integer.valueOf(P.get(i).get(9))+1);
                int totalmovs = 2*Integer.valueOf(P.get(i).get(12))-1;
                int eg = totalmovs - Integer.valueOf(P.get(i).get(8)) - Integer.valueOf(P.get(i).get(9));
                m.setEndgame(eg);
                if (P.get(i).get(5).equals(P.get(i).get(10))) m.setPlayWho(m.getPlayerOne());
                else m.setPlayWho(m.getPlayerTwo());
                String histo = "";
                String s = "";
                for(int j = 13; j < P.get(i).size(); j++) {
                    s = P.get(i).get(j);
                    if (!s.equals("n")) {
                        histo += (s + " ");
                    }
                    else {
                        histo += "\n";
                    }
                }
                m.setHistorial(histo);
                //m.getTime().start();
                m.changeState(8);
            }
        });
        lefty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                --i;
                if (i <= -1)i = P.size()-1;
                CambiaTablero();
            }
        });
        righty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++i;
                if (i >= P.size())i=0;
                CambiaTablero();
            }
        });

    }

    JPanel getMainPanel(){
        return MainPanel;
    }

    private void generataulell() {
        layeredPane = new JLayeredPane();
        Dimension boardSize = new Dimension(700,700);
        layeredPane.setBounds(150,100,700,700);
        layeredPane.setPreferredSize(boardSize);
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
            if (row == 0)
                square.setBackground( i % 2 == 0 ? marron : blanco );
            else
                square.setBackground( i % 2 == 0 ? blanco : marron);
        }
    }

    private void CambiaTablero() {
        MainPanel.remove(layeredPane);
        generataulell();
        MainPanel.add(layeredPane);
        String Info = "Problema: " + P.get(i).get(1);
        Tagy.setText(Info);
        pintaPieza(P.get(i).get(6));
    }

    private void pintaPieza(String fen) {

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

    private String setInformacion(int i ) {
        String Info = "Juega: " + P.get(i).get(5) + ", " + P.get(i).get(3) + ", " + P.get(i).get(4) + "\n";
        if (!P.get(i).get(3).equals("Humano"))
            Info += "Eres el jugador 2, color: " + P.get(i).get(11) + ", te quedan " + (Integer.parseInt(P.get(i).get(12))-Integer.parseInt(P.get(i).get(9))) + " movimientos";
        else {
            Info += "Eres el jugador 1, color: " + P.get(i).get(10) + ", te quedan " + (Integer.parseInt(P.get(i).get(12))-Integer.parseInt(P.get(i).get(8))) + " movimientos";
        }
        return Info;
    }
}
