package com.conmefo.desktop.view;

import javax.imageio.ImageIO; 
import java.awt.image.BufferedImage;
import java.io.IOException;


import com.conmefo.model.chess.core.model.pieces.Piece;

public final class PieceIconLoader {
   // private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage loadImage(Piece piece) {
		BufferedImage img = null;
        String imagePath = "/images/" + piece.color.getDisplayName() + piece.type.getDisplayName() + ".png";
		//System.out.println("Loading image: " + imagePath);
		try {
			img = ImageIO.read(PieceIconLoader.class.getResourceAsStream(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}



