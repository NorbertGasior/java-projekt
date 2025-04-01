import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JComboBox<String> modeDropdown;
    private JComboBox<String> tempoDropdown;
    private JComboBox<String> themeDropdown;
    private JPanel nameInputPanel;
    private CardLayout nameInputLayout;
    private JToggleButton toggleVsComputer;

    public MainMenu() {
        setTitle("Szachy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();

        setVisible(true);
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout());
        modeDropdown = new JComboBox<>(new String[]{"Klasyczne", "Casablanki", "Fishera"});
        tempoDropdown = new JComboBox<>(new String[]{"Błyskawiczne", "Klasyczne", "Bez limitu"});
        themeDropdown = new JComboBox<>(new String[]{"Jasny", "Ciemny"});

        topPanel.add(new JLabel("Tryb gry:"));
        topPanel.add(modeDropdown);
        topPanel.add(new JLabel("Tempo:"));
        topPanel.add(tempoDropdown);
        topPanel.add(new JLabel("Motyw:"));
        topPanel.add(themeDropdown);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel togglePanel = new JPanel(new FlowLayout());

        toggleVsComputer = new JToggleButton("Z komputerem");
        toggleVsComputer.setSelected(true);
        toggleVsComputer.setPreferredSize(new Dimension(200, 40));

        JButton toggleVsPlayer = new JButton("Z graczem");
        toggleVsPlayer.setPreferredSize(new Dimension(200, 40));

        togglePanel.add(toggleVsComputer);
        togglePanel.add(toggleVsPlayer);
        centerPanel.add(togglePanel, BorderLayout.NORTH);

        toggleVsPlayer.addActionListener(e -> {
            toggleVsComputer.setSelected(false);
            nameInputLayout.show(nameInputPanel, "twoPlayers");
        });

        toggleVsComputer.addActionListener(e -> {
            toggleVsComputer.setSelected(true);
            nameInputLayout.show(nameInputPanel, "onePlayer");
        });

        nameInputLayout = new CardLayout();
        nameInputPanel = new JPanel(nameInputLayout);

        JPanel onePlayerPanel = new JPanel();
        onePlayerPanel.add(new JLabel("Imię:"));
        onePlayerPanel.add(new JTextField(10));

        JPanel twoPlayersPanel = new JPanel();
        twoPlayersPanel.add(new JLabel("Gracz 1:"));
        twoPlayersPanel.add(new JTextField(10));
        twoPlayersPanel.add(new JLabel("Gracz 2:"));
        twoPlayersPanel.add(new JTextField(10));

        nameInputPanel.add(onePlayerPanel, "onePlayer");
        nameInputPanel.add(twoPlayersPanel, "twoPlayers");

        centerPanel.add(nameInputPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        nameInputLayout.show(nameInputPanel, "onePlayer");
    }

    private void setupBottomPanel() {
        JButton startButton = new JButton("GRAJ");
        startButton.addActionListener(e -> {
            new ChessBoardWindow();
            dispose();
        });
        add(startButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
