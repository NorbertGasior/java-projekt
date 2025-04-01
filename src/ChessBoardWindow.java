import javax.swing.*;
import java.awt.*;

public class ChessBoardWindow extends JFrame {

    private static final int BOARD_SIZE = 8;
    private JPanel boardPanel;

    public ChessBoardWindow() {
        setTitle("Plansza szachowa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(640, 640));
        setLayout(new BorderLayout());

        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        add(boardPanel, BorderLayout.CENTER);

        createBoard();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createBoard() {
        boardPanel.removeAll();
        boolean white = true;

        for (int row = 0; row < BOARD_SIZE; row++) {
            white = !white;
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel square = new JPanel();
                square.setBackground(white ? Color.WHITE : Color.GRAY);
                boardPanel.add(square);
                white = !white;
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }
}
