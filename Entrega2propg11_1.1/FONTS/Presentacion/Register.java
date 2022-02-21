package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JPasswordField passwordField1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton registraButton;
    private JButton atrasButton;
    private boolean ver = false;

    private String Name;
    private String Contrasenya = "";

    public Register(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);
        registraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Falta mandar las cosas a comprovar i
                // devolver mensaje de error si mal volver a 0 si bien
                Name = textField1.getText();

                char[] aux = passwordField1.getPassword();
                for(char a: aux) Contrasenya += a;

                if(m.CheckReg(Name,Contrasenya)){
                    m.Reg(Name,Contrasenya);
                    m.changeState(0);
                    return;
                }
                Contrasenya = "";
                m.usage(1);
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
