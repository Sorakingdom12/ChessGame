package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarPsw extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton validarButton;
    private JButton atrasButton;

    public CambiarPsw(ControladorPresentacion m) {
        getContentPane().add(panel1);


        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(11);
            }
        });

        validarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (m.validPass(textField1.getText()) == 0) {
                    m.actPass(textField1.getText());
                    m.setPass(textField1.getText());
                    m.changeState(11);
                }else {
                    m.usage(1+m.validPass(textField1.getText()));
                }
            }
        });
    }



    public JPanel getPanel1() {
        return panel1;
    }
}
