package Persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProblemaDatabase extends Database{
    private File file;

    public ProblemaDatabase(String nom) {
        super(nom);
    }

    public File getPartidaDatabaseFile() {
        return file;
    }

    public void crearDatabase() {
        file = new File(nom);

        if (!file.exists()) {
            try {
                file = new File(nom);
                file.createNewFile();

                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Problemas");
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

    //Se reescribe el fichero entero porque así los problemas estarán en orden en el fichero
    public void guardaProblemas(ArrayList<ArrayList<String>> infoProb) {
        //Se pasa una nueva matriz de problemas, con el problema nuevo añadido, y se reescribe el fichero
        //infoProb[0] = codi_user
        //infoProb[1] = codi_prob
        //infoProb[2] = tema
        //infoProb[3] = depth
        //infoProb[4] = width
        //infoProb[5] = Starter
        //infoProb[6] = dificulty
        //infoProb[7] = Fen

        try {
            BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
            buffer.write("");
            buffer.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("Problemas");

            for (ArrayList<String> p : infoProb) {
                bw.newLine();
                bw.write(p.get(0) + " " + p.get(1) + " " + p.get(2) + " " + p.get(3) + " " + p.get(4) + " " + p.get(5) + " " + p.get(6) + " " + p.get(7));
                bw.flush();
            }
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<ArrayList<String> > getProblemas() {
        ArrayList< ArrayList<String> > p = new ArrayList< ArrayList<String> >();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); //Leemos el titulo del fichero
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> problema = new ArrayList<String>();
                String[] llsplit = line.split(" ");
                for (int i=0; i<13; i++) {
                    problema.add(llsplit[i]);
                }
                p.add(problema);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

}
