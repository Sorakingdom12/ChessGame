package Presentacion;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemaDif {
    private JPanel panel1;
    private JTextField textField1;
    private JButton atrasButton;
    private JRadioButton facilRadioButton;
    private JRadioButton mediaRadioButton;
    private JRadioButton dificilRadioButton;
    private JButton confirmarButton;
    private JRadioButton blancasRadioButton;
    private JRadioButton negrasRadioButton;

    public TemaDif(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);
        facilRadioButton.setSelected(true);
        ButtonGroup g1 = new ButtonGroup();
        g1.add(facilRadioButton);
        g1.add(mediaRadioButton);
        g1.add(dificilRadioButton);

        facilRadioButton.setActionCommand("facil");
        mediaRadioButton.setActionCommand("media");
        dificilRadioButton.setActionCommand("dificil");


        blancasRadioButton.setSelected(true);
        ButtonGroup g2 = new ButtonGroup();
        g2.add(blancasRadioButton);
        g2.add(negrasRadioButton);

        blancasRadioButton.setActionCommand("blanco");
        negrasRadioButton.setActionCommand("negro");



        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPub(false);
                m.setPlayerTwo("Humano");
                m.setPlayerTwo("Humano");
                m.setPartidas_skynet(0);
                m.changeState(3);
            }
        });

        facilRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(facilRadioButton.getModel(),true);
            }
        });
        mediaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(mediaRadioButton.getModel(),true);
            }
        });
        dificilRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(dificilRadioButton.getModel(),true);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m.setDif(g1.getSelection().getActionCommand());
                m.setTema(Integer.parseInt(String.valueOf(textField1.getText())));
                m.setEndgame(2*m.getTema()-1);
                m.setCol(g2.getSelection().getActionCommand());
                if (m.getInfoProb(m.getPub(),m.getTema(),m.getCol(),m.getDif()).size() == 0){
                    JOptionPane.showMessageDialog(null,"No existen problemas con estas características");
                }
                else m.changeState(7);
            }
        });
    }
    JPanel getMainPanel(){
        return panel1;
    }
}
