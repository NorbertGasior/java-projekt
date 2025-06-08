package Chess;

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
		
		return  hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,0,1)|| //up
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,0)|| //right
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,0,-1)|| //down
				hitbyRook(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,0)|| // left
				
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,-1)|| // up left
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,-1,1)|| //down left
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,1)|| //down right
				hitbyBishop(move.newcolumns,move.newrows,king,Kingcolumn,Kingrow,1,-1)|| //up right
				
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
			if(Kingcolumn - (i * colvalue) == col && Kingrow - (i * rowvalue) == row) {
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
		return checkedbyPawn(gameboard.getPiece(Kingcolumn+1, Kingrow+colorval),king,col,row)||
				checkedbyPawn(gameboard.getPiece(Kingcolumn-1, Kingrow+colorval),king,col,row);
	}
	
	private boolean checkedbyPawn(Pieces p, Pieces k, int col, int row) {
		return p!=null && !logic.sameTeam(p, k) && p.name.equals("Pawn");
	}
}
