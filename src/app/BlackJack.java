package app;
import services.DeckBuilderService;
import services.DeckShufflerService;
import services.GameLogicService;
import ui.GameRenderer;
import ui.GameUIManager;

public class BlackJack {
  private GameLogicService gameLogicService;
    private GameRenderer gameRenderer;
    private GameUIManager gameUIManager;

    public BlackJack() {
        DeckBuilderService deckBuilderService = new DeckBuilderService();
        DeckShufflerService deckShufflerService = new DeckShufflerService();
        gameLogicService = new GameLogicService(deckBuilderService, deckShufflerService);
        gameRenderer = new GameRenderer(gameLogicService);
        gameUIManager = new GameUIManager(gameRenderer);
        gameRenderer.setGameUIManager(gameUIManager);
    }
}
