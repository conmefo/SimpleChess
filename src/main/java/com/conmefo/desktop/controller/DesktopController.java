package com.conmefo.desktop.controller;

import java.awt.event.*;

import com.conmefo.desktop.view.BoardPanel;
import com.conmefo.model.chess.core.model.GameState;
import com.conmefo.model.chess.core.model.Position;

public class DesktopController extends MouseAdapter {
	public final GameState gameState;
	public final BoardPanel boardPanel;
	public Position selectedPosition;
	
	DesktopController(GameState gameState, BoardPanel boardPanel) {
		this.gameState = gameState;
		this.boardPanel = boardPanel;
		this.selectedPosition = null;
	}
	
	@Override
	public void mousePressed (MouseEvent e) {
		selectedPosition = new Position	(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		selectedPosition = null;
	}
	
	@Override
	public void mouseDragged (MouseEvent e) {
		selectedPosition = new Position	(e.getX(), e.getY());
	}
	
	@Override
	public void mouseMoved (MouseEvent e) {
		selectedPosition = new Position	(e.getX(), e.getY 	());
	}
}