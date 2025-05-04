package com.conmefo.model.chess.core.model.pieces;

import java.util.List;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.Position;

abstract public class Piece {
    public final PieceType type;
    public final PieceColor color;
    
    Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public abstract List<Position> getPsuedoLegalMoves(Position position, Board Board);
    public abstract List<Position> getPsuedoLegalAttacks(Position position, Board Board);
}
