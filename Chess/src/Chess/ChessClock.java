package Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Zegar szachowy integrowany z ustawieniami czasu i inkrementacji.
 * Po wyczerpaniu czasu wyświetla komunikat o zwycięstwie.
 */
public class ChessClock extends JPanel {
    private final int initialTime;   // czas początkowy w sekundach
    private final int increment;     // inkrementacja w sekundach
    
    private int timeWhite;           // pozostały czas białych
    private int timeBlack;           // pozostały czas czarnych
    public boolean whiteTurn=true;

    private final JLabel labelWhite;
    private final JLabel labelBlack;
    private Timer timer;

    public ChessClock(Gameboard board) {
    	
        this.initialTime = board.settings.getMinutes() * 60;
        this.increment   = board.settings.getIncrement();
        this.timeWhite   = initialTime;
        this.timeBlack   = initialTime;

        // Interfejs
        setLayout(new GridLayout(2,1,5,5));
        labelWhite = makeLabel();
        labelBlack = makeLabel();
        // czarny u góry, biały na dole
        add(labelBlack);
        add(labelWhite);
        updateLabels();
        highlightActive();

        // Timer co sekundę
        timer = new Timer(1000, (ActionEvent e) -> {
            if (whiteTurn) {
                timeWhite--;
                if (timeWhite <= 0) {
                    timer.stop();
                    showEndDialog("Czarni wygrywają na czas!");
                    return;
                }
            } else {
                timeBlack--;
                if (timeBlack <= 0) {
                    timer.stop();
                    showEndDialog("Biali wygrywają na czas!");
                    return;
                }
            }
            updateLabels();
        });
    }

    private JLabel makeLabel() {
        JLabel l = new JLabel("00:00", SwingConstants.CENTER);
        l.setFont(new Font("SansSerif", Font.BOLD, 32));
        l.setOpaque(true);
        l.setBackground(Color.BLACK);
        l.setForeground(Color.WHITE);
        return l;
    }

    private void updateLabels() {
        labelWhite.setText(format(timeWhite));
        labelBlack.setText(format(timeBlack));
    }

    private String format(int total) {
        int t = Math.max(0, total);
        int m = t / 60;
        int s = t % 60;
        return String.format("%02d:%02d", m, s);
    }

    private void highlightActive() {
        // podświetlenie aktywnego licznika
        labelWhite.setBorder(whiteTurn
            ? BorderFactory.createLineBorder(Color.RED, 3)
            : null);
        labelBlack.setBorder(!whiteTurn
            ? BorderFactory.createLineBorder(Color.RED, 3)
            : null);
    }

    /**
     * Wywołuje się po każdym ruchu, by dodać inkrement i przełączyć turę.
     */
    public void switchTurn() {
        if (whiteTurn) timeWhite  += increment;
        else          timeBlack  += increment;
        whiteTurn = !whiteTurn;
        highlightActive();
    }

    /**
     * Rozpoczyna odliczanie.
     */
    public void start() {
        timer.start();
    }

    /**
     * Zatrzymuje odliczanie.
     */
    public void pause() {
        timer.stop();
    }

    /**
     * Resetuje zegar do czasu początkowego.
     */
    public void reset() {
        pause();
        timeWhite = timeBlack = initialTime;
        whiteTurn = true;
        updateLabels();
        highlightActive();
    }

    /**
     * Wyświetla komunikat o zakończeniu partii.
     */
    private void showEndDialog(String message) {
        JOptionPane.showMessageDialog(
            SwingUtilities.getWindowAncestor(this),
            message,
            "Koniec gry",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}