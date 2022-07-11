package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author jdall
 */
public class DobbleGame implements IDobbleGame {
    //          ATRIBUTOS           //
    private final int cantPlayers;
    private final List<Player> players;
    private final Dobble dobble;
    private final String mode;
    private int turnoPlayer;
    private final List<Card> CardsMesa;
    private String elementSelected;
    private String ganador;
    private int error;

    /**
     * Constructor del juego
     * @param cantJugadores cantidad de jugadores
     * @param cantCartas cantidad de cartas del mazo
     * @param modo modo de juego
     */
    public DobbleGame(int cantJugadores, int cantCartas, String modo,int numElementos,List<String> elementos) {
        Scanner entrada = new Scanner(System.in);
        List<Player> jugadores = new ArrayList<>();
        this.cantPlayers = cantJugadores;
        this.players = jugadores;
        this.dobble = new Dobble(numElementos, cantCartas, elementos);
        this.mode = modo;
        this.turnoPlayer = 1;
        this.CardsMesa = new ArrayList<>();
        this.elementSelected=null;
    }

    //          GETTERS          //
    /**
     * obtiene la lista de jugadores del juego
     * @return jugadores del juego
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * obtiene el set de cartas
     * @return set de cartas
     */
    public Dobble getDobble() {
        return dobble;
    }

    /**
     * obtiene el modo de juego
     * @return modo de juego
     */
    public String getMode() {
        return mode;
    }

    /**
     * obtiene a quien le toca jugar
     * @return turno del jugador
     */
    public int getTurnoPlayer() {
        return turnoPlayer;
    }

    /**
     * obtiene la cantidad de jugadores
     * @return cantidad de jugadores
     */
    public int getCantPlayers() {
        return cantPlayers;
    }

    /**
     * Obtiene las cartas que se encuentran en la mesa
     * @return cartas que se encuentran en la mesa
     */
    public List<Card> getCardsMesa() {
        return CardsMesa;
    }

    /**
     * Obtiene el elemento que selecciona la cpu
     * @return elemento que selecciona la cpu
     */
    public String getElementSelected(){ return elementSelected; }

    /**
     * Obtiene el ganador del juego
     * @return Ganador del juego
     */
    public String getGanador() { return ganador; }

    /**
     * Obtiene el error que se produce
     * @return error que se produce
     */
    public int getError() { return error; }

    //              Metodos Auxiliares              //
    /**
     * Registra a nuevo Jugador en el juego
     * @param namePlayer Nombre del Jugador
     */
    @Override
    public void register(String namePlayer){
        error = 0;
        int existe = 0;
        if(namePlayer.isEmpty()) {
            error =2;
        }else{
            if (players.isEmpty()) {
                Player player = new Player(namePlayer, 1);
                players.add(player);
            } else {
                for (Player value : players) {
                    if (Objects.equals(namePlayer, value.getNombre())) {
                        existe++;
                    }
                }
                if (existe == 0) {
                    Player player = new Player(namePlayer, players.size() + 1);
                    players.add(player);
                } else {
                    error = 1;
                }
            }
        }
    }

    /**
     * Entrega un string con la informacion de quien es el turno
     * @return string con la informacion de quien es el turno de jugar
     */
    @Override
    public String whoseTurnIsIt(){
        return "Es el turno del jugador " + turnoPlayer + ": " +
                players.get(turnoPlayer - 1).getNombre();
    }

