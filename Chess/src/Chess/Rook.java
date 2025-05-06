package Chess;

import java.awt.image.BufferedImage;

public class Rook extends Pieces{

	public Rook(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Knight";
		this.sprite = sheet.getSubimage(4*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
//dgsgsfg
}
