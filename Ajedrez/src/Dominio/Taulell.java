package Dominio;

import java.util.ArrayList;

import javafx.util.Pair;

public class Taulell {

    //ini

    //vector de todas las fichas en juego de los dos colores
    private  ArrayList<Ficha> Blanca;
    private  ArrayList<Ficha> Negra;


    //matriz tablero
    private  int [][] Tablero;

    //estado actual de la partida
    private String Fen_act;

    /* quien está jugando. si es turno = 0 -> empiezan blancas, si turno = 1 -> empiezan negras */
    private  int turno = -1;

    /* quien empieza jugando. si es turno = 0 -> empiezan blancas, si turno = 1 -> empiezan negras */
    /* no varía mientras se juega el problema, viene dado por el fen */
    private int empieza_jugando = -1;

    /*posición del rey en cada jugada*/
    private int posx_rey_blanco;
    private int posy_rey_blanco;

    private int posx_rey_negro;
    private int posy_rey_negro;


//funciones

    Taulell (String Fen) {
        this.Tablero = new int[8][8];

        for (int a = 0; a < Tablero.length;++a){
            for (int b = 0; b < Tablero[a].length; ++b) {
                Tablero[a][b] = -2;
            }
        }

        this.Blanca = new ArrayList<Ficha>();
        this.Negra = new ArrayList<Ficha>();
        this.Fen_act = Fen;
    }

    public  int [][] get_tablero() {
        return Tablero;
    }

    /* Devuelve si el fen es correcto */
    public int fen_ok() {
       return fen_to_mat();
    }

    /* Imprime el tablero por pantalla */
    public  char[][] mat_char(){
        char[][] matchar = new char[8][8];

        for (int a = 0; a < Tablero.length;++a){
            for (int b = 0; b < Tablero[a].length; ++b) {
                if (Tablero[a][b] != -1){
                    matchar[a][b] = get_clau_ficha(Tablero[a][b]);
                }
                else matchar[a][b] = ' ';
            }
        }
        return matchar;
    }

