package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.image.BufferedImage;

public class Queen extends Pieces{
	
	public Queen(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Queen";
		this.sprite = sheet.getSubimage(sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
		return (!(columns!=this.columns && rows!=this.rows)||(Math.abs(columns-this.columns)==Math.abs(rows-this.rows)))&& ((columns<gameboard.settings.getColumns())&&(columns>=0)&&(rows<8)&&(rows>=0));
	}
	public boolean isbetween(int columns, int rows) {
		int minrow = Math.min(rows, this.rows);
		int maxrow = Math.max(rows, this.rows);
		int mincol = Math.min(columns, this.columns);
		int maxcol = Math.max(columns, this.columns);
		if(columns == this.columns) {
			while(minrow!=maxrow-1) {
				minrow++;
				if(gameboard.getPiece(this.columns, minrow)!=null) {
					return true;
				}
			}
		}else if(rows == this.rows) {
			while(mincol!=maxcol-1) {
				mincol++;
				if(gameboard.getPiece(mincol, this.rows)!=null) {
					return true;
				}
			}
		}
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
