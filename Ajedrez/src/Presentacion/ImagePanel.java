package Presentacion;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class ImagePanel extends JPanel {

    private Image fondo;

    public ImagePanel() {
        fondo = new ImageIcon("Presentacion/ini.png").getImage();
    }

    private void initComponents(){}
    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

}