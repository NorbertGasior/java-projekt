package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Load extends MouseAdapter{
	Gameboard gameboard;
	public Load(Gameboard gameboard) {
		this.gameboard=gameboard;
	
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		int col = e.getX()/gameboard.SizeofTile;
		int row = e.getY()/gameboard.SizeofTile;
		
		Pieces PieceXY = gameboard.getPiece(col, row);
		if(PieceXY != null) {
			gameboard.selectedPiece = PieceXY;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int colu = e.getX()/gameboard.SizeofTile;
		int row = e.getY()/gameboard.SizeofTile;
		
		if(gameboard.selectedPiece != null) {
			Move move = new Move(gameboard, gameboard.selectedPiece, colu, row);
			if(gameboard.logic.isValidMove(move)) {
				gameboard.logic.makeMove(move);
				gameboard.logic.updateGameState();
			}else {
				gameboard.selectedPiece.xPos = gameboard.selectedPiece.columns * gameboard.SizeofTile;
				gameboard.selectedPiece.yPos = gameboard.selectedPiece.rows * gameboard.SizeofTile;
			}
		}
		gameboard.selectedPiece = null;
		
		gameboard.repaint();
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(gameboard.selectedPiece != null) {
			gameboard.selectedPiece.xPos = e.getX() - gameboard.SizeofTile/2;
			gameboard.selectedPiece.yPos = e.getY() - gameboard.SizeofTile/2;
			
			gameboard.repaint();
		}
		
		
	}	
}
