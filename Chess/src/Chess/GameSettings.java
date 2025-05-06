package Chess;

import java.awt.Color;

public class GameSettings {
	private String player1Name;
	private String player2Name;
	private boolean isPvP;
	private int minutes;
	private int increment;
	private GameType gameType;
	private int BoardTypeChoice = 1;
	private int rows=8;
	private int columns=8;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getBoardTypeChoice() {
		return BoardTypeChoice;
	}

	public void setBoardTypeChoice(int boardTypeChoice) {
		BoardTypeChoice = boardTypeChoice;
	}

	public GameSettings() {
		// ustawienia domyślne
		this.isPvP = true;
		this.minutes = 5;
		this.increment = 0;
		this.gameType = GameType.CLASSIC;
	}

	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}

	public boolean isPvP() {
		return isPvP;
	}

	public void setPvP(boolean isPvP) {
		this.isPvP = isPvP;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

}
