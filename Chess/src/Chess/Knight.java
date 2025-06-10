package Chess;

import java.awt.image.BufferedImage;

public class Knight extends Pieces{

	public Knight(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Knight";
		this.sprite = sheet.getSubimage(3*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
		return Math.abs(columns-this.columns)*Math.abs(columns-this.columns) + Math.abs(rows-this.rows)*Math.abs(rows-this.rows) == 5 && ((columns<gameboard.settings.getColumns())&&(columns>=0)&&(rows<8)&&(rows>=0));
	}
}
