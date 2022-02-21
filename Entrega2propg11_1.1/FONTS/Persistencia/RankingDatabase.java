package Persistencia;

import java.io.*;
import java.util.ArrayList;

public class RankingDatabase extends Database{
    private File file;

    public RankingDatabase(String nom) {
        super(nom);
    }

    public void crearDatabase() {
        file = new File(nom);

        if (!file.exists()) {
            try {
                file = new File(nom);
                file.createNewFile();

                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Rankings");
                bw.flush();
                bw.close();

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                line = br.readLine();
                br.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //infoRank[0] = codiR
    //infoRank[1] = scoreboard.integer
    //inforRank[2] = scoreboard.double
    public void guardarRankings (ArrayList<ArrayList<String>> infoRank) {
        try {
            ArrayList<ArrayList<String>> rankings = getRankings();
            boolean found = false;
            int codeR = Integer.parseInt(infoRank.get(0).get(0));
            for (int i = 0; i < rankings.size() && !found; ++i) {
                int codeAux = Integer.parseInt(rankings.get(i).get(0));
                if (codeAux == codeR) {
                    found = true;
                    boolean full = true;
                    while (full) {
                        if (i < rankings.size())rankings.remove(i);
                        else full = false;
                        if (i < rankings.size() && !rankings.get(i).get(0).equals(infoRank.get(0).get(0))) full = false;
                    }
                    for (ArrayList<String> l : infoRank) {
                        rankings.add(i, l);
                        ++i;
                    }
                }
                else if (codeAux > codeR) {
                    found = true;
                    for (ArrayList<String> l : infoRank) {
                        rankings.add(i, l);
                        ++i;
                    }
                }
            }
            if (!found) {
                for (ArrayList<String> l : infoRank) {
                    rankings.add(l);
                }
            }
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.write("");
            buffer.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("Rankings");

            for (ArrayList<String> p : rankings) {
                bw.newLine();
                bw.write(p.get(0) + " " + p.get(1) + " " + p.get(2));
                bw.flush();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<ArrayList<String>> getRankings() {
        ArrayList<ArrayList<String>> rankings = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); //Leemos el titulo del fichero
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> rank = new ArrayList<String>();
                String[] llsplit = line.split(" ");
                for (int i=0; i<3; i++) {
                    rank.add(llsplit[i]);
                }
                rankings.add(rank);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rankings;
    }
}
