package ui;
import javax.swing.*;
import java.awt.*;

import domain.Card;
import services.GameLogicService;

public class GameRenderer {
    private GameLogicService gameLogicService;
    private GameUIManager gameUIManager;
    private JPanel gamePanel;
    private final String BACK_CARD_PATH = "../assets/cards/BACK.png";
    private final int cardWidth = 110;
    private final int cardHeight = 154;

    public GameRenderer(GameLogicService gameLogicService) {
        this.gameLogicService = gameLogicService;
        setupGamePanel();
    }

    public void setGameUIManager(GameUIManager gameUIManager) {
        this.gameUIManager = gameUIManager;
    }

    private void setupGamePanel() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        gamePanel.setBackground(new Color(53, 101, 77));
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    private void drawGame(Graphics g) {
        try {
            Image hiddenCardImage = new ImageIcon(getClass().getResource(BACK_CARD_PATH)).getImage();
            if (gameUIManager != null && !gameUIManager.isStayButtonEnabled()) {
                hiddenCardImage = new ImageIcon(getClass().getResource(gameLogicService.getHiddenCard().getImagePath())).getImage();
            }
            g.drawImage(hiddenCardImage, 20, 20, cardWidth, cardHeight, null);

            drawDealerCard(g);
            drawPlayerCard(g);
            displayResult(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawDealerCard(Graphics g) {
        for (int i = 0; i < gameLogicService.getDealerHand().size(); i++) {
          Card card = gameLogicService.getDealerHand().get(i);
          Image cardImage = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
          g.drawImage(cardImage, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
        }
      }
  
    private void drawPlayerCard(Graphics g) {
        for (int i = 0; i < gameLogicService.getPlayerHand().size(); i++) {
            Card card = gameLogicService.getPlayerHand().get(i);
            Image cardImage = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
            g.drawImage(cardImage, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
        }
    }

    private void displayResult(Graphics g) {
        if (gameUIManager != null && !gameUIManager.isStayButtonEnabled()) {
            String message = getResultMessage();
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.setColor(Color.WHITE);
            g.drawString(message, 220, 250);
        }
    }

    private String getResultMessage() {
        int dealerSum = gameLogicService.getDealerSum();
        int playerSum = gameLogicService.getPlayerSum();
        if (dealerSum > 21) {
            return "Dealer busts. You win!";
        } else if (playerSum > 21) {
            return "You bust. Dealer wins!";
        } else if (dealerSum > playerSum) {
            return "Dealer wins!";
        } else if (dealerSum < playerSum) {
            return "You win!";
        } else {
            return "It's a tie!";
        }
    }

    public void onHit() {
        gameLogicService.playerHit();
    }

    public void onStay() {
        gameLogicService.dealerTurn();
    }

    public boolean isPlayerBusted() {
        return gameLogicService.isPlayerBusted();
    }
}
