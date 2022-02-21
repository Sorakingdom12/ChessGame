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

public class JugadorDatabase extends Database{
    private File file;

    public JugadorDatabase(String nom) {
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
                bw.write("Jugadores");
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

    public void guardaJugador(ArrayList<String> infoJug) {
        //infoJug[0] = id  infoJug[1] = username  infoJug[2] = password
        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(file,true); //true es para activar el append para que no sobreescriba lo que había
            bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(infoJug.get(0) + " " + infoJug.get(1) + " " + infoJug.get(2));
            //nueva línea
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarJugador(ArrayList<String> infoJug) {
        //infoJug[0] = id  infoJug[1] = username  infoJug[2] = password
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); // leemos el titulo del fichero
            String line = "";
            int mark = 1;
            boolean found = false;
            while (((line = br.readLine()) != null) && !found) {
                String[] llsplit = line.split("\\s+");
                if (llsplit[0].equals(infoJug.get(0))) {
                    found = true;
                }
                mark++;
            }

            File temp = new File("temp.txt");
            FileWriter fw2 = new FileWriter(temp);
            BufferedWriter bw = new BufferedWriter(fw2);

            BufferedReader buff = new BufferedReader(new FileReader(file));

            //Escribimos hasta la línea donde se encuentra el jugador a modificar
            int lineReaded = 1;
            while ((line = buff.readLine()) != null) {
                if (lineReaded != mark) {
                    bw.write(line);
                    bw.newLine();
                }
                lineReaded++;
            }

            //Escribimos al jugador con la información modificada
            bw.write(infoJug.get(0) + " " + infoJug.get(1) + " " + infoJug.get(2));

            bw.close();
            buff.close();
            fw2.close();

            //renombramos el fichero temp al nombre del fichero original. El fichero original se reescribirá
            //con la información que hay en temp
            temp.renameTo(file);

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String> > getJugadores() {
        ArrayList< ArrayList<String> > j = new ArrayList< ArrayList<String> >();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); //Leemos el titulo del fichero
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> jugador = new ArrayList<String>();
                String[] llsplit = line.split(" ");
                for (int i=0; i<3; i++) {
                    jugador.add(llsplit[i]);
                }
                j.add(jugador);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return j;
    }
}
