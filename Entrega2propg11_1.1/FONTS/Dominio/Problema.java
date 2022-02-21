package Dominio;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

// Si el problema és public procedent de l'app Code User serà -1.
public class Problema {

    private int  Codi;
    private int Code_User;
    private int Tema;
    private int depth;
    private int width;
    private String Starter;
    private String dificulty;
    private String Fen;
    private int Error;

    /* PUBLIC CREATOR */

    public Problema(Joc J,String Fen,int tema) {
        Taulell t = new Taulell(Fen);
        int FenOk = t.fen_ok();
        Error = FenOk;
        if (FenOk == 0) {
            depth = 2 * tema;
            String valid = checkValid(Fen);
            if (!valid.equals("Wrong")) {
                Code_User = 0;
                dificulty = valid;

                this.Fen = Fen;
                if (this.Fen.charAt(Fen.length() - 9) == 'w') setStarter("blanca");
                else setStarter("negra");
                J.afegeixPub(this);
                //Codi = J.getPublicsSize()-1;
            }
        }
    }

    public Problema(int codi_p, int codi_u, int tem, int d, int w, String start, String dif, String f) {
        Codi = codi_p;
        Code_User = codi_u;
        Tema = tem;
        depth = d;
        width = w;
        Starter = start;
        dificulty = dif;
        Fen = f;
    }

    public Problema(Joc J, Player p, String Fen, String tema_prob) {
        int tema = Integer.parseInt(String.valueOf(tema_prob));
        depth = 2*tema;
        String valid = checkValid(Fen);

        if (!valid.equals("Wrong")) {
            Code_User = J.getCodi(p);
            Codi = J.getCodeProbNew(Code_User);
            dificulty = valid;
            this.Fen = Fen;
            if (this.Fen.charAt(Fen.length()-9) == 'w') setStarter("blanca");
            else setStarter("negra");
        }
        else {
            Codi = -1;
        }
    }

    int getDepth(){return depth;}

    int getWidth(){return width;}

    public void setCodi(int a){
        Codi = a;
    }

    public String getStarter() {
        return Starter;
    }

    public void setStarter(String starter) {
        Starter = starter;
    }

    public int getTema() {
        return Tema;
    }

    public String getDificulty() {
        return dificulty;
    }

    public int getCodi() {
        return Codi;
    }

    public String getFen() {
        return Fen;
    }

    public void minusCodi(){
        --Codi;
    }

    public String checkValid(String Fen) {
        Taulell Board = new Taulell(Fen);
        Error = Board.fen_ok();
        if (Error != 0) return "Wrong";
        char p1 = Fen.charAt(Fen.length()-9);
        String start, loser;
        if (p1 == 'w') {
            loser = "negra";
            start = "blanca";
        }
        else if (p1 == 'b'){
            loser = "blanca";
            start = "negra";
        }
        else return "Wrong";

        int aux_depth = depth;
        boolean mate_found;
        boolean mate = mate_found = exploreFen(Board, 1, loser, start);
        int aux_width = width;

        while (mate) {
            depth -= 2;
            width = 0;
            mate = exploreFen(Board, 1, loser, start);
            if (mate) {
                aux_depth = depth;
                aux_width = width;
            }
            else {
                depth = aux_depth;
                width = aux_width;
            }
        }

        if (!mate_found){
            return "Wrong";
        }
        else {
            if (width <= 4000) {
                Tema = depth/2;
                return "facil";

            }
            else if (width <= 150000) {
                Tema = depth/2;
                return "media";
            }
            else {
                Tema = depth/2;
                return "dificil";
            }
        }
    }

