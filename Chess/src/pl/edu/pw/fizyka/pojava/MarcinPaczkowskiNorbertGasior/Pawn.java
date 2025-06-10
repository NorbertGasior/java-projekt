package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.image.BufferedImage;

public class Pawn extends Pieces{

	public Pawn(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Pawn";
		this.sprite = sheet.getSubimage(5*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
			int colorindex = isWhite ? 1 : -1;
			if(this.columns == columns && rows == this.rows - colorindex && gameboard.getPiece(columns, rows)==null) {
				return true;
			}
			if(isFirstmove && this.columns == columns && rows == this.rows -colorindex*2 && gameboard.getPiece(columns, rows) ==null && gameboard.getPiece(columns, rows + colorindex)==null ) {
				return true;
			}
			if(columns == this.columns -1 && rows == this.rows - colorindex && gameboard.getPiece(columns, rows)!=null) {
				return true;
			}
			if(columns == this.columns +1 && rows == this.rows - colorindex && gameboard.getPiece(columns, rows)!=null) {
				return true;
			}
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns-1 && rows == this.rows - colorindex && gameboard.getPiece(columns, rows+colorindex)!=null) {
				return true;
			}
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns+1 && rows == this.rows - colorindex && gameboard.getPiece(columns, rows+colorindex)!=null) {
				return true;
			}
			return false;
	}
	
}