    /* Transforma el fen (fen -> mat): si el fen es correcto lo transforma, si el fen es incorrecto devuelve el coódigo correspondiente al fallo */
    public int fen_to_mat(){

        /*i son las filas de la matriz y la j son las columnas*/
        int i = 0;
        int j = 0;

        /* la posicion del vector de fichas*/
        int auxn = 0;
        int auxb = 0;

        /* separar la lectura de lo que son las fichas del tablero a quien empieza del fen*/
        boolean fin_fichas = false;

        /* variables de errores */
        boolean fallo_fin = false;
        int num_peones_n = 0, num_peones_b = 0;
        boolean fallo_muchas_f = false;
        boolean fallo_pocas_f = false;
        boolean fallo_muchas_c = false;
        boolean fallo_pocas_c = false;
        int rey_blanco_id = 0, rey_negro_id = 0;
        boolean rey_blanco = false, rey_negro = false;

        /* leer cada letra,numero,- del fen y pasarlo a una matriz, */
        int l = Fen_act.length();
        if (l == 0) return 16;
        for (int k = 0; k < l ; k++) {

            /* leer las fichas */
            if ( !fin_fichas ) {

                if (Fen_act.charAt(k) != '/') {

                    /* mirar si la columna que pondremos supera el limite */
                    if (j < 8 && Fen_act.charAt(k) == ' ') return 16;

                    if (j == 8 && Fen_act.charAt(k) != ' ') {
                        fallo_muchas_c = true;
                        break;
                    }

                    /* lectura de una torre negra*/
                    if (Fen_act.charAt(k) == 'r') {
                        Negra.add(new Torre(auxn + 0, "torre", "negra", '♜' /*r*/, i, j, 5.5));
                        Tablero[i][j] = auxn;
                        ++j;
                        auxn++;
                    }
                    /* lectura de una torre blanca*/
                    else if (Fen_act.charAt(k) == 'R') {
                        Blanca.add(new Torre(auxb + 16, "torre", "blanca", '♖' /*R*/, i, j, 5.5));
                        Tablero[i][j] = auxb + 16;
                        ++j;
                        auxb++;
                    }
                    /* lectura de una rey negro*/
                    else if (Fen_act.charAt(k) == 'k') {
                        Negra.add(new Rey(auxn + 0, "rey", "negra",'♚' /*k*/, i, j, 10000));
                        if (!rey_negro) rey_negro = true;
                        else { return 12; }
                        rey_negro_id = auxn;
                        Tablero[i][j] = auxn;
                        posx_rey_negro = i;
                        posy_rey_negro = j;
                        ++j;
                        auxn++;
                    }
                    /* lectura de una rey blanco*/
                    else if (Fen_act.charAt(k) == 'K') {
                        Blanca.add(new Rey(auxb + 16, "rey", "blanca",'♔'/*K*/, i,j, 10000));
                        if (!rey_blanco) rey_blanco = true;
                        else{ return 13; }
                        rey_blanco_id = auxb + 16;
                        Tablero[i][j] = auxb + 16;
                        posx_rey_blanco = i;
                        posy_rey_blanco = j;
                        ++j;
                        auxb++;
                    }
                    /* lectura de una reina negra*/
                    else if (Fen_act.charAt(k) == 'q') {
                        Negra.add(new Reina(auxn + 0, "reina", "negra", '♛'/*'q'*/, i, j, 10));
                        Tablero[i][j] = auxn;
                        ++j;
                        auxn++;
                    }
                    /* lectura de una reina blanca*/
                    else if (Fen_act.charAt(k) == 'Q') {
                        Blanca.add(new Reina(auxb + 16, "reina", "blanca",'♕'/*'Q'*/, i, j, 10));
                        Tablero[i][j] = auxb + 16;
                        ++j;
                        auxb++;
                    }
                    /* lectura de una alfil negro*/
                    else if (Fen_act.charAt(k) == 'b') {
                        Negra.add(new Alfil(auxn + 0, "alfil", "negra", '♝'/*'b'*/, i, j, 3.5));
                        Tablero[i][j] = auxn;
                        ++j;
                        auxn++;
                    }
                    /* lectura de una alfil blanco*/
                    else if (Fen_act.charAt(k) == 'B') {
                        Blanca.add(new Alfil(auxb + 16, "alfil", "blanca",'♗'/*'B'*/, i, j, 3.5));
                        Tablero[i][j] = auxb + 16;
                        ++j;
                        auxb++;
                    }
                    /* lectura de una caballo negro*/
                    else if (Fen_act.charAt(k) == 'n') {
                        Negra.add(new Caball(auxn + 0, "caball", "negra",'♞'/*'n'*/, i, j, 3));
                        Tablero[i][j] = auxn;
                        ++j;
                        auxn++;
                    }
                    /* lectura de una caballo blanco*/
                    else if (Fen_act.charAt(k) == 'N') {
                        Blanca.add(new Caball(auxb + 16, "caball", "blanca",'♘'/*'N'*/, i, j, 3));
                        Tablero[i][j] = auxb + 16;
                        ++j;
                        auxb++;
                    }
                    /* lectura de una peon negro*/
                    else if (Fen_act.charAt(k) == 'p') {
                        Negra.add(new Peo(auxn + 0, "peo", "negra",'♟'/*'p'*/, i, j, 1));
                        if (!Negra.get(auxn).pos_ok(i,j,this)) return 10;
                        Tablero[i][j] = auxn;
                        ++j;
                        auxn++;
                        num_peones_n++;
                    }
                    /* lectura de una peon blanco*/
                    else if (Fen_act.charAt(k) == 'P') {
                        Blanca.add(new Peo(auxb + 16, "peo", "blanca",'♙'/*'P'*/, i, j, 1));
                        if (!Blanca.get(auxb).pos_ok(i,j,this)) return 10;
                        Tablero[i][j] = auxb + 16;
                        ++j;
                        auxb++;
                        num_peones_b++;
                    }
                    /* lectura de quien juega, */
                    else if (Fen_act.charAt(k) == ' ') {
                        fin_fichas = true;
                    }
                    /* si fen_act[k] es un numero no hay ficha en nuestro tablero*/
                    else if (k != l-1 && valido(Fen_act.charAt(k)) && !valido(Fen_act.charAt(k+1))){

                        if(Integer.parseInt(String.valueOf(Fen_act.charAt(k))) + j > 8){
                            fallo_muchas_c = true;
                            break;
                        }

                        for (int x = 0; x < Integer.parseInt(String.valueOf(Fen_act.charAt(k))); x++) {
                            Tablero[i][j + x] = -1;
                        }
                        j += Integer.parseInt(String.valueOf(Fen_act.charAt(k)));
                    }
                    else return 16;

                }
                /* el caracter es una '/' quiere decir que es la siguiente fila de nuestra matriz*/
                else {
                    /* esto mira si tenemos mas de 8 filas en el fen */
                    if (i == 7) {
                        fallo_muchas_f = true ;
                        break;
                    }
                    /* aumenta nuestra fila */
                    i++;

                    /* mira si tenemos menos de 8 columnas en la fila i*/
                    if ( j < 7 ) {
                        fallo_pocas_c = true;
                        break;
                    }
                    /* poner las columnas a 0 i+1 */
                    j = 0;
                }
            }
            /* Ahora leemos quien juega */
            else{

                if (i < 7 ) fallo_pocas_f = true ;
                else if (j < 7 ) fallo_pocas_c = true ;
                if (Fen_act.charAt(k) == 'w') {
                    empieza_jugando = 0;
                    turno = 0;
                }
                else if (Fen_act.charAt(k) == 'b') {
                    empieza_jugando = 0;
                    turno = 1;
                }
                /* esto es para mirar que el final es " - - 0 1" */
                else if ((k == l - 2 || k == l - 4  || k == l - 6 || k == l - 8) && Fen_act.charAt(k) != ' ' ) {
                    fallo_fin = true;
                }
                else if ((k == l - 5 || k == l - 7 ) && Fen_act.charAt(k) != '-' ) {
                    fallo_fin = true;
                }
                else if (Fen_act.charAt(k) != '0' && k == l - 3) fallo_fin = true;
                else if (Fen_act.charAt(k) != '1' && k == l - 1) fallo_fin = true;
            }
        }


        /* usage error */

        Ficha reyn= Negra.get(rey_negro_id);
        int x = reyn.get_pos_x();
        int y = reyn.get_pos_y();

        Ficha reyb= Blanca.get(rey_blanco_id-16);
        int x2 = reyb.get_pos_x();
        int y2 = reyb.get_pos_y();

        if (fallo_muchas_c) return 1;
        else if (fallo_pocas_c) return 2;
        else if ( num_peones_b > 8 ) return 5;
        else if (num_peones_n > 8 ) return 6;
        else if (Blanca.size() > 16) return 7;
        else if (Negra.size() > 16) return 8;
        else if (fallo_pocas_f ) return 4;
        else if (fallo_muchas_f ) return 3;
        else if (fallo_fin) return 9;

        else if (rey_negro && !reyn.pos_ok(x,y,this)) {
            return 11;
        }
        //else if (rey_negro && !Negra.get(rey_negro_id).pos_ok()) return 11;

        else if (rey_blanco && !reyb.pos_ok(x2,y2,this)) {
            return 11;
        }
        //else if (rey_blanco && !Blanca.get(rey_blanco_id - 16).pos_ok(x,y)) return 11;
        else if (!rey_negro) return 14;
        else if (!rey_blanco) return 15;
        else {
            /* saca el tablero por pantalla */
            //pintar_tablero();
            //System.out.println("♘");
            return 0;
        }

    }

