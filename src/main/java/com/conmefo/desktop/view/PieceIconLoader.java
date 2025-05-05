package com.conmefo.desktop.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.conmefo.model.chess.core.model.pieces.Piece;

public class PieceIconLoader {
    private static final Map<String, ImageIcon> cache = new HashMap<>();

    public static String buildFilenameKey(Piece piece) {
        if (piece == null) {
            return null;
        }

        String filenameKey = new String();
        filenameKey = piece.color.getDisplayName() + piece.type.getDisplayName() + ".png";

        return filenameKey.toString();
    }

    public static ImageIcon loadImages(Piece piece){
        if (piece == null) {
            return null;
        }

        String filenameKey = buildFilenameKey(piece);

        if (cache.containsKey(filenameKey)) {
            return cache.get(filenameKey);
        }

        String path = "/images/" + filenameKey;
        ImageIcon icon = new ImageIcon(PieceIconLoader.class.getResource(path));
        if (icon != null) {
            cache.put(filenameKey, icon);
            return icon;
        }

        return null;
    }
}
