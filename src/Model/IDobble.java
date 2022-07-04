package Model;
import java.util.List;
public interface IDobble {
    Card nthCard(int n);

    boolean Isdobble();

    int FindTotalCards(Card cartaMuestra);

    int numCards();

    int requiredElements(Card cartaMuestra);

    boolean perteneceCard(Card carta);

    List<Card> missingCards();

    void deleteCard(int n);
}
