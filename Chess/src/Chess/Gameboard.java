package Chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class Gameboard extends JPanel {
    private GameSettings settings;
    public int SizeofTile = 80;
    private int rows;
    private int columns;
    private Color c1, c2;
    ArrayList<Pieces> pieceslist = new ArrayList<>();
    Load load = new Load(this);
    Gamelogic logic = new Gamelogic(this);
    public Gameboard(GameSettings settings) {
        this.settings = settings;
        this.setPreferredSize(new Dimension(settings.getColumns() * SizeofTile, settings.getRows() * SizeofTile));
        this.addMouseListener(load);
        this.addMouseMotionListener(load);
        rows = settings.getRows();
        columns = settings.getColumns();
        // ustawienia kolorów pola
        if (settings.getBoardTypeChoice() == 1) {
            c1 = new Color(243, 255, 237);
            c2 = new Color(109, 150, 88);
        } else if (settings.getBoardTypeChoice() == 2) {
            c1 = new Color(225, 198, 153);
            c2 = new Color(88, 57, 39);
        } else if (settings.getBoardTypeChoice() == 3) {
            c1 = new Color(242, 206, 242);
            c2 = new Color(120, 42, 189);
        }
        addPieces();
    }
    public Pieces selectedPiece;
    
    public Pieces getPiece(int col, int row) {
    	for(Pieces piece : pieceslist) {
    		if(piece.columns == col && piece.rows == row) {
    			return piece;
    		}
    	}
    	return null;
    }
    private void addPieces() {
        pieceslist.clear();
        if (settings.getGameType() == GameType.FISCHER) {
            initFischerPieces();
        } else if (settings.getGameType() == GameType.CLASSIC) {
            initClassicPieces();
        } else if (settings.getGameType() == GameType.CAPABLANCA) {
            // na razie używamy klasycznego układu dla Capablanki
            initClassicPieces();
        }
    }

    private void initClassicPieces() {
        // czarne figury
        pieceslist.add(new Rook(this, 0, 0, false));
        pieceslist.add(new Knight(this, 0, 1, false));
        pieceslist.add(new Bishop(this, 0, 2, false));
        pieceslist.add(new Queen(this, 0, 3, false));
        pieceslist.add(new King(this, 0, 4, false));
        pieceslist.add(new Bishop(this, 0, 5, false));
        pieceslist.add(new Knight(this, 0, 6, false));
        pieceslist.add(new Rook(this, 0, 7, false));
        // czarne piony
        for (int col = 0; col < 8; col++) {
            pieceslist.add(new Pawn(this, 1, col, false));
        }
        // białe piony
        for (int col = 0; col < 8; col++) {
            pieceslist.add(new Pawn(this, 6, col, true));
        }
        // białe figury
        pieceslist.add(new Rook(this, 7, 0, true));
        pieceslist.add(new Knight(this, 7, 1, true));
        pieceslist.add(new Bishop(this, 7, 2, true));
        pieceslist.add(new Queen(this, 7, 3, true));
        pieceslist.add(new King(this, 7, 4, true));
        pieceslist.add(new Bishop(this, 7, 5, true));
        pieceslist.add(new Knight(this, 7, 6, true));
        pieceslist.add(new Rook(this, 7, 7, true));
    }

    private void initFischerPieces() {
        Random rand = new Random();
        int[] backRank = new int[8]; // 0=Rook,1=Knight,2=Bishop,3=Queen,4=King
        List<Integer> avail = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            avail.add(i);
        }
        // rozmieszczenie gońców na różnych kolorach
        List<Integer> evens = Arrays.asList(0, 2, 4, 6);
        Collections.shuffle(evens, rand);
        int b1 = evens.get(0);
        avail.remove(Integer.valueOf(b1));
        List<Integer> odds = Arrays.asList(1, 3, 5, 7);
        Collections.shuffle(odds, rand);
        int b2 = odds.get(0);
        avail.remove(Integer.valueOf(b2));
        backRank[b1] = 2;
        backRank[b2] = 2;
        // królowa
        Collections.shuffle(avail, rand);
        int q = avail.remove(0);
        backRank[q] = 3;
        // skoczki
        Collections.shuffle(avail, rand);
        int n1 = avail.remove(0);
        int n2 = avail.remove(0);
        backRank[n1] = 1;
        backRank[n2] = 1;
        // wieże i król (król pomiędzy wieżami)
        Collections.sort(avail);
        int r1 = avail.get(0);
        int k = avail.get(1);
        int r2 = avail.get(2);
        backRank[r1] = 0;
        backRank[k] = 4;
        backRank[r2] = 0;
        // dodanie czarnych figur
        for (int col = 0; col < 8; col++) {
            switch (backRank[col]) {
                case 0: pieceslist.add(new Rook(this, 0, col, false)); break;
                case 1: pieceslist.add(new Knight(this, 0, col, false)); break;
                case 2: pieceslist.add(new Bishop(this, 0, col, false)); break;
                case 3: pieceslist.add(new Queen(this, 0, col, false)); break;
                case 4: pieceslist.add(new King(this, 0, col, false)); break;
            }
        }
        // czarne piony
        for (int col = 0; col < 8; col++) {
            pieceslist.add(new Pawn(this, 1, col, false));
        }
        // białe piony
        for (int col = 0; col < 8; col++) {
            pieceslist.add(new Pawn(this, 6, col, true));
        }
        // dodanie białych figur
        for (int col = 0; col < 8; col++) {
            switch (backRank[col]) {
                case 0: pieceslist.add(new Rook(this, 7, col, true)); break;
                case 1: pieceslist.add(new Knight(this, 7, col, true)); break;
                case 2: pieceslist.add(new Bishop(this, 7, col, true)); break;
                case 3: pieceslist.add(new Queen(this, 7, col, true)); break;
                case 4: pieceslist.add(new King(this, 7, col, true)); break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                g2d.setColor((c + r) % 2 == 0 ? c1 : c2);
                g2d.fillRect(c * SizeofTile, r * SizeofTile, SizeofTile, SizeofTile);
            }
        }
        for (Pieces piece : pieceslist) {
            piece.paint(g2d);
        }
    }
}
