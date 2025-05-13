package com.conmefo.desktop.view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import com.conmefo.desktop.controller.Mouse;
import com.conmefo.model.chess.core.model.GameState;
import com.conmefo.model.chess.core.model.Position;
import com.conmefo.model.chess.core.model.pieces.Piece;

public class BoardPanel extends JPanel implements Runnable{
    public final GameState gameStateRef;
    public final static int SQUARE_SIZE = 66;
    public final static int HALF_SIZE = SQUARE_SIZE / 2;

    public final Color LIGHT_SQUARE_COLOR = new Color(255, 206, 158);
    public final Color DARK_SQUARE_COLOR = new Color(209, 139, 71);
    public final Color SELECTED_SQUARE_COLOR = new Color(0, 255, 0, 128);
    public final Color HIGHLIGHTED_SQUARE_COLOR = new Color(255, 255, 0, 128);
    
    public final int FPS = 120;
    final double TIME_PER_FRAME = 1000000000 / FPS;
    
    Mouse mouse = new Mouse();
    
    public Position activePiecePosition;
    boolean isPressed = true;

    Thread gameThread;

    
    public BoardPanel(GameState gameState) {
        this.gameStateRef = gameState;
        this.setPreferredSize(new java.awt.Dimension(SQUARE_SIZE * 11, SQUARE_SIZE * 8));
        activePiecePosition = null;
        addMouseMotionListener(mouse);
		addMouseListener(mouse);
    }

    @Override
	public void run() {
		double delta = 0;
		long lastTime = System.nanoTime();
		
		while (gameThread != null) {
			
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / TIME_PER_FRAME;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
	}

    public void update() {
        System.out.println("running");
        if (mouse.dragging) return;
		if (mouse.pressed) {
			if (activePiecePosition == null) {
                Piece piece = gameStateRef.board.getPiece(getPosition(mouse.x, mouse.y));
				if (piece != null && piece.color == gameStateRef.currentTurn){
                    activePiecePosition = getPosition(mouse.x, mouse.y);
                }
			} 
            // else if (gameStateRef.attemptMove(activePiecePosition, getPosition(mouse.x, mouse.y))){
            //         activePiecePosition = null;
			// 	} else {
            //             Piece piece = gameStateRef.board.getPiece(getPosition(mouse.x, mouse.y));
            //             if (piece != null && piece.color == gameStateRef.currentTurn){
            //                 activePiecePosition = getPosition(mouse.x, mouse.y);
            //             }
			// 	}
		} else {
			if (activePiecePosition != null && mouse.released == true) {
				if (gameStateRef.attemptMove(activePiecePosition, getPosition(mouse.x, mouse.y))){
					activePiecePosition = null;
				} else {
					System.out.println("Invalid move! " + activePiecePosition + " " + getPosition(mouse.x, mouse.y));
				}
                mouse.released = false;
			}
		}

        
	}

    public void LaunchGame() {
		gameThread = new Thread(this);
		gameThread.start();
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
                Position pos = new Position(row, col);
                Piece piece = gameStateRef.board.getPiece(new Position(row, col));
                if (!pos.equals(activePiecePosition)){
                    if (piece != null) {
                        g.drawImage(PieceIconLoader.loadImage(piece), col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
                    }
                } 
            }
        }

        if (activePiecePosition != null){
      //      System.out.println(gameStateRef.board.getPiece(activePiecePosition).type + " " + activePiecePosition);
        }

        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                if (row == 7 && col == 8 && activePiecePosition.row == 7){
                //    System.out.println("zz");
                }
                if (pos.equals(activePiecePosition)){
                    Piece piece = gameStateRef.board.getPiece(new Position(row, col));
                  //  System.out.println("draw active piece");
                    g.drawImage(PieceIconLoader.loadImage(piece), mouse.x - HALF_SIZE, mouse.y - HALF_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
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
