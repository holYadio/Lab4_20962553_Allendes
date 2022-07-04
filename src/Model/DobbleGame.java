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
    private int cantPlayers;
    private List<Player> players;
    private Dobble dobble;
    private String mode;
    private int turnoPlayer;
    private List<Card> CardsMesa;

    /**
     * Constructor del juego
     * @param cantJugadores cantidad de jugadores
     * @param cantCartas cantidad de cartas del mazo
     * @param modo modo de juego
     */
    public DobbleGame(int cantJugadores, int cantCartas, String modo) {
        Scanner entrada = new Scanner(System.in);
        List<Player> jugadores = new ArrayList<>();
        List<String> elementos = new ArrayList<>();
        int cantElementosTotal = 0;
        boolean continuacion = true;
        while(continuacion){
            System.out.println("Ingrese una opcion para la lista de elementos:\n "
                    + "1. Ingresar una lista de elementos\n "
                    + "2. Ocupar una lista de numeros (ingresar el maximo num)\n "
                    + "3. Ocupar lista de letras con 13 elementos (Maximo 4 elementos por carta)");
            int numOpcion = entrada.nextInt();
            if (numOpcion == 1){
                System.out.println("Cual es la cantidad de elementos que desea ingresar: ");
                cantElementosTotal = entrada.nextInt();
                for(int i = 0; i < cantElementosTotal; i++){
                    System.out.println("Ingrese el elemente "+ (i+1) +':');
                    Scanner elemento = new Scanner(System.in);
                    String element = elemento.nextLine();
                    elementos.add(element);
                }
                continuacion = false;
            } else if(numOpcion == 2){
                System.out.println("Ingresar el maximo de la lista: ");
                cantElementosTotal = entrada.nextInt();
                for(int i = 1; i <= cantElementosTotal; i++){
                    String strI = i + "";
                    elementos.add(strI);
                }

                continuacion = false;
            } else if(numOpcion == 3){
                elementos.add("a");
                elementos.add("b");
                elementos.add("c");
                elementos.add("d");
                elementos.add("e");
                elementos.add("f");
                elementos.add("g");
                elementos.add("h");
                elementos.add("i");
                elementos.add("j");
                elementos.add("k");
                elementos.add("l");
                elementos.add("m");
                continuacion = false;
            } else{
                System.out.println("Ingrese una opcion valida");
            }
        }
        System.out.println("Ingrese la cantidad de elementos por carta");
        int numElementos = entrada.nextInt();
        this.cantPlayers = cantJugadores;
        this.players = jugadores;
        this.dobble = new Dobble(numElementos, cantCartas, elementos);
        this.mode = modo;
        this.turnoPlayer = 1;
        this.CardsMesa = new ArrayList<>();
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

    //              Metodos Auxiliares              //
    /**
     * Registra a nuevo Jugador en el juego
     * @param namePlayer Nombre del Jugador
     */
    @Override
    public void register(String namePlayer){
        int existe = 0;
        if(players.isEmpty()){
            Player player = new Player(namePlayer,1);
            players.add(player);
        } else{
            if(players.size()< cantPlayers){
                for(int i = 0; i < players.size();i++){
                    if(namePlayer == players.get(i).getNombre()){
                        existe ++;
                        System.out.println(namePlayer);
                        System.out.println(players.get(i).getNombre());
                    }
                }
                if(existe == 1){
                    System.out.println("ERROR: El usuario ya esta registrado");
                }else{
                    Player player = new Player(namePlayer,players.size()+1);
                    players.add(player);
                }
            } else{
                System.out.println("ERROR: Ya se encuentra el maximo de jugadores para este juego");
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
    public void play(int i){
        if("stackmode".equals(mode.toLowerCase())){
            switch (i) {
                case 1:
                    Scanner entrada = new Scanner(System.in);
                    System.out.println("Ingrese el elemento que se comparte:\n");
                    String element = entrada.nextLine();
                    if ((CardsMesa.get(0).existeElemento(element)) &&
                            (CardsMesa.get(1).existeElemento(element))){
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(0));
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(1));
                        CardsMesa.remove(1);
                        CardsMesa.remove(0);
                        int puntos = players.get(turnoPlayer - 1).getPuntos() + 2;
                        players.get(turnoPlayer - 1).setPuntos(puntos);
                        System.out.println("El elemento en comun es correcto\n");
                    }else{
                        System.out.println("El elemento no esta en las dos cart"
                                + "as");
                    }
                    if(players.size() == turnoPlayer){
                        turnoPlayer = 1;
                    }else{
                        turnoPlayer += 1;
                    }
                    break;
                //Pass
                case 2:
                    if(players.size() == turnoPlayer){
                        turnoPlayer = 1;
                    }else{
                        turnoPlayer += 1;
                    }
                    System.out.println("Ha pasado el turno");
                    break;
                //Finish
                case 3:
                    Player temp;
                    for(int k = 1;k < (players.size());k++){
                        for (int j = 0 ; j < (players.size() - 1); j++){
                            if(players.get(j).getPuntos() > players.get(j+1).getPuntos()){
                                temp = players.get(j);
                                players.set(j, players.get(j+1)) ;
                                players.set(j + 1, temp);
                            }
                        }
                    }
                    if(players.get(players.size()-1).getPuntos()== players.get(players.size()-2).getPuntos()){
                        int empate =0;
                        int maximo = players.get(players.size()-1).getPuntos();
                        for(int j = players.size()-2; j >= 0; j--){
                            if (players.get(j).getPuntos() == maximo){
                                empate++;
                            }
                        }
                        if(empate == players.size()-1){
                            System.out.println("Es un empate entre los jugadores\n");
                        }else {
                            String ganadores = players.get(players.size()-1).getNombre();
                            while(empate > 0){
                                int z = players.size()-2;
                                ganadores += " ," + players.get(z).getNombre();
                                z--;
                                empate--;
                            }
                            System.out.println("Los ganadores son :" + ganadores
                                    + ".\n");
                        }
                    }else{
                        System.out.println("El ganador es " +
                                players.get(players.size()-1).getNombre()+".\n");
                    }

                    break;
                default:
                    System.out.println("Ingrese una opcion Correcta\n");
                    break;
            }
        }else if("demomode".equals(mode.toLowerCase())){
            while(dobble.getCardsSet().size() > 1){
                if(CardsMesa.isEmpty()){
                    int x = dobble.numCards();
                    System.out.println("Cartas en mesa");
                    System.out.println(dobble.nthCard(x-1));
                    System.out.println(dobble.nthCard(x-2));
                    CardsMesa.add(dobble.nthCard(x-1));
                    CardsMesa.add(dobble.nthCard(x-2));
                    dobble.deleteCard(x);
                    dobble.deleteCard(x-1);

                }
                for(int j = 0; j < dobble.nthCard(0).size();j++){
                    String element = CardsMesa.get(0).getCard().get(j);
                    if ((CardsMesa.get(0).existeElemento(element)) &&
                            (CardsMesa.get(1).existeElemento(element))){
                        System.out.println("El elemento que selecciona " +
                                players.get(turnoPlayer - 1).getNombre() + " es: "
                                + element + ". \n");
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(0));
                        players.get(turnoPlayer - 1).getMazoPlayer().add(CardsMesa.get(1));

                        int puntos = players.get(turnoPlayer - 1).getPuntos() + 2;
                        players.get(turnoPlayer - 1).setPuntos(puntos);

                    }
                }
                if(players.size() == turnoPlayer){
                    turnoPlayer = 1;
                }else{
                    turnoPlayer += 1;
                }
                CardsMesa.remove(1);
                CardsMesa.remove(0);
            }
            Player temp;
            for(int k = 1;k < (players.size());k++){
                for (int j = 0 ; j < (players.size() - 1); j++){
                    if(players.get(j).getPuntos() > players.get(j+1).getPuntos()){
                        temp = players.get(j);
                        players.set(j, players.get(j+1)) ;
                        players.set(j + 1, temp);
                    }
                }
            }
            if(players.get(players.size()-1).getPuntos()== players.get(players.size()-2).getPuntos()){
                int empate =0;
                int maximo = players.get(players.size()-1).getPuntos();
                for(int j = players.size()-2; j >= 0; j--){
                    if (players.get(j).getPuntos() == maximo){
                        empate++;
                    }
                }
                if(empate == players.size()-1){
                    System.out.println("Es un empate entre los jugadores\n");
                }else {
                    String ganadores = players.get(players.size()-1).getNombre();
                    while(empate > 0){
                        int z = players.size()-2;
                        ganadores += " ," + players.get(z).getNombre();
                        z--;
                        empate--;
                    }
                    System.out.println("Los ganadores son :" + ganadores
                            + ".\n");
                }
            }else{
                System.out.println("El ganador es " +
                        players.get(players.size()-1).getNombre()+".\n");
            }
        }else{
            System.out.println("Modo de juego invalido o no esta implementado\n");
        }
    }

    /**
     * Crea una representacion como string del juego
     * @return retorna un string con la informacion del juego
     */
    @Override
    public String toString(){
        String texto = " El modo de juegos es: " + mode
                + "\n" + dobble.toString()
                + "\nLa cantidad de Jugadores es: " + cantPlayers
                + "\nLos jugadores son: \n";
        for(int i =0;i<players.size();i++){
            texto += players.get(i).toString() + "\n";
        }
        texto += "Las cartas en mesa son: \n";
        for(int i = 0; i < CardsMesa.size();i++){
            texto += CardsMesa.get(i).toString() + "\n";
        }
        texto += this.whoseTurnIsIt();
        return texto;
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
