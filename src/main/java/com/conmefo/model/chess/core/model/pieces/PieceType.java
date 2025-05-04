package com.conmefo.model.chess.core.model.pieces;

public  enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING;

    public boolean isPawn() {
        return this == PAWN;
    }

    public boolean isRook() {
        return this == ROOK;
    }

    public boolean isKnight() {
        return this == KNIGHT;
    }

    public boolean isBishop() {
        return this == BISHOP;
    }

    public boolean isQueen() {
        return this == QUEEN;
    }

    public boolean isKing() {
        return this == KING;
    }
} 
