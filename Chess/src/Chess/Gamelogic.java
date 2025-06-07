package Chess;

import java.util.ArrayList;
import java.util.List;

public class Gamelogic {
    private Gameboard gameboard;
    private boolean previousMove=false;
    private boolean kingmoved = false;
    private boolean castleavailable = false;
    public Gamelogic(Gameboard gameboard) {
        this.gameboard = gameboard;
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
        return true;   
    }

    public void makeMove(Move move) {
    	if(move.piece.name.equals("Pawn")) {
    		MakePawnMove(move);
    	}else {
        move.piece.columns = move.newcolumns;
        move.piece.rows = move.newrows;
        move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
        move.piece.yPos = move.newrows * gameboard.SizeofTile;
        if(previousMove == false)previousMove = true;
        else previousMove = false;
        capture(move);
        move.piece.alreadymoved=true;
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
    	
    	
    	 move.piece.columns = move.newcolumns;
         move.piece.rows = move.newrows;
         move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
         move.piece.yPos = move.newrows * gameboard.SizeofTile;
         if(previousMove == false)previousMove = true;
         else previousMove = false;
         capture(move);
         move.piece.alreadymoved=true;
         System.out.println(gameboard.Enpassant + "\n");
		
	}
	public void capture(Move move) {
    	
        gameboard.pieceslist.remove(move.captured);
    }

    private boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
    
    
}
