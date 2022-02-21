package Presentacion;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Skynet {
    private JPanel panel1;
    private ControladorPresentacion m;
    private JButton Backy;
    private JLabel m1_score;
    private JLabel m2_score;
    private JLabel winner;
    private JLabel m1;
    private JLabel m2;
    private JButton done;
    private String fen;
    private String col;
    private int tema;
    private int endgame;



    JPanel getPanel1() { return panel1; }

    Skynet(ControladorPresentacion m, Pair<Integer,String> PC, String colo, int tem) {
        this.m = m;
        m1.setText(m.getPlayerOne());
        m2.setText(m.getPlayerTwo());
        fen = PC.getValue();
        tema = tem;
        col = colo;
        endgame = 2*tema-1;

        boolean win = m.computaIA(fen, col, endgame);

        if (win) m.setSkynet_wins(new Pair<>(m.getSkynet_wins().getKey()+1,m.getSkynet_wins().getValue()));
        else m.setSkynet_wins(new Pair<>(m.getSkynet_wins().getKey(),m.getSkynet_wins().getValue()+1));

        m1_score.setText(String.valueOf(m.getSkynet_wins().getKey()));
        m2_score.setText(String.valueOf(m.getSkynet_wins().getValue()));

        if (m.getPartidas_skynet() == 0) {
            done.setText("Acabar");
            if (m.getSkynet_wins().getKey() > m.getSkynet_wins().getValue()) winner.setText("Máquina 1");
            else if (m.getSkynet_wins().getKey() < m.getSkynet_wins().getValue()) winner.setText("Máquina 2");
            else winner.setText("TIE");
        }

        Backy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(3);
            }
        });
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (done.getText().equals("Acabar")) {
                    m.setSkynet_wins(new Pair<>(0,0));
                    m.changeState(3);
                }
                else m.changeState(6);
            }
        });
    }

}
