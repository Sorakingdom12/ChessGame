package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.util.Pair;

public class CreaMod {
    private JPanel panel1;
    private JButton c0;
    private JButton c1;
    private JButton c2;
    private JButton c3;
    private JButton c4;
    private JButton c5;
    private JButton c6;
    private JButton c7;
    private JButton c8;
    private JButton c9;
    private JButton c10;
    private JButton c11;
    private JButton c12;
    private JButton c13;
    private JButton c14;
    private JButton c15;
    private JButton c16;
    private JButton c17;
    private JButton c18;
    private JButton c19;
    private JButton c20;
    private JButton c21;
    private JButton c22;
    private JButton c23;
    private JButton c24;
    private JButton c25;
    private JButton c26;
    private JButton c27;
    private JButton c28;
    private JButton c29;
    private JButton c30;
    private JButton c31;
    private JButton c32;
    private JButton c33;
    private JButton c34;
    private JButton c35;
    private JButton c36;
    private JButton c37;
    private JButton c38;
    private JButton c39;
    private JButton c40;
    private JButton c41;
    private JButton c42;
    private JButton c43;
    private JButton c44;
    private JButton c45;
    private JButton c46;
    private JButton c47;
    private JButton c48;
    private JButton c49;
    private JButton c50;
    private JButton c51;
    private JButton c52;
    private JButton c53;
    private JButton c54;
    private JButton c55;
    private JButton c56;
    private JButton c57;
    private JButton c58;
    private JButton c59;
    private JButton c60;
    private JButton c61;
    private JButton c62;
    private JButton c63;
    private JButton pN;
    private JButton cN;
    private JButton aN;
    private JButton tN;
    private JButton rN;
    private JButton kN;
    private JButton pB;
    private JButton cB;
    private JButton aB;
    private JButton tB;
    private JButton rB;
    private JButton kB;
    private JButton C3PO;
    private JButton Verify;
    private JButton atras;
    private JRadioButton Negras;
    private JRadioButton Blancas;
    private JTextField fenArea;
    private JTextField TemaArea;
    private int ficha = -1;
    private String aux = "";
    private Pair<Integer,String> P;

    JPanel getPanel1(){
        return panel1;
    }

