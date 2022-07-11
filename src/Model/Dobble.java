package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 */
public class Dobble implements IDobble {
    //          ATRIBUTOS         //
    private final List<Card> cardsSet;
    private final List<String> listaElementos;

    /**
     * Constructor del set de cartas
     * @param numElementos numero de elementos por carta
     * @param cantCartas cantidad de cartas del set
     * @param elementos elementos para armar el set de cartas
     *
     */
    public Dobble(int numElementos, int cantCartas, List<String> elementos) {
        ArrayList<Card> cartas = new ArrayList<>();
        List<String> elementosCarta = new ArrayList<>();
        int id = 1;

        for (int i = 0; i < numElementos; i++) {
            String element = elementos.get(i);
            elementosCarta.add(element);
        }
        Card firstCard = new Card(id, elementosCarta);
        cartas.add(firstCard);
        id++;
        for (int j = 1; j <= (numElementos - 1); j++) {
            elementosCarta = new ArrayList<>();
            elementosCarta.add(elementos.get(0));
            for (int k = 1; k <= (numElementos - 1); k++) {
                elementosCarta.add(elementos.get(((numElementos - 1) * j + (k + 1)) - 1));
            }
            Card carta;
            carta = new Card(id, elementosCarta);
            cartas.add(carta);
            id++;
        }

        for (int i = 1; i <= (numElementos - 1); i++) {
            for (int j = 1; j <= (numElementos - 1); j++) {
                elementosCarta = new ArrayList<>();
                elementosCarta.add(elementos.get(i));
                for (int k = 1; k <= (numElementos - 1); k++) {
                    elementosCarta.add(elementos.get(((numElementos - 1) + 2 + (k - 1)
                            * (numElementos - 1) + (((i - 1) * (k - 1) + j - 1)
                            % (numElementos - 1))) - 1));
                }
                Card carta;
                carta = new Card(id, elementosCarta);
                cartas.add(carta);
                id++;
            }
        }

        for (int i = cartas.size() - 1; i > 0; i--) {
            int randomInt = (int) Math.floor(Math.random() * (i + 1));
            Card carta = cartas.get(i);
            cartas.set(i, cartas.get(randomInt));
            cartas.set(randomInt, carta);
        }

        ArrayList<Card> cards = new ArrayList<>();
        if (cantCartas < ((numElementos - 1) + ((numElementos - 1) * (numElementos - 1)) + 1)
                && cantCartas > 0) {


            for (int i = 0; i < cantCartas; i++) {
                Card card = cartas.get(i);
                card.setId(i + 1);
                cards.add(card);
            }

        }else{
            for(int i = 0; i < cartas.size();i++){
                Card card = cartas.get(i);
                card.setId(i + 1);
                cards.add(card);
            }
        }
        this.cardsSet = cards;
        this.listaElementos = elementos;
    }

    //          GETTERS         //
    /**
     * Obtiene el set de cartas
     * @return retorna el set de cartas
     */
    public List<Card> getCardsSet() {
        return cardsSet;
    }

    //          METODOS AUXILIARES          //
    /**
     * Obtiene la carta en la posicion n-1 entregada
     * @param n posicion de la carta que se desea encontrar
     * @return carta en la posicion señalada
     */
    @Override
    public Card nthCard(int n) {
        return cardsSet.get(n);
    }

    /**
     * verifica si es o no un juego dobble válido
     * @return booleano que comprueba si es o no un set de cartas valido
     */
    @Override
    public boolean Isdobble(){
        for(int i = 0; i < (cardsSet.size() - 1);i++){
            for(int j = i+1; j < cardsSet.size();j++)
                if(cardsSet.get(i).verificarCarta(cardsSet.get(j).getCard())){
                    return false;
                }
        }
        return true;
    }

    /**
     * Calcula la cantidad de cartas que se pueden crear a partir de una carta
     * de muestra
     * @param cartaMuestra carta de muestra
     * @return cantidad de cartas que es posible crear
     */
    @Override
    public int FindTotalCards(Card cartaMuestra) {
        int i = cartaMuestra.size();
        return (((i - 1) * (i - 1) + (i - 1) + 1));
    }

    /**
     * calcula la cantidad de cartas
     * @return cantidad de cartas
     */
    @Override
    public int numCards() {
        return cardsSet.size();
    }

    /**
     * Calcula la cantidad de elementos necesarios para generar un set de cartas
     * válido
     * @param cartaMuestra carta de muestra
     * @return cantidad de elementos necesarios para generar el conjunto valido
     */
    @Override
    public int requiredElements(Card cartaMuestra) {
        int i = cartaMuestra.size();
        return (((i - 1) * (i - 1) + (i - 1) + 1));
    }

    /**
     * compara si la carta existe dentro del set de cartas a comparar
     * @param carta carta a comparar
     * @return booleano que verífica la existencia de la carta
     */
    @Override
    public boolean perteneceCard(Card carta){
        for (Card card : cardsSet) {
            if (carta.verificarCarta(card.getCard())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la lista de cartas que le faltan al mazo para estar completo
     * @return lista de cartas que le faltan al mazo para estar completo
     */
    @Override
    public List<Card> missingCards(){
        Dobble mazoCompleto = new Dobble(cardsSet.get(0).size(),0,listaElementos);
        for (Card card : cardsSet) {
            mazoCompleto.getCardsSet().remove(card);
        }
        for(int i = 0; i < mazoCompleto.getCardsSet().size();i++){
            mazoCompleto.getCardsSet().get(i).setId(i+1);
        }
        return mazoCompleto.getCardsSet();
    }

    /**
     * Elimina la n carta
     * @param n Número de la carta que se desea eliminar
     */
    @Override
    public void deleteCard(int n){
        cardsSet.remove(n-1);
    }

    /**
     * Crea una representacion del set de cartas como String
     * @return retorna la representacion como string del mazo
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("El cardsSet tiene las cartas:\n");
        for (Card card : cardsSet) {
            texto.append(card.toString()).append("\n");
        }
        return texto.toString();
    }

    /**
     * comprueba si dos dobble son iguales
     * @param o CardsSet a comparar
     * @return booleano de si es igual o no dos set de cartas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dobble dobble = (Dobble) o;
        return Objects.equals(cardsSet, dobble.cardsSet) && Objects.equals(listaElementos, dobble.listaElementos);
    }
}

