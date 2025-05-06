package Chess;

public class Gamelogic {
    private Gameboard gameboard;

    public Gamelogic(Gameboard gameboard) {
        this.gameboard = gameboard;
    }

    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.captured)) {
            return false;
        }
        return true;
    }

    public void makeMove(Move move) {
        move.piece.columns = move.newcolumns;
        move.piece.rows = move.newrows;
        move.piece.xPos = move.newcolumns * gameboard.SizeofTile;
        move.piece.yPos = move.newrows * gameboard.SizeofTile;

        capture(move);
    }

    public void capture(Move move) {
        gameboard.pieceslist.remove(move.captured);
    }

    private boolean sameTeam(Pieces p1, Pieces p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isWhite == p2.isWhite;
    }
}
