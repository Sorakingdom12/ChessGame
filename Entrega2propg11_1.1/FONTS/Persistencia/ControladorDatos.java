package Persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControladorDatos {
    public ProblemaDatabase probDB;
    public JugadorDatabase jugDB;
    public RankingDatabase rankDB;
    public PartidaDatabase partDB;

    public ControladorDatos() {
        probDB = new ProblemaDatabase("Problemas.txt");
        probDB.crearDatabase();

        jugDB = new JugadorDatabase("Jugadores.txt");
        jugDB.crearDatabase();

        rankDB = new RankingDatabase("Rankings.txt");
        rankDB.crearDatabase();

        partDB = new PartidaDatabase("Partidas.txt");
        partDB.crearDatabase();

    }

    // Guarda en la DB de jugadores toda la info del jugador pasada por parámetros en el vector infoJug
    public void guardaJugador(ArrayList<String> infoJug) {
        jugDB.guardaJugador(infoJug);
    }

    //Actualiza el jugador con idJugador info[3] con la información que se le pasa por parámetros
    public void actualizarJugador(ArrayList<String> infoJug) {
        jugDB.actualizarJugador(infoJug);
    }

    //Devuelve en una matriz toda la info de todos los jugadores del juego registrados hasta el momento. Cada
    // fila es la info de cada jugador, idJugador, username y password
    public ArrayList< ArrayList<String> > getJugadores() {
        return jugDB.getJugadores();
    }

    //Se guarda la nueva matriz de problemas en el fichero
    public void guardaProblemas(ArrayList<ArrayList<String>> infoProb) {
        probDB.guardaProblemas(infoProb);
    }

    //Devuelve todos los problemas guardados en el fichero (base de datos)
    public ArrayList<ArrayList<String>> getProblemas() {
        return probDB.getProblemas();
    }

    //Reescribe el fichero de rankings
    public void guardarRankings (ArrayList<ArrayList<String>> infoRank) {
        rankDB.guardarRankings(infoRank);
    }

    //Devuelve todos los rankings guardados en el fichero (base de datos)
    public ArrayList<ArrayList<String>> getRankings() {
        return rankDB.getRankings();
    }

    //Guarda una determinada partida en el fichero
    public void guardarPartida(ArrayList<String> infoPart) {
        partDB.guardarPartida(infoPart);
    }

    //Devuelve todos los parámetros de la partida con codi_user = indices[0] y codi_prob = indices[1]
    public ArrayList<String> getPartida(ArrayList<String> indices) {
        return partDB.getPartida(indices);
    }

    public ArrayList<ArrayList<String>> getPartidas(String codi) {
        return partDB.getPartidas(codi);
    }

    public void eliminarPartida(ArrayList<String> a) {
        partDB.eliminarPartida(a);
    }
}
