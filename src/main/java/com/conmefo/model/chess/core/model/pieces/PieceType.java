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

    public String getDisplayName() {
        if (this == QUEEN){
            return "Queen";
        }

        if (this == KING){
            return "King";
        }

        if (this == BISHOP){
            return "Bishop";
        }

        if (this == ROOK){
            return "Rook";
        }

        if (this == PAWN){
            return "Pawn";
        }

        return "Knight";
    }
} 
