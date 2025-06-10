package pl.edu.pw.fizyka.pojava.MarcinPaczkowskiNorbertGasior;

import java.util.ArrayList;
import java.util.List;

public class Checkscanner {
	Gameboard gameboard;
	Gamelogic logic;
	public Checkscanner(Gameboard gameboard, Gamelogic logic) {
		this.gameboard = gameboard;
		this.logic = logic;
	}
	
	public boolean isKingchecked(Move move) {
		
		Pieces king = logic.findKing(move.piece.isWhite);
		assert king != null;
		
		int Kingcolumn = king.columns;
		int Kingrow = king.rows;
		
		if(gameboard.selectedPiece != null && gameboard.selectedPiece.name.equals("King")) {
			Kingcolumn = move.newcolumns;
			Kingrow = move.newrows;
		}
		
		return  hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,0,1)|| 
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,0)|| 
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,0,-1)|| 
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,0)|| 
				
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,-1)|| 
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,1)|| 
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,1)|| 
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,-1)|| 
				
				hitbyKnight(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow)||
				
				hitbyKing(king, Kingcolumn, Kingrow)||
				
				hitbyPawn(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow);
				
	}
	
	private boolean hitbyRook(int col, int row, Pieces king, int Kingcolumn, int Kingrow, int colvalue, int rowvalue) {
		for(int i=1; i<8;++i) {
			if(Kingcolumn + (i * colvalue) == col && Kingrow + (i * rowvalue) == row) {
				break;
			}
			
			Pieces piece = gameboard.getPiece(Kingcolumn + (i * colvalue), Kingrow + (i * rowvalue));
			if(piece!=null && piece!= gameboard.selectedPiece) {
				if(!logic.sameTeam(king, piece)&&(piece.name.equals("Rook") || piece.name.equals("Queen"))) {
					return true;
				}
				break;
			}
			
		}
		return false;
	}
	
	private boolean hitbyBishop(int col, int row, Pieces king, int Kingcolumn, int Kingrow, int colvalue, int rowvalue) {
		for(int i=1; i<8;++i) {
			if(Kingcolumn + (i * colvalue) == col && Kingrow + (i * rowvalue) == row) {
				break;
			}
			
			Pieces piece = gameboard.getPiece(Kingcolumn - (i * colvalue), Kingrow - (i * rowvalue));
			if(piece!=null && piece!= gameboard.selectedPiece) {
				if(!logic.sameTeam(king, piece)&&(piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
					return true;
				}
				break;
			}
			
		}
		return false;
	}
	
	private boolean hitbyKnight(int col, int row, Pieces king, int Kingcolumn, int Kingrow) {
		return checkedbyKnight(gameboard.getPiece(Kingcolumn-1, Kingrow-2), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn-1, Kingrow+2), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn+1, Kingrow-2), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn+1, Kingrow+2), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn-2, Kingrow-1), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn-2, Kingrow+1), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn+2, Kingrow-1), king, col, row)||
				checkedbyKnight(gameboard.getPiece(Kingcolumn+2, Kingrow+1), king, col, row);
		
	}
	
	private boolean checkedbyKnight(Pieces p, Pieces k, int col, int row) {
		return (p!=null && !logic.sameTeam(p, k) && p.name.equals("Knight") && !(p.columns==col && p.rows == row));
	}
	
	private boolean hitbyKing(Pieces king, int Kingcolumn, int Kingrow) {
		return checkedbyKing(gameboard.getPiece(Kingcolumn-1, Kingrow),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn-1, Kingrow+1),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn-1, Kingrow-1),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn+1, Kingrow),king)||
		       checkedbyKing(gameboard.getPiece(Kingcolumn+1, Kingrow-1),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn+1, Kingrow+1),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn, Kingrow+1),king)||
			   checkedbyKing(gameboard.getPiece(Kingcolumn, Kingrow-1),king);
	}
	
	private boolean checkedbyKing(Pieces p, Pieces k) {
		return p!=null && !logic.sameTeam(p, k) && p.name.equals("King");
	}
	
	private boolean hitbyPawn(int col, int row, Pieces king, int Kingcolumn, int Kingrow) {
		int colorval = king.isWhite ? 1 : -1;
		return checkedbyPawn(gameboard.getPiece(Kingcolumn+1, Kingrow-colorval),king,col,row)||
				checkedbyPawn(gameboard.getPiece(Kingcolumn-1, Kingrow-colorval),king,col,row);
	}
	
	private boolean checkedbyPawn(Pieces p, Pieces k, int col, int row) {
		return p!=null && !logic.sameTeam(p, k) && p.name.equals("Pawn");
	}
	 public boolean isCurrentlyInCheck(boolean isWhite) {
	        Pieces king = logic.findKing(isWhite);
	        return isKingchecked(new Move(gameboard, king, king.columns, king.rows));
	    }
	public boolean GameisOver(Pieces king) {
		List<Pieces> piecesCopy = new ArrayList<>(gameboard.pieceslist);
	    for (Pieces piece : piecesCopy) {
	        if (gameboard.logic.sameTeam(piece, king)) {
	            gameboard.selectedPiece = piece == king ? king : null;
	            for (int row = 0; row < gameboard.rows; row++) {
	                for (int col = 0; col < gameboard.columns; col++) {
	                    Move move = new Move(gameboard, piece, col, row);
	                    if (gameboard.logic.isValidMove(move)) {
	                        return false; 
	                    }
	                }
	            }
	        }
	    }

	    
	    Move dummyMove = new Move(gameboard, king, king.columns, king.rows);
	    if (isKingchecked(dummyMove)) {
	        System.out.println("checkmate");
	    } else {
	        System.out.println("stalemate");
	    }

	    return true;
	}
}