    CreaMod(ControladorPresentacion m, boolean CoM) {
        fenArea.setEditable(false);
        ButtonGroup g1 = new ButtonGroup();
        g1.add(Negras);
        g1.add(Blancas);
        Blancas.setSelected(true);
        Dimension d = new Dimension(25,25);
        pN.setText("\u265F");
        cN.setText("\u265E");
        aN.setText("\u265D");
        tN.setText("\u265C");
        rN.setText("\u265B");
        kN.setText("\u265A");
        pB.setText("\u2659");
        cB.setText("\u2658");
        aB.setText("\u2657");
        tB.setText("\u2656");
        rB.setText("\u2655");
        kB.setText("\u2654");


        c0.setText(""); c0.setSize(d);
        c1.setText(""); c1.setSize(d);
        c2.setText(""); c2.setSize(d);
        c3.setText(""); c3.setSize(d);
        c4.setText(""); c4.setSize(d);
        c5.setText(""); c5.setSize(d);
        c6.setText(""); c6.setSize(d);
        c7.setText(""); c7.setSize(d);
        c8.setText(""); c8.setSize(d);
        c9.setText(""); c9.setSize(d);
        c10.setText(""); c10.setSize(d);
        c11.setText(""); c11.setSize(d);
        c12.setText(""); c12.setSize(d);
        c13.setText(""); c13.setSize(d);
        c14.setText(""); c14.setSize(d);
        c15.setText(""); c15.setSize(d);
        c16.setText(""); c16.setSize(d);
        c17.setText(""); c17.setSize(d);
        c18.setText(""); c18.setSize(d);
        c19.setText(""); c19.setSize(d);
        c20.setText(""); c20.setSize(d);
        c21.setText(""); c21.setSize(d);
        c22.setText(""); c22.setSize(d);
        c23.setText(""); c23.setSize(d);
        c24.setText(""); c24.setSize(d);
        c25.setText(""); c25.setSize(d);
        c26.setText(""); c26.setSize(d);
        c27.setText(""); c27.setSize(d);
        c28.setText(""); c28.setSize(d);
        c29.setText(""); c29.setSize(d);
        c30.setText(""); c30.setSize(d);
        c31.setText(""); c31.setSize(d);
        c32.setText(""); c32.setSize(d);
        c33.setText(""); c33.setSize(d);
        c34.setText(""); c34.setSize(d);
        c35.setText(""); c35.setSize(d);
        c36.setText(""); c36.setSize(d);
        c37.setText(""); c37.setSize(d);
        c38.setText(""); c38.setSize(d);
        c39.setText(""); c39.setSize(d);
        c40.setText(""); c40.setSize(d);
        c41.setText(""); c41.setSize(d);
        c42.setText(""); c42.setSize(d);
        c43.setText(""); c43.setSize(d);
        c44.setText(""); c44.setSize(d);
        c45.setText(""); c45.setSize(d);
        c46.setText(""); c46.setSize(d);
        c47.setText(""); c47.setSize(d);
        c48.setText(""); c48.setSize(d);
        c49.setText(""); c49.setSize(d);
        c50.setText(""); c50.setSize(d);
        c51.setText(""); c51.setSize(d);
        c52.setText(""); c52.setSize(d);
        c53.setText(""); c53.setSize(d);
        c54.setText(""); c54.setSize(d);
        c55.setText(""); c55.setSize(d);
        c56.setText(""); c56.setSize(d);
        c57.setText(""); c57.setSize(d);
        c58.setText(""); c58.setSize(d);
        c59.setText(""); c59.setSize(d);
        c60.setText(""); c60.setSize(d);
        c61.setText(""); c61.setSize(d);
        c62.setText(""); c62.setSize(d);
        c63.setText(""); c63.setSize(d);

        c0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c0.setText(aux);
                updatefen();
            }
        });
        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c1.setText(aux);
                updatefen();
            }
        });
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c2.setText(aux);
                updatefen();
            }
        });
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c3.setText(aux);
                updatefen();
            }
        });
        c4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c4.setText(aux);
                updatefen();
            }
        });
        c5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c5.setText(aux);
                updatefen();
            }
        });
        c6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c6.setText(aux);
                updatefen();
            }
        });
        c7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c7.setText(aux);
                updatefen();
            }
        });
        c8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c8.setText(aux);
                updatefen();
            }
        });
        c9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c9.setText(aux);
                updatefen();
            }
        });
        c10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c10.setText(aux);
                updatefen();
            }
        });
        c11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c11.setText(aux);
                updatefen();
            }
        });
        c12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c12.setText(aux);
                updatefen();
            }
        });
        c13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c13.setText(aux);
                updatefen();
            }
        });
        c14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c14.setText(aux);
                updatefen();
            }
        });
        c15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c15.setText(aux);
                updatefen();
            }
        });
        c16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c16.setText(aux);
                updatefen();
            }
        });
        c17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c17.setText(aux);
                updatefen();
            }
        });
        c18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c18.setText(aux);
                updatefen();
            }
        });
        c19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c19.setText(aux);
                updatefen();
            }
        });
        c20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c20.setText(aux);
                updatefen();
            }
        });
        c21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c21.setText(aux);
                updatefen();
            }
        });
        c22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c22.setText(aux);
                updatefen();
            }
        });
        c23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c23.setText(aux);
                updatefen();
            }
        });
        c24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c24.setText(aux);
                updatefen();
            }
        });
        c25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c25.setText(aux);
                updatefen();
            }
        });
        c26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c26.setText(aux);
                updatefen();
            }
        });
        c27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c27.setText(aux);
                updatefen();
            }
        });
        c28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c28.setText(aux);
                updatefen();
            }
        });
        c29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c29.setText(aux);
                updatefen();
            }
        });
        c30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c30.setText(aux);
                updatefen();
            }
        });
        c31.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c31.setText(aux);
                updatefen();
            }
        });
        c32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c32.setText(aux);
                updatefen();
            }
        });
        c33.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c33.setText(aux);
                updatefen();
            }
        });
        c34.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c34.setText(aux);
                updatefen();
            }
        });
        c35.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c35.setText(aux);
                updatefen();
            }
        });
        c36.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c36.setText(aux);
                updatefen();
            }
        });
        c37.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c37.setText(aux);
                updatefen();
            }
        });
        c38.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c38.setText(aux);
                updatefen();
            }
        });
        c39.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c39.setText(aux);
                updatefen();
            }
        });
        c40.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c40.setText(aux);
                updatefen();
            }
        });
        c41.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c41.setText(aux);
                updatefen();
            }
        });
        c42.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c42.setText(aux);
                updatefen();
            }
        });
        c43.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c43.setText(aux);
                updatefen();
            }
        });
        c44.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c44.setText(aux);
                updatefen();
            }
        });
        c45.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c45.setText(aux);
                updatefen();
            }
        });
        c46.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c46.setText(aux);
                updatefen();
            }
        });
        c47.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c47.setText(aux);
                updatefen();
            }
        });
        c48.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c48.setText(aux);
                updatefen();
            }
        });
        c49.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c49.setText(aux);
                updatefen();
            }
        });
        c50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c50.setText(aux);
                updatefen();
            }
        });
        c51.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c51.setText(aux);
                updatefen();
            }
        });
        c52.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c52.setText(aux);
                updatefen();
            }
        });
        c53.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c53.setText(aux);
                updatefen();
            }
        });
        c54.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c54.setText(aux);
                updatefen();
            }
        });
        c55.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c55.setText(aux);
                updatefen();
            }
        });
        c56.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c56.setText(aux);
                updatefen();
            }
        });
        c57.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c57.setText(aux);
                updatefen();
            }
        });
        c58.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c58.setText(aux);
                updatefen();
            }
        });
        c59.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c59.setText(aux);
                updatefen();
            }
        });
        c60.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c60.setText(aux);
                updatefen();
            }
        });
        c61.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c61.setText(aux);
                updatefen();
            }
        });
        c62.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c62.setText(aux);
                updatefen();
            }
        });
        c63.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pintaFicha();
                c63.setText(aux);
                updatefen();
            }
        });


        if (CoM) {
            P = m.getPMod();
            fenArea.setText(P.getValue());
            TemaArea.setText(String.valueOf(m.getTema()));
            PintaPMod();
        }

        C3PO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = -1;
            }
        });
        pN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 0;
            }
        });
        cN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 1;
            }
        });
        aN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 2;
            }
        });
        tN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 3;
            }
        });
        rN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 4;
            }
        });
        kN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 5;
            }
        });
        pB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 6;
            }
        });
        cB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 7;
            }
        });
        aB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 8;
            }
        });
        tB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 9;
            }
        });
        rB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 10;
            }
        });
        kB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ficha = 11;
            }
        });
        Verify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fen = fenArea.getText();
                int pistacho;
                if (Blancas.isSelected()) fen += " w ";
                else fen += " b ";


                if (CoM){
                    fen += "- - 0 1";
                    pistacho = m.validaf(fen,TemaArea.getText(),P.getKey());
                }
                else {
                    fen += "- - 0 1";
                    pistacho = m.NouProb(fen, TemaArea.getText());
                }
                if (pistacho != 0) JOptionPane.showMessageDialog(null, "Fen Erroneo o Tema Imposible");
                else {
                    JOptionPane.showMessageDialog(null, "Problema Correctamente Agregado.");
                    m.limpia();
                    m.changeState(3);

                }
            }
        });
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.limpia();
                m.changeState(3);
            }
        });
    }

    private void pintaFicha(){
        aux = "";
        switch (ficha) {
            case -1:
                aux = " " ;
                break;
            case 0:
                aux = "\u265F";
                break;
            case 1:
                aux = "\u265E";
                break;
            case 2:
                aux = "\u265D";
                break;
            case 3:
                aux  = "\u265C";
                break;
            case 4:
                aux = "\u265B";
                break;
            case 5:
                aux = "\u265A";
                break;
            case 6:
                aux = "\u2659";
                break;
            case 7:
                aux = "\u2658";
                break;
            case 8:
                aux = "\u2657";
                break;
            case 9:
                aux = "\u2656";
                break;
            case 10:
                aux = "\u2655";
                break;
            case 11:
                aux = "\u2654";
                break;
            default:
                aux = " ";
        }
    }

    private String traduce(String piece) {
        if (piece.equals("\u2654")) return "K";
        else if (piece.equals("\u2655")) return "Q";
        else if (piece.equals("\u2656")) return "R";
        else if (piece.equals("\u2657")) return "B";
        else if (piece.equals("\u2658")) return "N";
        else if (piece.equals("\u2659")) return "P";
        else if (piece.equals("\u265A")) return "k";
        else if (piece.equals("\u265B")) return "q";
        else if (piece.equals("\u265C")) return "r";
        else if (piece.equals("\u265D")) return "b";
        else if (piece.equals("\u265E")) return "n";
        else if (piece.equals("\u265F")) return "p";
        return " ";
    }

    private void updatefen(){
        Component[] Board = panel1.getComponents();
        int count = 0;
        String Fen = "";
        for (int i = 0; i < 8; ++i){

            for (int j = 0; j < 8; ++j) {

                Component c = Board[i*8 + j];
                JButton butt = (JButton) c;
                String piece = traduce(butt.getText());
                if (piece.equals(" ")) count += 1;
                else {
                    if (count != 0) Fen += String.valueOf(count); count = 0;
                    Fen += piece;
                }
            }
            if (count != 0) Fen += String.valueOf(count); count = 0;
            if (i != 7) Fen += "/";
        }
        fenArea.setText(Fen);
    }

    private void PintaPMod(){
        String Fen = P.getValue().substring(0,P.getValue().length()-10);

        int index = 0;
        Component[] c = panel1.getComponents();
        int i = 0;
        while(i<64){
            JButton auxi = (JButton)c[i];
            if (Fen.charAt(index)>='1' && Fen.charAt(index)<= '8') {
                int a = Integer.parseInt(String.valueOf(Fen.charAt(index)));
                i += a;
            }
            else {
                if (Fen.charAt(index)!='/') {
                    auxi.setText(traduce2(String.valueOf(Fen.charAt(index))));
                    ++i;
                }
            }
            ++index;
        }
    }

    private String traduce2 (String piece) {
        if (piece.equals("K")) return "\u2654";
        else if (piece.equals("Q")) return "\u2655";
        else if (piece.equals("R")) return "\u2656";
        else if (piece.equals("B")) return "\u2657";
        else if (piece.equals("N")) return "\u2658";
        else if (piece.equals("P")) return "\u2659";
        else if (piece.equals("k")) return "\u265A";
        else if (piece.equals("q")) return "\u265B";
        else if (piece.equals("r")) return "\u265C";
        else if (piece.equals("b")) return "\u265D";
        else if (piece.equals("n")) return "\u265E";
        else if (piece.equals("p")) return "\u265F";
        return " ";
    }
}
