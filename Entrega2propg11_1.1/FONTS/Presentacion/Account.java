package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Account {
    private JPanel panel1;
    private JButton jugarPartidaButton;
    private JButton crearProblemaButton;
    private JButton editarProblemaButton;
    private JButton eliminarProblemaButton;
    private JButton editarUsuarioButton;
    private JButton atrasButton;
    private JButton compartirProblemaButton;


    Account(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);
        jugarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(4);
            }
        });
        crearProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(10);
            }
        });
        editarProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m.changeState(20);
            }
        });
        eliminarProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(15);
            }
        });
        editarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(11);
            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(0);
            }
        });
        compartirProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(14);
            }
        });
    }
    public JPanel getpanel1(){
        return this.panel1;
    }

}
