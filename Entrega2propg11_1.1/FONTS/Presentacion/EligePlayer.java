package Presentacion;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EligePlayer {
    private JPanel panel1 ;
    private JButton atrasButton;
    private JRadioButton Human1;
    private JRadioButton cpuFacilRadioButton;
    private JRadioButton cpuMediaRadioButton;
    private JRadioButton cpuDificilRadioButton;
    private JRadioButton Humano2;
    private JRadioButton cpuFacilRadioButton1;
    private JRadioButton cpuMediaRadioButton1;
    private JRadioButton cpuDificilRadioButton1;
    private JButton Confirmar;

    EligePlayer(ControladorPresentacion m) {
        Dimension d = new Dimension(720,720);
        panel1.setPreferredSize(d);
        Human1.setSelected(true);
        Humano2.setSelected(true);

        Human1.setActionCommand("Humano");
        Humano2.setActionCommand("Humano");
        cpuFacilRadioButton.setActionCommand("IA1");
        cpuFacilRadioButton1.setActionCommand("IA1");
        cpuMediaRadioButton.setActionCommand("IA2");
        cpuMediaRadioButton1.setActionCommand("IA2");
        cpuDificilRadioButton.setActionCommand("IA3");
        cpuDificilRadioButton1.setActionCommand("IA3");

        ButtonGroup g1 = new ButtonGroup();
        g1.add(Human1);
        g1.add(cpuFacilRadioButton);
        g1.add(cpuMediaRadioButton);
        g1.add(cpuDificilRadioButton);

        ButtonGroup g2 = new ButtonGroup();
        g2.add(Humano2);
        g2.add(cpuFacilRadioButton1);
        g2.add(cpuMediaRadioButton1);
        g2.add(cpuDificilRadioButton1);

        Human1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(Human1.getModel(),true);
            }
        });
        cpuFacilRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(cpuFacilRadioButton.getModel(),true);

            }
        });
        cpuMediaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(cpuMediaRadioButton.getModel(),true);

            }
        });
        cpuDificilRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.setSelected(cpuDificilRadioButton.getModel(),true);

            }
        });
        Humano2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g2.setSelected(Humano2.getModel(),true);

            }
        });
        cpuFacilRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g2.setSelected(cpuFacilRadioButton1.getModel(),true);

            }
        });
        cpuMediaRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g2.setSelected(cpuMediaRadioButton1.getModel(),true);

            }
        });
        cpuDificilRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g2.setSelected(cpuDificilRadioButton1.getModel(),true);

            }
        });
         atrasButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                m.setPub(false);
                m.changeState(3);
             }
         });
        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.setPlayerOne(g1.getSelection().getActionCommand());
                m.setPlayerTwo(g2.getSelection().getActionCommand());
                m.setPlayWho(g1.getSelection().getActionCommand());
                if (m.getPlayerOne().equals("Humano") || m.getPlayerTwo().equals("Humano")) m.changeState(6);
                else m.changeState(22);
            }
        });
    }
    JPanel getPanel1(){
        return panel1;
    }

}
