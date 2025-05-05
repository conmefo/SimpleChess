package com.conmefo.model.chess.core.rules;

import java.util.List;
import java.util.ArrayList;

import com.conmefo.model.chess.core.model.Board;
import com.conmefo.model.chess.core.model.GameStatus;
import com.conmefo.model.chess.core.model.Position;
import com.conmefo.model.chess.core.model.pieces.*;

public class GameRules {

    public static GameStatus determineGameStatus(Board board, GameStatus gameStatus) {
        Position whiteKingPosition = findKingPosition(board, PieceColor.WHITE);
        Position blackKingPosition = findKingPosition(board, PieceColor.BLACK);

        boolean isWhiteInCheck = isSquareAttacked(whiteKingPosition, board, PieceColor.WHITE);
        boolean isBlackInCheck = isSquareAttacked(blackKingPosition, board, PieceColor.BLACK);

        boolean whiteCanMove = false;
        boolean blackCanMove = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position currentPosition = new Position(i, j);
                Piece piece = board.piece[i][j];
                if (piece != null) {
                    List<Position> legalMoves = getLegalMoves(currentPosition, board);
                    if (piece.color == PieceColor.WHITE && !legalMoves.isEmpty()) {
                        whiteCanMove = true;
                    } else if (piece.color == PieceColor.BLACK && !legalMoves.isEmpty()) {
                        blackCanMove = true;
                    }
                }
            }
        }

       if (gameStatus == GameStatus.BLACK_TURN && !whiteCanMove) {
            if (isWhiteInCheck) {
                return GameStatus.WHITE_CHECKMATE;
            } else {
                return GameStatus.STALEMATE;
            }
        }

        if (gameStatus == GameStatus.WHITE_TURN && !blackCanMove) {
            if (isBlackInCheck) {
                return GameStatus.BLACK_CHECKMATE;
            } else {
                return GameStatus.STALEMATE;
            }
        }

        return (gameStatus == GameStatus.WHITE_TURN) ? GameStatus.BLACK_TURN : GameStatus.WHITE_TURN;
    }
    
    public static List<Position> getLegalMoves(Position position, Board board) {
        Piece piece = board.piece[position.row][position.col];
        if (piece == null) {
            return new ArrayList<>();
        }
        
        List<Position> psuedoLegalMoves = piece.getPsuedoLegalMoves(position, board);
        List<Position> legalMoves = new ArrayList<>(); 
        
        for (Position moves : psuedoLegalMoves){
            Board tempBoard = board.copy(); 

            Position from = position;
            Position to = moves;

            if (isCastling(from, to, board)){
                if (to.col > from.col) {
                    if (!board.checkEmpty(from.row, from.col + 1, to.col) || isSquareAttacked(new Position(from.row, from.col + 1), board, piece.color) || isSquareAttacked(new Position(from.row, to.col), board, piece.color)) {
                        continue;
                    }
                } else {
                    if (!board.checkEmpty(from.row, to.col, from.col - 1) || isSquareAttacked(new Position(from.row, to.col), board, piece.color) || isSquareAttacked(new Position(from.row, from.col - 1), board, piece.color)) {
                        continue;
                    }
                }
            }
            

            tempBoard.simulateMove(position, moves);

            Position kingPosition = findKingPosition(tempBoard, piece.color);
            if (isSquareAttacked(kingPosition, tempBoard, piece.color)) {
                continue;
            }

            legalMoves.add(moves);
        }

        return legalMoves;
    }
    
    boolean isEnPassant (Position from, Position to, Board board) {
        Piece piece = board.piece[from.row][from.col];
        if (piece == null || !piece.type.isPawn()) {
            return false;
        }
        
        if (Math.abs(from.row - to.row) != 1 || Math.abs(from.col - to.col) != 1) {
            return false;
        }
        
        Piece targetPiece = board.piece[from.row][to.col];
        if (!(targetPiece instanceof Pawn)  || targetPiece.color == piece.color) {
            return false;
        }


        if (board.piece[to.row][to.col] != null) {
            return false;
        }
        
        return true;
    }
    
    static boolean isCastling (Position from, Position to, Board board) {
        Piece piece = board.piece[from.row][from.col];
        if (piece == null || !piece.type.isKing()) {
            return false;
        }
        
        // if (((King) piece).hasMoved) {
        //     return false;
        // }
        
        // if (Math.abs(from.col - to.col) != 2) {
        //     return false;
        // }
        
        int rookCol = (to.col > from.col) ? 7 : 0;
        Piece rook = board.piece[from.row][rookCol];
        
        if (rook == null || !rook.type.isRook()) {
            return false;
        }

        return true;
    }

    public static Position findKingPosition(Board board, PieceColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.piece[i][j];
                if (piece != null && piece.type.isKing() && piece.color == color) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public static boolean isSquareAttacked(Position position, Board board, PieceColor color) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.piece[i][j];
                if (piece != null && piece.color != color) {
                    if (piece.getPsuedoLegalAttacks(new Position(i, j), board).contains(position)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}   
