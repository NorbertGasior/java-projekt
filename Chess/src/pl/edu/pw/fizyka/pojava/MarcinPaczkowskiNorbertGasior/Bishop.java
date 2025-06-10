package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.image.BufferedImage;

public class Bishop extends Pieces{

	public Bishop(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Bishop";
		this.sprite = sheet.getSubimage(2*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
		return (Math.abs(columns-this.columns)==Math.abs(rows-this.rows))&& ((columns<gameboard.settings.getColumns())&&(columns>=0)&&(rows<8)&&(rows>=0));
	}
	public boolean isbetween(int columns, int rows) {
		int col = this.columns;
		int row = this.rows;
		if(this.columns>columns) {
			if(this.rows>rows) {
				while(col != columns+1) {
					col--;
					row--;
					if(gameboard.getPiece(col, row)!=null) {
						return true;
					}
				}
			}else {
				while(col != columns+1) {
					col--;
					row++;
					if(gameboard.getPiece(col, row)!=null) {
						return true;
					}
				}
			}
		}else if(this.columns<columns){
			if(this.rows>rows) {
				while(col != columns-1) {
					col++;
					row--;
					if(gameboard.getPiece(col, row)!=null) {
						return true;
					}
				}				
			 }else {
				 while(col != columns-1) {
    					col++;
    					row++;
    					if(gameboard.getPiece(col, row)!=null) {
    						return true;
    					}
    				}				
			 }
		}
		
		return false;
	}
}
