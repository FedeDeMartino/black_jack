package services;
import java.util.ArrayList;

import domain.Card;

public class GameLogicService {
    private ArrayList<Card> deck;
    private DeckBuilderService deckBuilderService;
    private DeckShufflerService deckShufflerService;

    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;
    private Card hiddenCard;

    private int dealerSum;
    private int dealerAceCount;

    private int playerSum;
    private int playerAceCount;

    public GameLogicService(DeckBuilderService deckBuilderService, DeckShufflerService deckShufflerService) {
        this.deckBuilderService = deckBuilderService;
        this.deckShufflerService = deckShufflerService;
        startGame();
    }

    public void startGame() {
        deck = deckBuilderService.buildDeck();
        deckShufflerService.shuffleDeck(deck);
        resetHands();
    }

    private void resetHands() {
        dealerHand = new ArrayList<>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size() - 1);
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card dealerCard = deck.remove(deck.size() - 1);
        dealerSum += dealerCard.getValue();
        dealerAceCount += dealerCard.isAce() ? 1 : 0;
        dealerHand.add(dealerCard);

        playerHand = new ArrayList<>();
        playerSum = 0;
        playerAceCount = 0;

        for (int i = 0; i < 2; i++) {
            Card playerCard = deck.remove(deck.size() - 1);
            playerSum += playerCard.getValue();
            playerAceCount += playerCard.isAce() ? 1 : 0;
            playerHand.add(playerCard);
        }
    }

    public void playerHit() {
        Card card = deck.remove(deck.size() - 1);
        playerSum += card.getValue();
        playerAceCount += card.isAce() ? 1 : 0;
        playerHand.add(card);
    }

    public void dealerTurn() {
        while (dealerSum < 17) {
            Card card = deck.remove(deck.size() - 1);
            dealerSum += card.getValue();
            dealerAceCount += card.isAce() ? 1 : 0;
            dealerHand.add(card);
        }
    }

    public int reducePlayerAce() {
        while (playerSum > 21 && playerAceCount > 0) {
            playerSum -= 10;
            playerAceCount--;
        }
        return playerSum;
    }

    public int reduceDealerAce() {
        while (dealerSum > 21 && dealerAceCount > 0) {
            dealerSum -= 10;
            dealerAceCount--;
        }
        return dealerSum;
    }

    public boolean isPlayerBusted() {
        return reducePlayerAce() > 21;
    }


    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public int getPlayerSum() {
        return reducePlayerAce();
    }

    public int getDealerSum() {
        return reduceDealerAce();
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }
}
