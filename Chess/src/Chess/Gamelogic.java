package Chess;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gamelogic {
	
    private Gameboard gameboard;
    private boolean previousMove=false;
    Checkscanner checkscanner;
    public boolean ifkingchecked = false;
    char s;
    String full = "";
    public Gamelogic(Gameboard gameboard) {
        this.gameboard = gameboard;
        checkscanner= new Checkscanner(gameboard, this);
    }
    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.captured)) {
            return false;
        }
        if(move.piece.isWhite==previousMove) {
        	return false;
        }
        if(!move.piece.PiececanMove(move.newcolumns, move.newrows)) {
        	return false;
        }
        if(move.piece.isbetween(move.newcolumns, move.newrows)) {
        	return false;
        }
        if(checkscanner.isKingchecked(move)) {
        	ifkingchecked = true;
        	return false;
        }
        return true;   
    }
    
    Pieces findKing(boolean isWhite) {
    	for (Pieces piece : gameboard.pieceslist) {
    		if(isWhite == piece.isWhite && piece.name.equals("King")) {
    			return piece;
    		}
    	}
    	return null;
    }
    
    public void makeMove(Move move) {
    	if(move.piece.name.equals("Pawn")) {
    		MakePawnMove(move);
    		char s = move.Movenotation[move.newcolumns];
    		System.out.println(s +""+(8- move.newrows));
    		
    	}else {
    	if(move.piece.name.equals("Knight"))s='N';
    	else if(move.piece.name.equals("King"))s='K';
    	else if(move.piece.name.equals("Queen"))s='Q';
    	else if(move.piece.name.equals("Rook"))s='R';
    	else if(move.piece.name.equals("Bishop"))s='B';
    	full+=s;
    	full+=move.Movenotation[move.newcolumns];
    	System.out.println(full +""+(8- move.newrows));
        move.piece.columns = move.newcolumns;
        move.piece.rows = move.newrows;
        move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
        move.piece.yPos = move.newrows * gameboard.SizeofTile;
        if(previousMove == false)previousMove = true;
        else previousMove = false;
        capture(move);
        move.piece.alreadymoved=true;
    	}
    	ifkingchecked = false;
    	
    }
    

    private void MakePawnMove(Move move) {
    	
    	int Colorindex = move.piece.isWhite ? 1 : -1;
    	if(gameboard.getTileValue(move.newcolumns, move.newrows)==gameboard.Enpassant) {
    		move.captured = gameboard.getPiece(move.newcolumns, move.newrows + Colorindex);
    	}
    	if(Math.abs(move.piece.rows-move.newrows)==2) {
    		gameboard.Enpassant = gameboard.getTileValue(move.newcolumns, move.newrows+Colorindex);
    	}else {
    		gameboard.Enpassant=-1;
    	}
    	
    	Colorindex = move.piece.isWhite ? 0 : 7;
    	if(move.newrows==Colorindex) {
    		
    		char q = '\u2655';
    		char r = '\u2656';
    		char b = '\u2657';
    		char n = '\u2658';
    		
    		
    		
    		Object[] options = {q, r, b, n};
            int choice = JOptionPane.showOptionDialog(
                    null,                                 // Komponent nadrzędny (null = centrum ekranu)
                    "Wybierz figurę:",             			// Wiadomość
                    "Promocja figury",                        // Tytuł okna
                    JOptionPane.DEFAULT_OPTION,           // Typ opcji
                    JOptionPane.QUESTION_MESSAGE,         // Typ ikony
                    null,                                 // Niestandardowa ikona (null = domyślna)
                    options,                              // Tablica opcji
                    options[0]                            // Opcja domyślna
            );

            if (choice >= 0) {
                String pieceName = switch (choice) {
                    case 0 -> "Hetman";
                    case 1 -> "Wieża";
                    case 2 -> "Goniec";
                    case 3 -> "Skoczek";
                    default -> "Nieznana figura";
                };
                JOptionPane.showMessageDialog(null, "Wybrałeś: " + pieceName);
                if(pieceName.equals("Hetman")) {
                	capture(move);
                	move.captured = move.piece;
                	capture(move);
                	gameboard.pieceslist.add(new Queen(gameboard,move.newrows,move.newcolumns,move.piece.isWhite));
                }
                if(pieceName.equals("Wieża")) {
                	capture(move);
                	move.captured = move.piece;
                	capture(move);
                	gameboard.pieceslist.add(new Rook(gameboard,move.newrows,move.newcolumns,move.piece.isWhite));
                }
                if(pieceName.equals("Goniec")) {
                	capture(move);
                	move.captured = move.piece;
                	capture(move);
                	gameboard.pieceslist.add(new Bishop(gameboard,move.newrows,move.newcolumns,move.piece.isWhite));
                }
                if(pieceName.equals("Skoczek")) {
                	capture(move);
                	move.captured = move.piece;
                	capture(move);
                	gameboard.pieceslist.add(new Knight(gameboard,move.newrows,move.newcolumns,move.piece.isWhite));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie dokonano wyboru.");
            }
            
    	}
    	 move.piece.columns = move.newcolumns;
         move.piece.rows = move.newrows;
         move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
         move.piece.yPos = move.newrows * gameboard.SizeofTile;
         if(previousMove == false)previousMove = true;
         else previousMove = false;
         capture(move);
         move.piece.alreadymoved=true;
         
		
	}
	public void capture(Move move) {
    	
        gameboard.pieceslist.remove(move.captured);
    }

    public boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
    
    
}
