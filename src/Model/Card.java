package Model;
import java.util.List;
/**
 *
 * @author Jhoisan Allendes Fuentes
 *
 */


public class Card implements ICard {
    //          ATRIBUTOS         //
    private List<String> card;
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
     * Comprueba si una lista esta dento de los elementos de la carta
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
        if(x < cardComp.size()){
            return false;
        }
        return true;
    }

    /**
     * Comprueba la existencia de algun elemento en la carta
     * @param elemento
     * @return booleano que comprueba la existencia del elemento en la carta
     */
    @Override
    public boolean existeElemento(String elemento){
        for(int i = 0; i < card.size();i++){
            if(card.get(i) == null ? elemento == null : card.get(i).equals(elemento)){
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
        for(int i = 0; i < card.size(); i ++){
            if (card1.existeElemento(card.get(i))){
                return true;
            }
        }
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }
    /**
     * Crea una representacion de la carta como String
     * @return retorna la representacion como string de la carta
     */

    public String toString2(){
        String texto = "Carta " + id + " : \n       ";
        for(int i = 0; i < (card.size() - 1); i++){
            texto += card.get(i) + "\n       ";
        }
        texto += card.get((card.size()-1));
        return texto;
    }

    /**
     * Crea una representacion de la carta como String
     * @return retorna la representacion como string de la carta
     */
    @Override
    public String toString() {
        String texto = "Carta " + id + " : ";
        for(int i = 0; i < (card.size() - 1); i++){
            texto += card.get(i) + ", ";
        }
        texto += card.get((card.size()-1)) + '.';
        return texto;
    }

}