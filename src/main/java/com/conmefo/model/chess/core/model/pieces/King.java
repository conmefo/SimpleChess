package com.conmefo.model.chess.core.model.pieces;

import java.util.ArrayList;
import java.util.List;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;

public class King extends Piece {
    final int dx[] = { 1, -1, 0, 0, -1, 1, 1, -1 };
    final int dy[] = { 0, 0, 1, -1, 1, -1, 1, -1 };
    public boolean hasMoved = false;

    public King(PieceColor color, boolean hasMoved) {
        super(PieceType.KING, color);
        this.hasMoved = hasMoved;
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

        if (!hasMoved) {
            if (board.isWithinBounds(new Position(position.row, position.col + 2)) && board.piece[position.row][position.col + 3] != null) {
                Piece rook = board.piece[position.row][position.col + 3];
                if (rook instanceof Rook && !((Rook) rook).hasMoved) {
                    moves.add(new Position(position.row, position.col + 2));
                }
            }

            if (board.isWithinBounds(new Position(position.row, position.col - 2)) && board.piece[position.row][position.col - 4] != null) {
                Piece rook = board.piece[position.row][position.col - 4];
                if (rook instanceof Rook && !((Rook) rook).hasMoved) {
                    moves.add(new Position(position.row, position.col - 2));
                }
            }
        }

        return moves;
    }

    @Override
    public List<Position> getPsuedoLegalAttacks(Position position, Board board) {
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
}
