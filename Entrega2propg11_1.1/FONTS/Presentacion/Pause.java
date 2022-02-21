package Presentacion;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.xml.bind.annotation.XmlType;
import java.net.URL;

public class Pause extends JFrame {
    private JPanel pausa;

    JPanel getpausa(){
        return pausa;
    }


    Pause(ControladorPresentacion m, Timer time){
        URL urli = this.getClass().getResource("/Presentacion/Piezas/ezgif.com-resize.gif");
        Dimension d = new Dimension(900, 1000);
        pausa = new JPanel();
        pausa.setPreferredSize(d);
        JLabel l = new JLabel(new ImageIcon(urli));
        l.setBounds(50,0,850,850);
        pausa.add(l);
        pausa.setLayout(null);
        JButton b1 = new JButton("Continuar");
        b1.setBounds(375,800,200,50);

        pausa.add(b1);
        getContentPane().add(pausa);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                time.start();
                m.changeState(8);
            }
        });
    }

}