    public boolean valido (char c) {
        if(c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')
            return true;
        else return false;
    }


    /* Devuelve el nombre de la ficha con el id que le pasamos por parámetro */

    public String get_nom_ficha(int id) {
        if (id >= 0 && id <= 15 ) return Negra.get(id).get_nom();
        else return Blanca.get(id-16).get_nom();
    }


    /* Devuelve la clau de la ficha con el id que le pasamos por parámetro */

    public char get_clau_ficha(int id) {
        if (id >= 0 && id <= 15 ) return Negra.get(id).get_clau();
        else return Blanca.get(id-16).get_clau();
    }


    /* Devuelve la Dominio.Ficha que tiene el id que le pasamos por parámetro*/

    public  Ficha get_ficha(int id) {
        if (id >= 0 && id <= 15 ) return Negra.get(id);
        else return Blanca.get(id-16);
    }


    /* Devuelve el id de la ficha que se encuentra en la pos (x, y) */

    public  int get_id_ficha(int x, int y) {
        return Tablero[x][y];
    }


    /* Devuelve el color de la ficha con el id que le pasamos por parámetro*/

    public  String get_color_ficha (int id) {
        if (id >= 0 && id <= 15) return "negra";
        else return "blanca";
    }


    /* Quien está jugando. si es turno = 0 -> empiezan blancas, si turno = 1 -> empiezan negras */

    public  int quien_juega(){
        return turno;
    }


    /* Quien empieza jugando. si es turno = 0 -> empiezan blancas, si turno = 1 -> empiezan negras */

    public int quien_empieza() {
        return empieza_jugando;
    }


    /* esta funcion te mira que la posición del tablero que le pasas está dentro del tablero o fuera */

    public  boolean dentro_del_tablero(int x, int y) {
        if ( x <= -1 || x >= 8|| y <= -1 || y >= 8 ){ return false; }
        else return true;
    }


    /* Se aplica el movimiento que ha elegido el algoritmo*/

    public Ficha mover(Ficha f, int x, int y ) {

        //f.set_muerte_temp(mata_temp);

        int pos_xv = f.get_pos_x();
        int pos_yv = f.get_pos_y();
        Tablero[pos_xv][pos_yv] = -1;
        f.actualizar_pos(x,y);
        if (f.get_nom() == "rey") {
            if (f.get_color() =="blanca") {
                posx_rey_blanco = x;
                posy_rey_blanco = y;
            }
            else {
                posx_rey_negro = x;
                posy_rey_negro = y;
            }
        }
        if (turno == 1) turno = 0;
        else turno = 1;
        Ficha vic;
        //System.out.println("pipo6---> "+ x + " "+ y);
        if (Tablero[x][y] != -1){
            //System.out.println("pipo4");
            get_ficha(Tablero[x][y]).setMuerte_temp();
            vic = get_ficha(Tablero[x][y]);
        }
        else {
            vic = null;
        }
        //System.out.println("pipo5");
        Tablero[x][y] = f.get_id();
        //pintar_tablero();
        return vic;
    }


    public  void resucitar (Ficha f) {
        if (f != null) {
            int posx = f.get_pos_x();
            int posy = f.get_pos_y();
            Tablero[posx][posy] = f.get_id();
            get_ficha(Tablero[posx][posy]).setMuerte_temp();
        }
    }


    public boolean to_promote(Ficha f) {
        if (f.get_color() == "blanca") {
            if (f.get_pos_x() == 0) return true;
        }
        else {
            if (f.get_pos_x() == 7) return true;
        }
        return false;
    }


    public Ficha promover_peon(Ficha f, String forma) {
        int posx = f.get_pos_x();
        int posy = f.get_pos_y();
        if (f.get_color().equals("blanca")) {
            int index = f.get_id()-16;
            if (forma.equals("reina")) {
                //Blanca.remove(index);
                Blanca.set(index, new Reina (index+16, "reina", "blanca",'♕', posx, posy, 10.0));
                String nom = get_ficha(index+16).get_nom();
            }
            else if (forma.equals("caball")) {
                //Blanca.remove(index);
                Blanca.set(index, new Caball (index+16, "caball", "blanca",'♘', posx, posy, 3.0));
            }
            else if (forma.equals("torre")) {
                //Blanca.remove(index);
                Blanca.set(index, new Torre (index+16, "torre", "blanca",'♖', posx, posy, 5.5));
            }
            else if(forma.equals("alfil")) {
                //Blanca.remove(index);
                Blanca.set(index, new Alfil (index+16, "alfil", "blanca",'♗', posx, posy, 3.5));
            }
        }
        else if (f.get_color().equals("negra")) {
            int index = f.get_id();
            if (forma.equals("reina")) {
                //Negra.remove(index);
                Negra.set(index, new Reina (index, "reina", "negra",'♛', posx, posy, 10.0));
            }
            if (forma.equals("caball")) {
                //Negra.remove(index);
                Negra.set(index, new Caball (index, "caball", "negra",'♞', posx, posy, 3.0));
            }
            if (forma.equals("torre")) {
                //Negra.remove(index);
                Negra.set(index, new Torre (index, "torre", "negra",'♜', posx, posy, 5.5));
            }
            if (forma.equals("alfil")) {
                //Negra.remove(index);
                Negra.set(index, new Alfil (index, "alfil", "negra",'♝', posx, posy, 3.5));
            }
        }
        return f;
    }


    public void despromover(Ficha f, Ficha origen) {
        //int posx = f.get_pos_x();
        //int posy = f.get_pos_y();
        if (f.get_color().equals("blanca")) {
            int index = f.get_id()-16;
            //Blanca.remove(index);
            Blanca.set(index, origen);//new Peo(index+16, "peo", "blanca", '♙', posx, posy, 1.0));
        }
        else if (f.get_color().equals("negra")) {
            int index = f.get_id();
            //Negra.remove(index);
            Negra.set(index, origen);//new Peo(index, "peo", "negra", '♟', posx, posy, 1.0));
        }
    }


    /* Mira si no hay fichas en el camino ni fichas del mismo equipo en la posición final */

    public  boolean camino_ok(int pos_x, int pos_y, int mov_x, int mov_y, int count, String colorf) {
        int x = pos_x, y = pos_y;
        //System.out.println(Tablero[pos_x][pos_y]+ " " + pos_x+ " " +pos_y + " " + x + " " + y);
        while (count > 1) {
            x = x+mov_x;
            y = y+mov_y;
            /*Si posición incorrecta o si hay ficha*/
            if (!dentro_del_tablero(x,y)|| Tablero[x][y] != -1 ) {
                return false;
            }
            --count;
        }

        /*Comprobamos la posición final del movimiento*/
        x = x+mov_x;
        y = y+mov_y;

        /*Si posición incorrecta del tablero*/
        if (!dentro_del_tablero(x,y)) {
            return false;
        }
        else {

                /*Si hay ficha en la posición final*/
                if (Tablero[x][y] != -1) {
                    if (get_color_ficha(Tablero[x][y]).contentEquals("blanca") && colorf.contentEquals("blanca")) return false;
                    else if (get_color_ficha(Tablero[x][y]).equals("negra") && colorf.equals("negra")) return false;


                }

                /*Si es un peón*/
                if (get_nom_ficha(Tablero[pos_x][pos_y]).equals("peo")) {
                    /*Comprobamos si el movimiento es recto y va a matar*/
                    if (mov_y == 0 && Tablero[x][y] != -1) return false;

                        /*Comprobamos si el movimiento es diagonal y no es para matar*/
                    else if ((mov_y == 1 || mov_y == -1) && Tablero[x][y] == -1) return false;
                }
                /*Si es un rey*/
                if (get_nom_ficha(Tablero[pos_x][pos_y]).equals("rey")) {
                    /*Comprobamos que es una posición en la que puede estar*/
                    if (Tablero[pos_x][pos_y] >= 0 && Tablero[pos_x][pos_y] <= 15) {
                        if (Negra.get(Tablero[pos_x][pos_y]).pos_ok(x,y,this) == false) return false;

                    }
                    if (Tablero[pos_x][pos_y] >= 16 && Tablero[pos_x][pos_y] <= 31) {
                        if (Blanca.get(Tablero[pos_x][pos_y] - 16 ).pos_ok(x,y,this) == false) return false;
                    }
                }
        }


        return true;
    }


    /* Comprueba si el rey del color "color" está en jaque*/

    public boolean jaque(String color) {

        //pintar_tablero();

        int posx, posy, x,y;
        if (color == "blanca") {
            posx = posx_rey_blanco;
            posy = posy_rey_blanco;
        }
        else {
            posx = posx_rey_negro;
            posy = posy_rey_negro;
        }

        /* Si no hay ficha en el camino: seguimos avanzando en la dirección correspondiente. */
        /* Si hay una ficha en el camino comprobamos cuál es. Si es una ficha de color contrario que pueda hacer
           jaque en esa dirección return true. Si no, hacemos break, ya que cualquier ficha que haya detrás no
           podrá llegar al rey. */
        /* Los caballos se comprueban aparte, porque pueden saltar fichas. */


         /* Dirección arriba */

        x = posx -1;
        y = posy;
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
        }

        /* Dirección abajo */
        x = posx + 1;
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
        }

