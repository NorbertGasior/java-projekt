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
    	if(ifcheck(move, gameboard)) {
    		return false;
    	}
        if (sameTeam(move.piece, move.captured)) {
            return false;
        }
        if(move.piece.isWhite==previousMove) {
        	return false;
        }
        if (move.piece.name == "Rook") {
        	if(move.oldcolumns!=move.newcolumns && move.oldrows != move.newrows) {
        		return false;
        	}
        	if(isbetween(move, gameboard)) {
        		return false;
        	}
        }
        if (move.piece.name == "Bishop") {
        	if((Math.abs(move.oldcolumns-move.newcolumns))!=(Math.abs(move.oldrows-move.newrows))) {
        		return false;
        	}
        	if(isbetween(move, gameboard)) {
        		return false;
        	}
        }
        if (move.piece.name == "Queen") {
        	if(((Math.abs(move.oldcolumns-move.newcolumns))!=(Math.abs(move.oldrows-move.newrows)))&&(move.oldcolumns!=move.newcolumns && move.oldrows != move.newrows)) {
        		return false;
        	}
        	if(isbetween(move, gameboard)) {
        		return false;
        	}
        }
        if (move.piece.name == "Knight") {
        	int poz = move.oldcolumns - move.newcolumns;
        	int pion = move.newrows - move.oldrows;
        	double vec = Math.sqrt(Math.pow(poz, 2)+Math.pow(pion, 2));
        	if(vec!=Math.sqrt(5)) {
        		return false;
        	}
        }
        if (move.piece.name == "King") {
        	int pion = Math.abs(move.newrows - move.oldrows);
        	int poz = Math.abs(move.oldcolumns - move.newcolumns);
        	if((pion>1)||(poz>1)) {
        		return false;
        	}
        	
        }
        if (move.piece.name == "Pawn") {
        	if(move.piece.isWhite) {
        		if((move.oldrows == 6)&&(move.oldrows-move.newrows > 2)) {
        			return false;
        		}
        		else if((move.oldrows !=6)&&(move.oldrows-move.newrows > 1)){
        			return false;
        		}
        		else if((move.oldcolumns==move.newcolumns)&&(move.captured!=null)) {
        			return false;
        		}
        		else if(Math.abs(move.oldcolumns-move.newcolumns)>1) {
        			return false;
        		}
        		else if((Math.abs(move.oldcolumns-move.newcolumns)==1)&&(move.oldrows-move.newrows==0)) {
        			return false;
        		}
        		else if((Math.abs(move.oldcolumns-move.newcolumns)==1)&&(move.oldrows-move.newrows == 1)&&(move.captured==null)) {
        			return false;
        		}
        		else if((move.oldrows == 6)&&isbetween(move, gameboard)) {
        			return false;
        		}
        	}else {
        		if((move.oldrows == 1)&&(move.newrows-move.oldrows > 2)) {
        			return false;
        		}
        		else if((move.oldrows !=1)&&(move.newrows-move.oldrows > 1)){
        			return false;
        		}
        		else if((move.oldcolumns==move.newcolumns)&&(move.captured!=null)) {
        			return false;
        		}
        		else if(Math.abs(move.oldcolumns-move.newcolumns)>1) {
        			return false;
        		}
        		else if((Math.abs(move.oldcolumns-move.newcolumns)==1)&&(move.newrows-move.oldrows==0)) {
        			return false;
        		}
        		else if((Math.abs(move.oldcolumns-move.newcolumns)==1)&&(move.newrows-move.oldrows == 1)&&(move.captured==null)) {
        			return false;
        		}
        		else if((move.oldrows == 1)&&isbetween(move, gameboard)) {
        			return false;
        		}
        	}
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
    }

    public void capture(Move move) {
    	
        gameboard.pieceslist.remove(move.captured);
    }

    private boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
    public boolean ifcheck(Move move, Gameboard gameboard) {
        List<Integer> kingIndexes = new ArrayList<>();
        for (int i = 0; i < gameboard.pieceslist.size(); i++) {
            Pieces piece = gameboard.pieceslist.get(i);
            if ("King".equals(piece.name)) {
                kingIndexes.add(i);
            }
        }

        for (int index : kingIndexes) {
            Pieces king = gameboard.pieceslist.get(index);
            int row = king.rows;
            int col = king.columns;

            // Horizontal and Vertical Directions
            int[][] directions = {{0,1},{0,-1},{1,0},{-1,0},
                                  {1,1},{1,-1},{-1,1},{-1,-1}};
            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];
                while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                    Pieces target = gameboard.getPiece(c, r);
                    if (target == null) {
                        r += dir[0];
                        c += dir[1];
                        continue;
                    }
                    if (!sameTeam(king, target)) {
                        // Optional: check if this enemy piece *could* attack the king
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }
   
    public boolean castle(boolean between, boolean kingmoved, boolean rookmoved, boolean check) {
    	if(between == false && kingmoved == false && rookmoved == false && check == false ) {
    		
    	}
    	return false;
    }
    
    public boolean isbetween(Move move,Gameboard gameboard) {
    	if(move.piece.name == "Bishop" || move.piece.name == "Queen") {
    		int col = move.oldcolumns;
    		int row = move.oldrows;
    		if(move.oldcolumns>move.newcolumns) {
    			if(move.oldrows>move.newrows) {
    				while(col != move.newcolumns+1) {
    					col--;
    					row--;
    					if(gameboard.getPiece(col, row)!=null) {
    						return true;
    					}
    				}
    			}else {
    				while(col != move.newcolumns+1) {
    					col--;
    					row++;
    					if(gameboard.getPiece(col, row)!=null) {
    						return true;
    					}
    				}
    			}
    		}else if(move.oldcolumns<move.newcolumns){
				if(move.oldrows>move.newrows) {
					while(col != move.newcolumns-1) {
    					col++;
    					row--;
    					if(gameboard.getPiece(col, row)!=null) {
    						return true;
    					}
    				}				
				 }else {
					 while(col != move.newcolumns-1) {
	    					col++;
	    					row++;
	    					if(gameboard.getPiece(col, row)!=null) {
	    						return true;
	    					}
	    				}				
				 }
			}
    	}
    	if(move.piece.name == "Rook" || move.piece.name == "Queen") {
    		int minrow = Math.min(move.oldrows, move.newrows);
    		int maxrow = Math.max(move.oldrows, move.newrows);
    		int mincol = Math.min(move.oldcolumns, move.newcolumns);
    		int maxcol = Math.max(move.oldcolumns, move.newcolumns);
    		if(move.oldcolumns == move.newcolumns) {
    			while(minrow!=maxrow-1) {
    				minrow++;
    				if(gameboard.getPiece(move.oldcolumns, minrow)!=null) {
    					return true;
    				}
    			}
    		}else if(move.oldrows == move.newrows) {
    			while(mincol!=maxcol-1) {
    				mincol++;
    				if(gameboard.getPiece(mincol, move.oldrows)!=null) {
    					return true;
    				}
    			}
    		}else return false;
    		
    	}
    	if(move.piece.name == "Pawn" && Math.abs(move.newrows-move.oldrows)==2) {
    		int row = Math.min(move.newrows, move.oldrows);
    		if(gameboard.getPiece(move.oldcolumns, row+1)!=null) {
    			return true;
    		}
    	}
    	if(move.piece.name == "King" && Math.abs(move.newcolumns - move.oldcolumns)==2) {
    		if(gameboard.getPiece(move.newcolumns, move.oldrows)!=null) {
    			return true;
    		}
    		int haboshbabosh = move.newcolumns - move.oldcolumns;
    		if(haboshbabosh <0) {
    			if(gameboard.getPiece(move.newcolumns+1, move.oldrows)!=null) {
        			return true;
        		}
    			if(gameboard.getPiece(move.newcolumns-1, move.oldrows)!=null) {
        			return true;
        		}
    		}
    		if(haboshbabosh >0) {
    			if(gameboard.getPiece(move.newcolumns-1, move.oldrows)!=null) {
        			return true;
        		}
    		}
    	}
    	return false;
    }
    
}
