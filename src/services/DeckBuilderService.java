package services;
import java.util.ArrayList;

import domain.Card;

public class DeckBuilderService {
    public ArrayList<Card> buildDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        String[] types = {"H", "D", "C", "S"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String type : types) {
            for (String value : values) {
                deck.add(new Card(value, type));
            }
        }

        return deck;
    }
}
