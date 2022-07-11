package Model;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jhoisan Allendes Fuentes
 *
 */


public class Card implements ICard {
    //          ATRIBUTOS         //
    private final List<String> card;
    private int id;


    /**
     * Constructor de la carta
     * @param id id de la carta
     * @param listElementos  elementos de la carta
     */
    public Card(int id, List<String> listElementos){
        this.id = id;
        this.card = listElementos;
    }

    //          GETTERS         //
    /**
     * Metodo para obtener la lista de elementos que compone la carta
     * @return elementos de la carta
     */
    public List<String> getCard() {
        return card;
    }

    //          SETTERS         //
    /**
     * Establece la id de la carta
     * @param id id de la carta
     */
    public void setId(int id) {
        this.id = id;
    }


    //          METODOS AUXILIARES          //
    /**
     * Comprueba si una lista esta dentro de los elementos de la carta
     * @param cardComp lista a comprobar
     * @return existe la lista en la carta
     */
    @Override
    public boolean verificarCarta(List<String> cardComp){
        int x = 0;
        for(int i = 0; i < card.size();i++){
            for(int j = 0; i < cardComp.size();i++)
                if(card.get(i).equals(cardComp.get(j))){
                    x++;
                }
        }
        return x >= cardComp.size();
    }

    /**
     * Comprueba la existencia de algún elemento en la carta
     * @param elemento elemento al cual se le verifica la existencia
     * @return booleano que comprueba la existencia del elemento en la carta
     */
    @Override
    public boolean existeElemento(String elemento){
        for (String s : card) {
            if (Objects.equals(s, elemento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * calcula la cantidad de elementos de las cartas
     * @return cantidad de elementos de las cartas
     */

    @Override
    public int size(){
        return card.size();
    }

    /**
     * comprueba si dos cartas son iguales
     * @param o carta a comparar
     * @return booleano de si es igual o no dos cartas
     */
    @Override
    public boolean equals(Object o) {
        Card card1 = (Card) o;
        for (String s : card) {
            if (card1.existeElemento(s)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Crea una representacion de la carta como String
     * @return retorna la representacion como string de la carta
     */

    public String toString2(){
        StringBuilder texto = new StringBuilder("Carta " + id + " : \n       ");
        for(int i = 0; i < (card.size() - 1); i++){
            texto.append(card.get(i)).append("\n       ");
        }
        texto.append(card.get((card.size() - 1)));
        return texto.toString();
    }

    /**
     * Crea una representacion de la carta como String
     * @return retorna la representacion como string de la carta
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("Carta " + id + " : ");
        for(int i = 0; i < (card.size() - 1); i++){
            texto.append(card.get(i)).append(", ");
        }
        texto.append(card.get((card.size() - 1))).append('.');
        return texto.toString();
    }

}