package com.conmefo.model.chess.core.model;

import com.conmefo.model.chess.core.model.pieces.Piece;

public class Board {
    public final Piece[][] piece = new Piece[8][8];
    public Position isDoubleMovePosition = null;

    public boolean isWithinBounds(Position position) {
        return position.row >= 0 && position.row < 8 && position.col >= 0 && position.col < 8;
    }

    public Board copy(){
        Board newBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.piece[i][j] != null) {
                    newBoard.piece[i][j] = this.piece[i][j];
                }
            }
        }
        return newBoard;
    }
}    

