package services;
import java.util.ArrayList;
import java.util.Random;

import domain.Card;

public class DeckShufflerService {
    public void shuffleDeck(ArrayList<Card> deck) {
        Random rand = new Random();
        for (int i = 0; i < deck.size(); i++) {
            int randomIndexToSwap = rand.nextInt(deck.size());
            Card temp = deck.get(randomIndexToSwap);
            deck.set(randomIndexToSwap, deck.get(i));
            deck.set(i, temp);
        }

        System.out.println("Shuffled deck");
        System.out.println(deck);
    }
}
