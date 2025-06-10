package Chess;

import java.awt.image.BufferedImage;

public class King extends Pieces{

	public King(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "King";
		this.sprite = sheet.getSubimage(0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns,int rows) {
		
		return canCastle(columns,rows)||(Math.abs(columns-this.columns)<2 && Math.abs(rows-this.rows)<2);
	}
	public boolean canCastle(int col, int row) {
		if(this.rows==row) {
			if(col == 6) {
				Pieces rook = gameboard.getPiece(7, row);
				if(rook !=null && rook.isFirstmove && isFirstmove){
					return  gameboard.getPiece(5, row) == null &&
							gameboard.getPiece(6, row) == null &&
							!gameboard.logic.checkscanner.isKingchecked(new Move(gameboard, this, 5, row));
				}
			}else if(col == 2) {
				Pieces rook = gameboard.getPiece(0, row);
				if(rook !=null && rook.isFirstmove && isFirstmove){
					return  gameboard.getPiece(3, row) == null &&
							gameboard.getPiece(2, row) == null &&
							gameboard.getPiece(1, row) == null &&
							!gameboard.logic.checkscanner.isKingchecked(new Move(gameboard, this, 3, row));
				}
			}
		}
		return false;
	}
}
