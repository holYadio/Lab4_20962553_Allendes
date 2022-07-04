package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jhoisan Allendes Fuentes
 */
public class Player implements IPlayer {
    //          ATRIBUTOS         //
    private String nombre;
    private int id;
    private List<Card> mazoPlayer;
    private int puntos;

    /**
     * Constructor del Jugador
     * @param nombre Nombre del Jugador
     * @param id Id del Jugador
     */
    public Player(String nombre, int id){
        this.nombre = nombre;
        this.id = id;
        this.mazoPlayer = new ArrayList<>();
        this.puntos = 0;
    }


    //          GETTERS         //

    /**
     * obtiene el nombre del jugador
     * @return nombre del Jugador
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la id del jugador
     * @return id del jugador
     */
    @Override
    public int getId(){
        return id;
    }

    /**
     * Obtiene el mazo del jugador
     * @return Mazo de cartas del jugador
     */
    @Override
    public List<Card> getMazoPlayer() {
        return mazoPlayer;
    }

    /**
     * obtiene la cantidad de puntos del Jugador
     * @return retorna los puntos del jugador
     */
    @Override
    public int getPuntos() {
        return puntos;
    }

    //          SETTERS         //
    /**
     * Establece la cantidad de puntos del jugador
     * @param puntos puntos asociados al jugador
     */
    @Override
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //          METODOS AUXILIARES          //
    /**
     * Crea una representacion del jugador como String
     * @return retorna la representacion como string del jugador
     */
    @Override
    public String toString() {
        String texto = "Jugador " + id + ": " + nombre + "\n";
        texto += "El mazo del jugador es: \n";
        for (int i = 0; i < (mazoPlayer.size()); i++) {
            texto += mazoPlayer.get(i).toString() + "\n";
        }
        texto += "El jugador tiene un puntaje de " + puntos + "\n";
        return texto;
    }

    /**
     * comprueba si dos jugadores son iguales
     * @param o Jugador a comparar
     * @return booleano de si es igual o no dos Jugadores
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getId() == player.getId() && getNombre().equals(player.getNombre()) && Objects.equals(getMazoPlayer(), player.getMazoPlayer());
    }

}