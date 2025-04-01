package szachy;

import java.awt.Color;
import java.awt.Font;

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
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class mainframe extends JFrame implements ChangeListener{
	JPanel panelsrodkowy;
    int minuta,sekunda = 0,inkrement; //czas
    int wyborpartii;
	JMenuBar menubar;
	JMenu rodzajpartii, wybormotywu,tempo;
	JMenuItem normalchess_1,fischerchess_1,capablancachess_1; //wybory w rodzaju partii
    JMenuItem temp1p0_2,temp1p2_2,temp3p0_2,temp3p2_2,temp5p0_2,temp10p0_2,temp15p0_2,temp30p0_2,tempnotstand_2; //wybory w tempie
    JMenuItem classic_3,wooden_3,thirdstyle_3; //wybory w motywie
	public mainframe() {
        this.setSize(1000,1000);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        final boolean[] opcja1Wlaczona = {true}; // Tablica, żeby zmieniać wartość w lambdzie
        updateButton(button, opcja1Wlaczona[0]);

        button.addActionListener(e -> {
            opcja1Wlaczona[0] = !opcja1Wlaczona[0]; // Przełącz opcję
            updateButton(button, opcja1Wlaczona[0]); // Zaktualizuj wygląd
        });
        this.add(button);
        this.setJMenuBar(menubar);
	}
    public static void main(String[] args){
        mainframe chess = new mainframe();
        chess.setVisible(true);
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

