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
		return true;
	}
}
