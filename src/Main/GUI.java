package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame {
	
	JButton eingabe = new JButton("Eingabe");
	JButton ausgabe = new JButton("Ausgabe");
	JButton exit = new JButton("Beenden");
	DialogE dialog;
	DialogA dialog2;
	
	
	public GUI() {
		
		setTitle("Vokabeltrainer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		setVisible(true);
		setSize(800, 600);
		setLayout(null);
		setResizable(false);
		
		
		eingabe.setBounds(170, 150, 180, 120);
		ausgabe.setBounds(450, 150, 180, 120);
		exit.setBounds(310, 300, 180, 120);
		
		eingabe.addActionListener(new ButtonHandler());
		ausgabe.addActionListener(new ButtonHandler());
		exit.addActionListener(new ButtonHandler());
		
		add(eingabe);
		add(ausgabe);
		add(exit);
	}
	public class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == eingabe) {
				dialog = new DialogE();
				dialog.addWindowListener(new WindowHandler());
				setVisible(false);
			}
			
			if(e.getSource() == ausgabe) {
				dialog2 = new DialogA();
				dialog2.addWindowListener(new WindowHandler());
				setVisible(false);
			}
			
			if(e.getSource() == exit) {
				System.exit(0);
			}
		}

		
		
	}
	public class WindowHandler implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowClosed(WindowEvent e) {
			if(e.getSource() == dialog) {
				setVisible(true);
			}
			if(e.getSource() == dialog2) {
				setVisible(true);
			}
		}
		@Override
		public void windowClosing(WindowEvent e) {
			if(e.getSource() == dialog) {
				setVisible(true);
			}
			if(e.getSource() == dialog2) {
				setVisible(true);
			}			
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void windowOpened(WindowEvent e) {
			
		}
	}
}
