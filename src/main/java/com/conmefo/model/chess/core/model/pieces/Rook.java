package com.conmefo.model.chess.core.model.pieces;

import java.util.ArrayList;
import java.util.List;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;

public class Rook extends Piece {
    final int dx[] = { 1, -1, 0, 0 };
    final int dy[] = { 0, 0, 1, -1 };

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public List<Position> getPsuedoLegalMoves(Position position, Board board) {
        List<Position> moves = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            int x = position.row;
            int y = position.col;
            while (true) {
                x += dx[i];
                y += dy[i];
                
                Position newPosition = new Position(x, y);
                
                if (!board.isWithinBounds(newPosition)) {
                    break;
                }
                
                if (board.piece[x][y] != null) {
                    if (board.piece[x][y].color != this.color) {
                        moves.add(newPosition);
                    }
                    break;
                }
                
                moves.add(newPosition);
            }
        }

        return moves;
    }

    @Override
    public List<Position> getPsuedoLegalAttacks(Position position, Board Board) {
        return getPsuedoLegalMoves(position, Board);
    }
}
