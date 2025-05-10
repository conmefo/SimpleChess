package com.conmefo.desktop.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import com.conmefo.model.chess.core.model.GameState;
import com.conmefo.model.chess.core.model.Position;
import com.conmefo.model.chess.core.model.pieces.Piece;

public class BoardPanel extends JPanel{
    public final GameState gameStateRef;
    public final static int SQUARE_SIZE = 65;

    public final Color LIGHT_SQUARE_COLOR = new Color(255, 206, 158);
    public final Color DARK_SQUARE_COLOR = new Color(209, 139, 71);
    public final Color SELECTED_SQUARE_COLOR = new Color(0, 255, 0, 128);
    public final Color HIGHLIGHTED_SQUARE_COLOR = new Color(255, 255, 0, 128);
    

    public BoardPanel(GameState gameState) {
        this.gameStateRef = gameState;
        this.setPreferredSize(new java.awt.Dimension(SQUARE_SIZE * 11, SQUARE_SIZE * 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        drawBoard(g);

        drawPieces(g);
    }

    public void drawBoard(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLightSquare = (row + col) % 2 == 0;
                g.setColor(isLightSquare ? LIGHT_SQUARE_COLOR : DARK_SQUARE_COLOR);
                g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void drawPieces(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = gameStateRef.board.getPiece(new Position(row, col));
                if (piece != null) {
                    g.drawImage(PieceIconLoader.loadImage(piece), col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
                }
            }
        }
    }

    public Position getPosition(int x, int y) {
        int row = y / SQUARE_SIZE;
        int col = x / SQUARE_SIZE;
        return new Position(row, col);
    }        
}
