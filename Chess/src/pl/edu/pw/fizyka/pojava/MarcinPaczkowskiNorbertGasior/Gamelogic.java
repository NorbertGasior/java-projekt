package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gamelogic {
	
    private Gameboard gameboard;
    public boolean previousMove=true;
    Checkscanner checkscanner;
    public boolean ifkingchecked = false;
    public boolean checknotation =false;
    char s;
    String full = "";
    private boolean isGameOver = false;
    private boolean isWhiteTurn = true;
    public Gamelogic(Gameboard gameboard) {
        this.gameboard = gameboard;
        checkscanner= new Checkscanner(gameboard, this);
    }
    
    public boolean isValidMove(Move move) {
    	if(isGameOver) {
    		return false;
    	}
    	if (sameTeam(move.piece, move.captured)) {
    	    return false;
    	}
    	if (move.piece.isWhite != previousMove) {
    	    return false;
    	}
    	if (!move.piece.PiececanMove(move.newcolumns, move.newrows)) {
    	    return false;
    	}
    	if (move.piece.isbetween(move.newcolumns, move.newrows)) {
    	    return false;
    	}

    	// --- KLUCZOWA CZĘŚĆ:
    	
    	simulateMove(move);
    	boolean kingChecked = checkscanner.isKingchecked(move);
    	undoSimulatedMove(move);

    	if (kingChecked) {
    		
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
    		
    	}else if(move.piece.name.equals("King")){
    		MakeKingMove(move);
    	}
    	if(move.piece.name.equals("Knight"))s='N';
    	else if(move.piece.name.equals("King"))s='K';
    	else if(move.piece.name.equals("Queen"))s='Q';
    	else if(move.piece.name.equals("Rook"))s='R';
    	else if(move.piece.name.equals("Bishop"))s='B';
    	full+=s;
    	if(move.captured!=null) {
    		full+=move.Movenotation[8];
    	}
    	full+=move.Movenotation[move.newcolumns];
    	if(ifkingchecked)
    		System.out.println(full +""+(8- move.newrows)+""+move.Movenotation[9]+ifkingchecked);
    	else
    		System.out.println(full +""+(8- move.newrows)+checkscanner.isKingchecked(move));
    	s = ' ';
    	full = "";
        move.piece.columns = move.newcolumns;
        move.piece.rows = move.newrows;
        move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
        move.piece.yPos = move.newrows * gameboard.SizeofTile;
        if(previousMove == false)previousMove = true;
        else previousMove = false;
        capture(move);
        move.piece.alreadymoved=true;
     // sterowanie zegarem
        if (clock != null) {
            clock.switchTurn();
        }
    	isWhiteTurn = !isWhiteTurn;
    	move.piece.isFirstmove=false;
    	
    	
    	//gameboard.rotatechessboard();
    }
    
    public void updateGameState() {
    	
        Pieces king = findKing(isWhiteTurn);
        if (checkscanner.GameisOver(king)) {
        	
            if (checkscanner.isCurrentlyInCheck(isWhiteTurn)) {
            	clock.pause();
                String message = isWhiteTurn ? "Czarny wygrywa " : "Biały wygrywa";
                JOptionPane.showMessageDialog(null, message, "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
            	clock.pause();
                JOptionPane.showMessageDialog(null, "Pat! Remis.", "Koniec gry", JOptionPane.INFORMATION_MESSAGE);
                
            }
        }
    }
   
	private void MakeKingMove(Move move) {
    	if(Math.abs(move.piece.columns- move.newcolumns)==2) {
    		Pieces rook;
    		if(move.piece.columns < move.newcolumns) {
    			rook = gameboard.getPiece(7,move.piece.rows);
    			rook.columns = 5;
    		}else {
    			rook = gameboard.getPiece(0,move.piece.rows);
    			rook.columns = 3;
    		}
    		rook.xPos=rook.columns*gameboard.SizeofTile;
    	}
    	
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
                    null,                                 
                    "Wybierz figurę:",             			
                    "Promocja figury",                       
                    JOptionPane.DEFAULT_OPTION,           
                    JOptionPane.QUESTION_MESSAGE,         
                    null,                                 
                    options,                              
                    options[0]                            
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
    	
         
         
	}
    public void simulateMove(Move move) {
        move.captured = gameboard.getPiece(move.newcolumns, move.newrows);
        gameboard.setPiece(null, move.piece.rows, move.piece.columns);
        gameboard.setPiece(move.piece, move.newrows, move.newcolumns);
        move.piece.columns = move.newcolumns;
        move.piece.rows = move.newrows;
    }

    public void undoSimulatedMove(Move move) {
        gameboard.setPiece(move.captured, move.newrows, move.newcolumns);
        gameboard.setPiece(move.piece, move.oldrows, move.oldcolumns);
        move.piece.columns = move.oldcolumns;
        move.piece.rows = move.oldrows;
    }
	public void capture(Move move) {
    	
        gameboard.pieceslist.remove(move.captured);
    }

    public boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
    
    private ChessClock clock;

    public void setClock(ChessClock clock) {
        this.clock = clock;
    }
   
    	
    
    public Move AImove() {
    	List<Move> PossibleMoves = new ArrayList<>();
    	int AiPieceMoves=0;
    	List<Pieces> copycat = new ArrayList<>(gameboard.pieceslist);
    	for(Pieces piece : copycat) {
    		if(piece.isWhite==false) {
    			for(int row=0;row<gameboard.settings.getRows();row++) {
    				for(int col=0;col<gameboard.settings.getRows();col++) {
    					if(isValidMove(new Move(gameboard,piece,col,row))) {
    						PossibleMoves.add(new Move(gameboard,piece,col,row));
    		    			AiPieceMoves++;
    					}
        			}
    			}
    			
    		}
    	}
    	if(AiPieceMoves!=0) {
    		Random random = new Random();
        	int AIChoice = random.nextInt(AiPieceMoves);
        	return PossibleMoves.get(AIChoice);
    	}
    	
    	return null;
    	
    }
    
}
