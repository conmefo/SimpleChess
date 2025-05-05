package com.conmefo.model.chess.core.model;

import java.io.PipedOutputStream;

import com.conmefo.model.chess.core.model.pieces.*;


public class Board {
    public final Piece[][] piece = new Piece[8][8];
    public Position isDoubleMovePosition = null;

    public void initializeBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piece[i][j] = null;
            }
        }

        for (int i = 0; i < 8; i++) {
            piece[1][i] = new Pawn(PieceColor.WHITE);
            piece[6][i] = new Pawn(PieceColor.BLACK);
        }

        piece[0][0] = new Rook(PieceColor.WHITE, false);
        piece[0][1] = new Knight(PieceColor.WHITE);
        piece[0][2] = new Bishop(PieceColor.WHITE);
        piece[0][3] = new Queen(PieceColor.WHITE);
        piece[0][4] = new King(PieceColor.WHITE, false);
        piece[0][5] = new Bishop(PieceColor.WHITE);
        piece[0][6] = new Knight(PieceColor.WHITE);
        piece[0][7] = new Rook(PieceColor.WHITE, false);

        piece[7][0] = new Rook(PieceColor.BLACK, false);
        piece[7][1] = new Knight(PieceColor.BLACK);
        piece[7][2] = new Bishop(PieceColor.BLACK);
        piece[7][3] = new Queen(PieceColor.BLACK);
        piece[7][4] = new King(PieceColor.BLACK, false);
        piece[7][5] = new Bishop(PieceColor.BLACK);
        piece[7][6] = new Knight(PieceColor.BLACK);
        piece[7][7] = new Rook(PieceColor.BLACK, false);
    }

    public boolean isWithinBounds(Position position) {
        return position.row >= 0 && position.row < 8 && position.col >= 0 && position.col < 8;
    }

    public void setPiece(Position position, Piece piece) {
        this.piece[position.row][position.col] = piece;
    }

    public Piece getPiece(Position position) {
        return piece[position.row][position.col];
    }

    public boolean checkEmpty (int row, int colFrom, int colTo){
        for (int i = colFrom; i <= colTo; i++){
            if (piece[row][i] != null){
                return false;
            }
        }
        return true;
    }

    public void simulateMove (Position from, Position to){
        Piece curPiece = piece[from.row][from.col];

        if (curPiece.type.isPawn() && piece[to.row][to.col] == null && Math.abs(from.col - to.col) == 1 && Math.abs(from.row - to.row) == 1){
            piece[from.row][to.col] = null;
        }

        piece[to.row][to.col] = curPiece;
        piece[from.row][from.col] = null;
        
        if (curPiece.type.isKing() && Math.abs(from.col - to.col) == 2){
            // Castling move
            if (to.col > from.col) {
                // Move the rook to the new position
                piece[to.row][to.col - 1] = piece[to.row][7];
                piece[to.row][7] = null;
            } else {
                // Move the rook to the new position
                piece[to.row][to.col + 1] = piece[to.row][0];
                piece[to.row][0] = null;
            } 
        }

        if (curPiece.type.isPawn() && Math.abs(from.row - to.row) == 2){
            isDoubleMovePosition = to;
        } else {
            isDoubleMovePosition = null;
        }
    }

    public Board copy(){
        Board newBoard = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.piece[i][j] != null) {
                    newBoard.piece[i][j] = this.piece[i][j];
                }
            }
        }
        return newBoard;
    }
}    

