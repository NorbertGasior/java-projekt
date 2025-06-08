package Chess;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuFrame extends JFrame implements ChangeListener {
	JPanel panelsrodkowy, nameInputPanel, centerPanel;
	GameSettings settings = new GameSettings();
	
	CardLayout nameInputLayout;
	JTextField textfield1, textfield2, textfieldai;

	public MenuFrame() {
		setTitle("Szachy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		this.setinterfejs();
		setVisible(true);
	}

	public void setinterfejs() {
		this.setSize(800, 800);
		panelsrodkowy = new JPanel();
		centerPanel = new JPanel(new GridBagLayout());
		JButton button = new JButton();
		updateButton(button, settings.isPvP());

		button.addActionListener(e -> {
			settings.setPvP(!settings.isPvP());

			if (settings.isPvP() == false) {

				updateButton(button, settings.isPvP());// Zaktualizuj wygląd
				nameInputLayout.show(nameInputPanel, "onePlayer");

			} else if (settings.isPvP() == true) {

				updateButton(button, settings.isPvP());
				nameInputLayout.show(nameInputPanel, "twoPlayers");
			}
		});
		button.setPreferredSize(new Dimension(200, 40));
		nameInputLayout = new CardLayout();
		nameInputPanel = new JPanel(nameInputLayout);

		textfield1 = new JTextField(10);
		textfield2 = new JTextField(10);
		textfieldai = new JTextField(10);
		JPanel onePlayerPanel = new JPanel();
		onePlayerPanel.add(new JLabel("Imię:"));
		onePlayerPanel.add(textfieldai);

		JPanel twoPlayersPanel = new JPanel();
		twoPlayersPanel.add(new JLabel("Gracz 1:"));
		twoPlayersPanel.add(textfield1);
		twoPlayersPanel.add(new JLabel("Gracz 2:"));
		twoPlayersPanel.add(textfield2);

		nameInputPanel.add(onePlayerPanel, "onePlayer");
		nameInputPanel.add(twoPlayersPanel, "twoPlayers");

		panelsrodkowy.add(button);
		this.add(panelsrodkowy, BorderLayout.NORTH);
		nameInputLayout.show(nameInputPanel, "twoPlayers");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(nameInputPanel, gbc);

		// Ustawienie drugiego pola tekstowego (x=1, y=0)
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;

		JButton startButton = new JButton("GRAJ");
		startButton.setPreferredSize(new Dimension(200, 40));
		startButton.addActionListener(e -> {
			if (settings.isPvP() == false) {
				settings.setPlayer1Name("AI");
				settings.setPlayer2Name(textfieldai.getText());
			} else if (settings.isPvP() == true) {
				settings.setPlayer1Name(textfield1.getText());
				settings.setPlayer2Name(textfield2.getText());
			}
			new ChessBoardWindow(settings);
			dispose();
		});
		centerPanel.add(startButton, gbc);
		this.add(centerPanel);
		this.setJMenuBar(new ChessMenuBar(settings));
	}

	private static void updateButton(JButton button, boolean opcja1Wlaczona) {

		if (opcja1Wlaczona) {
			button.setText("▶ PVP" + "  |  AI");
		} else {
			button.setText("PVP" + "  |  ▶ AI");
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MenuFrame::new);
	}
}