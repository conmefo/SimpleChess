package com.conmefo.desktop.controller;

import java.awt.event.*;

public class Mouse extends MouseAdapter{
	public int x, y;
	public boolean pressed;
	public boolean dragging;
	public boolean released;
	
	@Override
	public void mousePressed (MouseEvent e) {
		pressed = true;
		released = false;
	}
	
	@Override
	public void mouseReleased (MouseEvent e) {
		pressed = false;
		dragging = false;
		released = true;
	}
	
	@Override
	public void mouseDragged (MouseEvent e) {
		x = e.getX();
		y = e.getY();
		dragging = true;
		released = false;
	}
	
	@Override
	public void mouseMoved (MouseEvent e) {
		x = e.getX();
		y = e.getY();
		released = false;
	}
}