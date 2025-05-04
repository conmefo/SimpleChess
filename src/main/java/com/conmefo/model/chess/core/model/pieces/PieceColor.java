package com.conmefo.model.chess.core.model.pieces;

enum PieceColor {
    WHITE,
    BLACK;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}