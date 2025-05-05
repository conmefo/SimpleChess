package com.conmefo.model.chess.core.model.pieces;

public enum PieceColor {
    WHITE,
    BLACK;

    public String getDisplayName(){
        return this == WHITE ? "White" : "Black";
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}