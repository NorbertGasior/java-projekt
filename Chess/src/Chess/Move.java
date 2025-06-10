package Chess;

public class Move {
	int oldcolumns;
	int oldrows;
	int newcolumns;
	int newrows;
	final char[] Movenotation= {'a','b','c','d','e','f','g','h','x','+'};
	Pieces piece;
	Pieces captured;
	public Move(Gameboard gameboard, Pieces piece, int newcol, int newrow) {
		this.oldcolumns = piece.columns;
		this.oldrows = piece.rows;
		this.newcolumns = newcol;
		this.newrows = newrow;
		
		this.piece = piece;
		this.captured = gameboard.getPiece(newcol, newrow);
	}
}
