package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceLogin {
    private JPanel panel1;
    private JButton registrarseButton;
    private JButton logInButton;


    InterfaceLogin(ControladorPresentacion m) {

        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(2);
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(1);
            }
        });
    }
    JPanel getpanel1(){
        return panel1;
    };
}
