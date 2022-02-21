package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarUsn extends JFrame{
    private JTextField textField1;
    private JPanel panel1;
    private JButton validarButton;
    private JButton atrasButton;

    public CambiarUsn(ControladorPresentacion  m) {
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
                if (m.validUser(textField1.getText())) {
                    m.actUser (textField1.getText());
                    m.setUser(textField1.getText());
                    m.changeState(11);
                }
                else{
                    JOptionPane.showMessageDialog( null,"El nombre no es válido");
                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
