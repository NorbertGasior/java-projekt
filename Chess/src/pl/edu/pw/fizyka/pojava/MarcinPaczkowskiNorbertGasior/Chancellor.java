package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chancellor extends Pieces{
	BufferedImage sheetchan1;
	{
		try {
			sheetchan1 = ImageIO.read(ClassLoader.getSystemResourceAsStream("Chess_clt45.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	Image spritechan1;
	BufferedImage sheetchan2;
	{
		try {
			sheetchan2 = ImageIO.read(ClassLoader.getSystemResourceAsStream("Chancellorblack.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	Image spritechan2;
	public Chancellor(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Chancellor";
		if(isWhite)
			this.spritechan1 = sheetchan1.getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
		else
			this.spritechan2 = sheetchan2.getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
		return (Math.abs(columns-this.columns)*Math.abs(columns-this.columns) + Math.abs(rows-this.rows)*Math.abs(rows-this.rows) == 5 ||!(columns!=this.columns && rows!=this.rows)) && ((columns<10)&&(columns>=0)&&(rows<8)&&(rows>=0));
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
		}else return false;
		return false;
	}
	public void paint(Graphics2D g2d) {
		g2d.drawImage(isWhite ? spritechan1 : spritechan2, xPos, yPos, null); 
	}
}
