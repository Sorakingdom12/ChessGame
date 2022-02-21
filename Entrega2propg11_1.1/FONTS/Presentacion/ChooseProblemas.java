package Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseProblemas {
    private JPanel panel1;
    private JButton Backy;
    private JSpinner battles;
    private JButton Accepty;
    private int battleAmount;
    private ControladorPresentacion m;

    public JPanel getPanel1() {
        return panel1;
    }

    ChooseProblemas(ControladorPresentacion m) {
        this.m = m;

        Accepty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battleAmount = (Integer) battles.getValue();
                if (battleAmount > 0) {
                    m.setPartidas_skynet(battleAmount);

                    m.changeState(6);
                }
                else JOptionPane.showMessageDialog(null, "Un valor negativo o nulo no es válido.");
            }
        });
        Backy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(3);
            }
        });
    }
}
