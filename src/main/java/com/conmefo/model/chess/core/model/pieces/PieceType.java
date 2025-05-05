package com.conmefo.model.chess.core.model.pieces;

public  enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING;

    public String getDisplayName() {
        switch (this) {
            case PAWN:
                return "Pawn";
            case ROOK:
                return "Rook";
            case KNIGHT:
                return "Knight";
            case BISHOP:
                return "Bishop";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                throw new IllegalArgumentException("Unknown piece type: " + this);
        }
    }

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
