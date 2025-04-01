package szachy;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class mainframe extends JFrame implements ChangeListener{
	JPanel panelsrodkowy, nameInputPanel,centerPanel;
	CardLayout nameInputLayout;
    int minuta,sekunda = 0,inkrement; //czas
    int wyborpartii;
    String nazwa1, nazwa2;
	JMenuBar menubar;
	JMenu rodzajpartii, wybormotywu,tempo;
	JMenuItem normalchess_1,fischerchess_1,capablancachess_1; //wybory w rodzaju partii
    JMenuItem temp1p0_2,temp1p2_2,temp3p0_2,temp3p2_2,temp5p0_2,temp10p0_2,temp15p0_2,temp30p0_2,tempnotstand_2; //wybory w tempie
    JMenuItem classic_3,wooden_3,thirdstyle_3; //wybory w motywie
    JTextField textfield1, textfield2, textfieldai;
    public mainframe() {
        setTitle("Szachy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setinterfejs();
        setVisible(true);
    }
	public void setinterfejs() {
        this.setSize(1000,1000);
		
		
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.insets = new Insets(5, 5, 5, 5);
		panelsrodkowy = new JPanel();
		centerPanel = new JPanel(new GridBagLayout());
		menubar = new JMenuBar();
		rodzajpartii = new JMenu("Rodzaj partii");
		tempo = new JMenu("Tempo");
        wybormotywu = new JMenu ("Motyw");
		normalchess_1 = new JMenuItem("szachy klasyczne");
        fischerchess_1 = new JMenuItem("szachy Fischera");
        capablancachess_1 = new JMenuItem("szachy Capablanki");
        //przyciski do rodzaju gry
        normalchess_1.addActionListener(e->{
            wyborpartii = 0;
        });
         fischerchess_1.addActionListener(e->{
            wyborpartii = 1;
        });
        capablancachess_1.addActionListener(e->{
            wyborpartii = 2;
        });
        rodzajpartii.add(normalchess_1);
        rodzajpartii.add(fischerchess_1);
        rodzajpartii.add(capablancachess_1);
        menubar.add(rodzajpartii);
        
        //przyciski do wyboru tempa
        
        temp1p0_2 = new JMenuItem("1 + 0");
        temp1p2_2 = new JMenuItem("1 + 2");
        temp3p0_2 = new JMenuItem("3 + 0");
        temp3p2_2 = new JMenuItem("3 + 2");
        temp5p0_2 = new JMenuItem("5 + 0");
        temp10p0_2 = new JMenuItem("10 + 0");
        temp15p0_2 = new JMenuItem("15 + 0");
        temp30p0_2 = new JMenuItem("30 + 0");
        tempnotstand_2 = new JMenuItem("niestandardowe");
        temp1p0_2.addActionListener(e->{
        	minuta = 1;
        	inkrement = 0;
        });
        tempo.add(temp1p0_2);
        temp1p2_2.addActionListener(e->{
        	minuta = 1;
        	inkrement = 2;
        });
        tempo.add(temp1p2_2);
        temp3p0_2.addActionListener(e->{
        	minuta = 3;
        	inkrement = 0;
        });
        tempo.add(temp3p0_2);
        temp3p2_2.addActionListener(e->{
        	minuta = 3;
        	inkrement = 2;
        });
        tempo.add(temp3p2_2);
        temp5p0_2.addActionListener(e->{
        	minuta = 5;
        	inkrement = 0;
        });
        tempo.add(temp5p0_2);
        temp10p0_2.addActionListener(e->{
        	minuta = 10;
        	inkrement = 0;
        });
        tempo.add(temp10p0_2);
        temp15p0_2.addActionListener(e->{
        	minuta = 15;
        	inkrement = 0;
        });
        tempo.add(temp15p0_2);
        temp30p0_2.addActionListener(e->{
        	minuta = 30;
        	inkrement = 0;
        });
        tempo.add(temp30p0_2);
        tempnotstand_2.addActionListener(e->{
        	String dane1 = JOptionPane.showInputDialog(this, "Minuty:", "Wprowadzanie tempa", JOptionPane.QUESTION_MESSAGE);
            String dane2 = JOptionPane.showInputDialog(this, "Inkrement:", "Wprowadzanie tempa", JOptionPane.QUESTION_MESSAGE);
            minuta = Integer.parseInt(dane1);
            inkrement = Integer.parseInt(dane2);
            if (dane1 != null && dane2 != null) {
                JOptionPane.showMessageDialog(this, "Tempo gry:\nMinuty: " + dane1 + "\nInkrement: " + dane2);
            }
        });
        tempo.add(tempnotstand_2);
        menubar.add(tempo);
        //przyciski do wyboru motywu
        classic_3 = new JMenuItem("klasyczny");
        wooden_3 = new JMenuItem("drewniany");
        thirdstyle_3 = new JMenuItem("trzeci");
        
        classic_3.addActionListener(e->{
        
        });
        wooden_3.addActionListener(e->{
        	
        });
        thirdstyle_3.addActionListener(e->{
        	
        });
        wybormotywu.add(classic_3);
        wybormotywu.add(wooden_3);
        wybormotywu.add(thirdstyle_3);
        menubar.add(wybormotywu);
        JButton button = new JButton();
        final boolean[] opcja1Wlaczona = {true} ; // Tablica, żeby zmieniać wartość w lambdzie
        updateButton(button, opcja1Wlaczona[0]);

        button.addActionListener(e -> {
            opcja1Wlaczona[0] = !opcja1Wlaczona[0]; // Przełącz opcję
            if(opcja1Wlaczona[0]==false) {
            	
            	updateButton(button, opcja1Wlaczona[0]);// Zaktualizuj wygląd
            	nameInputLayout.show(nameInputPanel,"onePlayer");
            	
            }
            else if(opcja1Wlaczona[0]==true) {
            	
            	updateButton(button, opcja1Wlaczona[0]);
            	nameInputLayout.show(nameInputPanel, "twoPlayers");
            }
        });
        button.setPreferredSize(new Dimension(200,40));
        nameInputLayout = new CardLayout();
        nameInputPanel = new JPanel(nameInputLayout);

        textfield1 = new JTextField(10);
        textfield2 = new JTextField(10);
        textfieldai = new JTextField(10);
        JPanel onePlayerPanel = new JPanel();
        onePlayerPanel.add(new JLabel("Imię:"));
        onePlayerPanel.add(textfieldai);
        nazwa1 = textfieldai.getText();
        
        JPanel twoPlayersPanel = new JPanel();
        twoPlayersPanel.add(new JLabel("Gracz 1:"));
        twoPlayersPanel.add(textfield1);
        twoPlayersPanel.add(new JLabel("Gracz 2:"));
        twoPlayersPanel.add(textfield2);
        nazwa1 = textfield1.getText();
        nazwa2 = textfield2.getText();
        
        nameInputPanel.add(onePlayerPanel, "onePlayer");
        nameInputPanel.add(twoPlayersPanel, "twoPlayers");

        panelsrodkowy.add(button);
        this.add(panelsrodkowy,BorderLayout.NORTH);
    	nameInputLayout.show(nameInputPanel, "twoPlayers");
    	gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(nameInputPanel, gbc);

        // Ustawienie drugiego pola tekstowego (x=1, y=0)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        
       
        JButton startButton = new JButton("GRAJ");
        startButton.setPreferredSize(new Dimension(200,40));
        startButton.addActionListener(e -> {
            new ChessBoardWindow();
            dispose();
        });
        centerPanel.add(startButton, gbc);
        this.add(centerPanel);
        this.setJMenuBar(menubar);
	}
  
	public String getNazwa1() {
		return nazwa1;
	}
	public void setNazwa1(String nazwa1) {
		this.nazwa1 = nazwa1;
	}
	public String getNazwa2() {
		return nazwa2;
	}
	public void setNazwa2(String nazwa2) {
		this.nazwa2 = nazwa2;
	}
	public static void main(String[] args){
        
        SwingUtilities.invokeLater(mainframe::new);
    }
    private static void updateButton(JButton button, boolean opcja1Wlaczona) {
    	
        if (opcja1Wlaczona) {
            button.setText("▶ PVP" + "  |  AI" );    
        } 
        else {
            button.setText("PVP" + "  |  ▶ AI");
        }
    }

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
