package com.conmefo.model.chess.core.rules;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;
import com.conmefo.model.chess.core.model.pieces.*;
public class GameRules {
    public Position findKingPosition(Board board, PieceColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.piece[i][j];
                if (piece != null && piece.type.isKing() && piece.color == color) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public boolean isSquareAttacked(Position position, Board board, PieceColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.piece[i][j];
                if (piece != null && piece.color != color) {
                    if (piece.getPsuedoLegalAttacks(new Position(i, j), board).contains(position)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}   
