package com.conmefo.model.chess.core.model.pieces;

import java.util.ArrayList;
import java.util.List;
import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public List<Position> getPsuedoLegalMoves(Position position, Board board) {
        List<Position> moves = new ArrayList<>();

        int direction = (color == PieceColor.WHITE) ? 1 : -1;
        int startRow = (color == PieceColor.WHITE) ? 1 : 6;

        // Move forward one square
        Position forwardMove = new Position(position.row + direction, position.col);
        if (board.isWithinBounds(forwardMove) && board.piece[forwardMove.row][forwardMove.col] == null) {
            moves.add(forwardMove);
        }

        // Move forward two squares from starting position
        if (position.row == startRow) {
            Position doubleForwardMove = new Position(position.row + 2 * direction, position.col);
            if (board.isWithinBounds(doubleForwardMove) && board.piece[doubleForwardMove.row][doubleForwardMove.col] == null) {
                moves.add(doubleForwardMove);
            }
        }

        // Capture diagonally
        for (int i = -1; i <= 1; i += 2) {
            Position diagonalCapture = new Position(position.row + direction, position.col + i);
            if (board.isWithinBounds(diagonalCapture) && board.piece[diagonalCapture.row][diagonalCapture.col] != null &&
                board.piece[diagonalCapture.row][diagonalCapture.col].color != this.color) {
                moves.add(diagonalCapture);
            }
        }

        // En passant capture
        if (board.isDoubleMovePosition != null) {
            Position lastMove = board.isDoubleMovePosition;
            if (lastMove.row == position.row && Math.abs(lastMove.col - position.col) == 1) {
                Position enPassantCapture = new Position(position.row + direction, lastMove.col);
                if (board.isWithinBounds(enPassantCapture) && board.piece[enPassantCapture.row][enPassantCapture.col] == null) {
                    moves.add(enPassantCapture);
                }
            }
        }
        return moves;
    }

    @Override
    public List<Position> getPsuedoLegalAttacks(Position position, Board board) {
        List<Position> moves = new ArrayList<>();

        int direction = (color == PieceColor.WHITE) ? 1 : -1;

        for (int i = -1; i <= 1; i += 2) {
            Position diagonalCapture = new Position(position.row + direction, position.col + i);
            if (board.isWithinBounds(diagonalCapture) && board.piece[diagonalCapture.row][diagonalCapture.col] != null &&
                board.piece[diagonalCapture.row][diagonalCapture.col].color != this.color) {
                moves.add(diagonalCapture);
            }
        }

        return moves;
    }

    
}