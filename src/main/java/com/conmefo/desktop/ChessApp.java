package com.conmefo.desktop;

import com.conmefo.desktop.view.BoardPanel;
import com.conmefo.desktop.view.MainFrame;
import com.conmefo.model.chess.core.model.GameState;

public class ChessApp {
    public static void main(String[] args) {
        GameState gameState = new GameState();
        BoardPanel boardPanel = new BoardPanel(gameState);
        MainFrame mainFrame = new MainFrame();
        mainFrame.add(boardPanel, "Center");
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }
}
