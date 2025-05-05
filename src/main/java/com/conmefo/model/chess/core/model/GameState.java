package com.conmefo.model.chess.core.model;

import java.util.ArrayList;
import java.util.List;

import com.conmefo.model.chess.core.model.pieces.*;
import com.conmefo.model.chess.core.rules.GameRules;

public class GameState {
    public Board board;
    public GameStatus status;
    public PieceColor currentTurn;

    public GameState() {
        this.board = new Board();
        this.status = GameStatus.WHITE_TURN;
        this.currentTurn = PieceColor.WHITE;
    }

    public boolean attemptMove(Position from, Position to) {
        Piece piece = board.getPiece(from);

        if (piece == null || piece.color != currentTurn) {
            return false; // Invalid move
        }

        List<Position> legalMoves = GameRules.getLegalMoves(from, board);

        for (Position legalMove : legalMoves) {
            if (legalMove.equals(to)) {
                
                board.simulateMove(from, to);
                
                currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
                status = GameRules.determineGameStatus(board, status);

                return true; 
            }
        }

        return false; 
    }
}
