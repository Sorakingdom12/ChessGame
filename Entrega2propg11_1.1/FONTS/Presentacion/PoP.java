package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoP {
    private JButton pub;
    private JButton priv;
    private JButton back;
    private JPanel MainPanel;
    private JButton guardadosButton;

    public PoP(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        MainPanel.setPreferredSize(d);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(3);
            }
        });
        priv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPub(false);
                m.changeState(5);
            }
        });
        pub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPub(true);
                m.changeState(5);
            }
        });
        guardadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.changeState(18);
            }
        });
    }
    JPanel getMainPanel(){
        return MainPanel;
    }
}
