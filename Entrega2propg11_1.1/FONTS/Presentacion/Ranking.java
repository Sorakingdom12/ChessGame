package Presentacion;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ranking {
    private JPanel panel1;
    private JTextField puntitos;
    private JButton Backy;
    private JTextArea tablaPuntitos;
    private JScrollPane scrollBar;
    private JTextField puntos_this_time;

    JPanel getPanel1() { return panel1; }

    Ranking(ControladorPresentacion m) {
        puntitos.setEditable(false);
        tablaPuntitos.setEditable(false);
        puntos_this_time.setEditable(false);

        Backy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { m.changeState(3); }
        });

        Pair<Integer, ArrayList<ArrayList<String>>> ranking = m.getRanking();
        ArrayList<ArrayList<String>> score = ranking.getValue();
        String codeU = String.valueOf(ranking.getKey());
        StringBuilder puntos = new StringBuilder();
        for (ArrayList<String> line : score) {
            if (line.get(1).equals(codeU))
                puntitos.setText("Puntuación: " + line.get(2));
            puntos.append("ID-Usuario: ");
            puntos.append(line.get(1));
            puntos.append(" - Puntuación: ");
            puntos.append(line.get(2));
            puntos.append("\n");
        }

        tablaPuntitos.setText(String.valueOf(puntos));
        puntos_this_time.setText("Puntuación: " + m.getPuntitos());

    }
}
