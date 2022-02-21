package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton iniciaSesionButton;
    private JButton atrasButton;

    private String nom;
    private String pass = "";

    Login(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);
        iniciaSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass = "";
                nom = textField1.getText();
                char[] aux = passwordField1.getPassword();
                for(char a: aux) pass += a;

                if (!nom.isEmpty() && !pass.isEmpty()){
                    if (m.Check(nom,pass)){
                        m.log(nom);
                        m.setUser(nom);
                        m.setPass(pass);
                        m.changeState(3);
                    }
                    else {
                        m.usage(1);
                    }
                }
            }
        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(0);
            }
        });
    }
    public JPanel getPanel1() {
        return panel1;
    }
}
