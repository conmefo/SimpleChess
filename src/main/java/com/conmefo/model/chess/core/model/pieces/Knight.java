package com.conmefo.model.chess.core.model.pieces;

import java.util.ArrayList;
import java.util.List;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;

public class Knight extends Piece {
    final int dx[] = { 2, -2, 2, -2, 1, -1, 1, -1 };
    final int dy[] = { 1, 1, 1, -1, 2, 2, -2, -2 };

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public List<Position> getPsuedoLegalMoves(Position position, Board board) {
        List<Position> moves = new ArrayList<>();
        
        for (int i = 0; i < 8; i++) {
            int x = position.row;
            int y = position.col;

            x += dx[i];
            y += dy[i];
            
            Position newPosition = new Position(x, y);
            
            if (!board.isWithinBounds(newPosition)) {
                continue;
            }
            
            if (board.piece[x][y] != null) {
                if (board.piece[x][y].color != this.color) {
                    moves.add(newPosition);
                }
                continue;
            }
            
            moves.add(newPosition);
        }

        return moves;
    }

    @Override
    public List<Position> getPsuedoLegalAttacks(Position position, Board Board) {
        return getPsuedoLegalMoves(position, Board);
    }
}
