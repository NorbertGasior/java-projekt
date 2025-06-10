package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Archbishop extends Pieces{
	BufferedImage sheetarch1;
	{
		try {
			sheetarch1 = ImageIO.read(ClassLoader.getSystemResourceAsStream("Chess_alt45.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	Image spritearch1;
	BufferedImage sheetarch2;
	{
		try {
			sheetarch2 = ImageIO.read(ClassLoader.getSystemResourceAsStream("Chancellorblack.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	Image spritearch2;
	public Archbishop(Gameboard gameboard, int rows, int columns, boolean isWhite) {
		super(gameboard);
		this.rows = rows;
		this.columns = columns;
		this.isWhite = isWhite;
		this.xPos = columns*gameboard.SizeofTile;
		this.yPos = rows*gameboard.SizeofTile;
		this.name = "Archbishop";
		if(isWhite)
			this.spritearch1 = sheetarch1.getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
		else
			this.spritearch2 = sheetarch2.getScaledInstance(gameboard.SizeofTile, gameboard.SizeofTile, BufferedImage.SCALE_SMOOTH);
	}
	public boolean PiececanMove(int columns, int rows) {
		
		return ((Math.abs(columns-this.columns)==Math.abs(rows-this.rows))||Math.abs(columns-this.columns)*Math.abs(columns-this.columns) + Math.abs(rows-this.rows)*Math.abs(rows-this.rows) == 5 )&& ((columns<10)&&(columns>=0)&&(rows<8)&&(rows>=0));
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
	public void paint(Graphics2D g2d) {
		g2d.drawImage(isWhite ? spritearch1 : spritearch2, xPos, yPos, null); 
	}
}
