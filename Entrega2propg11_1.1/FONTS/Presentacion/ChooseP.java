package Presentacion;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ChooseP {
    private JPanel MainPanel = new JPanel();
    private JButton lefty = new JButton();
    private JButton righty = new JButton();
    private JButton Accepty = new JButton("Confirmar");
    private JButton Backy = new JButton("Atrás");
    private JLabel Tagy = new JLabel("Problema : ");
    private JLabel Infy = new JLabel();
    private ArrayList<Pair<Integer,String>> P = new ArrayList<Pair<Integer, String>>();
    private JLayeredPane layeredPane = new JLayeredPane();
    private JPanel chessBoard = new JPanel();
    private int i = 0;


    ChooseP(ControladorPresentacion m){

        MainPanel = new JPanel();
        Dimension Ventana = new Dimension(1000,1000);
        generataulell();
        MainPanel.add(layeredPane);
        MainPanel.setPreferredSize(Ventana);
        MainPanel.setLayout(null);
        MainPanel.setBackground(new Color(238,229,218));

        URL flechaL = this.getClass().getResource("/Presentacion/Piezas/arrowL.png");
        URL flechaR = this.getClass().getResource("/Presentacion/Piezas/arrowR.png");
        lefty = new JButton(new ImageIcon(flechaL));
        righty = new JButton(new ImageIcon(flechaR));

        lefty.setBounds(25,410,100,100);
        righty.setBounds(875,410,100,100);
        Accepty.setBounds(150,870,300,50);
        Backy.setBounds(550,870,300,50);
        Tagy.setBounds(425,50,150,50);

        Font fonty = new Font("Arial Black",1,20);
        Tagy.setFont(fonty);

        String Info;
        Info = "Mate en "+ m.getTema()+" / Dificultad " + m.getDif()+ " / Empieza " + m.getCol();
        Infy.setText(Info);
        Infy.setFont(fonty);
        Infy.setBounds(225,800,700,50);

        Accepty.setFont(new Font("Arial Black",1,20));
        Backy.setFont(new Font("Arial Black",1,20));

        MainPanel.add(lefty);
        MainPanel.add(righty);
        MainPanel.add(Accepty);
        MainPanel.add(Backy);
        MainPanel.add(Tagy);
        MainPanel.add(Infy);

        m.setWin(m.getCol());
        P = m.getInfoProb(m.getPub(), m.getTema(), m.getCol(), m.getDif());
        CambiaTablero();
        Backy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPub(false);
                m.setPlayWho("");
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
                m.setPC(P.get(i));
                m.setJugada(1);
                m.setPlayWho(m.getPlayerOne());
                if (m.getPartidas_skynet() == 0)
                    m.changeState(8);
                else {
                    m.setPartidas_skynet(m.getPartidas_skynet()-1);
                    m.changeState(23);
                }
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

    private void pintaPieza(String fen){

        URL fondo;
        /*
        URL fondo = this.getClass().getResource("/Presentacion/Piezas/ReiB.png");
        JLabel piece = new JLabel( new ImageIcon(fondo)); // new ImageIcon("/Presentacion/peon.png").getImage()
        JPanel panel = (JPanel)chessBoard.getComponent(0);
        panel.add(piece);
        */
        int pos = 0;
        for (int j = 0; j < fen.length()-10;++j) {
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

    private void CambiaTablero(){
        MainPanel.remove(layeredPane);
        generataulell();
        MainPanel.add(layeredPane);
        Tagy.setText("Problema: " + P.get(i).getKey());
        pintaPieza(P.get(i).getValue());
    }

    private void generataulell(){
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

}
