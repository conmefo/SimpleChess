package com.conmefo.model.chess.core.model.pieces;

public enum PieceColor {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public String getDisplayName() {
        return this == BLACK ? "Black" : "White";
    }
}