    public boolean exploreFen(Taulell Board, int p_depth, String loser, String start) {

        boolean mate = false;
        Ficha replacement;
        boolean explore;
        explore = p_depth < depth;
        if(explore) {
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {

                    int id = Board.get_id_ficha(i, j);
                    if (id != -1 && Board.get_color_ficha(id).equals(start)) {
                        ArrayList<Pair<Integer, Integer>> Movs = Board.get_ficha(id).todos_movimientos(Board);

                        for (int k = 0; k < Movs.size(); ++k) {

                            replacement = Board.mover(Board.get_ficha(id), Movs.get(k).getKey(), Movs.get(k).getValue());

                            if (!Board.jaque(start)) { // Si la jugada es válida, entra
                                if (Board.get_ficha(id).get_nom().equals("peo") && Board.to_promote(Board.get_ficha(id))) {

                                    // Nos guardamos el peon que vamos a promover para substituirlo una vez acabemos
                                    Ficha peon = Board.promover_peon(Board.get_ficha(id), "reina");

                                    mate = Board.mate(loser);

                                    if (mate) {
                                        Board.despromover(Board.get_ficha(id), peon);
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    } else if (p_depth + 1 < depth) {
                                        mate = checkLoser(Board, p_depth + 1, loser, start);
                                    }

                                    if (mate) {
                                        Board.despromover(Board.get_ficha(id), peon);
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    }

                                    Board.promover_peon(Board.get_ficha(id), "caball");
                                    mate = Board.mate(loser);

                                    if (mate) {

                                        Board.despromover(Board.get_ficha(id), peon);
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    } else if (p_depth + 1 < depth) {
                                        mate = checkLoser(Board, p_depth + 1, loser, start);
                                    }

                                    if (mate) {
                                        Board.despromover(Board.get_ficha(id), peon);
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    }
                                    Board.despromover(Board.get_ficha(id), peon);
                                }
                                else {
                                    mate = Board.mate(loser);
                                    if (mate) {
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    } else if (p_depth + 1 < depth) {
                                        mate = checkLoser(Board, p_depth + 1, loser, start);
                                    }

                                    if (mate) {
                                        Board.mover(Board.get_ficha(id), i, j);
                                        Board.resucitar(replacement);
                                        width += 1;
                                        return true;
                                    }
                                }
                            }
                            Board.mover(Board.get_ficha(id), i, j);
                            Board.resucitar(replacement);
                            width += 1;
                        }
                    }
                }
            }
            return mate;
        }
        return false;
    }

    public boolean checkLoser(Taulell Board, int p_depth, String loser, String start) {
        boolean mate = true;
        Ficha replacement = null;

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                int id = Board.get_id_ficha(i,j);

                if (id != -1 && Board.get_color_ficha(id).equals(loser)) {

                    ArrayList<Pair<Integer, Integer>> Movs = Board.get_ficha(id).todos_movimientos(Board);

                    for (int k = 0; k < Movs.size(); ++k) {

                        replacement = Board.mover(Board.get_ficha(id),  Movs.get(k).getKey(),  Movs.get(k).getValue());

                        if (!Board.jaque(loser)) { /* Al mover me quedo en Jaque? */
                            if (Board.get_ficha(id).get_nom().equals("peo") && Board.to_promote(Board.get_ficha(id))) {

                                Ficha peon = Board.promover_peon(Board.get_ficha(id), "reina");

                                if (mate) mate = exploreFen(Board, p_depth + 1, loser, start);

                                if (!mate) {
                                    Board.despromover(Board.get_ficha(id), peon);
                                    Board.mover(Board.get_ficha(id), i, j);
                                    Board.resucitar(replacement);
                                    width += 1;
                                    return false;
                                }
                                Board.promover_peon(Board.get_ficha(id), "caball");

                                if (mate) mate = exploreFen(Board, p_depth + 1, loser, start);

                                if (!mate) {
                                    Board.despromover(Board.get_ficha(id), peon);
                                    Board.mover(Board.get_ficha(id), i, j);
                                    Board.resucitar(replacement);
                                    width += 1;
                                    return false;
                                }
                                Board.despromover(Board.get_ficha(id), peon);
                            }
                            else {
                                mate = exploreFen(Board, p_depth + 1, loser, start);
                                if (!mate) {
                                    Board.mover(Board.get_ficha(id), i, j);
                                    Board.resucitar(replacement);
                                    width += 1;
                                    return false;
                                }
                            }
                        }
                        Board.mover(Board.get_ficha(id), i, j);
                        Board.resucitar(replacement);
                        width += 1;
                    }
                }
            }
        }
        return mate;
    }
    public int getError() {
        return Error;
    }

}