package Persistencia;

//import sun.plugin2.gluegen.runtime.BufferFactory;

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

public class PartidaDatabase extends Database{
    private File file;

    public PartidaDatabase(String nom) {
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
                bw.write("Partidas");
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


    //FALTA ACTUALIZAR PARTIDA, POR SI HACEMOS DOS PAUSAS, EN EL FICHERO SOLO NOS INTERESA LA ULTIMA PAUSA



    //infoPart[0] = codi_user
    //infoPart[1] = codi_prob
    //infoPart[2] = tipo (publico o privado)
    //infoPart[3] = jugador1
    //infoPart[4] = jugador2
    //infoPart[5] = quien_juega
    //infoPart[6] = Fen_actual (solamente parte tablero)
    //infoPart[7] = timer (segundos)
    //infoPart[8] = mov_ejec_jug1
    //infoPart[9] = mov_ejec_jug2
    public void guardarPartida(ArrayList<String> infoPart) {
        //primero buscamos si ya hay alguna partida con los mismos codi_user, codi_prob y tipo
        //si ya hay alguna, se reeemplaza la partida
        //si no hay, se escribe al final

        FileWriter fw;
        BufferedWriter bw;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); // leemos el titulo del fichero
            String line = "";
            int mark = 1;
            boolean found = false;
            while (((line = br.readLine()) != null) && !found) {
                String[] llsplit = line.split("\\s+");
                if (llsplit[0].equals(infoPart.get(0)) && llsplit[1].equals(infoPart.get(1)) && llsplit[2].equals(infoPart.get(2))) {
                    found = true;
                }
                mark++;
            }

            if (found) {
                File temp = new File("temp.txt");
                FileWriter fw2 = new FileWriter(temp);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                BufferedReader buff = new BufferedReader(new FileReader(file));
                int lineReaded = 1;
                while ((line = buff.readLine()) != null) {
                    if (lineReaded != mark) {
                        bw2.write(line);
                        bw2.newLine();
                    }
                    lineReaded++;
                }

                for(String s: infoPart) {
                    bw2.write(s + " ");
                }

                bw2.close();
                buff.close();
                fw2.close();

                //renombramos el fichero temp al nombre del fichero original. El fichero original se reescribirá
                //con la información que hay en temp
                temp.renameTo(file);

                br.close();

            }
            else {
                fw = new FileWriter(file, true); //append = false, para que se sobreescriba el fichero
                bw = new BufferedWriter(fw);
                bw.newLine();
                for(String s: infoPart) {
                    bw.write(s + " ");
                }
                //nueva línea
                bw.flush();
                bw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //indice[0] = codi_user
    //indice[1] = codi_prob
    //si no hay ninguna partida con estos indices en el fichero, no se inicializara infoPart, así que será null
    // (comprobación que se tiene que hacer en dominio)
    public ArrayList<String> getPartida(ArrayList<String> indices) {
        ArrayList<String>  infoPart= new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); // leemos el titulo del fichero
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] llsplit = line.split("\\s+");
                if (llsplit[0].equals(indices.get(0)) && llsplit[1].equals(indices.get(1))) {
                    for(String s: llsplit){
                        infoPart.add(s);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return infoPart;
    }

    public ArrayList<ArrayList<String>> getPartidas(String codi) {
        ArrayList<ArrayList<String>> partidas = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); //Leemos el titulo del fichero
            String line = "";
            while ((line = br.readLine()) != null) {
                ArrayList<String> part = new ArrayList<String>();
                String[] llsplit = line.split(" ");
                if (llsplit[0].equals(codi)) {
                    for (String s: llsplit) {
                        part.add(s);
                    }
                    partidas.add(part);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return partidas;
    }

    public void eliminarPartida(ArrayList<String> infoPart) {
        ArrayList<ArrayList<String>> infoConservar = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            br.readLine(); //leemos el titulo del fichero
            String line = "";
            int mark = 1;
            boolean found = false;
            while (((line = br.readLine()) != null) && !found) {
                String[] llsplit = line.split("\\s+");
                if (llsplit[0].equals(infoPart.get(0)) && llsplit[1].equals(infoPart.get(1))&& llsplit[2].equals(infoPart.get(2))) {
                    found = true;
                }
                else {
                    ArrayList<String> infoP = new ArrayList<>();
                    for (String s: llsplit) {
                        infoP.add(s);
                    }
                    infoConservar.add(infoP);
                }
                mark++;
            }
            if (found) {
                BufferedWriter buffer = new BufferedWriter(new FileWriter(file));
                buffer.write("");
                buffer.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("Partidas");
                for (ArrayList<String> p: infoConservar) {
                    bw.newLine();
                    for (String s: p) {
                        bw.write(s+" ");
                    }
                    bw.flush();
                }
                bw.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
