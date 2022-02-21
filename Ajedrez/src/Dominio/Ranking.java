package Dominio;

import java.util.ArrayList;
import java.lang.*;
import java.util.Collections;
import java.util.Comparator;

import javafx.util.Pair;

public class Ranking {
    private int Codi_R;
    private ArrayList<Pair<Integer,Double>> Scoreboard;

    Ranking(int code){
        Codi_R = code;
        Scoreboard = new ArrayList<Pair<Integer, Double>>();
    }


    public void Update_Scoreboard (int Player, double score) {
        boolean found = false;
        boolean change = false;
        for (int i = 0; !found && i < Scoreboard.size(); ++i) {
            if (Scoreboard.get(i).getKey() == Player) {
                if (Scoreboard.get(i).getValue() < score) {
                    Scoreboard.set(i, new Pair<>(Player, score));
                    change = true;
                }
                found = true;
            }
        }
        if (!found) {
            Scoreboard.add(new Pair<> (Player, score));
            change = true;
        }
        if (change) order_Scoreboard();
    }

    public void order_Scoreboard() {
        Collections.sort(Scoreboard, new Comparator<Pair <Integer,Double> >() {
            @Override
            public int compare(Pair<Integer, Double> p1, Pair<Integer, Double> p2) {
                return p2.getValue().compareTo(p1.getValue());
            }
        });
    }

    /*public void Show_Scoreboard () {
        System.out.println("Dominio.Ranking: " + Codi_R);
        for (int i = 0; i < Scoreboard.size(); ++i) {
            System.out.println(" - User ID: " + Scoreboard.get(i).getKey() + ", Score: " + Scoreboard.get(i).getValue());
        }
    }*/

    public Pair<ArrayList<Pair<Integer, Double>>, Integer> check_Score (int p) {
        int pos = -1;
        for (int i = 0; i < Scoreboard.size(); ++i) {
            if (Scoreboard.get(i).getKey() == p) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            return new Pair<ArrayList<Pair<Integer, Double>>, Integer> (Scoreboard, pos);
        }
        else {
            return null;
            //System.out.println("Player " + p + " is not on this ranking. Complete the problem and try again.");
        }
    }
}