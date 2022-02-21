package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class edituser extends JFrame{
    private JButton cambiarUsuarioButton;
    private JButton cambiarContrasenaButton;
    private JPanel panel1;
    private JLabel Usn;
    private JLabel Pass;
    private JButton atrasButton;

    public edituser(ControladorPresentacion m) {
        Usn.setText(m.getUser());
        Pass.setText(m.getPass());

        getContentPane().add(panel1);

        cambiarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(12);
            }
        });
        cambiarContrasenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(13);
            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(3);
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
