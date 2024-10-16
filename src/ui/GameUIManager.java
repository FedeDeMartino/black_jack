package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUIManager {
  private JButton hitButton;
  private JButton stayButton;
  private JPanel gamePanel;
  private JPanel buttonPanel;
  private JFrame frame;
  private final int boardWidth = 600;
  private final int boardHeight = boardWidth;

  public GameUIManager(GameRenderer gameRenderer) {
      setupUI(gameRenderer);
  }

  private void setupUI(GameRenderer gameRenderer) {
      frame = new JFrame("Black Jack");
      frame.setSize(boardWidth, boardHeight);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      gamePanel = gameRenderer.getGamePanel();
      frame.add(gamePanel, BorderLayout.CENTER);

      buttonPanel = new JPanel();
      hitButton = new JButton("Hit");
      stayButton = new JButton("Stay");

      hitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              gameRenderer.onHit();
              if (gameRenderer.isPlayerBusted()) {
                  hitButton.setEnabled(false);
              }
              gamePanel.repaint();
          }
      });

      stayButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              hitButton.setEnabled(false);
              stayButton.setEnabled(false);
              gameRenderer.onStay();
              gamePanel.repaint();
          }
      });

      buttonPanel.add(hitButton);
      buttonPanel.add(stayButton);
      frame.add(buttonPanel, BorderLayout.SOUTH);
      frame.setVisible(true);
  }

  public boolean isStayButtonEnabled() {
    return stayButton.isEnabled();
  }
}
