package Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.net.URL;


public class App extends JFrame {
    private  JPanel panel1;
    public  Image imagenfondo, imagenfondo2;
    public  URL fondo,fondo2;
    public App() {
        /*
        atrásButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "holi");
            }
        });*/
        this.setBounds(0,0,1080,800);
        this.setTitle("ventana");

        this.setLocationRelativeTo(null);

        fondo = this.getClass().getResource("/Presentacion/inicio.png");
        imagenfondo = new ImageIcon(fondo).getImage();

        Container contenedor = getContentPane();

        JButton bo = new JButton();
        Dimension dim = new Dimension(200, 200);
        bo.setMaximumSize(dim);
        bo.setMinimumSize(dim);
        bo.setPreferredSize(dim);
        bo.setSize(dim);
        bo.setLayout(null);
        bo.setText("imagenfondo");
        contenedor.add(bo);
        contenedor.add(panel);
        this.setVisible(true);

    }
    public JPanel panel = new JPanel(){

        public void paintComponent(Graphics g){
            g.drawImage(imagenfondo, 0,0, getWidth(), getHeight(), this);
            //TitledBorder t1;

            //Font fuente = new Font("Arial Black",1,40);
            //t1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "title",TitledBorder.CENTER,TitledBorder.ABOVE_TOP,fuente);
            //t1.getTitleJustification(TitledBorder.CENTER);
            //panel.setBorder(BorderFactory.createLineBorder(Color.black));
            //panel.setBorder(t1);
            //ImageIcon icon = new ImageIcon("http://www.gifsanimados.org/data/media/613/ajedrez-imagen-animada-0044.gif");
            //ImageIcon icon2 = new ImageIcon("/Presentacion/PeonN.png");
            fondo2 = this.getClass().getResource("/Presentacion/gif4.gif");
            //imagenfondo2 = new ImageIcon(fondo).getImage();
            panel.setBorder(BorderFactory.createMatteBorder(-1,-1, -1,-1,new ImageIcon(fondo2)));

            panel.setBorder(BorderFactory.createLineBorder(Color.cyan, 20));
            /*
            Border compound1,comp2;
            comp2 = BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder());
            compound1 = BorderFactory.createLineBorder("title",TitledBorder.CENTER,TitledBorder.ABOVE_TOP);*/
        }
    };

    public static void main (String[] args) {
        App imagen = new App();
/*
        JFrame frame = new JFrame("App");
        //frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //agregar el panel a la ventana principal;
        frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        */
    }

}