    /**
     * Funcion para jugar
     * @param i opcion del juego que desean realizar
     */
    @Override
    public void play(int i,String entrada){
        if("User vs User".equals(mode)){
            switch (i) {
                case 1 -> {
                    String element = entrada;
                    if ((CardsMesa.get(0).existeElemento(element)) &&
                            (CardsMesa.get(1).existeElemento(element))) {
                        CardsMesa.get(0).setId(players.get(turnoPlayer-1).getMazoPlayer().size()+1);
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(0));
                        CardsMesa.get(1).setId(players.get(turnoPlayer-1).getMazoPlayer().size()+1);
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(1));
                        CardsMesa.remove(1);
                        CardsMesa.remove(0);
                        int puntos = players.get(turnoPlayer - 1).getPuntos() + 2;
                        players.get(turnoPlayer - 1).setPuntos(puntos);
                        error = 3;
                    } else {
                        error = 4;
                    }
                    if (players.size() == turnoPlayer) {
                        turnoPlayer = 1;
                    } else {
                        turnoPlayer += 1;
                    }
                }
                //Pass
                case 2 -> {
                    if (players.size() == turnoPlayer) {
                        turnoPlayer = 1;
                    } else {
                        turnoPlayer += 1;
                    }
                }
                //Finish
                case 3 -> {
                    Player temp;
                    for (int k = 1; k < (players.size()); k++) {
                        for (int j = 0; j < (players.size() - 1); j++) {
                            if (players.get(j).getPuntos() > players.get(j + 1).getPuntos()) {
                                temp = players.get(j);
                                players.set(j, players.get(j + 1));
                                players.set(j + 1, temp);
                            }
                        }
                    }
                    if (players.get(players.size() - 1).getPuntos() == players.get(players.size() - 2).getPuntos()) {
                        int empate = 0;
                        int maximo = players.get(players.size() - 1).getPuntos();
                        for (int j = players.size() - 2; j >= 0; j--) {
                            if (players.get(j).getPuntos() == maximo) {
                                empate++;
                            }
                        }
                        if (empate == players.size() - 1) {
                            ganador = ("Es un empate entre los jugadores\n");
                        }
                    } else {
                        ganador = ("El ganador es " + players.get(players.size() - 1).getNombre() + ".\n");
                    }
                }
            }
        }else if("Demo Mode".equals(mode)){
            if(i != 1){
                this.ponerCartasEnMesa();
                this.jugadaDemoMode();
            }
            if((i == 1) || (dobble.numCards()<= 1)){
                Player temp;
                for (int k = 1; k < (players.size()); k++) {
                    for (int j = 0; j < (players.size() - 1); j++) {
                        if (players.get(j).getPuntos() > players.get(j + 1).getPuntos()) {
                            temp = players.get(j);
                            players.set(j, players.get(j + 1));
                            players.set(j + 1, temp);
                        }
                    }
                }
                if (players.get(players.size() - 1).getPuntos() == players.get(players.size() - 2).getPuntos()) {
                    ganador = ("Es un empate entre las CPUs\n");
                }else {
                    ganador = ("El ganador es " + players.get(players.size() - 1).getNombre() + ".\n");
                }
            }else {

                if (players.size() == turnoPlayer) {
                    turnoPlayer = 1;
                } else {
                    turnoPlayer += 1;
                }
            }
        }
    }

    /**
     * Funcion que pone 2 cartas en la mesa
     */
    public void ponerCartasEnMesa(){
        if(CardsMesa.isEmpty()){
            int x = dobble.numCards();
            CardsMesa.add(dobble.nthCard(x-1));
            CardsMesa.add(dobble.nthCard(x-2));
            CardsMesa.get(0).setId(1);
            CardsMesa.get(1).setId(2);
            dobble.deleteCard(x);
            dobble.deleteCard(x-1);
        }
    }

    /**
     * Funcion que realiza la jugada en el modo demo
     */
    public void jugadaDemoMode(){
        for(int j = 0; j < dobble.nthCard(0).size();j++){
            String element = CardsMesa.get(0).getCard().get(j);
            if ((CardsMesa.get(0).existeElemento(element)) &&
                    (CardsMesa.get(1).existeElemento(element))){
                elementSelected = ("El elemento que selecciona \n" +
                        players.get(turnoPlayer - 1).getNombre() + " es: \n"
                        + element + '.');
                CardsMesa.get(0).setId(players.get(turnoPlayer-1).getMazoPlayer().size()+1);
                players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(0));
                CardsMesa.get(1).setId(players.get(turnoPlayer-1).getMazoPlayer().size()+1);
                players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(1));

                int puntos = players.get(turnoPlayer - 1).getPuntos() + 2;
                players.get(turnoPlayer - 1).setPuntos(puntos);
            }
        }
        CardsMesa.remove(1);
        CardsMesa.remove(0);
    }

    /**
     * Crea una representacion como string del juego
     * @return retorna un string con la informacion del juego
     */
    @Override
    public String toString(){
        StringBuilder texto = new StringBuilder(" El modo de juegos es: " + mode
                + "\n" + dobble.toString()
                + "\nLa cantidad de Jugadores es: " + cantPlayers
                + "\nLos jugadores son: \n");
        for (Player player : players) {
            texto.append(player.toString()).append("\n");
        }
        texto.append("Las cartas en mesa son: \n");
        for (Card card : CardsMesa) {
            texto.append(card.toString()).append("\n");
        }
        try{
            texto.append(this.whoseTurnIsIt());
        }catch (Exception e){
            error = 5;
        }

        return texto.toString();
    }

    /**
     * comprueba si dos games son iguales
     * @param o juego a comparar
     * @return booleano de la igualdad de los juegos
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DobbleGame that = (DobbleGame) o;
        return getCantPlayers() == that.getCantPlayers() &&
                getTurnoPlayer() == that.getTurnoPlayer() &&
                Objects.equals(getPlayers(), that.getPlayers()) &&
                getDobble().equals(that.getDobble()) && getMode().equals(that.getMode()) &&
                Objects.equals(getCardsMesa(), that.getCardsMesa());
    }



}
