package com.conmefo.desktop.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.conmefo.model.chess.core.model.Position;

public class PieceTransferable implements Transferable {
    public static final DataFlavor PIECE_POSITION_FLAVOR = new DataFlavor (Position.class, "Chess Piec Position");  
    public static final DataFlavor[]  SUPPORTED_FLAVORS = {PIECE_POSITION_FLAVOR};
    public final Position originalPiecePosition;

    public PieceTransferable (Position originalPIecePosition){
        this.originalPiecePosition = originalPIecePosition;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS.clone();
    }

    @Override 
    public boolean isDataFlavorSupported(DataFlavor askeDataFlavor){
        return PIECE_POSITION_FLAVOR.equals(askeDataFlavor);
    }

    @Override 
    public Object getTransferData(DataFlavor askeDataFlavor)    
}