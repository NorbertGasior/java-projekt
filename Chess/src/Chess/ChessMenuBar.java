package Chess;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ChessMenuBar extends JMenuBar {
	JMenu rodzajpartii, wybormotywu, tempo;
	JMenuItem normalchess_1, fischerchess_1, capablancachess_1; // wybory w rodzaju partii
	JMenuItem temp1p0_2, temp1p2_2, temp3p0_2, temp3p2_2, temp5p0_2, temp10p0_2, temp15p0_2, temp30p0_2, tempnotstand_2; // wybory
																															// w
																															// tempie
	JMenuItem classic_3, wooden_3, thirdstyle_3; // wybory w motywie

	public ChessMenuBar(GameSettings settings) {
		rodzajpartii = new JMenu("Rodzaj partii");
		tempo = new JMenu("Tempo");
		wybormotywu = new JMenu("Motyw");

		// przyciski do rodzaju gry

		normalchess_1 = new JMenuItem("szachy klasyczne");
		fischerchess_1 = new JMenuItem("szachy Fischera");
		capablancachess_1 = new JMenuItem("szachy Capablanki");
		normalchess_1.addActionListener(e -> {
			settings.setGameType(GameType.CLASSIC);
			settings.setRows(8);
			settings.setColumns(8);
		});
		fischerchess_1.addActionListener(e -> {
			settings.setGameType(GameType.FISCHER);
			settings.setRows(8);
			settings.setColumns(8);
		});
		capablancachess_1.addActionListener(e -> {
			settings.setGameType(GameType.CAPABLANCA);
			settings.setRows(8);
			settings.setColumns(10);
		});
		rodzajpartii.add(normalchess_1);
		rodzajpartii.add(fischerchess_1);
		rodzajpartii.add(capablancachess_1);
		this.add(rodzajpartii);

		// przyciski do wyboru tempa

		temp1p0_2 = new JMenuItem("1 + 0");
		temp1p2_2 = new JMenuItem("1 + 2");
		temp3p0_2 = new JMenuItem("3 + 0");
		temp3p2_2 = new JMenuItem("3 + 2");
		temp5p0_2 = new JMenuItem("5 + 0");
		temp10p0_2 = new JMenuItem("10 + 0");
		temp15p0_2 = new JMenuItem("15 + 0");
		temp30p0_2 = new JMenuItem("30 + 0");
		tempnotstand_2 = new JMenuItem("niestandardowe");
		temp1p0_2.addActionListener(e -> {
			settings.setMinutes(1);
			settings.setIncrement(0);
		});
		tempo.add(temp1p0_2);
		temp1p2_2.addActionListener(e -> {
			settings.setMinutes(1);
			settings.setIncrement(2);
		});
		tempo.add(temp1p2_2);
		temp3p0_2.addActionListener(e -> {
			settings.setMinutes(3);
			settings.setIncrement(0);
		});
		tempo.add(temp3p0_2);
		temp3p2_2.addActionListener(e -> {
			settings.setMinutes(3);
			settings.setIncrement(2);
		});
		tempo.add(temp3p2_2);
		temp5p0_2.addActionListener(e -> {
			settings.setMinutes(5);
			settings.setIncrement(0);
		});
		tempo.add(temp5p0_2);
		temp10p0_2.addActionListener(e -> {
			settings.setMinutes(10);
			settings.setIncrement(0);
		});
		tempo.add(temp10p0_2);
		temp15p0_2.addActionListener(e -> {
			settings.setMinutes(15);
			settings.setIncrement(0);
		});
		tempo.add(temp15p0_2);
		temp30p0_2.addActionListener(e -> {
			settings.setMinutes(30);
			settings.setIncrement(0);
		});
		tempo.add(temp30p0_2);
		tempnotstand_2.addActionListener(e -> {
			String dane1 = JOptionPane.showInputDialog(this, "Minuty:", "Wprowadzanie tempa",
					JOptionPane.QUESTION_MESSAGE);
			String dane2 = JOptionPane.showInputDialog(this, "Inkrement:", "Wprowadzanie tempa",
					JOptionPane.QUESTION_MESSAGE);
			settings.setMinutes(Integer.parseInt(dane1));
			settings.setIncrement(Integer.parseInt(dane2));
			if (dane1 != null && dane2 != null) {
				JOptionPane.showMessageDialog(this, "Tempo gry:\nMinuty: " + dane1 + "\nInkrement: " + dane2);
			}
		});
		tempo.add(tempnotstand_2);
		this.add(tempo);

		// przyciski do wyboru motywu

		classic_3 = new JMenuItem("klasyczny");
		wooden_3 = new JMenuItem("drewniany");
		thirdstyle_3 = new JMenuItem("trzeci");

		classic_3.addActionListener(e -> {
			settings.setBoardTypeChoice(1);
		});
		wooden_3.addActionListener(e -> {
			settings.setBoardTypeChoice(2);
		});
		thirdstyle_3.addActionListener(e -> {
			settings.setBoardTypeChoice(3);
		});
		wybormotywu.add(classic_3);
		wybormotywu.add(wooden_3);
		wybormotywu.add(thirdstyle_3);
		this.add(wybormotywu);
	}

}
