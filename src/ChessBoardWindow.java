package szachy;
import javax.swing.*;
import java.awt.*;

public class ChessBoardWindow extends JFrame {

    private static final int BOARD_SIZE = 8;
    private JPanel boardPanel, name1, name2, time;
    String user1, user2, minutes, seconds;
    int min, sec;

    public ChessBoardWindow() {
        setTitle("Plansza szachowa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(640, 640));
        setLayout(new BorderLayout());
        name1 = new JPanel();
        name2 = new JPanel();
        time = new JPanel(new GridBagLayout());
        boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        add(boardPanel, BorderLayout.CENTER);
        
        createBoard();
        showname();
        add(name1, BorderLayout.NORTH);
        add(name2, BorderLayout.SOUTH);
        add(time,BorderLayout.EAST);
        setResizable(false);
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
    private void showname() {
    	name1.removeAll();
    	name2.removeAll();
    	time.removeAll();
    	user1 = mainframe.getNazwa1();
    	user2 = mainframe.getNazwa2();
    	min = mainframe.getMinuta();
    	sec = mainframe.getSekunda();
    	if(min<10)minutes = "0" + String.valueOf(min);
    	else if(min>9) minutes = String.valueOf(min);
    	seconds = String.valueOf(sec+"0");
    	JLabel firstusername = new JLabel();
    	JLabel secondusername = new JLabel();
    	JLabel timeview = new JLabel();
    	firstusername.setText(user1);
    	secondusername.setText(user2);
    	timeview.setText(minutes + ":" + seconds);
    	name1.add(firstusername);
    	name2.add(secondusername);
    	time.add(timeview,new GridBagConstraints());
    	name1.revalidate();
        name1.repaint();
    }
}