        /* Dirección izquierda */
        x = posx;
        y = posy - 1;
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            y--;
        }

        /* Dirección derecha */
        y = posy + 1;
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            y++;
        }

        /* Direccion abajo derecha*/
        x = posx + 1;
        y = posy + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "peo") {
            //Si el peón es blanco y el rey negro -> amenaza
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            x = -1; //no entramos en el while
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
            y++;
        }

        /* Dirección abajo izquierda */
        x = posx + 1;
        y = posy - 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "peo") {
            //Si el peón es blanco y el rey negro -> amenaza
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            x = -1; //no entramos en el while
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
            y--;
        }

        /* Dirección arriba derecha */
        x = posx - 1;
        y = posy + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "peo") {
            //Si el peón es blanco y el rey negro -> amenaza
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            x = -1; //no entramos en el while
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
            y++;
        }

        x = posx - 1;
        y = posy - 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "peo") {
            //Si el peón es blanco y el rey negro -> amenaza
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            x = -1; //no entramos en el while
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
            y--;
        }

        /* Comprobación de caballos */
        int[] cax = {1, -1, -2, -2, -1,  1,  2,  2};
        int[] cay = {2,  2,  1, -1, -2, -2, -1,  1};
        for (int i = 0; i < 8; i++) {
            x = posx + cax[i];
            y = posy + cay[i];
            if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "caball") {
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            }
        }

        return false;
    }


    /* Comprueba si el rey de color "color" está en mate */

    public boolean mate(String color) {

        /* Color recibe jaque ? */

        if (!jaque(color)) return false;

            /* SI,  PODEMOS SALVARLA ? */
        else {

            int aux_x, aux_y;

            if (color.equals("blanca")) {
                /* SOMOS BLANQUITOS */

                aux_x = posx_rey_blanco;
                aux_y = posy_rey_blanco;

                //System.out.println("POS REY BLANCO " + posx_rey_blanco + " " + posy_rey_blanco);

                int id = get_id_ficha(aux_x, aux_y);                /* ID = ID REY BLANCO*/
                Ficha blanquito = get_ficha(id);
                ArrayList<Pair<Integer,Integer> > mov_rey = get_ficha(id).todos_movimientos(this); /* SE PUEDE SALVAR SOLO ? */

                for (int i = 0; i < mov_rey.size(); i++) {

                    posx_rey_blanco = mov_rey.get(i).getKey();
                    posy_rey_blanco = mov_rey.get(i).getValue();

                    Ficha Difunto = mover( blanquito, posx_rey_blanco, posy_rey_blanco);

                    if (!jaque(color)) {
                        /* Restablecemos la posición del rey, que sí se ha podido proteger */
                        posx_rey_blanco = aux_x;
                        posy_rey_blanco = aux_y;
                        mover(blanquito,aux_x,aux_y);
                        resucitar(Difunto);
                        return false;
                    }
                    mover(blanquito,aux_x,aux_y);
                    resucitar(Difunto);
                }
                /* Restablecemos la posición del rey, que no se ha podido proteger */
                posx_rey_blanco = aux_x;
                posy_rey_blanco = aux_y;

                /* Comprobamos si alguna ficha protege del jaque */
                for (int i = 0; i < Blanca.size(); i++) {
                    if (!Blanca.get(i).isMuerte_temp()) {
                        ArrayList<Pair<Integer, Integer>> mov_ficha = Blanca.get(i).todos_movimientos(this);
                        for (int j = 0; j < mov_ficha.size(); j++) {
                            /* Guardamos la posición inicial de la ficha, para restablcerla más adelante */
                            aux_x = Blanca.get(i).get_pos_x();
                            aux_y = Blanca.get(i).get_pos_y();

                            /* Muevo la ficha temporalmente para comprobar si protege del jaque */
                            //System.out.println("piporev");
                            Ficha victima = mover(Blanca.get(i), mov_ficha.get(j).getKey(), mov_ficha.get(j).getValue());
                            if (!jaque(color)) {
                                //System.out.println("piporev2");
                                /* Restablecemos la posición de la ficha, que sí ha podido proteger al rey */
                                Ficha aux = mover(Blanca.get(i), aux_x, aux_y);
                                resucitar(victima);
                                return false;
                            } else {
                                /* Restablecemos la posición de la ficha, que no ha podido proteger al rey */
                                //System.out.println("piporev3");
                                Ficha aux = mover(Blanca.get(i), aux_x, aux_y);
                                resucitar(victima);
                            }
                        }
                    }
                }
            }

            else {
                /*Guardamos la posición inicial del rey, para restablecerla más adelante*/
                aux_x = posx_rey_negro;
                aux_y = posy_rey_negro;
                int id = get_id_ficha(aux_x, aux_y);
                Ficha negrito =  get_ficha(id);
                /* Comprobamos si con algún movimiento del rey se protege del jaque */
                ArrayList<Pair<Integer,Integer> > mov_rey = get_ficha(id).todos_movimientos(this);
                for (int i = 0; i < mov_rey.size(); i++) {
                    posx_rey_negro = mov_rey.get(i).getKey();
                    posy_rey_negro = mov_rey.get(i).getValue();

                    Ficha Zombie = mover( negrito, posx_rey_negro,posy_rey_negro);

                    if (!jaque(color)) {
                        /* Restablecemos la posición del rey, que sí se ha podido proteger */
                        posx_rey_negro = aux_x;
                        posy_rey_negro = aux_y;

                        mover( negrito, aux_x, aux_y);
                        resucitar(Zombie);

                        return false;
                    }

                    mover( negrito, aux_x, aux_y);
                    resucitar(Zombie);
                }
                /* Restablecemos la posición del rey, que no se ha podido proteger */
                posx_rey_negro = aux_x;
                posy_rey_negro = aux_y;

                /* Comprobamos si alguna ficha protege del jaque */
                for (int i = 0; i < Negra.size(); i++) {
                    if (!Negra.get(i).isMuerte_temp()) {
                        ArrayList<Pair<Integer, Integer>> mov_ficha = Negra.get(i).todos_movimientos(this);
                        for (int j = 0; j < mov_ficha.size(); j++) {
                            /* Guardamos la posición inicial de la ficha, para restablcerla más adelante */
                            aux_x = Negra.get(i).get_pos_x();
                            aux_y = Negra.get(i).get_pos_y();

                            /* Muevo la ficha temporalmente para comprobar si protege del jaque */
                            //System.out.println("piporev4");
                            Ficha victima = mover(Negra.get(i), mov_ficha.get(j).getKey(), mov_ficha.get(j).getValue());
                            if (!jaque(color)) {
                                //System.out.println("piporev5");
                                /* Restablecemos la posición de la ficha, que sí ha podido proteger al rey */
                                Ficha aux = mover(Negra.get(i), aux_x, aux_y);
                                resucitar(victima);
                                return false;
                            } else {
                                /* Restablecemos la posición de la ficha, que no ha podido proteger al rey */
                                //System.out.println("piporev6");
                                Ficha aux = mover(Negra.get(i), aux_x, aux_y);
                                resucitar(victima);
                            }
                        }
                    }
                }
            }
        }
        /* NO SE LA PUEDE SALVAR*/
        //System.out.println("Hay mate sobre " + color);

        return true;
    }


    public ArrayList<Nodo> todos_movs(int color,double papipoints) {
       // System.out.println("todos los movimientos :");
        ArrayList<Nodo> nodos = new ArrayList<Nodo> ();
        Nodo n;
        ArrayList<Pair<Integer,Integer>> movs;
        /*Juegan las blancas*/
        if (color == 0) {
            for (int i = 0; i < Blanca.size(); i++) {
                Ficha f = Blanca.get(i);
                if(f.get_muerte_temp() == false) {
                    //System.out.println(" de la ficha " + f.get_nom());

                    movs = f.todos_movimientos(this);
                    for (int j = 0; j < movs.size(); j++) {
                        /* Para cada movimiento de la ficha Blanca[i] creamos un nodo */

                        //System.out.println(movs.get(j).getKey() + " " + movs.get(j).getValue());
                       // System.out.println(papipoints);
                        n = new Nodo(f, 0 + movs.get(j).getKey(), 0 + movs.get(j).getValue() , papipoints, new ArrayList<Nodo>());
                        nodos.add(n);
                    }
                }
            }
        }
        /*Juegan negras*/
        else {
            for (int i = 0; i < Negra.size(); i++) {
                Ficha f = Negra.get(i);
                if(f.get_muerte_temp() == false) {
                   // System.out.println("de la ficha " + f.get_nom());

                    movs = Negra.get(i).todos_movimientos(this);
                    for (int j = 0; j < movs.size(); j++) {
                        /* Para cada movimiento de la ficha Blanca[i] creamos un nodo */

                        //System.out.println(movs.get(j).getKey() + " " + movs.get(j).getValue());
                        n = new Nodo(f, 0 + movs.get(j).getKey(), 0 + movs.get(j).getValue(), papipoints, new ArrayList<Nodo>());
                        nodos.add(n);
                    }
                }
            }
        }
        return nodos;
    }


    public void puntua ( Nodo n, boolean OP, boolean promocion){
        // Analiza Play
        String Color1 = n.get_ficha().get_color();
        String Color2;
        if (Color1.equals("blanca")) Color2 = "negra";
        else Color2 = "blanca";
        int auxX = n.get_ficha().get_pos_x();
        int auxY = n.get_ficha().get_pos_y();
        Ficha peon = null;

        boolean prio_check = jaque(Color1);
        boolean prioMenace = amenaza( auxX, auxY, Color1); // Estoy Expuesto?
        boolean prioProtec = amenaza( auxX, auxY, Color2); // Estoy Protegido?
        double  prioSecure = Asegura( n.get_ficha(),Color2); // PROTEGO?
        double  prioattaca = ataca( n.get_ficha(),Color1); // AMENAZO?
        //pintar_tablero();


        Ficha Dead = mover(n.get_ficha(),n.get_posx(),n.get_posy());
        if (promocion) {
            peon = promover_peon(n.get_ficha(), n.getPromotion());
            n.set_ficha(get_ficha(n.get_ficha().get_id()));
        }

        if (mate(Color2)) {

            n.setPuntos(300000);

            if (promocion) {
                despromover(n.get_ficha(), peon);
                n.set_ficha(get_ficha(n.get_ficha().get_id()));
            }
            mover(n.get_ficha(),auxX,auxY);
            resucitar(Dead);

            return;
        }
        boolean post_check = jaque(Color1);
        boolean postMenace = amenaza( n.get_posx(), n.get_posy(), Color1); // EXPOSED?
        boolean postProtec = amenaza( n.get_posx(), n.get_posy(), Color2); // PROTECTED?
        double  postSecure = Asegura(n.get_ficha(),Color2); // PROTEGO?
        double  postattaca = ataca(n.get_ficha(),Color1); // AMENAZO?

        //pintar_tablero();

        if (promocion) {
            despromover(n.get_ficha(), peon);
            n.set_ficha(get_ficha(n.get_ficha().get_id()));
        }
        mover(n.get_ficha(),auxX,auxY);
        resucitar(Dead);

        // SUMA TIME

        double me = n.get_ficha().getPuntuacionBase(); // MI PUNTUACION BASE
        // POSITIVAS

        double Protec = 0.0;
        double Killer = 0.0;
        double Attack;
        double Secure;
        // NEGATIVAS
        double Danger = 0.0;
        double Dprotc = 0.0;

        // ACCIONES POSITIVAS

        if (postProtec && !n.get_ficha().get_nom().equals("rey")) Protec += me;
        if (Dead != null) Killer += 2*Dead.getPuntuacionBase();
        Attack = 0.5*(postattaca-prioattaca);

        // ACCIONES NEGATIVAS
        if (postSecure >= prioSecure) Secure = prioSecure + (postSecure-prioSecure);
        else Secure = postSecure + (postSecure-prioSecure)*1.5;

        if (!prioMenace && postMenace) Danger += 2*me;
        if (prioMenace && !postMenace) Danger -= me;
        if (!n.get_ficha().get_nom().equals("rey") && prio_check && !post_check) Danger -= 10000;
        if (prioProtec && !postProtec && !n.get_ficha().get_nom().equals("rey")) Dprotc += me;

        // Sumatorio!

        double Result =  Secure+ Attack + ((Protec + Killer ) - (Danger + Dprotc));

        double puntitos = n.get_puntuacion();

        if (OP){
            n.setPuntos(puntitos + Result);
        }
        // sino restas
        else {
            n.setPuntos(-puntitos - Result);
        }
    }


    public boolean amenaza(int posx, int posy, String color ) {

        int x,y;

        /* Dirección arriba */

        x = posx -1;
        y = posy;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]).equals("rey") && get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            x = -1;
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
        }

        /* Dirección abajo */
        x = posx + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            x = -1;
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
        }

        /* Dirección izquierda */
        x = posx;
        y = posy - 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            y = -1;
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            y--;
        }

        /* Dirección derecha */
        y = posy + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
            if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
            if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            x = -1;
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "torre") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            y++;
        }

        /* Direccion abajo derecha*/
        x = posx + 1;
        y = posy + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1){
            if (get_nom_ficha(Tablero[x][y]) == "peo") {
                //Si el peón es blanco y el rey negro -> amenaza
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1; //no entramos en el while
            }
            else if (get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1;
            }
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
            y++;
        }

        /* Dirección abajo izquierda */
        x = posx + 1;
        y = posy - 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1){
            if (get_nom_ficha(Tablero[x][y]) == "peo") {
                //Si el peón es blanco y el rey negro -> amenaza
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1; //no entramos en el while
            }
            else if (get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1;
            }
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x++;
            y--;
        }

        /* Dirección arriba derecha */
        x = posx - 1;
        y = posy + 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1){
            if (get_nom_ficha(Tablero[x][y]) == "peo") {
                //Si el peón es blanco y el rey negro -> amenaza
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                x = -1; //no entramos en el while
            }
            else if (get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1;
            }
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
            y++;
        }
        /* DIRECCION ARRIBA IZQUIERDA */
        x = posx - 1;
        y = posy - 1;
        if (dentro_del_tablero(x,y) && Tablero[x][y] != -1){
            if (get_nom_ficha(Tablero[x][y]) == "peo") {
                //Si el peón es blanco y el rey negro -> amenaza
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                x = -1; //no entramos en el while
            }
            else if (get_nom_ficha(Tablero[x][y]).equals("rey")&& get_ficha(Tablero[x][y]).pos_ok(posx,posy,this)){
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                x = -1;
            }
        }
        while (dentro_del_tablero(x,y)) {
            if (Tablero[x][y] != -1) {
                if (get_nom_ficha(Tablero[x][y]) == "reina" || get_nom_ficha(Tablero[x][y]) == "alfil") {
                    if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                    if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
                }
                break;
            }
            x--;
            y--;
        }

        /* Comprobación de caballos */
        int[] cax = {1, -1, -2, -2, -1,  1,  2,  2};
        int[] cay = {2,  2,  1, -1, -2, -2, -1,  1};
        for (int i = 0; i < 8; i++) {
            x = posx + cax[i];
            y = posy + cay[i];
            if (dentro_del_tablero(x,y) && Tablero[x][y] != -1 && get_nom_ficha(Tablero[x][y]) == "caball") {
                if (color == "blanca" && get_color_ficha(Tablero[x][y]) == "negra") return true;
                if (color == "negra" && get_color_ficha(Tablero[x][y]) == "blanca") return true;
            }
        }

        return false;
    }


    public double ataca (Ficha f,String Color) {
        double puntos = 0.0;
        String nom = f.get_nom();
        int posx, posy;
        int x = posx = f.get_pos_x();
        int y = posy = f.get_pos_y();


        if (nom.equals("peo")) {
            if (Color.equals("blanca")) {
                x = posx-1;
                y = posy+1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("negra"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
                x = posx-1;
                y = posy-1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("negra"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            } else {
                x = posx+1;
                y = posy+1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("blanca"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
                x = posx+1;
                y = posy-1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("blanca"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            }
        }

        else if (nom.equals("caball")) {
            int[] cax = {1, -1, -2, -2, -1, 1, 2, 2};
            int[] cay = {2, 2, 1, -1, -2, -2, -1, 1};
            for (int i = 0; i < 8; i++) {
                x = posx + cax[i];
                y = posy + cay[i];
                if (dentro_del_tablero(x, y) && Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            }
        }

        else if (nom.equals("rey")) {
            for (x = posx - 1; x < posx + 2; ++x){
                for (y = posy - 1; y < posy + 2; ++y){
                    if (dentro_del_tablero(x, y) && Tablero[x][y] != -1 && get_ficha(Tablero[x][y]).pos_ok(x,y,this)) {
                        if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                        if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    }
                }
            }
        }

        if (nom.equals("torre") || nom.equals("reina")){
            // ARRIBA
            x = posx-1;
            y = posy;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
            }
            // ABAJO
            x = posx+1;
            y = posy;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
            }
            // IZQUIERDA
            x = posx;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                y--;
            }
            // DERECHA
            x = posx;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                y++;
            }
        }

        if (nom.equals("alfil") || nom.equals("reina")){
            // ABAJO DERECHA
            x = posx+1;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
                y++;
            }
            // ABAJO IZQUIERDA
            x = posx+1;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
                y--;
            }
            // ARRIBA DERECHA
            x = posx-1;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
                y++;
            }
            // ABAJO DERECHA
            x = posx-1;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
                y--;
            }
        }

        return puntos;
    }


    public double Asegura (Ficha f,String Color) {
        double puntos = 0.0;
        String nom = f.get_nom();

        int  posx = f.get_pos_x();
        int  posy = f.get_pos_y();
        int x,y;

        if (nom.equals("peo")) {
            if (Color.equals("blanca")) {
                x = posx+1;
                y = posy+1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
                x = posx+1;
                y = posy-1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            } else {
                x = posx-1;
                y = posy+1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
                x = posx-1;
                y = posy-1;
                if (dentro_del_tablero(x,y)) {
                    if (Tablero[x][y] != -1 && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            }
        }

        else if (nom.equals("caball")) {
            int[] cax = {1, -1, -2, -2, -1, 1, 2, 2};
            int[] cay = {2, 2, 1, -1, -2, -2, -1, 1};
            for (int i = 0; i < 8; i++) {
                x = posx + cax[i];
                y = posy + cay[i];
                if (dentro_del_tablero(x, y) && Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey"))
                        puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                }
            }
        }

        else if (nom.equals("rey")) {
            for (x = posx - 1; x < posx + 2; ++x){
                for (y = posy - 1; y < posy + 2; ++y){
                    if (dentro_del_tablero(x, y) && Tablero[x][y] != -1 && get_ficha(Tablero[x][y]).pos_ok(x,y,this)) {
                        if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                        if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    }
                }
            }
        }

        if (nom.equals("torre") || nom.equals("reina")){
            // ARRIBA
            x = posx-1;
            y = posy;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
            }
            // ABAJO
            x = posx+1;
            y = posy;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
            }
            // IZQUIERDA
            x = posx;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                y--;
            }
            // DERECHA
            x = posx;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                y++;
            }
        }

        if (nom.equals("alfil") || nom.equals("reina")){
            // ABAJO DERECHA
            x = posx+1;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
                y++;
            }
            // ABAJO IZQUIERDA
            x = posx+1;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x++;
                y--;
            }
            // ARRIBA DERECHA
            x = posx-1;
            y = posy+1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
                y++;
            }
            // ABAJO DERECHA
            x = posx-1;
            y = posy-1;
            while (dentro_del_tablero(x,y)) {
                if (Tablero[x][y] != -1) {
                    if (Color.equals("blanca") && get_color_ficha(Tablero[x][y]).equals("negra") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    if (Color.equals("negra") && get_color_ficha(Tablero[x][y]).equals("blanca") && !get_nom_ficha(Tablero[x][y]).equals("rey")) puntos += get_ficha(Tablero[x][y]).getPuntuacionBase();
                    break;
                }
                x--;
                y--;
            }
        }

        return puntos;
    }


    public String mat_to_fen (){
        String res="";
        int count;
        for ( int i = 0; i < Tablero.length; i++ ){

            count = 0;

            for ( int j = 0; j < Tablero[0].length; j++){

                /* espacio */
                if (Tablero[i][j] == -1 ) count++;
                    /* ficha blanca */
                else {
                    if (count != 0 ) {
                        /* añadir el contador */
                        res += String.valueOf(count);
                        count = 0;
                    }
                    if (get_ficha(Tablero[i][j]).get_nom().equals("torre")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'R';
                        else res += 'r';
                    }
                    else if (get_ficha(Tablero[i][j]).get_nom().equals("alfil")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'B';
                        else res +='b';
                    }
                    else if (get_ficha(Tablero[i][j]).get_nom().equals("caball")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'N';
                        else res +='n';
                    }
                    else if (get_ficha(Tablero[i][j]).get_nom().equals("reina")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'Q';
                        else res +='q';
                    }
                    else if (get_ficha(Tablero[i][j]).get_nom().equals("rey")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'K';
                        else res +='k';
                    }
                    else if (get_ficha(Tablero[i][j]).get_nom().equals("peo")){
                        if(get_ficha(Tablero[i][j]).get_color().equals("blanca")) res += 'P';
                        else res +='p';
                    }
                }
            }
            if (count != 0) {
                res += String.valueOf(count);
            }
            if ( i == 7){
                res += ' ';
                int p = quien_juega();
                if (p == 0) res += 'w';
                else res += 'b';
                res += " - - 0 1";
            }
            else res += '/';
        }
        return res;
    }


}
