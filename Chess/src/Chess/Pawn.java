package Chess;

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
		if(isWhite) {
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns-1 && rows == this.rows-1 && gameboard.getPiece(columns, rows+1)!=null) {
				return true;
			}
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns+1 && rows == this.rows-1 && gameboard.getPiece(columns, rows+1)!=null) {
				return true;
			}
			if(this.rows - rows >2 || this.rows - rows <1) {
				return false;
			}
			if(this.rows - rows == 2 && alreadymoved) {
				return false;
			}
			if(gameboard.getPiece(columns, rows)!=null && this.columns - columns==0) {
				return false;
			}
			if(Math.abs(this.columns -columns)>1 || (Math.abs(this.columns -columns)==1 && Math.abs(rows- this.rows)==2)) {
				return false;
			}
			if(Math.abs(this.columns -columns)==1 &&gameboard.getPiece(columns, rows)==null) {
				return false;
			}
			
		}
		if(!isWhite) {
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns-1 && rows == this.rows+1 && gameboard.getPiece(columns, rows-1)!=null) {
				return true;
			}
			if(gameboard.getTileValue(columns, rows)==gameboard.Enpassant && columns == this.columns+1 && rows == this.rows+1 && gameboard.getPiece(columns, rows-1)!=null) {
				return true;
			}
			if(rows - this.rows >2 || rows - this.rows <1) {
				return false;
			}
			if(rows - this.rows == 2 && alreadymoved) {
				return false;
			}
			if(gameboard.getPiece(columns, rows)!=null && this.columns - columns==0) {
				return false;
			}
			if(Math.abs(this.columns -columns)>1 || (Math.abs(this.columns -columns)==1 && Math.abs(rows- this.rows)==2)) {
				return false;
			}
			if(Math.abs(this.columns -columns)==1 &&gameboard.getPiece(columns, rows)==null) {
				return false;
			}
			
		}
		return true;
		
	}
	public boolean isbetween(int columns, int rows) {
    	if(Math.abs(rows-this.rows)==2) {
    		int row = Math.min(rows, this.rows);
    		if(gameboard.getPiece(this.columns, row+1)!=null) {
    			return true;
    		}
    	}
    	return false;
    }
}
