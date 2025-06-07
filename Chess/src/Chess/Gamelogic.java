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

    private boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
    public boolean isbetween(Move move,Gameboard gameboard) {
    	if(move.piece.name == "Pawn" && Math.abs(move.newrows-move.oldrows)==2) {
    		int row = Math.min(move.newrows, move.oldrows);
    		if(gameboard.getPiece(move.oldcolumns, row+1)!=null) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
