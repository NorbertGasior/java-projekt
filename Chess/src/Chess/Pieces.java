package Chess;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pieces {
	public int rows, columns;
	public int xPos, yPos;
	public boolean isWhite;
	public String name;
	public int count;
	public boolean alreadymoved=false;
	BufferedImage sheet;
	{
		try {
			sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("Chesspieces.png"));
			if (sheet != null) {
				sheetScale = sheet.getWidth() / 6;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	Image sprite;
	protected int sheetScale = sheet.getWidth()/6;
	Gameboard gameboard;
	public Pieces(Gameboard gameboard) {
		this.gameboard = gameboard;
	}
	public boolean PiececanMove(int column, int row) {
		return true;
	}
	public boolean isbetween(int column, int row) {
		return false;
	}
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xPos, yPos, null); 
	}

}
