package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemaDifElim {
    private JPanel panel1;
    private JTextField textField1;
    private JButton atrasButton;
    private JRadioButton facilRadioButton;
    private JRadioButton mediaRadioButton;
    private JRadioButton dificilRadioButton;
    private JButton eliminarButton;
    private JRadioButton blancasRadioButton;
    private JRadioButton negrasRadioButton;

    JPanel getMainPanel(){
        return panel1;
    }

    public TemaDifElim(ControladorPresentacion m) {
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
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m.setDif(g1.getSelection().getActionCommand());
                m.setTema(Integer.parseInt(String.valueOf(textField1.getText())));
                m.setCol(g2.getSelection().getActionCommand());
                if (m.getInfoProb(m.getPub(),m.getTema(),m.getCol(),m.getDif()).size() == 0){
                    JOptionPane.showMessageDialog(null,"No existen problemas con estas características");
                }
                else {
                    m.changeState(17);
                }
            }
        });
    }
}

