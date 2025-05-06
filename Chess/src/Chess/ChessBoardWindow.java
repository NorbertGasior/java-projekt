package Chess;

import javax.swing.*;
import java.awt.*;

public class ChessBoardWindow extends JFrame {

	private JPanel name1, name2;
	String user1, user2, minutes, seconds;
	int min, sec;

//chyba git funkcja
	public ChessBoardWindow(GameSettings settings) {
		setTitle("Plansza szachowa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1500, 1200));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		name1 = new JPanel();
		name1.setSize(new Dimension(100,50));
		name2 = new JPanel();
		name2.setSize(new Dimension(100,50));
		Gameboard board = new Gameboard(settings);

		showname(settings);
		c.gridx = 0;
		c.gridy = 0;
		add(name1, c);
		c.gridx = 0;
		c.gridy = 50;
		this.add(board, c);
		c.gridx = 0;
		c.gridy = 51;
		add(name2, c);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// funkcja ustawiająca labele z nazwami gracza jeszcze do poprawki
	private void showname(GameSettings settings) {
		name1.removeAll();
		name2.removeAll();
		user1 = settings.getPlayer1Name();
		user2 = settings.getPlayer2Name();
		min = settings.getMinutes();
		// sec = MenuFrame.getSekunda();
		if (min < 10)
			minutes = "0" + String.valueOf(min);
		else if (min > 9)
			minutes = String.valueOf(min);
		seconds = String.valueOf(sec + "0");
		JLabel firstusername = new JLabel();
		JLabel secondusername = new JLabel();
		JLabel timeview = new JLabel();
		firstusername.setText(user1);
		secondusername.setText(user2);
		timeview.setText(minutes + ":" + seconds);
		name1.add(firstusername);
		name2.add(secondusername);
		name1.revalidate();
		name1.repaint();
	}
}
